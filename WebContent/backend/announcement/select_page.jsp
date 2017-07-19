<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>BA101G5 Announcement: Home</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>BA101G5 Announcement: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for BA101G5 Announcement: Home</p>

<h3>資料查詢:</h3>
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

<ul>
  <li><a href='listAllAnnouncement.jsp'>List</a> all Announcements. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="announcement.do" >
        <b>輸入最新消息編號 (如AN00000001):</b>
        <input type="text" name="ann_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="announcementSvc" scope="page" class="com.announcement.model.AnnouncementService" />
   
  <li>
     <FORM METHOD="post" ACTION="announcement.do" >
       <b>選擇最新消息編號:</b>
       <select size="1" name="ann_no">
         <c:forEach var="announcementVO" items="${announcementSvc.all}" > 
          <option value="${announcementVO.ann_no}">${announcementVO.ann_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="announcement.do" >
       <b>選擇最新消息:</b>
       <select size="1" name="ann_no">
         <c:forEach var="announcementVO" items="${announcementSvc.all}" > 
          <option value="${announcementVO.ann_no}">${announcementVO.ann_text}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>最新消息管理</h3>

<ul>
  <li><a href='addAnnouncement.jsp'>Add</a> a new Announcement.</li>
</ul>

</body>

</html>
