<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chatroom_members.model.*" %>
<%@ page import="java.util.*" %>

<%
Chatroom_MembersService chatroom_membersSvc=new Chatroom_MembersService();
List<Chatroom_MembersVO> list=chatroom_membersSvc.getCrm(request.getParameter("chatroom_no"));
pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>聊天室會員資料 - listOneChatroom.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllChatroom.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>聊天室編號</th>
		<th>會員編號</th>
		<th>聊天室會員身分</th>
		<th>BAN日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
<%@ include file="page3.file" %> 
	<c:forEach var="chatroom_membersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chatroom_membersVO.chatroom_no}</td>
			<td>${chatroom_membersVO.mem_no}</td>
			<td>${chatroom_membersVO.chatroom_role}</td>
			<td>${chatroom_membersVO.ban_until}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chatroom_members/chatroom_members.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="chatroom_no" value="${chatroom_membersVO.chatroom_no}">
			     <input type="hidden" name="mem_no" value="${chatroom_membersVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chatroom_members/chatroom_members.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="chatroom_no" value="${chatroom_membersVO.chatroom_no}">
			    <input type="hidden" name="mem_no" value="${chatroom_membersVO.mem_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page4.file" %>

</body>
</html>