<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>野太美 - 首頁</title>
<meta name="description" content="">
<meta name="keywords" content="">
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<!-- 最新編譯和最佳化的 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- 選擇性佈景主題 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<!-- 最新編譯和最佳化的 JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<style type="text/css">
html {
	height: 100%;
}
body{
	min-height: 100%;
	/*background-image:url(1-12030R10P4.jpg);*/
	background-image:url(4878670-full-hdtv-wallpapers--ed.jpg);
}
.navbar{
	opacity: 0.9;
}
#left-navigation-bar{
	position: fixed;
	z-index: 6000;
	width: 60px;
	height:100%;
	background-color: #8bdddd;
	opacity: 0.7;
	text-align: center;
	padding: 300px 0px;
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
	color: #091e4c;
}
</style>
</head>
<body>

	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />

<!-- .cr -->
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-push-1">


		</div>
					    <ol class="carousel-indicators">
					        <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
					        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
<!-- 					        <li data-target="#carousel-id" data-slide-to="2" class="active"></li> -->
					    </ol>
	</div>

<!-- 	<div class="row">
		<div class="col-sm-12" style="overflow: hidden;">
			<img src="hd-wallpaper-nature-hd-wallpapers-nature.jpg" style="opacity: 1;">
		</div>
	</div> -->

					<!-- bs-slider
					<div id="carousel-id" class="carousel slide" data-ride="carousel" style="position: fixed; top: 0; left:0;  z-index: -1;">
					    <!-- 幻燈片小圓點區 
					    <ol class="carousel-indicators">
					        <li data-target="#carousel-id" data-slide-to="0" class=""></li>
					        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
					        <li data-target="#carousel-id" data-slide-to="2" class="active"></li>
					    </ol>
					    <!-- 幻燈片主圖區
					    <div class="carousel-inner">
					        <div class="item">
					            <img src="https://api.fnkr.net/testimg/800x700/aaaaaa" alt="">
					            <div class="container">
					                <div class="carousel-caption">
					                    <h1>CSS可樂好喝超爽快</h1>
					                    <p>你喝過了嗎？</p>
					                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
					                </div>
					            </div>
					        </div>
					        <div class="item">
					            <img src="https://api.fnkr.net/testimg/2800x700/aaaaaa" alt="">
					            <div class="container">
					                <div class="carousel-caption">
					                    <h1>CSS可樂的外掛真方便</h1>
					                    <p>你安裝了嗎？</p>
					                    <p><a class="btn btn-lg btn-primary" href="#" role="button">更多</a></p>
					                </div>
					            </div>
					        </div>
					        <div class="item active">
					            <img src="https://api.fnkr.net/testimg/2800x700/aaaaaa" alt="">
					            <div class="container">
					                <div class="carousel-caption">
					                    <h1>我是標題喔～自己改文案吧</h1>
					                    <p>我是內文喔，你可以把字打在這裡呦</p>
					                    <p><a class="btn btn-lg btn-primary" href="#" role="button">詳細內容</a></p>
					                </div>
					            </div>
					        </div>
					    </div>
					    <!-- 上下頁控制區
					    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
					    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
					</div>
 -->
</div>

</body>
</html>