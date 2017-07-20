<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.purview_detail.model.*"%>
<%Purview_DetailVO purdVO = (Purview_DetailVO)request.getAttribute("purdVO"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admView</title>
</head>
<body>
	
	<table border="0">

	<tr>
		<td>管理員編號:</td>
		<td><%= (purdVO==null)? "???" : purdVO.getAdm_no()%></td>
	</tr>
	<tr>
		<td>權限編號:</td>
		<td><%= (purdVO==null)? "???" : purdVO.getPurview_no()%></td>
	</tr>
	
</table>
	
</body>
</html>