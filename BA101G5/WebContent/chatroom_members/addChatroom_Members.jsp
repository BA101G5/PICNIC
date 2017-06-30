<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chatroom_members.model.*"%>
<%
Chatroom_MembersVO chatroom_membersVO = (Chatroom_MembersVO) request.getAttribute("chatroom_membersVO");
%>

<html>
<head>
<title>員工資料新增 - addChatroom_Members.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料新增 - addChatroom_Members.jsp</h3>
		</td>	
		<td>
		   <a href="select_page.jsp"><img src="images/tomcat.gif" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="chatroom_members.do" name="form1">
<table border="0">



	<tr>
		<td>聊天室編號:</td>
		<td><input type="TEXT" name="chatroom_no" size="45" 
			value="<%= (chatroom_membersVO==null)? "CR00000002" : chatroom_membersVO.getChatroom_no()%>" /></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (chatroom_membersVO==null)? "MG00000006" : chatroom_membersVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<td>聊天室會員身分:</td>
		<td><input type="TEXT" name="chatroom_role" size="45"
			value="<%= (chatroom_membersVO==null)? "1" : chatroom_membersVO.getChatroom_role()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
		<td>BAN日期:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="ban_until" value="<%= (chatroom_membersVO==null)? null : chatroom_membersVO.getBan_until()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','ban_until','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	




</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
