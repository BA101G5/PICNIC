<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.chatroom_members.model.*" %>
<%
Chatroom_MembersVO chatroom_membersVO = (Chatroom_MembersVO) request.getAttribute("chatroom_membersVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>��ѫǷ|����� - listOneEmp.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>��ѫǷ|����� - ListOneEmp.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>��ѫǽs��</th>
		<th>�|���s��</th>
		<th>��ѫǷ|������</th>
		<th>BAN���</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=chatroom_membersVO.getChatroom_no()%></td>
		<td><%=chatroom_membersVO.getMem_no()%></td>
		<td><%=chatroom_membersVO.getChatroom_role()%></td>
		<td><%=chatroom_membersVO.getBan_until()%></td>
	</tr>
</table>

</body>
</html>