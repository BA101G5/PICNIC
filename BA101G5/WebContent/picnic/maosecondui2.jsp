<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.picnic.model.*" %>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>

	<% if(request.getMethod().toUpperCase().equals("POST")){
		request.setCharacterEncoding("Big5");
		String address =request.getParameter("address");
		String area =request.getParameter("area");
		address=area+address;
		String date = request.getParameter("date");
		String people=request.getParameter("people");
		String name =request.getParameter("name");
		request.setAttribute("address",address);
		request.setAttribute("date",date);
		request.setAttribute("people",people);
		request.setAttribute("name", name);
	} %>
	<h1>確認資訊</h1>
	<p>揪團名稱 : ${name}</p>
<p>地址 : ${address}</p>
<p>日期 : ${date}</p>
<p>限制人數 : ${people}</p>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>