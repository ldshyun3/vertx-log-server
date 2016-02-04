package com.clue;

import com.google.protobuf.InvalidProtocolBufferException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.HashMap;
import com.clue.protocol.VL;


public class LogManager {
    public static final String kLobbyRoomId = "lobby";

    Logger logger = LoggerFactory.getLogger(getClass());
    HashMap<String, User> users = new HashMap<String, User>();
    HashMap<String, Room> rooms = new HashMap<String, Room>();
    Room lobby = null;

    public void bind(HttpServer server, String path) {
        rooms.put(kLobbyRoomId, new Room(kLobbyRoomId));
        lobby = rooms.get(kLobbyRoomId);

        server.websocketHandler(ws -> {
            if (ws.path().equals(path) == false) {
                ws.reject();
            }

            logger.info("connected:" + ws.binaryHandlerID());
            User user = new User(ws);
            users.put(user.getKey(), user);

            ws.handler(data -> {
                parse(user, data);
            });

            ws.closeHandler(var -> {
                logger.info("closed:" + ws.binaryHandlerID());
                users.remove(User.getKey(ws));
            });
        }).requestHandler(req -> {
            System.out.println(req.uri());
            if (req.uri().equals("/")) {
                req.response().sendFile("webroot/list.html");
            }
            else if (req.uri().indexOf("/log/") == 0) {
                req.response().sendFile("webroot/view.html");
            }
            else if (req.uri().indexOf("/assets/") == 0) {
                System.out.println(req.uri());
                req.response().sendFile("webroot/" + req.uri());
            }
        });
    }

    User getUser(ServerWebSocket ws) {
        String key = User.getKey(ws);
        if (users.containsKey(key) == true) {
            return users.get(key);
        }
        return null;
    }

    Room getRoom(String roomId) {
        if (rooms.containsKey(roomId) == true) {
            return rooms.get(roomId);
        }
        return null;
    }

    void parse(User user, Buffer data) {
        byte type = data.getByte(0);
        MsgType messageType = MsgType.find(type);

        if (user == null) {
            logger.error("no lynx user!");
            return;
        }

        switch (messageType) {
            case JoinRoom: processJoinRoom(user, data.getBytes(1, data.length())); break;
            case Message: processMessage(user, data.getBytes(1, data.length())); break;
        }
    }

    void processJoinRoom(User user, byte[] data) {
        if (user.getRoomId().isEmpty() == false) {
            leaveRoom(user);
        }

        try {
            VL.Join join = null;
            join = VL.Join.parseFrom(data);
            joinRoom(user, join.getRoomId());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    void processMessage(User user, byte[] data) {
        Room room = getRoom(user.getRoomId());
        if (room != null) {
            room.send(MsgType.Message, data);
        }
    }

    void joinRoom(User user, String roomId) {
        if (user.getRoomId().isEmpty() == false) {
            logger.error("leave room needed");
            return;
        }

        Room room = rooms.get(roomId);
        if (room == null) {
            room = new Room(roomId);
            rooms.put(roomId, room);
            sendRoomList();
        }

        room.addMember(user);
        if (roomId.equals(kLobbyRoomId)) {
            sendRoomList();
            sendJoinLog(user);
        }
    }

    void leaveRoom(User user) {
        String roomId = user.getRoomId();
        if (roomId.isEmpty() == true) {
            logger.error("can leave room");
            return;
        }

        Room room = getRoom(roomId);
        if (room == null) {
            logger.error("no room" + user.getRoomId());
            return;
        }

        room.removeMember(user);
        if (room.getMembers().isEmpty() == true) {
            rooms.remove(roomId);
            sendRoomList();
        }
    }

    void sendRoomList() {
        VL.RoomList.Builder builder = VL.RoomList.newBuilder();
        for (Room room : rooms.values()) {
            builder.addRoomId(room.getKey());
        }
        VL.RoomList roomList = builder.build();
        lobby.send(MsgType.RoomList, roomList);
    }

    void sendJoinLog(User user) {
        String message = user.getKey() + " joined this room.";
        VL.Message.Builder builder = VL.Message.newBuilder();
        builder.setLevel(3).setMessage(message);
        Room room = getRoom(user.getRoomId());
        if (room != null) {
            room.send(MsgType.Message, builder.build());
        }
    }
}
