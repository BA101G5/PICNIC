<%@ page contentType="text/css; charset=UTF-8" pageEncoding="Big5"%>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>


<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- 選擇性佈景主題 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<!-- 最新編譯和最佳化的 JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/mustinclude/left_nav.css">
<style>
html {
	height: 100%;
}

@font-face {
	font-family: "NotoSansCJKtc";
	src: url(<%= request.getContextPath() %>/lib/NotoSansCJKtc-Light.otf);
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