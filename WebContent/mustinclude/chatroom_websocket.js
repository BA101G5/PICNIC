// console.log("chatroom_websocket.js / memNo = " + gObjCR.memNo);

function updateStatus(newStatus) {
  // document.getElementById("statusOutput").innerHTML = newStatus;
  console.log('chatroom_websocket.js / ' + newStatus);
}

function connect(endPointURL) {
  // 建立 websocket 物件
  webSocket = new WebSocket(endPointURL);

  webSocket.onopen = function(event) {
    updateStatus("WebSocket 成功連線. " + endPointURL);
    // document.getElementById('sendMessage').disabled = false;
    // document.getElementById('connect').disabled = true;
    // document.getElementById('disconnect').disabled = false;
  };

  webSocket.onmessage = function(event) {
    var messagesArea = document.getElementById("ulChat");
    updateStatus(' chatroom_websocket.js / onmessage / event.data = ' + event.data);
    var jsonObj = JSON.parse(event.data);
    var message = jsonObj.userName + ": " + jsonObj.message;// + "\r\n";
    messagesArea.innerHTML += '<li class="left clearfix">' + message + '</li>';
    // messagesArea.scrollTop = messagesArea.scrollHeight;
    onWinResize();
  };

  webSocket.onclose = function(event) {
    updateStatus("WebSocket 已離線");
  };
}


// var inputUserName = document.getElementById("userName");
// inputUserName.focus();

function sendMessage() {
    var userName = gObjCR.memName;//inputUserName.value.trim();
    // if (userName === ""){
    //     alert ("使用者名稱請勿空白!");
    //     inputUserName.focus();
    // return;
    // }

    var inputMessage = document.getElementById("inputCRMessage");
    var message = inputMessage.value.trim();

    if (message === ""){
        alert ("訊息請勿空白!");
        inputMessage.focus();
    }else{
        var jsonObj = {"userName" : userName, "message" : message, "myRoomNo":  gObjCR.myRoomNo};
        updateStatus(' sendMessage / json  = ' + JSON.stringify(jsonObj));
        webSocket.send(JSON.stringify(jsonObj));
        inputMessage.value = "";
        inputMessage.focus();
    }
}


function disconnect() {
  webSocket.close();
  // document.getElementById('sendMessage').disabled = true;
  // document.getElementById('connect').disabled = false;
  // document.getElementById('disconnect').disabled = true;
}

$(window).unload(function(){
  disconnect();
});

