<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.chatroom_message.model.*" %>
<%
Chatroom_MessageVO chatroom_messageVO = (Chatroom_MessageVO) request.getAttribute("chatroom_messageVO"); //Chatroom_MessageServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>��ѫǷ|����� - listOneChatroom_Message.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>��ѫǷ|����� - ListOneChatroom_Message.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>��ѰT���s��</th>
		<th>��ѫǽs��</th>
		<th>�T���o���̽s��</th>
		<th>�T���o���ɶ�</th>
		<th>�T������</th>
		<th>�T���Ϥ�</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=chatroom_messageVO.getCr_msg_no()%></td>
		<td><%=chatroom_messageVO.getChatroom_no()%></td>
		<td><%=chatroom_messageVO.getMem_no()%></td>
		<td><%=chatroom_messageVO.getMessage_date()%></td>
		<td><%=chatroom_messageVO.getMessage_text()%></td>
		<td><img src="<%=request.getContextPath()%>/chatroom_message/DBGifReader.do?cr_msg_no=${chatroom_messageVO.cr_msg_no}"></img></td>
	</tr>
</table>

</body>
</html>