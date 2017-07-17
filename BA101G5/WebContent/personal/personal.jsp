<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
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
<style type="text/css">
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
#btn {
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

#btn:hover {background-color:SkyBlue ;}

#btn:active {
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

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>