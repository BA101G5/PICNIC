<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chatroom.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ChatroomService chatroomSvc = new ChatroomService();
	List<ChatroomVO> list = chatroomSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<%-- <jsp:useBean id="general_memberSvc" scope="page" class="com.general_member.model.GeneralMemberService" /> --%>

<html>
<head>
<title>所有房間資料 - listAllChatroom.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1200'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有房間資料 - ListAllChatroom.jsp</h3>
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

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>房間編號</th>
		<th>標題</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="chatroomVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(chatroomVO.chatroom_no==param.chatroom_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${chatroomVO.chatroom_no}</td>
			<td>${chatroomVO.chatroom_name}</td>
<%-- 			<td><c:forEach var="general_memberVO" items="${general_memberSvc.all}"> --%>
<%--                     <c:if test="${chatroomVO.general_memberno==general_memberVO.general_memberno}"> --%>
<%-- 	                    ${general_memberVO.general_memberno}【${general_memberVO.dname} - ${general_memberVO.loc}】 --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="chatroom.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="chatroom_no" value="${chatroomVO.chatroom_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="chatroom.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="chatroom_no" value="${chatroomVO.chatroom_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
