<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.checklist.model.*" %>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%ChecklistVO checklistVO = (ChecklistVO) request.getAttribute("checklistVO"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>檢舉單資料 - listOneEmp.jsp</title>
</head>
<body>
<table border='1' bordercolor='#CCCCFF' width='800'>
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
		<c:if test="${checklistVO.chli_sta eq null }" var="test" scope="page" >
			<th>封鎖</th>
			<th>刪除</th>
		<</c:if>
	</tr>
	<tr>
		<td><%=checklistVO.getChli_no()%></td>
		<td><%=checklistVO.getChli_cate()%></td>
		<td><%=checklistVO.getChli_be_num()%></td>
		<td><%=checklistVO.getChli_memno()%></td>
		<td><%=checklistVO.getChli_start()%></td>
		<td><%=checklistVO.getChli_end()%></td>
		<td><%=checklistVO.getChli_day()%></td>
		<td><%=checklistVO.getChli_pun()%></td>
		<td><%=checklistVO.getChli_reason()%></td>
		<td><%=checklistVO.getChli_date()%></td>
		<td><%=checklistVO.getChli_sta()%></td>
	</tr>
</table>

</body>
</html>