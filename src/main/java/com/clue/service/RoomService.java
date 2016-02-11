package com.clue.service;

import com.clue.model.Room;
import com.clue.model.User;

import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * Created by raindays on 2016. 2. 9..
 */
public interface RoomService {
    void addRoom(Room room);
    void removeRoom(Room room);
    Room getRoom(String key);
    Boolean joinRoom(Room room, User user);
    HashMap<String, Room> getRooms();
    void leaveRoom(Room room, User user);
    void boradcast(Room room, byte messageType, ByteBuffer buffer);
    void boradcastWithout(Room room, User user, byte messageType, ByteBuffer buffer);
}
