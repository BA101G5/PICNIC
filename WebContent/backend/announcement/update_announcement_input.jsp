<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement.model.*"%>
<%
	AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO"); //AnnouncementServlet.java (Concroller), �s�Jreq��announcementVO���� (�]�A�������X��announcementVO, �]�]�A��J��ƿ��~�ɪ�announcementVO����)
%>
<html>
<head>
<title>�̷s������ƭק� - update_announcement_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<style>
.undone {
  margin-left : 10px;
  margin-right : 10px;
  font-size: 1.0rem;
  padding: 10px 20px;
  display: inline;
  background-color: #009ac9;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 100;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}
</style>
<body >

<div class="col-xs-12 col-sm-12">
<div style="float: left; font-size: 50px">��ƭק�:</div>
</div>
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

<FORM style="display:inline" METHOD="post" ACTION="announcement.do" name="form1">
<table border="0">
	<tr>
		<td>�̷s�����s��:<font color=red><b>*</b></font></td>
		<td><%=announcementVO.getAnn_no()%></td>
	</tr>
	<tr>
		<td>�̷s����:</td>
		<td><input type="TEXT" name="ann_text" size="45" value="<%=announcementVO.getAnn_text()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ann_no" value="<%=announcementVO.getAnn_no()%>">
<input  class="undone"  type="submit" value="�e�X�ק�"></FORM>
 <FORM style="display:inline" METHOD="post" ACTION="announcement.do" >
        <input class="undone" type="submit" value="��^">
        <input type="hidden" name="action" value="all_Announcements">
    </FORM>
</body>
</html>
