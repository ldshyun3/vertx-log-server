package com.clue.service;

import com.clue.model.User;
import com.clue.proto.RemoteLogger;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;

import java.util.HashMap;

/**
 * Created by raindays on 2016. 2. 9..
 */
public class UserServiceImpl implements UserService {
    HashMap<String, User> users = new HashMap<String, User>();

    public void addUser(ServerWebSocket ws) {
        users.put(ws.binaryHandlerID(), new User(ws));
    }

    public void addUser(User user) {
        users.put(user.getKey(), user);
    }

    public void removeUser(User user) {
        users.remove(user.getKey());
    }

    public User getUser(String key) {
        return users.get(key);
    }

    public User getUser(ServerWebSocket ws) {
        return getUser(ws.binaryHandlerID());
    }

    public void send(User user, RemoteLogger.MessageType messageType, com.google.protobuf.GeneratedMessage msg) {
        Buffer data = Buffer.buffer();
        data.appendInt(messageType.getNumber());
        data.appendBytes(msg.toByteArray());
        user.getSocket().writeFinalBinaryFrame(data);
    }

    public void send(User user, RemoteLogger.MessageType messageType, byte[] msg) {
        Buffer data = Buffer.buffer();
        data.appendInt(messageType.getNumber());
        data.appendBytes(msg);
        user.getSocket().writeFinalBinaryFrame(data);
    }
}
