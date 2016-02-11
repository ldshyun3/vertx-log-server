var ProtoBuf = dcodeIO.ProtoBuf;
var remoteLogger = ProtoBuf.loadProtoFile("./assets/idl/remoteLogger.proto");
var ReqRoomList = remoteLogger.build("com.clue.proto.ReqRoomList");
var ResRoomList = remoteLogger.build("com.clue.proto.ResRoomList");

function sendReqRoomList(socket) {
    var req = new ReqRoomList();
    req.pageCount = 100;
    var reqData = req.toArrayBuffer();

    var data = new Uint8Array(reqData.byteLength + 4);
    data[3] = 3;
    data.set(new Uint8Array(reqData), 4);
    socket.send(data);
}

function parseRoomList(data) {
    try {
        var buffer = new Int8Array(data, 0, 4);
        if (buffer[3] != 4) {
            console.log(buffer[3]);
            return null;
        }

        return ResRoomList.decode(data.slice(4));
    } catch (err) {
        console.log(err);
    }
}

function main() {
    var socket = new WebSocket("ws://localhost:8090/remoteLogger");
    socket.binaryType = "arraybuffer";

    socket.onopen = function() {
      console.log("onopen");
      sendReqRoomList(socket);
    };

    socket.onclose = function() {
    };

    socket.onmessage = function(evt) {
        console.log("on message");
        var roomList = parseRoomList(evt.data);
        if (roomList == null) {
            return;
        }

        $('#messages').empty();
        for (i=0; i<roomList.rooms.length; i++) {
            var room = roomList.rooms[i];
            var link = "<a href='../log/"+room.roomId+"'>"+room.roomId+"</a>";
            var badge = "<span class=\"badge\">" + room.count + "</span>";
            $('#messages').append($('<li class="list-group-item">').html(link + badge));
        }

        if (roomList.rooms.length == 0) {
            $('#messages').append($('<li class="list-group-item">').html("no room exist!"));
        }

        setTimeout(function(){ sendReqRoomList(socket); }, 2000);
    };
}

main();
