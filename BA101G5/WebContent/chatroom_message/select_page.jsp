<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Chatroom_Message: Home</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Chatroom_Message: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Chatroom_Message: Home</p>

<h3>��Ƭd��:</h3>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllChatroom_Message.jsp'>List</a> all Chatroom_Message. </li> <br><br>
  
  
    <FORM METHOD="post" ACTION="chatroom_message.do" >
    <li>
        <b>��J��ѰT���s�� (�pCM00000001):</b>
        <input type="text" name=cr_msg_no>
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </li>
    </FORM>
  
  <jsp:useBean id="chatroom_messageSvc" scope="page" class="com.chatroom_message.model.Chatroom_MessageService" />
   
  <li>
     <FORM METHOD="post" ACTION="chatroom_message.do" >
       <b>��ܲ�ѫǰT���s��:</b>
       <select size="1" name="cr_msg_no">
         <c:forEach var="chatroom_messageVO" items="${chatroom_messageSvc.all}" > 
          <option value="${chatroom_messageVO.cr_msg_no}">${chatroom_messageVO.cr_msg_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
</ul>


<h3>��ѫǰT���޲z</h3>

<ul>
  <li><a href='addChatroom_Message.jsp'>Add</a> a new Chatroom_Message.</li>
</ul>
  

</body>
</html>