package com.clue.controller;

import com.clue.model.Room;
import com.clue.model.User;
import com.clue.service.Service;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import com.google.flatbuffers.FlatBufferBuilder;
import com.clue.fbs.RemoteLogger.*;

import java.nio.ByteBuffer;

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
        byte messageType = data.getByte(0);
        if (user == null) {
            logger.error("no user!");
            return;
        }

        ByteBuffer buffer = ByteBuffer.wrap(data.getBytes(1, data.length()));
        switch (messageType) {
            case MessageType.ReqJoin: processReqJoin(user, buffer); break;
            case MessageType.ReqLog: processReqLog(user, buffer); break;
            case MessageType.ReqRoomList: processReqRoomList(user, buffer); break;
        }
    }

    void processReqJoin(User user, ByteBuffer buffer) {
        ReqJoin reqJoin = ReqJoin.getRootAsReqJoin(buffer);
        Room room = Service.room.getRoom(reqJoin.roomId());
        if (room == null) {
            room = new Room(reqJoin.roomId());
            Service.room.addRoom(room);
        }
        Service.room.joinRoom(room, user);
        logger.error(reqJoin.roomId());
    }

    void processReqLog(User user, ByteBuffer buffer) {
        ReqLog reqLog = ReqLog.getRootAsReqLog(buffer);
        Room room = Service.room.getRoom(user.getRoomId());
        if (room == null) {
            logger.error("no room exist!");
            return;
        }

        logger.error(reqLog.message());

        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int messageOffset = builder.createString(reqLog.message());

        NotiLog.startNotiLog(builder);
        NotiLog.addMessage(builder, messageOffset);
        NotiLog.addLevel(builder, reqLog.level());
        int packet = NotiLog.endNotiLog(builder);
        builder.finish(packet);

        Service.room.boradcast(room, MessageType.NotiLog, builder.dataBuffer());
//        Service.room.boradcastWithout(room, user, MessageType.NotiLog, builder.dataBuffer());
    }

    void processReqRoomList(User user, ByteBuffer buffer) {
        ReqRoomList reqRoomList = ReqRoomList.getRootAsReqRoomList(buffer);

        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int pageCount = Math.min(Service.room.getRooms().values().size(), reqRoomList.pageCount());
        int[] rooms = new int[pageCount];
        int index = 0;
        for (Room room : Service.room.getRooms().values()) {
            int roomIdOffset = builder.createString(room.getKey());
            rooms[index] = RoomInfo.createRoomInfo(builder, roomIdOffset, room.getMemberCount());
            index++;
            if (index == pageCount) {
                break;
            }
        }

        int roomsVector = ResRoomList.createRoomsVector(builder, rooms);

        ResRoomList.startResRoomList(builder);
        ResRoomList.addRooms(builder, roomsVector);
        int packet = ResRoomList.endResRoomList(builder);
        builder.finish(packet);

        ByteBuffer buf = builder.dataBuffer();

        /*
        logger.error(buf.array().length);
        ResRoomList test = ResRoomList.getRootAsResRoomList(buf);
        logger.error(test.roomsLength());
        logger.error(test.rooms(0).roomId());
        StringBuilder sb = new StringBuilder();
        byte[] arr = buf.array();
        for (Integer i = 0; i<arr.length; i++) {
            sb.append("\"");
            sb.append(i.toString());
            sb.append("\":");
            sb.append(arr[i]);
            sb.append(",");
        }
        logger.error(sb.toString());
        */

        Service.user.send(user, MessageType.ResRoomList, buf);
    }
}
