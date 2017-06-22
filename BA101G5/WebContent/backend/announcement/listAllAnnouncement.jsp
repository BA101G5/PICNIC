<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AnnouncementService announcementSvc = new AnnouncementService();
    List<AnnouncementVO> list = announcementSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有最新消息資料 - listAllAnnouncement.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有最新消息資料 - ListAllAnnouncement.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>編號</th>
		<th>最新消息</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="announcementVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${announcementVO.ann_no}</td>
			<td>${announcementVO.ann_text}</td>
			<td>
			  <FORM METHOD="post" ACTION="announcement.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ann_no" value="${announcementVO.ann_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="announcement.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ann_no" value="${announcementVO.ann_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
