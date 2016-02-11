package com.clue.model;

import java.util.HashSet;

public class Room {
    int lastUserNo = 0;
    String key = "";
    HashSet<User> members = new HashSet<User>();

    public Room(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public HashSet<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        user.setRoomId(key);
        members.add(user);
        lastUserNo++;
    }

    public void removeMember(User user) {
        members.remove(user);
        user.setRoomId("");
    }

    public int getMemberCount() {
        return members.size();
    }
}
