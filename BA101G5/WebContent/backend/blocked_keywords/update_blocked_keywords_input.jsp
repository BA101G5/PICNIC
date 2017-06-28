<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%
	Blocked_KeywordsVO blocked_keywordsVO = (Blocked_KeywordsVO) request.getAttribute("blocked_keywordsVO"); //Blocked_KeywordsServlet.java (Concroller), �s�Jreq��blocked_keywordsVO���� (�]�A�������X��blocked_keywordsVO, �]�]�A��J��ƿ��~�ɪ�blocked_keywordsVO����)
%>
<html>
<head>
<title>����r��ƭק� - update_blocked_keywords_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>����r��ƭק� - update_blocked_keywords_input.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
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
		<td>����r�s��:<font color=red><b>*</b></font></td>
		<td><%=blocked_keywordsVO.getKeyword_no()%></td>
	</tr>
	<tr>
		<td>����r:</td>
		<td><input type="TEXT" name="keyword" size="45" value="<%=blocked_keywordsVO.getKeyword()%>" /></td>
	</tr>
	<tr>
		<td>���N�r:</td>
		<td><input type="TEXT" name="replacement" size="45" value="<%=blocked_keywordsVO.getReplacement()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="keyword_no" value="<%=blocked_keywordsVO.getKeyword_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
