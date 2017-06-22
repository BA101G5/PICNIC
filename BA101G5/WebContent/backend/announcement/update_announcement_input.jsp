<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%
	AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO"); //AnnouncementServlet.java (Concroller), 存入req的announcementVO物件 (包括幫忙取出的announcementVO, 也包括輸入資料錯誤時的announcementVO物件)
%>
<html>
<head>
<title>最新消息資料修改 - update_announcement_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>最新消息資料修改 - update_announcement_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="announcement.do" name="form1">
<table border="0">
	<tr>
		<td>最新消息編號:<font color=red><b>*</b></font></td>
		<td><%=announcementVO.getAnn_no()%></td>
	</tr>
	<tr>
		<td>最新消息:</td>
		<td><input type="TEXT" name="ann_text" size="45" value="<%=announcementVO.getAnn_text()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ann_no" value="<%=announcementVO.getAnn_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
