Realtime log server for unity and web client

# Test Server
http://raindays.net:8090

Server
- Vert.x 3.2.0
- flatbuffers 1.3.0

# Custom header
It has 1 byte heading to figure out message type.

# flatbuffers idl
    namespace com.clue.fbs.RemoteLogger;

    // enum
    //-----------------------------------------------------------------------------
    enum MessageType : byte {
        ReqJoin = 0,
        ReqLog,
        NotiLog,
        ReqRoomList,
        ResRoomList
    }

    // tables
    //-----------------------------------------------------------------------------
    table ReqJoin {
        roomId:string;
    }

    table ReqLog {
        message:string;
        level:byte;
    }

    table NotiLog {
        message:string;
        level:byte;
    }

    table ReqRoomList {
        pageCount:int;
    }

    table ResRoomList {
        rooms:[RoomInfo];
    }

    // model class
    //-----------------------------------------------------------------------------
    table RoomInfo {
        roomId:string;
        count:int;
    }


# Unity BestHTTP websocket Client example

    using UnityEngine;
    using System.Collections;
    using System;
    using BestHTTP;
    using BestHTTP.WebSocket;
    using BestHTTP.WebSocket.Frames;
    using FlatBuffers;
    using com.clue.fbs.RemoteLogger;

    public class MainScene : MonoBehaviour {
        WebSocket socket = null;

        IEnumerator Start() {
            Connect("ws://localhost:8090/remoteLogger");

            int count = 1;
            while (true) {
                yield return new WaitForSeconds(1);
                if (socket.IsOpen) {
                    var builder = new FlatBufferBuilder(1);
                    var messageOffset = builder.CreateString("from unity");

                    ReqLog.StartReqLog(builder);
                    ReqLog.AddMessage(builder, messageOffset);
                    ReqLog.AddLevel(builder, (sbyte)UnityEngine.Random.Range(0, 7));
                    var packet = ReqLog.EndReqLog(builder);
                    builder.Finish(packet.Value);
                    Send(MessageType.ReqLog, builder.SizedByteArray());
                    count++;
                }
            }
        }

        public void Connect(string url) {
            if (socket != null) {
                Debug.LogError("WebSocket already created!");
                return;
            }

            socket = new WebSocket(new Uri(url));
            socket.OnOpen += WSocketOnOpen;
            socket.OnMessage += WSocketOnMessage;
            socket.OnClosed += WSocketOnClosed;
            socket.OnErrorDesc += WSocketOnError;
            socket.Open();
        }

        public void Send(string text) {
            if (socket != null && socket.IsOpen) {
                socket.Send(text);
            }
        }

        public void Stop() {
            if (socket != null) {
                socket.OnOpen = null;
                socket.OnMessage = null;
                socket.OnClosed = null;
                socket.OnErrorDesc = null;
                socket.Close();
                socket = null;
            }
        }

        public bool IsOpen() {
            return socket.IsOpen;
        }

        void WSocketOnOpen(WebSocket webSocket) {
            if (webSocket != socket) {
                return;
            }

            var builder = new FlatBufferBuilder(1);
            var roomIdOffset = builder.CreateString("logTestRoom" + UnityEngine.Random.Range(0, 1000));
            ReqJoin.StartReqJoin(builder);
            ReqJoin.AddRoomId(builder, roomIdOffset);
            var packet = ReqJoin.EndReqJoin(builder);
            builder.Finish(packet.Value);
            Send(MessageType.ReqJoin, builder.SizedByteArray());
        }

        void WSocketOnMessage(WebSocket webSocket, string message) {
            if (webSocket != socket) {
                return;
            }

            Debug.LogError(message);
        }

        void WSocketOnClosed(WebSocket webSocket, ushort code, string message) {
            if (webSocket != socket) {
                return;
            }

            string reason = code.ToString() + " : " + message;
            Debug.LogError(reason);
        }

        void WSocketOnError(WebSocket webSocket, string reason) {
            if (webSocket != socket) {
                return;
            }

            Debug.LogError(reason);
        }

        byte[] Serialize(byte type, byte[] message) {
            byte[] result = new byte[1 + message.Length];
            result[0] = type;
            System.Buffer.BlockCopy(message, 0, result, 1, message.Length);
            return result;
        }

        void Send(MessageType type, byte[] message){
            byte [] data = Serialize((byte)type, message);
            WebSocketBinaryFrame frame = new WebSocketBinaryFrame(data, true);
            socket.Send(frame);
        }
    }
