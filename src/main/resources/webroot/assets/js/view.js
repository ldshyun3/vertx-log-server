
function sendReqJoin(socket) {
    var builder = new flatbuffers.Builder(0);
    console.log(getRoomId());

    var roomIdOffset = builder.createString(getRoomId());
    com.clue.fbs.RemoteLogger.ReqJoin.startReqJoin(builder);
    com.clue.fbs.RemoteLogger.ReqJoin.addRoomId(builder, roomIdOffset);
    var packet = com.clue.fbs.RemoteLogger.ReqJoin.endReqJoin(builder);
    builder.finish(packet);
    var buf = builder.asUint8Array();

    var data = new Uint8Array(buf.byteLength + 1);
    data[0] = com.clue.fbs.RemoteLogger.MessageType.ReqJoin;
    data.set(buf, 1);
    socket.send(data);
}

function sendReqLogPing(socket) {
    var builder = new flatbuffers.Builder(0);
    console.log(getRoomId());

    var messageOffset = builder.createString("ping");
    com.clue.fbs.RemoteLogger.ReqLog.startReqLog(builder);
    com.clue.fbs.RemoteLogger.ReqLog.addMessage(builder, messageOffset);
    com.clue.fbs.RemoteLogger.ReqLog.addLevel(builder, 1);
    var packet = com.clue.fbs.RemoteLogger.ReqLog.endReqLog(builder);
    builder.finish(packet);
    var buf = builder.asUint8Array();

    var data = new Uint8Array(buf.byteLength + 1);
    data[0] = com.clue.fbs.RemoteLogger.MessageType.ReqLog;
    data.set(buf, 1);
    socket.send(data);


    var buf2 = new flatbuffers.ByteBuffer(data);
    var test = com.clue.fbs.RemoteLogger.ReqLog.getRootAsReqLog(buf2);
    console.log(test.message());
    console.log(JSON.stringify(builder.dataBuffer()));
    console.log(JSON.stringify(buf2));

}

function parseNotiLog(data) {
    try {
        var buffer = new Int8Array(data, 0, 1);
        if (buffer[0] != com.clue.fbs.RemoteLogger.MessageType.NotiLog) {
            console.log(buffer[0]);
            return null;
        }

        var buf = new flatbuffers.ByteBuffer(new Uint8Array(data, 1, data.length));
        var result = com.clue.fbs.RemoteLogger.NotiLog.getRootAsNotiLog(buf);
        console.log(JSON.stringify(result));
        return result;

    } catch (err) {
        console.log(err);
    }
}

function getRoomId() {
    var roomId = "";
    var getLocation = function(href) {
        var l = document.createElement("a");
        l.href = href;
        return l;
    };

    var l = getLocation(location.href);
    if (typeof(l.pathname) != 'undefined') {
        roomId = l.pathname.substring(5);
    }
    return roomId;
}


function main() {
    var socket = new WebSocket("ws://localhost:8090/remoteLogger");
    socket.binaryType = "arraybuffer";

    socket.onopen = function() {
        console.log("onopen");
        sendReqJoin(socket);
        sendReqLogPing(socket);
        $('#messages').append($('<p class="bg-info">').html("joined to roomId:" + getRoomId()));
    };

    socket.onclose = function() {
    };

    socket.onmessage = function(evt) {
        console.log("on message");
        var notiLog = parseNotiLog(evt.data);
        if (notiLog == null) {
            return;
        }

        var txt = notiLog.message().replace("<", "&lt;");
        txt = txt.replace(">", "&gt;");
        txt = txt.replace(/\n/g, "<br />");

        switch (notiLog.level()) {
        case 0: lv = "warning"; break;
        case 1: lv = "warning"; break;
        case 2: lv = "warning"; break;
        case 3: lv = "info"; break;
        case 4: lv = "danger"; break;
        case 5: lv = "danger"; break;
        case 6: lv = "danger"; break;
        }

        if ($("#message p").length > 500) {
            $("#message").find('p:first').remove();
        }

        $('#messages').append($('<p class="bg-' + lv + '">').html(txt));
        document.body.scrollTop = document.body.scrollHeight;

        setTimeout(function(){ sendReqLogPing(socket); }, 3000);
    };
}

main();
