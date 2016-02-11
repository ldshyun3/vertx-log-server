// automatically generated, do not modify

package RemoteLogger;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class NotiLog extends Table {
  public static NotiLog getRootAsNotiLog(ByteBuffer _bb) { return getRootAsNotiLog(_bb, new NotiLog()); }
  public static NotiLog getRootAsNotiLog(ByteBuffer _bb, NotiLog obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public NotiLog __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String message() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer messageAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public byte level() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createNotiLog(FlatBufferBuilder builder,
      int messageOffset,
      byte level) {
    builder.startObject(2);
    NotiLog.addMessage(builder, messageOffset);
    NotiLog.addLevel(builder, level);
    return NotiLog.endNotiLog(builder);
  }

  public static void startNotiLog(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addMessage(FlatBufferBuilder builder, int messageOffset) { builder.addOffset(0, messageOffset, 0); }
  public static void addLevel(FlatBufferBuilder builder, byte level) { builder.addByte(1, level, 0); }
  public static int endNotiLog(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

