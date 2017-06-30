<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chatroom_members.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
Chatroom_MembersService chatroom_membersSvc=new Chatroom_MembersService();
List<Chatroom_MembersVO> list=chatroom_membersSvc.getAll();
pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>�Ҧ���ѫǷ|����� - listAllChatroom_Members.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllChatroom_Members.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>
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

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>��ѫǽs��</th>
		<th>�|���s��</th>
		<th>��ѫǷ|������</th>
		<th>BAN���</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
<%@ include file="page1.file" %> 
	<c:forEach var="chatroom_membersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chatroom_membersVO.chatroom_no}</td>
			<td>${chatroom_membersVO.mem_no}</td>
			<td>${chatroom_membersVO.chatroom_role}</td>
			<td>${chatroom_membersVO.ban_until}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chatroom_members/chatroom_members.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="chatroom_no" value="${chatroom_membersVO.chatroom_no}">
			     <input type="hidden" name="mem_no" value="${chatroom_membersVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chatroom_members/chatroom_members.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="chatroom_no" value="${chatroom_membersVO.chatroom_no}">
			    <input type="hidden" name="mem_no" value="${chatroom_membersVO.mem_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>