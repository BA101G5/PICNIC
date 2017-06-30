<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chatroom_message.model.*"%>
<%
	Chatroom_MessageVO chatroom_messageVO = (Chatroom_MessageVO) request.getAttribute("chatroom_messageVO"); //EmpServlet.java (Concroller), �s�Jreq��chatroom_messageVO���� (�]�A�������X��chatroom_messageVO, �]�]�A��J��ƿ��~�ɪ�chatroom_messageVO����)
%>
<html>
<head>
<title>���u��ƭק� - update_chatroom_message_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>��ѫǰT����ƭק� - update_chatroom_message_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></td>
	</tr>
</table>

<h3>��ƭק�:</h3>
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

<FORM METHOD="post" ACTION="chatroom_message.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>��ѫǰT���s��:<font color=red><b>*</b></font></td>
		<td><%=chatroom_messageVO.getCr_msg_no()%></td>
	</tr>
	<tr>
		<td>��ѫǽs��:<font color=red><b>*</b></font></td>
		<td><%=chatroom_messageVO.getChatroom_no()%></td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><%=chatroom_messageVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>�T���o���ɶ�:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="message_date" value="<%= (chatroom_messageVO==null)? null : chatroom_messageVO.getMessage_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','message_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>
		</td>
	</tr>
		<tr>
		<td>�T������:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="message_text" size="45" value="<%=chatroom_messageVO.getMessage_text()%>" /></td>
	</tr>
	<tr>
	<td>�T���Ϥ�:</td>
		<td><input type="file" name="message_img" value="<%= (chatroom_messageVO==null)? null : chatroom_messageVO.getMessage_img()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="cr_msg_no" value="<%=chatroom_messageVO.getCr_msg_no()%>">
<input type="hidden" name="chatroom_no" value="<%=chatroom_messageVO.getChatroom_no()%>">
<input type="hidden" name="mem_no" value="<%=chatroom_messageVO.getMem_no()%>">
<input type="submit" value="�e�X�ק�"></FORM>

</body>
</html>
