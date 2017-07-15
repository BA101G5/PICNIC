<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic野餐網</title>
<jsp:include page="/mustinclude/head.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<style>
.banner {
	float: left;
	padding-left: 40px;
	padding: 20px;
}
.banner-top {
	clear: left;
}
.caption {
	text-align: center;
}
.btn {
	margin: 0px;
	margin-bottom: 0px;
}
.thumbnail {
	height: 350px;
	text-align: center;
	line-height: 180px;
	margin-top: 0px;
	bgcolor: blue;
}
.breadcrumb {
	margin-top: -20px;
	background: skyblue;
}
</style>
</head>
<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-8 col-sm-push-2">
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>/index.jsp">首頁</a></li>
					<li><a href="#" class="active">預購商品</a></li>

				</ol>
			</div>
			<div class="row">

				<div class="col-sm-12 col-sm-push-1 ">

					<div class="row">
						<div class="col-sm-8 col-sm-push-1">
							<div id="carousel-id" class="carousel slide" data-ride="carousel">
								<ol class="carousel-indicators">
									<li data-target="#carousel-id" data-slide-to="0" class=""></li>
									<li data-target="#carousel-id" data-slide-to="1" class=""></li>
									<li data-target="#carousel-id" data-slide-to="2" class="active"></li>
								</ol>
								<div class="carousel-inner">
									<div class="item">
										<img
											src="https://www.kpm-berlin.com/en/porcelain-shop/media/catalog/category/kurland_2-2800x700.jpg"
											alt="">
									</div>
									<div class="item">
										<img
											src="https://www.kpm-berlin.com/en/porcelain-shop/media/catalog/category/rocaille_2-2800x700.jpg"
											alt="">
									</div>
									<div class="item active">
										<img
											src="https://www.kpm-berlin.com/en/porcelain-shop/media/catalog/category/feldblume_2-2800x700.jpg"
											alt="">
									</div>
								</div>
								<a class="left carousel-control" href="#carousel-id"
									data-slide="prev"><span
									class="glyphicon glyphicon-chevron-left"></span></a> <a
									class="right carousel-control" href="#carousel-id"
									data-slide="next"><span
									class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col-sm-8 col-sm-push-1">
							<div class="col-sm-4 ">

								<a href="#" class="thumbnail">
									<p>第一次預購商品?</p>
									<div class="caption">
										<button type="button" class="btn btn-default">前往註冊</button>
									</div>
								</a>
							</div>
							<div class="col-sm-4 ">
								<a href="<%=request.getContextPath()%>/buycart/maosecond.jsp"
									class="thumbnail">
									<p>流行商品</p>
									<div class="caption">
										<button type="button" class="btn btn-default">前往</button>
									</div>
								</a>
							</div>
							<div class="col-sm-4 ">
								<a href="<%=request.getContextPath()%>/buycart/maosecond2.jsp"
									class="thumbnail">
									<p>商品分類</p>
									<div class="caption">
										<button type="button" class="btn btn-default">前往</button>
									</div>
								</a>
							</div>
						</div>
					</div>

					<div class="row ">
						<div class="col-sm-8 col-sm-push-1 ">
							<div class="btn-group btn-group-justified ">
								<a href="# " class="btn btn-default " role="button ">回標題</a>
							</div>
							<div class="col-sm-10 col-sm-push-3 ">
								<jsp:include page="/mustinclude/footer.jsp" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>