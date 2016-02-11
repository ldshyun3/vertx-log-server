package com.clue.service;

import com.clue.model.Room;
import com.clue.model.User;
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
        if (room.getMemberCount() == 0) {
            removeRoom(room);
        }
    }

    public void boradcast(Room room, byte messageType, byte[] buffer) {
        for (User member : room.getMembers()) {
            Service.user.send(member, messageType, buffer);
        }
    }

    public void boradcastWithout(Room room, User user, byte messageType, byte[] buffer) {
        for (User member : room.getMembers()) {
            if (user == member) {
                continue;
            }
            Service.user.send(member, messageType, buffer);
        }
    }
}
