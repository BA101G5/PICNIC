<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%
	Blocked_KeywordsVO blocked_keywordsVO = (Blocked_KeywordsVO) request.getAttribute("blocked_keywordsVO"); //Blocked_KeywordsServlet.java (Concroller), �s�Jreq��blocked_keywordsVO���� (�]�A�������X��blocked_keywordsVO, �]�]�A��J��ƿ��~�ɪ�blocked_keywordsVO����)
%>
<html>
<head>
<title>����r��ƭק� - update_blocked_keywords_input.jsp</title></head>
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

<FORM style="display:inline" METHOD="post" ACTION="blocked_keywords.do" name="form1">
<table border="0">
	<tr>
		<td>����r�s��:<font color=red><b>*</b></font></td>
		<td><%=blocked_keywordsVO.getKeyword_no()%></td>
	</tr>
	<tr>
		<td>����r:</td>
		<td><input type="TEXT" name="keyword" size="45" value="<%=blocked_keywordsVO.getKeyword()%>" /></td>
	</tr>
	<tr>
		<td>���N�r:</td>
		<td><input type="TEXT" name="replacement" size="45" value="<%=blocked_keywordsVO.getReplacement()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="keyword_no" value="<%=blocked_keywordsVO.getKeyword_no()%>">
<input  class="undone"  type="submit" value="�e�X�ק�"></FORM>
<FORM style="display:inline" METHOD="post" ACTION="<%=request.getContextPath()%>/backend/blocked_keywords/blocked_keywords.do" >
<input  class="undone"  type="submit" value="��^">
<input type="hidden" name="action" value="all_Blocked_Keywordss">
</FORM> 
</body>
</html>
