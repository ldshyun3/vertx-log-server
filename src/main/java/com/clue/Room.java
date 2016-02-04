package com.clue;

import java.util.HashSet;

public class Room {
    String key = "";
    HashSet<User> members = new HashSet<User>();
    int lastNo = 0;

    public Room(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public HashSet<User> getMembers() {
        return members;
    }

    public void send(MsgType messageType, com.google.protobuf.GeneratedMessage msg) {
        for (User user : members) {
            user.send(messageType, msg);
        }
    }

    public void send(MsgType messageType, byte[] msg) {
        for (User user : members) {
            user.send(messageType, msg);
        }
    }

    public void addMember(User user) {
        user.setRoomId(key);
        members.add(user);
        user.setNo(lastNo);
        lastNo++;
    }

    public void removeMember(User user) {
        members.remove(user);
        user.setRoomId("");
    }
}
