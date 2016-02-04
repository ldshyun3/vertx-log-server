h3. Test Server
http://raindays.net:8090

h3. Unity BestHTTP websocket example

    using UnityEngine;
    using System.Collections;
    using System;
    using BestHTTP;
    using BestHTTP.WebSocket;
    using BestHTTP.WebSocket.Frames;
    using ProtoFiles.VL;

    public class MainScene : MonoBehaviour {
        WebSocket socket = null;
        bool connected = false;
        ProtoLibSerializer serializer = new ProtoLibSerializer();

        IEnumerator Start() {
            Connect("ws://raindays.net:8090/vl");
            
            int count = 1;
            while (true) {
                yield return new WaitForSeconds(1);
                if (connected) {
                    Message message = new Message();
                    message.message = "message from unity " + count.ToString();
                    message.level = UnityEngine.Random.Range(0, 7);
                    Send(1, message);
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

            Join join = new Join();
            join.roomId = "logTestRoom" + UnityEngine.Random.Range(0, 1000);
            Send(2, join);
            connected = true;
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

            connected = false;
            string reason = code.ToString() + " : " + message;
            Debug.LogError(reason);
        }

        void WSocketOnError(WebSocket webSocket, string reason) {
            if (webSocket != socket) {
                return;
            }

            Debug.LogError(reason);
        }

        byte[] Serialize(ProtoBuf.IExtensible msg) {
            try {
                using (var stream = new System.IO.MemoryStream()) {
                    serializer.Serialize(stream, msg);
                    return stream.ToArray();
                }
            }
            catch (System.Exception e) {
                Debug.LogWarning(e.StackTrace);
                Debug.LogError(e.Message);
            }
            return null;
        }
        
        byte[] Serialize(byte type, ProtoBuf.IExtensible msg) {
            byte[] data = Serialize(msg);
            byte[] rv = new byte[1 + data.Length];
            rv[0] = type;
            System.Buffer.BlockCopy(data, 0, rv, 1, data.Length);
            return rv;
        }
        
        void Send(byte type, ProtoBuf.IExtensible msg){
            byte [] data = Serialize(type, msg);
            WebSocketBinaryFrame frame = new WebSocketBinaryFrame(data, true);  
            socket.Send(frame);
        }
    }
