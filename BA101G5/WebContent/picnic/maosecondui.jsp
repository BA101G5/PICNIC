<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place.model.*"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<jsp:include page="/mustinclude/head.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
       
    <![endif]-->
<style>
#map {
	height: 600px;
	width: 800px;
}

#new {
	margin-top: 10px;
}

.breadcrumb {
	margin-top: -25px;
	background: skyblue;
}
</style>
</head>

<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container-fluid">
		<div class="row ">

			<div class="col-xs-8 col-xs-push-2">

				<ol class="breadcrumb">
					<li><a href="#">回到首頁</a></li>
					<li><a href="#">回上上頁</a></li>
					<li class="active">回上一頁</li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-3 col-sm-push-1">
				<h3>請選擇條件</h3>
				<form action="http://yahoo.com.tw">
					<div class="list-group">
						<a href="#" class="list-group-item active">First item</a> <a
							href="#" class="list-group-item">
							
							<table>
								<tr>
									<td>縣市<select name="" id="input" class="form-control"
										required="required">
											<option value=""></option>
									</select></td>
								</tr>
								<tr>
									<td>地點<input type="text" name=""></td>
								</tr>
								<tr>
									<td>時間<input type="text" name=""></td>
								</tr>
							</table></a>
							
							 <a href="#" class="list-group-item"><P>
								輸入一個可以清楚表達揪團性質的名子<input type="textarea" rows="4" cols="50"
									name="">
							</P></a>
					</div>


					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<a href="#" class="btn btn-default" role="button">重設</a>
						</div>
						<div class="btn-group">
							<a href="#" class="btn btn-default" role="button">送出</a>
						</div>

					</div>
			</div>
			</form>
			<div class="col-sm-8 col-xs-1 col-sm-push-1">
				<div id="map"></div>
				<script>
					var map;

					function initMap() {
						map = new google.maps.Map(document
								.getElementById('map'), {
							center : {
								lat : 25.1942462,
								lng : 121.56093629999998
							},
							zoom : 8
						});
					}
				</script>
				<script
					src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAK_1dtxdSOtLRWPJy6ntqCm9kxqrLmRPs&callback=initMap"
					"
    async defer></script>
			</div>
		</div>
		<div class="row">
			<div id="new">
				<div class="col-xs-8 col-xs-push-2 ">
					<a href="# " class="thumbnail ">

						<div class="caption "></div>
					</a>
				</div>
			</div>

			<div class="row ">
				<div class="col-xs-8 col-xs-push-2 ">
					<div class="btn-group btn-group-justified ">
						<a href="# " class="btn btn-default " role="button ">回到</a>
					</div>
					<jsp:include page="/mustinclude/footer.jsp" />
				</div>
			</div>

		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js "></script>
</body>
</html>


