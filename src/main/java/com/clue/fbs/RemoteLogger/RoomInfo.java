// automatically generated, do not modify

package com.clue.fbs.RemoteLogger;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class RoomInfo extends Table {
  public static RoomInfo getRootAsRoomInfo(ByteBuffer _bb) { return getRootAsRoomInfo(_bb, new RoomInfo()); }
  public static RoomInfo getRootAsRoomInfo(ByteBuffer _bb, RoomInfo obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public RoomInfo __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String roomId() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer roomIdAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public int count() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createRoomInfo(FlatBufferBuilder builder,
      int roomIdOffset,
      int count) {
    builder.startObject(2);
    RoomInfo.addCount(builder, count);
    RoomInfo.addRoomId(builder, roomIdOffset);
    return RoomInfo.endRoomInfo(builder);
  }

  public static void startRoomInfo(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addRoomId(FlatBufferBuilder builder, int roomIdOffset) { builder.addOffset(0, roomIdOffset, 0); }
  public static void addCount(FlatBufferBuilder builder, int count) { builder.addInt(1, count, 0); }
  public static int endRoomInfo(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

