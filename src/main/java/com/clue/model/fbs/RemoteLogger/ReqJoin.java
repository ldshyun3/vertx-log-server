// automatically generated, do not modify

package RemoteLogger;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class ReqJoin extends Table {
  public static ReqJoin getRootAsReqJoin(ByteBuffer _bb) { return getRootAsReqJoin(_bb, new ReqJoin()); }
  public static ReqJoin getRootAsReqJoin(ByteBuffer _bb, ReqJoin obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public ReqJoin __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String roomId() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer roomIdAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }

  public static int createReqJoin(FlatBufferBuilder builder,
      int roomIdOffset) {
    builder.startObject(1);
    ReqJoin.addRoomId(builder, roomIdOffset);
    return ReqJoin.endReqJoin(builder);
  }

  public static void startReqJoin(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addRoomId(FlatBufferBuilder builder, int roomIdOffset) { builder.addOffset(0, roomIdOffset, 0); }
  public static int endReqJoin(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

