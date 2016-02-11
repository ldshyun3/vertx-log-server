package com.clue.service;

import com.clue.model.Room;
import com.clue.model.User;
import com.clue.proto.RemoteLogger;
import java.util.HashMap;

/**
 * Created by raindays on 2016. 2. 9..
 */
public class RoomServiceImpl implements RoomService {
    HashMap<String, Room> rooms = new HashMap<String, Room>();

    public RoomServiceImpl() {
    }

    public void addRoom(Room room) {
        if (room != null) {
            if (rooms.containsKey(room.getKey()) == false) {
                rooms.put(room.getKey(), room);
            }
        }
    }

    public void removeRoom(Room room) {
        if (room != null) {
            rooms.remove(room.getKey());
        }
    }

    public Room getRoom(String key) {
        return rooms.get(key);
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public Boolean joinRoom(Room room, User user) {
        if (room == null) {
            return false;
        }
        room.addMember(user);
        return true;
    }

    public void leaveRoom(Room room, User user) {
        if (room == null) {
            return;
        }
        room.removeMember(user);
    }

    public void boradcast(Room room, RemoteLogger.MessageType messageType, com.google.protobuf.GeneratedMessage msg) {
        for (User member : room.getMembers()) {
            Service.user.send(member, messageType, msg);
        }
    }

    public void boradcastWithout(Room room, User user, RemoteLogger.MessageType messageType, com.google.protobuf.GeneratedMessage msg) {
        for (User member : room.getMembers()) {
            if (user == member) {
                continue;
            }
            Service.user.send(member, messageType, msg);
        }
    }
}
