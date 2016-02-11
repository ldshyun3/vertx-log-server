package com.clue.service;

import com.clue.model.User;
import com.clue.proto.RemoteLogger;
import io.vertx.core.http.ServerWebSocket;

/**
 * Created by raindays on 2016. 2. 9..
 */
public interface UserService {
    void addUser(ServerWebSocket ws);
    void addUser(User user);
    void removeUser(User user);
    User getUser(String key);
    User getUser(ServerWebSocket ws);
    void send(User user, RemoteLogger.MessageType messageType, com.google.protobuf.GeneratedMessage msg);
    void send(User user, RemoteLogger.MessageType messageType, byte[] msg);
}
