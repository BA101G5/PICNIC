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
			dateFormat: 'yy-mm-dd',
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
					action="<%=request.getContextPath()%>/picnic/picnic.do">
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
									</select><br></td>
								</tr>
								<tr>
									<td>地點 : <input type="text" name="address"
										value="${sessionScope.address} "><br>
										<font color="red")>${errorMsgs.address}</font></td>
								</tr>
								<tr>
									<td>限制人數 : <input type="text" name="people"
										value="${sessionScope.people}"><br>
										<font color="red")>${errorMsgs.people}</font></td>
								</tr>
								<tr>
									<td>日 期 : <input type="text" id="datepicker" name="date"
										value="${sessionScope.date}"><br>
										<font color="red")>${errorMsgs.date}</font></td>
								</tr>
								<tr>
									<td>時間 : <br> <select id="SearchTime" name="hour"
										style="width: 85px;"><option value="">請選擇</option>
											<option value=" 06:00:00">06:00</option>
											<option value=" 06:30:00">06:30</option>
											<option value=" 07:00:00">07:00</option>
											<option value=" 07:30:00">07:30</option>
											<option value=" 08:00:00">08:00</option>
											<option value=" 08:30:00">08:30</option>
											<option value=" 09:00:00">09:00</option>
											<option value=" 09:30:00">09:30</option>
											<option value=" 10:00:00">10:00</option>
											<option value=" 10:30:00">10:30</option>
											<option selected="selected" value=" 11:00:00">11:00</option>
											<option value=" 11:30:00">11:30</option>
											<option value=" 12:00:00">12:00</option>
											<option value=" 12:30:00">12:30</option>
											<option value=" 13:00:00">13:00</option>
											<option value=" 13:30:00">13:30</option>
											<option value=" 14:00:00">14:00</option>
											<option value=" 14:30:00">14:30</option>
											<option value=" 15:00:00">15:00</option>
											<option value=" 15:30:00">15:30</option>
											<option value=" 16:00:00">16:00</option>
											<option value=" 16:30:00">16:30</option>
											<option value=" 17:00:00">17:00</option>
											<option value=" 17:30:00">17:30</option>
											<option value=" 18:00:00">18:00</option>
											<option value=" 18:30:00">18:30</option>
											<option value=" 19:00:00">19:00</option>
											<option value=" 19:30:00">19:30</option>
											<option value=" 20:00:00">20:00</option>
											<option value=" 20:30:00">20:30</option>
											<option value=" 21:00:00">21:00</option>
											<option value=" 21:30:00">21:30</option>
											<option value=" 22:00:00">22:00</option>
											<option value=" 22:30:00">22:30</option>
											<option value=" 23:00:00">23:00</option>
											<option value=" 23:30:00">23:30</option>
									</select></td>
								</tr>
							</table>
						</a> <a href="#" class="list-group-item">請輸入可以清楚表達揪團性質的名子: <input
							type="textarea" rows="4" cols="50" name="name"
							value="${sessionScope.picnic_name}"></a><br>
							<font color="red")>${errorMsgs.name}</font>
					</div>

					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<button type="reset" class="btn btn-default" value="Reset">重設</button>
						</div>
						<div class="btn-group">
							<button type="submit" class="btn btn-default" value="Submit">送出</button>
						</div>
						<input type="hidden" name="action" value="checkbeforeinsert">
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



