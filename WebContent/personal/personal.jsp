<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>個人頁面 - Picnic野餐網</title>
<jsp:include page="/mustinclude/head.jsp" />

<style type="text/css">
.table-striped tbody tr:nth-of-type(odd){
				background-color: AliceBlue ;
			}
			.table-hover tbody tr:hover{
				background-color: GreenYellow ;
			}

.row-padding {
    margin-top: 25px;
    margin-bottom: 25px;
}
.news {
    background: #fff;
	position: relative;
	overflow: hidden;
	z-index:1;
 	-webkit-transition: all 0.5s ease;
	-moz-transition: all 0.5s ease;
	-ms-transition: all 0.5s ease;
	-o-transition: all 0.5s ease;
	transition: all 0.5s ease;	

	border: 1px solid #eaeaea;
}

.news .img-figure {
	position: relative;
}

.news .img-figure img {
	position: relative;	
	overflow: hidden;
	-webkit-transition: all 1s ease;
	-moz-transition: all 1s ease;
	-ms-transition: all 1s ease;
	-o-transition: all 1s ease;
	transition: all 1s ease;
}

.news:hover .img-figure img {
	-webkit-transform: scale(1.10);
	-moz-transform: scale(1.10);
	-ms-transform: scale(1.10);
	-o-transform: scale(1.10);
	transform: scale(1.10);
}

.news .title {
	/*padding: 29px 30px;*/
	padding: 45px 30px;
	position: absolute;
	left: 0;
	background: #fff;
	width: 100%;
	height: 100%;
	-webkit-transition: all 0.5s ease;
	-moz-transition: all 0.5s ease;
	-ms-transition: all 0.5s ease;
	-o-transition: all 0.5s ease;
	transition: all 0.5s ease;	
}

.news .title h1 {
	margin: 0;
	text-transform: uppercase;
	font-weight: bold;
	font-size: 19px;
/*	color: #3D6AA2;*/
	text-align: center;
}

.news:hover .title  {
	margin-top: -90px;
	background: #fff;
}

.news  p.description {
	position: relative;
	font-weight: normal;
	line-height: 22px;
	opacity: 0;

	text-align: center;

	padding: 15px 30px 0px 30px;

	-webkit-transition: all 0.5s ease;
	-moz-transition: all 0.5s ease;
	-ms-transition: all 0.5s ease;
	-o-transition: all 0.5s ease;
	transition: all 0.5s ease;	
}

.news:hover p.description  {
	opacity: 1;
}

p.more {
	text-align: center;
}

.more a {
    position: relative;
    font-size: 13px;
    margin-top: 30px;
    display: block;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -ms-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
    display: inline-block;
    color: #28998b;
}

.more a:after {
    content: '';
    width: 18px;
    height: 1px;

    position: absolute;
    left: 55px;
    top: 9px;

    background: #28998b;

    vertical-align: middle;
    margin-left: 10px;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -ms-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}


.news:hover a:after {
    width: 30px;
}





h3 {
	text-align: center;
	padding: 15px;
}

body {
	font-family: "微軟正黑體", Arial, sans-serif;
}

li+a {
	padding: 0 10px;
	color: #595959;
	cursor: pointer;
	border-bottom: 2px solid transparent;
	line-height: 58px;
	margin-right: 20px;
	text-decoration: none;
}

.list {
	list-style: none;
}

.bb {
	font-size: 16px;

	/*display: flex;*/
	/*justify-content: center;*/
}

#tab4 {
	margin-top: 10px;
}
#btn,.btn{
  color: #fff;
    background-color: #5bc0de;
    border-color: #46b8da;
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 16px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
  
}

#btn:hover,.btn:hover{background-color:SkyBlue ;}

#btn:active,.btn:active{
  background-color: 	#40E0D0;
 
  transform: translateY(4px);
}
</style>
</head>
<body>

	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	
	
<c:if test="${not empty gVO}">

	<jsp:include page="/personal/g_personal.jsp" />
</c:if>
<c:if test="${not empty mVO}">

	<jsp:include page="/personal/m_personal.jsp" />
</c:if>		
	<script>
	
	
	
		function doFirst() {
			document.getElementsByClassName('li1')[0].style.borderBottom = "2px solid blue";
		}
		document.getElementsByClassName('li1')[0]
				.addEventListener(
						'click',
						function() {
							document.getElementsByClassName('li1')[0].style.borderBottom = "2px solid blue";
							document.getElementsByClassName('li1')[1].style.borderBottom = "";
							document.getElementsByClassName('li1')[2].style.borderBottom = "";
							document.getElementsByClassName('li1')[3].style.borderBottom = "";
							document.getElementsByClassName('li1')[4].style.borderBottom = "";
						}, false);

		document.getElementsByClassName('li1')[1]
				.addEventListener(
						'click',
						function() {
							document.getElementsByClassName('li1')[1].style.borderBottom = "2px solid blue";
							document.getElementsByClassName('li1')[0].style.borderBottom = "";
							document.getElementsByClassName('li1')[2].style.borderBottom = "";
							document.getElementsByClassName('li1')[3].style.borderBottom = "";
							document.getElementsByClassName('li1')[4].style.borderBottom = "";
						}, false);

		document.getElementsByClassName('li1')[2]
				.addEventListener(
						'click',
						function() {
							document.getElementsByClassName('li1')[2].style.borderBottom = "2px solid blue";
							document.getElementsByClassName('li1')[1].style.borderBottom = "";
							document.getElementsByClassName('li1')[0].style.borderBottom = "";
							document.getElementsByClassName('li1')[3].style.borderBottom = "";
							document.getElementsByClassName('li1')[4].style.borderBottom = "";
						}, false);

		document.getElementsByClassName('li1')[3]
				.addEventListener(
						'click',
						function() {
							document.getElementsByClassName('li1')[3].style.borderBottom = "2px solid blue";
							document.getElementsByClassName('li1')[1].style.borderBottom = "";
							document.getElementsByClassName('li1')[2].style.borderBottom = "";
							document.getElementsByClassName('li1')[0].style.borderBottom = "";
							document.getElementsByClassName('li1')[4].style.borderBottom = "";
						}, false);

		document.getElementsByClassName('li1')[4]
				.addEventListener(
						'click',
						function() {
							document.getElementsByClassName('li1')[4].style.borderBottom = "2px solid blue";
							document.getElementsByClassName('li1')[1].style.borderBottom = "";
							document.getElementsByClassName('li1')[2].style.borderBottom = "";
							document.getElementsByClassName('li1')[0].style.borderBottom = "";
							document.getElementsByClassName('li1')[3].style.borderBottom = "";
						}, false);

		window.addEventListener('load', doFirst, false);
	</script>

<!-- 	<script src="https://code.jquery.com/jquery.js"></script> -->
<!-- 	<script -->
<!-- 		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

</body>
</html>