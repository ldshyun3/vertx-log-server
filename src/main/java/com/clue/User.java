package com.clue;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;

public class User {
    String key;
    String roomId;
    int no = 0;
    ServerWebSocket socket;

    public User(ServerWebSocket ws) {
        this.key = ws.binaryHandlerID();
        this.roomId = "";
        this.socket = ws;
    }

    public String getKey() {
        return key;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    public void sendRaw(byte[] data) {
        socket.writeFinalBinaryFrame(Buffer.buffer(data));
    }

    public void send(MsgType messageType, com.google.protobuf.GeneratedMessage msg) {
        Buffer data = Buffer.buffer();
        data.appendByte(messageType.getType());
        data.appendBytes(msg.toByteArray());
        socket.writeFinalBinaryFrame(data);
    }

    public void send(MsgType messageType, byte[] msg) {
        Buffer data = Buffer.buffer();
        data.appendByte(messageType.getType());
        data.appendBytes(msg);
        socket.writeFinalBinaryFrame(data);
    }
}
