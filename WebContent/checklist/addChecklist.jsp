<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.checklist.model.*"%>
<%@ page import="com.general_member.model.*"%>
<%
ChecklistVO checklistVO = (ChecklistVO) request.getAttribute("checklistVO");
%>

<%
	String chli_be_num = request.getParameter("chli_be_num");
	pageContext.setAttribute("chli_be_num", chli_be_num);
	
	String chli_cate = request.getParameter("chli_cate");
	pageContext.setAttribute("chli_cate", chli_cate);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增檢舉單 - addChecklist.jsp</title>
</head>
<body>
<h3>檢舉單填寫:</h3>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
<script type="text/javascript">
    function closeWindow() {
        window.close();
    }
</script>
<FORM METHOD="post" ACTION="checklist.do" name="form1">
	<table border="1">
		<tr style="display:none;">
			<td>檢舉類別</td>
			<td><input type="text" name="chli_cate"
			value="${chli_cate}"/></td>
<!-- 			<td> -->
<!-- 				<select name="chli_cate"> -->
<!-- 					<option value="0">文章檢舉</option> -->
<!-- 					<option value="1">留言檢舉</option> -->
<!-- 					<option value="2">揪團檢舉</option> -->
<!-- 					<option value="3">會員檢舉</option> -->
<!-- 					<option value="4">商家檢舉</option> -->
<!-- 				</select></td> -->
		</tr>
		<tr style="display:none;">
			<td>被檢舉對象</td>
			<td><input type="text" name="chli_be_num"
			value="${chli_be_num}"/></td>
		</tr>
		<tr style="display:none;">
			<td>檢舉會員</td>
			<td><input type="text" name="chli_memno"
			value="${sessionScope.gVO.getMEM_NO()}"/></td>
		</tr>
		<tr>
			<td>檢舉事由</td>
			<td><textarea cols="30" rows="5" name="chli_reason" ></textarea></td>
		</tr>
		<tr>
			<td>檢舉日期</td>
			<td><input  readOnly="true" name="chli_date"
			value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}"/>"/></td>
		</tr>
	</table>
<br>
<input type="hidden" name="action" value="insertChecklist"/>
<input type="submit" value="送出檢舉"/>

</FORM>
</body>
</html>
