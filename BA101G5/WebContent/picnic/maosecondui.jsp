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
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAK_1dtxdSOtLRWPJy6ntqCm9kxqrLmRPs&callback=initMap"
	async defer></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!--[if lt IE 9]>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
       
    <![endif]-->
<script>
	$(function() {
		$("#datepicker").datepicker({
	        minDate: 0
		});
	});
</script>

<script>
	var array={aoeu,aoeu,aoeu};
	var map;

	function initMap() {
		map = new google.maps.Map(document.getElementById('map'), {
			center : {
				lat : 25.1942462,
				lng : 121.56093629999998
			},
			zoom : 13
		});
	}
</script>
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
			<div class="col-sm-8 col-sm-push-2">
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>/index.jsp">首頁</a></li>
					<li><a href="#" class="active">開團</a></li>

				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 col-sm-push-2">
				<form method="post"
					action="<%=request.getContextPath()%>/picnic/maosecondui2.jsp">
					<h3>請輸入開團資訊</h3>

					<div class="list-group">
						<a href="#" class="list-group-item active">First item</a> <a
							href="#" class="list-group-item">
							<table>
								<tr>
									<td>縣市: <select name="area" id="input"
										class="form-control" required="required">
											<option value="台北市">台北市</option>
											<option value="aoeu">新北市</option>
											<option value="aoeu">桃園市</option>
											<option value="aoeu">臺中市</option>
											<option value="aoeu">臺南市</option>
											<option value="aoeu">基隆市</option>
											<option value="aoeu">新竹市</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
											<option value="aoeu">aeou</option>
									</select></td>
								</tr>
								<tr>
									<td>地點 : <input type="text" name="address"
										value="${sessionScope.address} "></td>
								</tr>
								<tr>
									<td>Date : <input type="text" id="datepicker" name="date"
										value="${sessionScope.date}"></td>
								</tr>
								<tr>
									<td>限制人數 : <input type="text" name="people"
										value="${sessionScope.people}"></td>
								</tr>
							</table>
						</a> <a href="#" class="list-group-item">請輸入可以清楚表達揪團性質的名子: <input
							type="textarea" rows="4" cols="50" name="name"
							value="${sessionScope.name}"></a>
					</div>

					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button type="reset" class="btn btn-default" value="Reset">重設</button>
						</div>
						<div class="btn-group">
							<button type="submit" class="btn btn-default" value="Submit">送出</button>
						</div>

					</div>
				</form>
			</div>

			<div class="col-sm-8 col-xs-1 col-sm-push-2">
				<div id="map"></div>


			</div>
		</div>
		<div class="row">
			<div id="new">
				<div class="col-xs-8 col-xs-push-2 ">
					<a href="# " class="thumbnail ">

						<div class="caption"></div>
					</a>
				</div>
			</div>

			<div class="row ">
				<div class="col-sm-8 col-sm-push-2 ">
					<div class="btn-group btn-group-justified ">
						<a href="# " class="btn btn-default " role="button ">回標題</a>
					</div>
					<div class="col-sm-11 col-sm-push-3 ">
						<jsp:include page="/mustinclude/footer.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<%
		session.removeAttribute("address");
		session.removeAttribute("date");
		session.removeAttribute("people");
		session.removeAttribute("name");
	%>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js "></script>
</body>
</html>



