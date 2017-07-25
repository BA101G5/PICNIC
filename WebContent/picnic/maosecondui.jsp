<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.place.model.*"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic野餐網</title>
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
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			minDate : 0
		});
	});
</script>
	<script>
var Latitude =22.6158015;
var Longitude =120.7120023;
var zoom =7;
 function change(value){

	 switch(value) {
	    case '新北市':
	    	Latitude =24.91571;
	        Longitude =121.6739;
	        zoom =12;
	        break;
	    case '高雄市':
	    	Latitude =23.01087;
	        Longitude =120.666;
	        zoom =12;
	        break;
	    case '臺中市':
	    	Latitude =24.23321;
	        Longitude =120.9417;
	        zoom =12;
	        break;
	    case '臺北市':
	    	Latitude =25.09108;
	        Longitude =121.5598;
	        zoom =12;
	        break;
	    case '桃園縣':
	    	Latitude =24.93759;
	        Longitude =121.2168;
	        zoom =12;
	        break;
	    case '臺南市':
	    	Latitude =23.1417;
	        Longitude =120.2513;
	        zoom =12;
	        break;
	    case '彰化縣':
	    	Latitude =23.99297;
	        Longitude =120.4818;
	        zoom =12;
	        break;
	    case '屏東縣':
	    	Latitude =22.54951;
	        Longitude =120.62;
	        zoom =12;
	        break;
	    case '雲林縣':
	    	Latitude =23.75585;
	        Longitude =120.3897;
	        zoom =12;
	        break;
	    case '苗栗縣':
	    	Latitude =24.48927;
	        Longitude =120.9417;
	        zoom =12;
	        break;
	    case '嘉義縣':
	    	Latitude =23.45889;
	        Longitude =120.574;
	        zoom =12;
	        break;
	    case '新竹縣':
	    	Latitude =24.70328;
	        Longitude =121.1252;
	        zoom =12;
	        break;
	    case '南投縣':
	    	Latitude =23.83876;
	        Longitude =120.9876;
	        zoom =12;
	        break;
	    case '宜蘭縣':
	    	Latitude =24.69295;
	        Longitude =121.7195;
	        zoom =12;
	        break;
	    case '基隆市':
	    	Latitude =25.10898;
	        Longitude =121.7081;
	        zoom =12;
	        break;
	    case '花蓮縣':
	    	Latitude =23.7569;
	        Longitude =121.3542;
	        zoom =12;
	        break;
	    case '嘉義市':
	    	Latitude =23.47545;
	        Longitude =120.4473;
	        zoom =12;
	        break;
	    case '臺東縣':
	    	Latitude =22.98461;
	        Longitude =120.9876;
	        zoom =12;
	        break;
	    default:    	
	} 
	 initMap()
 }



	var customLabel = {
		restaurant : {
			label : 'R'
		},
		bar : {
			label : 'B'
		}
	};

	function initMap() {
	
		var map = new google.maps.Map(document.getElementById('map'), {
			center :{lat:Latitude, lng: Longitude},
			zoom : zoom
		});
		var infoWindow = new google.maps.InfoWindow;

		// Change this depending on the name of your PHP or XML file
		downloadUrl(
				'<%=request.getContextPath()%>/place.xml',
				function(data) {
					var xml = data.responseXML;
					var markers = xml.documentElement
							.getElementsByTagName('marker');
					Array.prototype.forEach.call(markers, function(markerElem) {
						var name = markerElem.getAttribute('name');
						var address = markerElem.getAttribute('address');
						var type = markerElem.getAttribute('type');
						var point = new google.maps.LatLng(
								parseFloat(markerElem.getAttribute('lat')),
								parseFloat(markerElem.getAttribute('lng')));

						var infowincontent = document.createElement('div');
						var strong = document.createElement('strong');
						strong.textContent = name
						infowincontent.appendChild(strong);
						infowincontent
								.appendChild(document.createElement('br'));

						var text = document.createElement('text');
						text.textContent = address
						infowincontent.appendChild(text);
						var icon = customLabel[type] || {};
						var marker = new google.maps.Marker({		
							map : map,
							position : point,
							label : icon.label,
							title : address.split(' ')[1]
						});
						marker.addListener('click', function() {
							infoWindow.setContent(infowincontent);
							infoWindow.open(map, marker);	
							document.getElementById("address").value=$(this).attr("title");
						});
					});
				});
	}
	function downloadUrl(url, callback) {
		var request = window.ActiveXObject ? new ActiveXObject(
				'Microsoft.XMLHTTP') : new XMLHttpRequest;

		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				request.onreadystatechange = doNothing;
				callback(request, request.status);
			}
		};

		request.open('GET', url, true);
		request.send(null);
	}

	function doNothing() {
	}
