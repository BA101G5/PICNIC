<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.checklist.model.*" %>
<%@ page import="java.util.*" %>
<%
	ChecklistService checklistSvc = new ChecklistService();
	List<ChecklistVO> list = checklistSvc.getAllUndone();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有檢舉 - listAllChecklist.jsp</title>
</head>
<body>
<b><font>照0401寫法</font></b>
<table border='1'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
			<h3>所有檢舉 - ListAllChecklist.jsp</h3>
			<a href="<%=request.getContextPath()%>/checklist/select_page.jsp">回SelectPage</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF'>

	<tr>
		<th>檢舉單編號</th>
		<th>檢舉類別</th>
		<th>被檢舉對象</th>
		<th>檢舉會員</th>
	 	<th>懲罰開始日期</th>
<%--		<th>懲罰結束日期</th> --%>
		<th>懲罰天數</th>
		<th>懲處類型</th>
		<th>檢舉事由</th>
		<th>檢舉日期</th>
		<th>處理狀態</th>
		<th>封鎖</th>
		<th>刪除</th>
	</tr>
	<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
	<%@ include file="pages/page1.file" %>
	<c:forEach var="checklistVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
			<tr align='center' valign='middle'>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">	
				<td>${checklistVO.chli_no}</td>
				
				<c:if test="${checklistVO.chli_cate eq 0}" var="0" scope="page"><td>文章檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 1}" var="0" scope="page"><td>留言檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 2}" var="0" scope="page"><td>揪團檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 3}" var="0" scope="page"><td>會員檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 4}" var="0" scope="page"><td>商家檢舉</td></c:if>
				
				<td>${checklistVO.chli_be_num}</td>
				<td>${checklistVO.chli_memno}</td>		
				<td><input  readOnly="true" name="chli_start"
			value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}"/>"/></td>
<%-- 				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_end}"/></td> --%>
				<td><select name="chli_day">
						<option value="7">7</option>
						<option value="14">14</option>
						<option value="21">21</option>
						<option value="28">28</option>
					</select></td>
				<td><select name="chli_pun">
						<option value="0">禁止開團</option>
						<option value="1">禁止參團</option>
						<option value="2">禁止留言</option>
						<option value="3">禁止發文章</option>
						<option value="4">商品下架</option>
					</select></td>
				<td>${checklistVO.chli_reason}</td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_date}"/></td>
				<td>等待處理</td>
				<td>	
						<input type="hidden" name="chli_no" value="${checklistVO.chli_no}">		
						<input type="hidden" name="action" value="update">
						<input type="submit" value="封鎖">
				</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
						<input type="submit" value="刪除">
						<input type="hidden" name="chli_no" value="${checklistVO.chli_no}">
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		
	</c:forEach>
</table>

<%@ include file="pages/page2.file" %>

</body>
</html>