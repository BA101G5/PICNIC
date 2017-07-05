<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pboard_article.model.*"%>
<%
	Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO"); //Pboard_ArticleServlet.java (Concroller), 存入req的pboard_articleVO物件 (包括幫忙取出的pboard_articleVO, 也包括輸入資料錯誤時的pboard_articleVO物件)
%>
<html>
<head>
<title>留言資料修改 - update_pboard_article_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>留言資料修改 - update_pboard_article_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="pboard_article.do" name="form1">
<table border="0">
	<tr>
		<td>留言編號:<font color=red><b>*</b></font></td>
		<td><%=pboard_articleVO.getArticle_no()%></td>
	</tr>
	<tr>
		<td>留言標題:</td>
		<td><input type="TEXT" name="article_title" size="45" value="<%=pboard_articleVO.getArticle_title()%>" /></td>
	</tr>
	<tr>
		<td>留言內文:</td>
		<td><input type="TEXT" name="article_text" size="45"	value="<%=pboard_articleVO.getArticle_text()%>" /></td>
	</tr>
	<tr>
		<td>發表時間:</td>
		<td bgcolor="#CCCCFF">
			<input type="TEXT" name="article_post" size="45"	value="<%=pboard_articleVO.getArticle_post()%>" />
<!-- 		    <input class="cal-TextBox" -->
<%-- 			onFocus="this.blur()" size="9" readonly type="text" name="article_post" value="<%=pboard_articleVO.getArticle_post()%>"> --%>
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','article_post','BTN_date');return false;"> 
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="開始日期"></a>-->
		</td>
	</tr>
	<tr>
		<td>kind:</td>
		<td><input type="TEXT" name="article_kind" size="45"	value="<%=pboard_articleVO.getArticle_kind()%>" /></td>
	</tr>
	<tr>
		<td>瀏覽數:</td>
		<td><input type="TEXT" name="article_views" size="45"	value="<%=pboard_articleVO.getArticle_views()%>" /></td>
	</tr>
	<tr>
		<td>作者:</td>
		<td><input type="TEXT" name="author_no" size="45"	value="<%=pboard_articleVO.getAuthor_no()%>" /></td>
	</tr>
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>作者:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(pboard_articleVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="article_no" value="<%=pboard_articleVO.getArticle_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllPboard_Article.jsp 與 複合查詢 listPboard_Articles_ByCompositeQuery.jsp-->
<input type="submit" value="送出修改"></FORM>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (此範例目前用於:istAllPboard_Article.jsp 與 複合查詢 listPboard_Articles_ByCompositeQuery.jsp)</b>
</body>
</html>
