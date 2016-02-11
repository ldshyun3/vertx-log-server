package com.clue.service;

/**
 * Created by raindays on 2016. 2. 9..
 */
public class Service {
    public static UserService user = null;
    public static RoomService room = null;

    public void Initialize() {
        user = new UserServiceImpl();
        room = new RoomServiceImpl();
    }
}
