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
<style>
td{
	height:45px
}
</style>
<body>

			<%if (request.getAttribute("testundone").equals("0")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="�w�B�z�峹���|">
				<input type="hidden" name="chli_cate" value="0">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("1")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="�w�B�z�d�����|">
				<input type="hidden" name="chli_cate" value="1">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("2")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="�w�B�z�������|">
				<input type="hidden" name="chli_cate" value="2">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("3")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="�w�B�z�|�����|">
				<input type="hidden" name="chli_cate" value="3">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("4")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="�w�B�z�Ӯa���|">
				<input type="hidden" name="chli_cate" value="4">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
<table border='1' bordercolor='#CCCCFF' align="center">
	<tr style="height:80px" align="center">
		<th>���|��s��</th>
		<th>���|���O</th>
		<th>�Q���|��H</th>
		<th>���|�|��</th>
<%--	 	<th>�g�@�}�l���</th>--%>
<%--		<th>�g�@�������</th> --%>
		<th>�g�@�Ѽ�</th>
		<th>�g�B����</th>
		<th>���|�ƥ�</th>
		<th>���|���</th>
		<th>�B�z���A</th>
		<th>����</th>
		<th>�R��</th>
	</tr>
	<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
	
	<c:forEach var="checklistVO" items="${checklistVO}" >	
			<tr align='center' valign='middle'>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">	
				<td>${checklistVO.chli_no}</td>		
				<c:if test="${checklistVO.chli_cate eq 0}" var="0" scope="page"><td>�峹���|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 1}" var="0" scope="page"><td>�d�����|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 2}" var="0" scope="page"><td>�������|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 3}" var="0" scope="page"><td>�|�����|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 4}" var="0" scope="page"><td>�Ӯa���|</td></c:if>
				
				<td>${checklistVO.chli_be_num}</td>
				<td>${checklistVO.chli_memno}</td>		
				<td style="display:none;"><input readOnly="true" name="chli_start"
			value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}"/>"/></td>

				<td><select name="chli_day">
						<option value="7">7</option>
						<option value="14">14</option>
						<option value="21">21</option>
						<option value="28">28</option>
					</select></td>
				<td><select name="chli_pun">
						<option value="0">�T��}��</option>
						<option value="1">�T��ѹ�</option>
						<option value="2">�T��d��</option>
						<option value="3">�T��o�峹</option>
						<option value="4">�ӫ~�U�[</option>
					</select></td>
				<td>${checklistVO.chli_reason}</td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_date}"/></td>
				<td>���ݳB�z</td>
				<td>	
						<input type="hidden" name="chli_no" value="${checklistVO.chli_no}">	
						<input type="hidden" name="chli_cate" value="${checklistVO.chli_cate}">	
						<input type="hidden" name="chli_be_num" value="${checklistVO.chli_be_num}">	
						<input type="hidden" name="action" value="update">
						<input type="submit" value="����">
				</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
						<input type="submit" value="�R��">
						<input type="hidden" name="chli_no" value="${checklistVO.chli_no}">
						<input type="hidden" name="chli_cate" value="${checklistVO.chli_cate}">	
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		
	</c:forEach>
</table>
</body>
</html>