<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pboard_article.model.*"%>
<%
	Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO"); //Pboard_ArticleServlet.java (Concroller), �s�Jreq��pboard_articleVO���� (�]�A�������X��pboard_articleVO, �]�]�A��J��ƿ��~�ɪ�pboard_articleVO����)
%>
<html>
<head>
<title>�d����ƭק� - update_pboard_article_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�d����ƭק� - update_pboard_article_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a></td>
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

<FORM METHOD="post" ACTION="pboard_article.do" name="form1">
<table border="0">
	<tr>
		<td>�d���s��:<font color=red><b>*</b></font></td>
		<td><%=pboard_articleVO.getArticle_no()%></td>
	</tr>
	<tr>
		<td>�d�����D:</td>
		<td><input type="TEXT" name="article_title" size="45" value="<%=pboard_articleVO.getArticle_title()%>" /></td>
	</tr>
	<tr>
		<td>�d������:</td>
		<td><input type="TEXT" name="article_text" size="45"	value="<%=pboard_articleVO.getArticle_text()%>" /></td>
	</tr>
	<tr>
		<td>�o��ɶ�:</td>
		<td bgcolor="#CCCCFF">
			<input type="TEXT" name="article_post" size="45"	value="<%=pboard_articleVO.getArticle_post()%>" />
<!-- 		    <input class="cal-TextBox" -->
<%-- 			onFocus="this.blur()" size="9" readonly type="text" name="article_post" value="<%=pboard_articleVO.getArticle_post()%>"> --%>
<!-- 			<a class="so-BtnLink" -->
<!-- 			href="javascript:calClick();return false;" -->
<!-- 			onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);" -->
<!-- 			onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);" -->
<!-- 			onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','article_post','BTN_date');return false;"> 
		    <img align="middle" border="0" name="BTN_date"	src="images/btn_date_up.gif" width="22" height="17" alt="�}�l���"></a>-->
		</td>
	</tr>
	<tr>
		<td>kind:</td>
		<td><input type="TEXT" name="article_kind" size="45"	value="<%=pboard_articleVO.getArticle_kind()%>" /></td>
	</tr>
	<tr>
		<td>�s����:</td>
		<td><input type="TEXT" name="article_views" size="45"	value="<%=pboard_articleVO.getArticle_views()%>" /></td>
	</tr>
	<tr>
		<td>�@��:</td>
		<td><input type="TEXT" name="author_no" size="45"	value="<%=pboard_articleVO.getAuthor_no()%>" /></td>
	</tr>
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>�@��:<font color=red><b>*</b></font></td> -->
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
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�Ω�:istAllPboard_Article.jsp �P �ƦX�d�� listPboard_Articles_ByCompositeQuery.jsp-->
<input type="submit" value="�e�X�ק�"></FORM>

<br>�e�X�ק諸�ӷ��������|:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (���d�ҥثe�Ω�:istAllPboard_Article.jsp �P �ƦX�d�� listPboard_Articles_ByCompositeQuery.jsp)</b>
</body>
</html>