</script>
 <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxmcF3GtUMJYpT78-gvq2jG3ER8wE15tg&callback=initMap">
    </script>


<style>
#map {
	height: 60%;
	width: 100%;
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
	<div class="container">
		<div class="row">
				
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>/index.jsp">首頁</a></li>
					<li><a href="#" class="active">開團</a></li>

				</ol>
		
		</div>
		<div class="row" style="align:center;">
			<div class="col-md-4">
				<form method="post"
					action="<%=request.getContextPath()%>/picnic/picnic.do">
					<div class="list-group">
						<br> <a href="#" class="list-group-item active">請輸入開團資訊</a> <a
							href="#" class="list-group-item">
							<table>
								<tr>
									<td>縣市: <select name="area" id="input"
										class="form-control" required="required" onmouseup="change(this.value)">
											<option value="新北市">新北市</option>
											<option value="高雄市">高雄市</option>
											<option value="臺中市">臺中市</option>
											<option value="桃園縣">桃園縣</option>
											<option value="臺南市">臺南市</option>
											<option value="彰化縣">彰化縣</option>
											<option value="屏東縣">屏東縣</option>
											<option value="雲林縣">雲林縣</option>
											<option value="苗栗縣">苗栗縣</option>
											<option value="嘉義縣">嘉義縣</option>
											<option value="新竹縣">新竹縣</option>
											<option value="南投縣">南投縣</option>
											<option value="宜蘭縣">宜蘭縣</option>
											<option value="基隆市">基隆市</option>
											<option value="花蓮縣">花蓮縣</option>
											<option value="嘉義市">嘉義市</option>
											<option value="臺東縣">臺東縣</option>
									</select><br></td>
								</tr>
								<tr>
									<td>地點 :<br> <input type="text"  name="address"  id="address"
										value="${sessionScope.address} "><br> <font
										color="red")>${errorMsgs.address}</font></td>
								</tr>
								<tr>
									<td>限制人數 :<br> <input type="text" name="people"
										value="${sessionScope.people}"><br> <font
										color="red")>${errorMsgs.people}</font></td>
								</tr>
								<tr>
									<td>日 期 :<br> <input type="text" id="datepicker"
										name="date" value="${sessionScope.date}"><br> <font
										color="red")>${errorMsgs.date}</font></td>
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
							value="${sessionScope.picnic_name}"><br> <font
							color="red")>${errorMsgs.name}</font></a> <
						<div class="btn-group btn-group-justified">
							<div class="btn-group">
								<button type="reset" class="btn btn-default" value="Reset">重設</button>
							</div>
							<div class="btn-group">
								<button type="submit" class="btn btn-default" value="Submit">送出</button>
							</div>
							<input type="hidden" name="action" value="checkbeforeinsert">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-8 ">
				<div id="map"></div>


			</div>
		</div>
		<div class="row">
			<div id="new">
			
					<a href="#" class="thumbnail">

						<div class="caption"></div>
					</a>
			
			</div>

			<div class="row">
			
					<div class="btn-group btn-group-justified ">
						<a href="# " class="btn btn-default " role="button ">回標題</a>
					</div>
				
						<jsp:include page="/mustinclude/footer.jsp" />
					
			
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



