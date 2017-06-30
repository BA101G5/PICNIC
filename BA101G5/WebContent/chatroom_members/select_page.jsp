<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Chatroom_Members: Home</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Chatroom_Members: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Chatroom_Members: Home</p>

<h3>資料查詢:</h3>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllChatroom_Members.jsp'>List</a> all Chatroom_Members. </li> <br><br>
  
  
    <FORM METHOD="post" ACTION="chatroom_members.do" >
    <li>
        <b>輸入聊天室編號 (如CR00000001):</b>
        <input type="text" name=chatroom_no></li><br>
    <li>
        <b>輸入會員編號 (如MG00000001):</b>
        <input type="text" name="mem_no"></li>
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  
  <jsp:useBean id="chatroom_membersSvc" scope="page" class="com.chatroom_members.model.Chatroom_MembersService" />
   
  <li>
     <FORM METHOD="post" ACTION="chatroom_members.do" >
       <b>選擇聊天室編號:</b>
       <select size="1" name="chatroom_no">
         <c:forEach var="chatroom_membersVO" items="${chatroom_membersSvc.allpk}" > 
          <option value="${chatroom_membersVO.chatroom_no}">${chatroom_membersVO.chatroom_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Crno">
    </FORM>
  </li>
  



<h3>聊天室會員管理</h3>

<ul>
  <li><a href='addChatroom_Members.jsp'>Add</a> a new Chatroom_Members.</li>
</ul>
  

</body>
</html>