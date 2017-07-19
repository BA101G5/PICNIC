<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.checklist.model.*" %>
<%-- �����m�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller EmpServlet.java�w�s�Jrequest��EmpVO����--%>
<%ChecklistVO checklistVO = (ChecklistVO) request.getAttribute("checklistVO"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>���|���� - listOneEmp.jsp</title>
</head>
<body>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>���|��s��</th>
		<th>���|���O</th>
		<th>�Q���|��H</th>
		<th>���|�|��</th>
		<th>�g�@�}�l���</th>
		<th>�g�@�������</th>
		<th>�g�@�Ѽ�</th>
		<th>�g�B����</th>
		<th>���|�ƥ�</th>
		<th>���|���</th>
		<th>�B�z���A</th>
		<c:if test="${checklistVO.chli_sta eq null }" var="test" scope="page" >
			<th>����</th>
			<th>�R��</th>
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