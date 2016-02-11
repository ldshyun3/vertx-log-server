// automatically generated, do not modify

package RemoteLogger;

public final class MessageType {
  private MessageType() { }
  public static final byte ReqJoin = 0;
  public static final byte ReqLog = 1;
  public static final byte NotiLog = 2;
  public static final byte ReqRoomList = 3;
  public static final byte ResRoomList = 4;

  private static final String[] names = { "ReqJoin", "ReqLog", "NotiLog", "ReqRoomList", "ResRoomList", };

  public static String name(int e) { return names[e]; }
};

