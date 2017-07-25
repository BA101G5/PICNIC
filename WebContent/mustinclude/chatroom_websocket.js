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
    if(jsonObj.userNo == gObjCR.memNo){
      messagesArea.innerHTML += '<li class="right clearfix"><span class="chat-img pull-right"><img src="'+gObjCR.getContextPath +'/general_member/DBGift.do?MEM_NO='+jsonObj.userNo +'" alt="User Avatar" class="img-circle" /></span><div class="chat-body clearfix"><div class="header"><small class=" text-muted"><span class="glyphicon glyphicon-time"></span>'+ jsonObj.timestamp +'</small><strong class="pull-right primary-font">' + jsonObj.userName + '</strong></div><p>' + jsonObj.message + '</p></div></li>';
    }else{
      messagesArea.innerHTML += '<li class="left clearfix"><span class="chat-img pull-left"><img src="'+gObjCR.getContextPath +'/general_member/DBGift.do?MEM_NO='+jsonObj.userNo +'" alt="User Avatar" class="img-circle" /></span><div class="chat-body clearfix"><div class="header"><strong class="primary-font">' + jsonObj.userName + '</strong> <small class="pull-right text-muted"><span class="glyphicon glyphicon-time"></span>'+ jsonObj.timestamp +'</small></div><p>'+jsonObj.message+'</p></div></li>';
    }
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
    var userNo = gObjCR.memNo;
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
        var jsonObj = {"userNo": userNo, "userName": userName, "message": message, "myRoomNo":  gObjCR.myRoomNo, "timestamp": moment().format('YYYY-MM-DD HH:MM:SS')};
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

