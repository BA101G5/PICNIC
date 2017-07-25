<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.administrator.model.*"
		 import="com.purview_detail.model.*"
		 import="com.purview.model.*"
		 import = "java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>test</title>
		<script src="${pageContext.request.contextPath}/view/js/sweetalert.min.js"></script>
		<link href="${pageContext.request.contextPath}/view/js/sweetalert.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/view/js/admView.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
	</head>
	<body>
		<form name = 'form' action = '${pageContext.request.contextPath}/test.do' method='post'>
            <textarea name="content" id="content" rows="10" cols="80"></textarea>
            	<script>CKEDITOR.replace('content', {});</script>
            <input type = 'submit' value = '送出'>
        </form>
		
		<table>
			<c:forEach var="pic" items="${picniclist}">
				<tr>
					<td>${pic.picnic_no}</td>
					<td>${pic.picnic_name}</td>
					<td>${pic.picnic_desc}</td>
					<td>${pic.picnic_startup}</td>
					<td>${pic.picnic_setup}</td>
					<td>${pic.picnic_chk}</td>
					<td>${pic.picnic_date}</td>
					<td>${pic.picnic_pl}</td>
					<td>${pic.picnic_sta}</td>
					<td>${pic.ord_total}</td>
					<td>${pic.ord_date}</td>
					<td>${pic.ord_dm}</td>
					<td>${pic.ord_sta}</td>
				</tr>
			</c:forEach>
		</table>
		
		
		
	</body>
</html>
