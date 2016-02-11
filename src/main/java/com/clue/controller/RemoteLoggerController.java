package com.clue.controller;

import com.clue.model.Room;
import com.clue.model.User;
import com.clue.proto.RemoteLogger;
import com.clue.service.Service;
import com.google.protobuf.InvalidProtocolBufferException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


public class RemoteLoggerController {
    Logger logger = LoggerFactory.getLogger(getClass());

    public void bind(HttpServer server, String path) {
        server.websocketHandler(ws -> {
            if (ws.path().equals(path) == false) {
                ws.reject();
            }

            logger.info("connected:" + ws.binaryHandlerID());
            Service.user.addUser(ws);

            ws.handler(data -> {
                User user = Service.user.getUser(ws);
                parse(user, data);
            });

            ws.closeHandler(var -> {
                logger.info("closed:" + ws.binaryHandlerID());
                User user = Service.user.getUser(ws);
                Room room = Service.room.getRoom(user.getRoomId());
                Service.room.leaveRoom(room, user);
                Service.user.removeUser(user);
            });
        });
    }

    void parse(User user, Buffer data) {
        int head0 = data.getInt(0);
        RemoteLogger.MessageType messageType = RemoteLogger.MessageType.valueOf(head0);

        if (user == null) {
            logger.error("no user!");
            return;
        }

        switch (messageType) {
            case MsgReqJoin: processReqJoin(user, data.getBytes(4, data.length())); break;
            case MsgReqLog: processReqLog(user, data.getBytes(4, data.length())); break;
            case MsgReqRoomList: processReqRoomList(user, data.getBytes(4, data.length())); break;
        }
    }

    void processReqJoin(User user, byte[] data) {
        RemoteLogger.ReqJoin reqJoin = null;
        try {
            reqJoin = RemoteLogger.ReqJoin.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return;
        }

        // update user info
        Room room = Service.room.getRoom(reqJoin.getRoomId());
        if (room == null) {
            room = new Room(reqJoin.getRoomId());
            Service.room.addRoom(room);
        }
        Service.room.joinRoom(room, user);
    }

    void processReqLog(User user, byte[] data) {
        RemoteLogger.ReqLog reqSync = null;
        try {
            reqSync = RemoteLogger.ReqLog.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return;
        }

        Room room = Service.room.getRoom(user.getRoomId());
        if (room == null) {
            logger.error("no room exist!");
            return;
        }

        RemoteLogger.NotiLog.Builder notiSyncBuilder = RemoteLogger.NotiLog.newBuilder();
        notiSyncBuilder.setMessage(reqSync.getMessage());
        notiSyncBuilder.setLevel(reqSync.getLevel());
        Service.room.boradcastWithout(room, user, RemoteLogger.MessageType.MsgNotiLog, notiSyncBuilder.build());
    }

    void processReqRoomList(User user, byte[] data) {
        RemoteLogger.ReqRoomList reqRoomList = null;
        try {
            reqRoomList = RemoteLogger.ReqRoomList.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
            return;
        }

        RemoteLogger.ResRoomList.Builder resRoomListBuilder = RemoteLogger.ResRoomList.newBuilder();
        int index = 0;
        for(Room room : Service.room.getRooms().values()) {
            resRoomListBuilder.addRooms(index, toRoom(room));
            index++;
            if (reqRoomList.getPageCount() == index) {
                break;
            }
        }

        Service.user.send(user, RemoteLogger.MessageType.MsgResRoomList, resRoomListBuilder.build());
    }

    RemoteLogger.Room toRoom(Room room) {
        RemoteLogger.Room.Builder builder = RemoteLogger.Room.newBuilder();
        builder.setCount(room.getMembers().size());
        builder.setRoomId(room.getKey());
        return builder.build();
    }
}
