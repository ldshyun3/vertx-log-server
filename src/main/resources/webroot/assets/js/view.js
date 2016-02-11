var ProtoBuf = dcodeIO.ProtoBuf;
var remoteLogger = ProtoBuf.loadProtoFile("../assets/idl/remoteLogger.proto");
var ReqJoin = remoteLogger.build("com.clue.proto.ReqJoin");
var ReqLog = remoteLogger.build("com.clue.proto.ReqLog");
var NotiLog = remoteLogger.build("com.clue.proto.NotiLog");

function sendReqJoin(socket) {
    var req = new ReqJoin();
    req.roomId = getRoomId();
    var reqData = req.toArrayBuffer();

    var data = new Uint8Array(reqData.byteLength + 4);
    data[3] = 0;
    data.set(new Uint8Array(reqData), 4);
    socket.send(data);
}

function parseNotiLog(data) {
    try {
        var buffer = new Int8Array(data, 0, 4);
        if (buffer[3] != 2) {
            console.log(buffer[3]);
            return null;
        }

        return NotiLog.decode(data.slice(4));
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
    var socket = new WebSocket("ws://raindays.net:8090/remoteLogger");
    socket.binaryType = "arraybuffer";

    socket.onopen = function() {
        console.log("onopen");
        sendReqJoin(socket);
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

        var txt = notiLog.message.replace("<", "&lt;");
        txt = txt.replace(">", "&gt;");
        txt = txt.replace(/\n/g, "<br />");

        switch (notiLog.level) {
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
    };
}

main();
