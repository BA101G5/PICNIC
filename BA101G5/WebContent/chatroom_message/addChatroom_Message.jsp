<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chatroom_message.model.*"%>
<%
Chatroom_MessageVO chatroom_messageVO = (Chatroom_MessageVO) request.getAttribute("chatroom_messageVO");
%>

<html>
<head>
<title>員工資料新增 - addChatroom_Message.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>
$(function (){
	 
    function format_float(num, pos)
    {
        var size = Math.pow(10, pos);
        return Math.round(num * size) / size;
    }
 
    function preview(input) {
 
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('.preview').attr('src', e.target.result);
                var KB = format_float(e.total / 1024, 2);
                $('.size').text("檔案大小：" + KB + " KB");
            }
 
            reader.readAsDataURL(input.files[0]);
        }
    }
 
    $("body").on("change", ".upl", function (){
        preview(this);
    })
    
})
</script>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料新增 - addChatroom_Message.jsp</h3>
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

<FORM METHOD="post" ACTION="chatroom_message.do" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>聊天室訊息編號:</td>
		<td><input type="TEXT" name="cr_msg_no" size="45" 
			value="<%= (chatroom_messageVO==null)? "CM00000001" : chatroom_messageVO.getCr_msg_no()%>" /></td>
	</tr>
	<tr>
		<td>聊天室編號:</td>
		<td><input type="TEXT" name="chatroom_no" size="45" 
			value="<%= (chatroom_messageVO==null)? "CR00000001" : chatroom_messageVO.getChatroom_no()%>" /></td>
	</tr>
	<tr>
		<td>訊息發布者編號:</td>
		<td><input type="TEXT" name="mem_no" size="45" 
			value="<%= (chatroom_messageVO==null)? "MG00000001" : chatroom_messageVO.getMem_no()%>" /></td>
	</tr>
	<tr>
		<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
		<td>訊息發布時間:</td>
		<td bgcolor="#CCCCFF">
		    <input class="cal-TextBox"
			onFocus="this.blur()" size="9" readonly type="text" name="message_date" value="<%= (chatroom_messageVO==null)? date_SQL : chatroom_messageVO.getMessage_date()%>">
			<a class="so-BtnLink"
			href="javascript:calClick();return false;"
			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','message_date','BTN_date');return false;">
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>
		</td>
	</tr>
	<tr>
		<td>訊息內文:</td>
		<td><input type="TEXT" name="message_text" size="45"
			value="<%= (chatroom_messageVO==null)? "1" : chatroom_messageVO.getMessage_text()%>" /></td>
	</tr>
	<tr>
	<td>訊息圖片:</td>
		<td>
		<input type="file" name="message_img" class="upl"
			value="<%= (chatroom_messageVO==null)? null : chatroom_messageVO.getMessage_img()%>" />
		<img class="preview" style="max-width: 150px; max-height: 150px;">
        </td>
	</tr>
	



</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
