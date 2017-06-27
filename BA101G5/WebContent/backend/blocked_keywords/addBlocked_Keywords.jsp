<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%
Blocked_KeywordsVO blocked_keywordsVO = (Blocked_KeywordsVO) request.getAttribute("blocked_keywordsVO");
%>

<html>
<head>
<title>����r��Ʒs�W - addBlocked_Keywords.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>����r��Ʒs�W - addBlocked_Keywords.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="100" border="1">�^����</a>
	    </td>
	</tr>
</table>

<h3>�������r:</h3>
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

<FORM METHOD="post" ACTION="blocked_keywords.do" name="form1">
<table border="0">

	<tr>
		<td>����r:</td>
		<td><input type="TEXT" name="keyword" size="45" 
			value="<%= (blocked_keywordsVO==null)? "����r" : blocked_keywordsVO.getKeyword()%>" /></td>
	</tr>
	<tr>
		<td>���N�r:</td>
		<td><input type="TEXT" name="replacement" size="45" 
			value="<%= (blocked_keywordsVO==null)? "���N�r" : blocked_keywordsVO.getReplacement()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>
