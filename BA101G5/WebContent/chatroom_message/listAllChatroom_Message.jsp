<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.chatroom_message.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
Chatroom_MessageService chatroom_messageSvc=new Chatroom_MessageService();
List<Chatroom_MessageVO> list=chatroom_messageSvc.getAll();
pageContext.setAttribute("list",list);
%>
<html>
<head>
<title>�Ҧ���ѫǷ|����� - listAllChatroom_Message.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllChatroom_Message.jsp</h3>
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
		<th>��ѰT���s��</th>
		<th>��ѫǽs��</th>
		<th>�T���o���̽s��</th>
		<th>�T���o���ɶ�</th>
		<th>�T������</th>
		<th>�T���Ϥ�</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
<%@ include file="page1.file" %> 
	<c:forEach var="chatroom_messageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${chatroom_messageVO.cr_msg_no}</td>
			<td>${chatroom_messageVO.chatroom_no}</td>
			<td>${chatroom_messageVO.mem_no}</td>
			<td>${chatroom_messageVO.message_date}</td>
			<td>${chatroom_messageVO.message_text}</td>
			<td><img src="<%=request.getContextPath()%>/chatroom_message/DBGifReader.do?cr_msg_no=${chatroom_messageVO.cr_msg_no}" style=max-width:150px;max-height:150px;></img></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chatroom_message/chatroom_message.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="cr_msg_no" value="${chatroom_messageVO.cr_msg_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/chatroom_message/chatroom_message.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="cr_msg_no" value="${chatroom_messageVO.cr_msg_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%@ include file="page2.file" %>
</body>
</html>