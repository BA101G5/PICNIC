<%@ page contentType="text/css; charset=UTF-8" pageEncoding="Big5"%>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>

<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<!-- 最新編譯和最佳化的 JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<link href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mustinclude/left_nav.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mustinclude/chatroom.css">


<style>
html {
	height: 100%;
}

@font-face {
	font-family: "NotoSansCJKtc";
<%-- 	src: url(<%= request.getContextPath() %>/lib/NotoSansCJKtc-Light.otf); --%>
}
body {
	background-image:
		url(http://ms-cache.walkerplus.com/walkertouch/wtd/images/n/103670.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	font-family: "NotoSansCJKtc";
	font-size: 21px;
}
</style>