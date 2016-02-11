// automatically generated, do not modify

package com.clue.fbs.RemoteLogger;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class ReqRoomList extends Table {
  public static ReqRoomList getRootAsReqRoomList(ByteBuffer _bb) { return getRootAsReqRoomList(_bb, new ReqRoomList()); }
  public static ReqRoomList getRootAsReqRoomList(ByteBuffer _bb, ReqRoomList obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public ReqRoomList __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int pageCount() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createReqRoomList(FlatBufferBuilder builder,
      int pageCount) {
    builder.startObject(1);
    ReqRoomList.addPageCount(builder, pageCount);
    return ReqRoomList.endReqRoomList(builder);
  }

  public static void startReqRoomList(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addPageCount(FlatBufferBuilder builder, int pageCount) { builder.addInt(0, pageCount, 0); }
  public static int endReqRoomList(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

