<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pboard_article.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
	List<Pboard_ArticleVO> list = pboard_articleSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="general_memberSvc" scope="page" class="com.general_member.model.GeneralMemberService" />

<html>
<head>
<title>�Ҧ��d����� - listAllPboard_Article.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1200'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ��d����� - ListAllPboard_Article.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1200'>
	<tr>
		<th>�d���s��</th>
		<th>���\�νs��</th>
		<th>���D</th>
		<th>����</th>
		<th>�o��ɶ�</th>
		<th>�s����</th>
		<th>kind</th>
		<th>�@��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="pboard_articleVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(pboard_articleVO.article_no==param.article_no) ? 'bgcolor=#CCCCFF':''}><!--�N�ק諸���@���[�J����Ӥw-->
			<td>${pboard_articleVO.article_no}</td>
			<td>${pboard_articleVO.picnic_no}</td>
			<td>${pboard_articleVO.article_title}</td>
			<td>${pboard_articleVO.article_text}</td>
			<td>${pboard_articleVO.article_post}</td>
			<td>${pboard_articleVO.article_views}</td>
			<td>${pboard_articleVO.article_kind}</td>
			<td>${pboard_articleVO.author_no}</td>		
<%-- 			<td><c:forEach var="general_memberVO" items="${general_memberSvc.all}"> --%>
<%--                     <c:if test="${pboard_articleVO.general_memberno==general_memberVO.general_memberno}"> --%>
<%-- 	                    ${general_memberVO.general_memberno}�i${general_memberVO.dname} - ${general_memberVO.loc}�j --%>
<%--                     </c:if> --%>
<%--                 </c:forEach> --%>
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="pboard_article.do">
			     <input type="submit" value="�ק�"> 
			     <input type="hidden" name="article_no" value="${pboard_articleVO.article_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="pboard_article.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="article_no" value="${pboard_articleVO.article_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
