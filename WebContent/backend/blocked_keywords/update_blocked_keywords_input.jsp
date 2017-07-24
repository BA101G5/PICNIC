<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%
	Blocked_KeywordsVO blocked_keywordsVO = (Blocked_KeywordsVO) request.getAttribute("blocked_keywordsVO"); //Blocked_KeywordsServlet.java (Concroller), 存入req的blocked_keywordsVO物件 (包括幫忙取出的blocked_keywordsVO, 也包括輸入資料錯誤時的blocked_keywordsVO物件)
%>
<html>
<head>
<title>關鍵字資料修改 - update_blocked_keywords_input.jsp</title></head>
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
<div style="float: left; font-size: 50px">資料修改:</div>
</div>
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

<FORM style="display:inline" METHOD="post" ACTION="blocked_keywords.do" name="form1">
<table border="0">
	<tr>
		<td>關鍵字編號:<font color=red><b>*</b></font></td>
		<td><%=blocked_keywordsVO.getKeyword_no()%></td>
	</tr>
	<tr>
		<td>關鍵字:</td>
		<td><input type="TEXT" name="keyword" size="45" value="<%=blocked_keywordsVO.getKeyword()%>" /></td>
	</tr>
	<tr>
		<td>取代字:</td>
		<td><input type="TEXT" name="replacement" size="45" value="<%=blocked_keywordsVO.getReplacement()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="keyword_no" value="<%=blocked_keywordsVO.getKeyword_no()%>">
<input  class="undone"  type="submit" value="送出修改"></FORM>
<FORM style="display:inline" METHOD="post" ACTION="<%=request.getContextPath()%>/backend/blocked_keywords/blocked_keywords.do" >
<input  class="undone"  type="submit" value="返回">
<input type="hidden" name="action" value="all_Blocked_Keywordss">
</FORM> 
</body>
</html>
