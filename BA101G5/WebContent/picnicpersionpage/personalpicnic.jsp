<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic野餐網</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<jsp:include page="/mustinclude/head.jsp" />
<style>
.breadcrumb {
	margin-top: -25px;
	background: skyblue;
}
</style>

</head>
<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container" style="background-color: gray;">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="/BA101G5456/index.jsp">首頁</a></li>
				<li><a href="#" class="active">開團</a></li>
			</ol>
		</div>
		<div class="row">
			<div class="col-md-8">
				<h1 class="page-header">
					Heading <small>Text</small>
				</h1>
				<h2>
					<a href="#"> Title</a>
				</h2>
				<hr>
				<img class="img-responsive" src="http://placehold.it/900x300" alt="">
				<hr>
				<p></p>
				<ul class="pager">
					<li class="previous"><a href="#">&larr; Older</a></li>
					<li class="next"><a href="#">Newer &rarr;</a></li>
				</ul>
			</div>
			<div class="col-md-4">
				<div class="well">
					<h4>野餐團介紹</h4>
					<div class="row">
						<div class="well">
							<p>
							<form method="post" action="">
								<a href="#">修改</a>
							</form>
							</p>

						</div>
						<div class="col-lg-6">
							<ul class="list-unstyled">
								<li><a href="#">成員</a></li>
								<li><a href="#">成員</a></li>

							</ul>
						</div>

						<div class="col-lg-6">
							<ul class="list-unstyled">
								<li><a href="#">成員</a></li>
								<li><a href="#">成員</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<div class="row">
	<div class="col-md-12">
		<jsp:include page="/mustinclude/footer.jsp" />
	</div></div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>