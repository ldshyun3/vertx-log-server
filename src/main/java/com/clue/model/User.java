package com.clue.model;

import io.vertx.core.http.ServerWebSocket;

public class User {
    String key;
    String roomId;
    ServerWebSocket socket;

    public User(ServerWebSocket ws) {
        this.key = ws.binaryHandlerID();
        this.roomId = "";
        this.socket = ws;
    }

    public String getKey() {
        return key;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public static String getKey(ServerWebSocket socket) {
        return socket.binaryHandlerID();
    }

    public ServerWebSocket getSocket() {
        return socket;
    }

    public void setSocket(ServerWebSocket socket) {
        this.socket = socket;
    }
}
