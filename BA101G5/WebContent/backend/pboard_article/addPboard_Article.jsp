<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pboard_article.model.*"%>
<%
Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO");
%>

<html>
<head>
<title>留言資料新增 - addPboard_Article.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料新增 - addPboard_Article.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料留言:</h3>
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

<FORM METHOD="post" ACTION="pboard_article.do" name="form1">
<table border="0">

	<tr>
		<td>標題:</td>
		<td><input type="TEXT" name="article_title" size="45" 
			value="<%= (pboard_articleVO==null)? "標題" : pboard_articleVO.getArticle_title()%>" /></td>
	</tr>
	<tr>
		<td>留言:</td>
		<td><input type="TEXT" name="article_text" size="45" 
			value="<%= (pboard_articleVO==null)? "留言" : pboard_articleVO.getArticle_text()%>" /></td>
	</tr>
	<tr>
		<td>作者:</td>
		<td><input type="TEXT" name="author_no" size="45" 
			value="<%= (pboard_articleVO==null)? "MG00000001" : pboard_articleVO.getAuthor_no()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
