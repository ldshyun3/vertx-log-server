// automatically generated, do not modify

package com.clue.fbs.RemoteLogger;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class ResRoomList extends Table {
  public static ResRoomList getRootAsResRoomList(ByteBuffer _bb) { return getRootAsResRoomList(_bb, new ResRoomList()); }
  public static ResRoomList getRootAsResRoomList(ByteBuffer _bb, ResRoomList obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public ResRoomList __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public RoomInfo rooms(int j) { return rooms(new RoomInfo(), j); }
  public RoomInfo rooms(RoomInfo obj, int j) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int roomsLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createResRoomList(FlatBufferBuilder builder,
      int roomsOffset) {
    builder.startObject(1);
    ResRoomList.addRooms(builder, roomsOffset);
    return ResRoomList.endResRoomList(builder);
  }

  public static void startResRoomList(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addRooms(FlatBufferBuilder builder, int roomsOffset) { builder.addOffset(0, roomsOffset, 0); }
  public static int createRoomsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startRoomsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endResRoomList(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

