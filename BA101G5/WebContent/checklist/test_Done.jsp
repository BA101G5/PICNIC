<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.checklist.model.*" %>
<%@ page import="java.util.*" %>
<%List<ChecklistVO> checklistVO = (List<ChecklistVO>) request.getAttribute("checklistVO"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<%if (request.getAttribute("testdone")=="0"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="未處理文章檢舉">
				<input type="hidden" name="chli_cate" value="0">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="1"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="未處理留言檢舉">
				<input type="hidden" name="chli_cate" value="1">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="2"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="未處理揪團檢舉">
				<input type="hidden" name="chli_cate" value="2">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="3"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="未處理會員檢舉">
				<input type="hidden" name="chli_cate" value="3">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="4"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="未處理商家檢舉">
				<input type="hidden" name="chli_cate" value="4">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
<jsp:useBean id="checklistSvc" scope="page" class="com.checklist.model.ChecklistService" />
<table border='1' bordercolor='#CCCCFF'>
		<tr>
			<th>檢舉單編號</th>
			<th>檢舉類別</th>
			<th>被檢舉對象</th>
			<th>檢舉會員</th>
			<th>懲罰開始日期</th>
			<th>懲罰結束日期</th>
			<th>懲罰天數</th>
			<th>懲處類型</th>
			<th>檢舉事由</th>
			<th>檢舉日期</th>
			<th>處理狀態</th>
		</tr>
		<c:forEach var="checklistVO" items="${checklistVO}">
			<tr align='center' valign='middle'>
				<td>${checklistVO.chli_no}</td>
			
				<c:if test="${checklistVO.chli_cate eq 0}" var="0" scope="page"><td>文章檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 1}" var="0" scope="page"><td>留言檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 2}" var="0" scope="page"><td>揪團檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 3}" var="0" scope="page"><td>會員檢舉</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 4}" var="0" scope="page"><td>商家檢舉</td></c:if>
				
				<td>${checklistVO.chli_be_num}</td>
				<td>${checklistVO.chli_memno}</td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_start}"/> </td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_end}"/></td>
				<td>${checklistVO.chli_day}</td>
				
				<c:if test="${checklistVO.chli_pun eq 0}" var="0" scope="page"><td>禁止開團</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 1}" var="0" scope="page"><td>禁止參團</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 2}" var="0" scope="page"><td>禁止留言</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 3}" var="0" scope="page"><td>禁止發文章</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 4}" var="0" scope="page"><td>商品下架</td></c:if>
				
				<td>${checklistVO.chli_reason}</td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_date}"/></td>
				<td>${checklistVO.chli_sta}</td>
			</tr>
		
	</c:forEach>
</table>


</body>
</html>