<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
    List<Blocked_KeywordsVO> list = blocked_keywordsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有關鍵字資料 - listAllBlocked_Keywords.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有關鍵字資料 - ListAllBlocked_Keywords.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
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
		<th>關鍵字</th>
		<th>取代字</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="blocked_keywordsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${blocked_keywordsVO.keyword_no}</td>
			<td>${blocked_keywordsVO.keyword}</td>
			<td>${blocked_keywordsVO.replacement}</td>
			<td>
			  <FORM METHOD="post" ACTION="blocked_keywords.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="keyword_no" value="${blocked_keywordsVO.keyword_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="blocked_keywords.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="keyword_no" value="${blocked_keywordsVO.keyword_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
