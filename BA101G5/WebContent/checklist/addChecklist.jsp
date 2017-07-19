<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.checklist.model.*"%>
<%
ChecklistVO checklistVO = (ChecklistVO) request.getAttribute("checklistVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�s�W���|�� - addChecklist.jsp</title>
</head>
<body>
<h3>���|���g:</h3>
<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
<script type="text/javascript">
    function closeWindow() {
        window.close();
    }
</script>
<FORM METHOD="post" ACTION="checklist.do" name="form1">
	<table border="1">
		<tr>
			<td>���|���O</td>
			<td>
				<select name="chli_cate">
					<option value="0">�峹���|</option>
					<option value="1">�d�����|</option>
					<option value="2">�������|</option>
					<option value="3">�|�����|</option>
					<option value="4">�Ӯa���|</option>
				</select></td>
		</tr>
		<tr>
			<td>�Q���|��H</td>
			<td><input type="text" name="chli_be_num"
			value="MG00000007"/></td>
		</tr>
		<tr>
			<td>���|�|��</td>
			<td><input type="text" name="chli_memno"
			value="MG00000001"/></td>
		</tr>
		<tr>
			<td>���|�ƥ�</td>
			<td><textarea cols="30" rows="5" name="chli_reason"></textarea></td>
		</tr>
		<tr>
			<td>���|���</td>
			<td><input  readOnly="true" name="chli_date"
			value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}"/>"/></td>
		</tr>
	</table>
<br>
<input type="hidden" name="action" value="insertChecklist"/>
<input type="submit" value="�e�X���|"/>

</FORM>
</body>
</html>
