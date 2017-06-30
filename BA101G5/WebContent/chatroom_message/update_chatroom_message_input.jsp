<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chatroom_message.model.*"%>
<%
	Chatroom_MessageVO chatroom_messageVO = (Chatroom_MessageVO) request.getAttribute("chatroom_messageVO"); //EmpServlet.java (Concroller), 存入req的chatroom_messageVO物件 (包括幫忙取出的chatroom_messageVO, 也包括輸入資料錯誤時的chatroom_messageVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_chatroom_message_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>聊天室訊息資料修改 - update_chatroom_message_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="chatroom_message.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>聊天室訊息編號:<font color=red><b>*</b></font></td>
		<td><%=chatroom_messageVO.getCr_msg_no()%></td>
	</tr>
	<tr>
		<td>聊天室編號:<font color=red><b>*</b></font></td>
		<td><%=chatroom_messageVO.getChatroom_no()%></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=chatroom_messageVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>訊息發布時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="message_date" value="<%= (chatroom_messageVO==null)? null : chatroom_messageVO.getMessage_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','message_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
		<tr>
		<td>訊息內文:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="message_text" size="45" value="<%=chatroom_messageVO.getMessage_text()%>" /></td>
	</tr>
	<tr>
	<td>訊息圖片:</td>
		<td><input type="file" name="message_img" value="<%= (chatroom_messageVO==null)? null : chatroom_messageVO.getMessage_img()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="cr_msg_no" value="<%=chatroom_messageVO.getCr_msg_no()%>">
<input type="hidden" name="chatroom_no" value="<%=chatroom_messageVO.getChatroom_no()%>">
<input type="hidden" name="mem_no" value="<%=chatroom_messageVO.getMem_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>
