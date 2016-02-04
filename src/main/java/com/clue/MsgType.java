package com.clue;

public enum MsgType {
    None((byte)0),
    Message((byte)1),
    JoinRoom((byte)2),
    RoomList((byte)3),
    ;

    private byte type;
    public byte getType() {
        return type;
    }

    private MsgType(byte value) {
        this.type = value;
    }

    static public MsgType find(int value) {
        for (MsgType item : values()) {
            if (item.getType() == value) {
                return item;
            }
        }
        return MsgType.None;
    }
}
