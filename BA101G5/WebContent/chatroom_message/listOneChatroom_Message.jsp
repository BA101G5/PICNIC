<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.chatroom_message.model.*" %>
<%
Chatroom_MessageVO chatroom_messageVO = (Chatroom_MessageVO) request.getAttribute("chatroom_messageVO"); //Chatroom_MessageServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>聊天室會員資料 - listOneChatroom_Message.jsp</title>
</head>
<body bgcolor='white'>

	<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>聊天室會員資料 - ListOneChatroom_Message.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>聊天訊息編號</th>
		<th>聊天室編號</th>
		<th>訊息發布者編號</th>
		<th>訊息發布時間</th>
		<th>訊息內文</th>
		<th>訊息圖片</th>
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