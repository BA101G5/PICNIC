<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%
AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO");
%>

<html>
<head>
<title>�̷s������Ʒs�W - addAnnouncement.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�̷s������Ʒs�W - addAnnouncement.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/back1.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>��Ƴ̷s����:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
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
		<td>�̷s����:</td>
		<td><input type="TEXT" name="ann_text" size="45" 
			value="<%= (announcementVO==null)? "�̷s����" : announcementVO.getAnn_text()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>
