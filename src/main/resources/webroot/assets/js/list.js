
function sendReqRoomList(socket) {
    var builder = new flatbuffers.Builder(1);

    com.clue.fbs.RemoteLogger.ReqRoomList.startReqRoomList(builder);
    com.clue.fbs.RemoteLogger.ReqRoomList.addPageCount(builder, 100);
    var packet = com.clue.fbs.RemoteLogger.ReqRoomList.endReqRoomList(builder);
    builder.finish(packet);
    var buf = builder.asUint8Array();

    var data = new Uint8Array(buf.byteLength + 1);
    data[0] = com.clue.fbs.RemoteLogger.MessageType.ReqRoomList;
    data.set(buf, 1);
    socket.send(data);
}

function parseRoomList(data) {
    try {
        var buffer = new Uint8Array(data, 0, 1);
        if (buffer[0] != com.clue.fbs.RemoteLogger.MessageType.ResRoomList) {
            console.log(buffer[0]);
            return null;
        }

        var buf = new flatbuffers.ByteBuffer(new Int8Array(data, 1, data.byteLength-1));
        return com.clue.fbs.RemoteLogger.ResRoomList.getRootAsResRoomList(buf);
    } catch (err) {
        console.log(err);
    }
}

function main() {
    var socket = new WebSocket("ws://" + location.hostname + ":8090/remoteLogger");
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
        for (i=0; i<roomList.roomsLength(); i++) {
            var room = roomList.rooms(i);
            var link = "<a href='../log/"+room.roomId()+"'>"+room.roomId()+"</a>";
            var badge = "<span class=\"badge\">" + room.count() + "</span>";
            $('#messages').append($('<li class="list-group-item">').html(link + badge));
        }

        if (roomList.roomsLength() == 0) {
            $('#messages').append($('<li class="list-group-item">').html("no room exist!"));
        }

        setTimeout(function(){ sendReqRoomList(socket); }, 2000);
    };
}

main();
