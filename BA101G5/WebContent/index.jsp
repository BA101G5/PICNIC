<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Examples</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<!-- ææ°ç·¨è­¯åæä½³åç CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- é¸ææ§ä½æ¯ä¸»é¡ -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<!-- ææ°ç·¨è­¯åæä½³åç JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<style type="text/css">
#navigation-bar{
	position: fixed;
	width: 60px;
	height:100%;
	background-color: gray;
	text-align: center;
	padding: 200px 0px;
}
.home_section{
	float: left;
}
.block{
	float: left;
	text-align: center;
}
#home_middle{
	background-color: lightgreen;
}
#home_right{
	background-color: pink;
	width: 360px;
	text-align: center;
}

#announcement_block{
	background-color: yellow;
	width: 360px;
	height: 100px;
	text-align: center;
	display:table-cell;
	vertical-align: middle;
}
#announcement{
	padding: 0px 16px;
}
#four_block{
	background-color: lightblue;
	width: 360px;
	height: 360px;
}

#four_block > :nth-child(1){
	background-color: orange;
}
#four_block > :nth-child(4){
	background-color: orange;
}

.bs-glyphicons-list {
    padding-left: 0px;
    list-style: outside none none;
}
.bs-glyphicons-list > li{
	margin: 16px 0px;
}
.glyphicon{
	font-size: 36px;
}
#chat_room{
	position: fixed;
	background-color: #a0a;
	height: 50px;
	width: 200px;
	padding: 8px;
}

</style>
</head>
<body>
  <div id="navigation-bar">
  	<ul class="bs-glyphicons-list">
	  	<li title="æå¡"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></li>
	  	<li title="ç«å§ä¿¡"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></li>
	  	<li title="æå­¸"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span></li>
	  	<li title="éæ¼æå"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span></li>
  	<ul>
  </div>
  <div id="home_middle" class="home_section">
  	<img src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder">
  </div>
  <div id="home_right" class="home_section">
  	<div id="announcement_block">
  		<div id="announcement">2017-05-31 [最新訊息] 一二三四五六七</div>
  	</div>
  	<div id="four_block">
			<div class="block">論壇</div>
			<div class="block">參團</div>
			<div class="block">精選</div>
			<div class="block">揪團</div>
		</div>
  	<div id="home_search">
  		搜尋列
  	</div>
  </div>
	<div id="chat_room"><span class="glyphicon glyphicon-send" aria-hidden="true"></span>聊天室</div>

  <script type="text/javascript">
  	var flagChatRoom = 0;
  	var heightAnnouncementBlock = 100;
		var widthNavigation = 60;
		var widthHomeRight = 360;
		var clientWidth = $(window).width();
  	var clientHeight = $(window).height();
  	$('.home_section').css('height', clientHeight + 'px');
		$('#home_middle')
			.css('width', clientWidth - widthHomeRight + 'px')
			.css('padding-left', widthNavigation + 'px');
		$('.block')
			.css('width', widthHomeRight / 2 + 'px')
			.css('height', widthHomeRight / 2 + 'px')
			.css('line-height', widthHomeRight / 2 + 'px');
		$('#chat_room')
			.css('left', clientWidth - widthHomeRight + 'px')
			.css('top', clientHeight - 50 + 'px')
			.css('height', clientHeight - 50 + 'px')
			.css('width', widthHomeRight + 'px');
		$('img')
			.css('width', '100%')
			.css('height', '100%');


		$('#chat_room').click(function(){
			flagChatRoom = ( flagChatRoom + 1 ) % 2;
			$('#chat_room')
				.css('top', (flagChatRoom===0?clientHeight-50:heightAnnouncementBlock) + 'px')
		});
  </script>
</body>
</html>