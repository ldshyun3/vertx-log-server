package com.clue.service;

import com.clue.model.User;
import io.vertx.core.http.ServerWebSocket;

import java.nio.ByteBuffer;

/**
 * Created by raindays on 2016. 2. 9..
 */
public interface UserService {
    void addUser(ServerWebSocket ws);
    void addUser(User user);
    void removeUser(User user);
    User getUser(String key);
    User getUser(ServerWebSocket ws);
    void send(User user, byte messageType, ByteBuffer buffer);
}
