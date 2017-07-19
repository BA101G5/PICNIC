<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<c:forEach var="pic" items="${picniclist}" varStatus="status">
		<tr>
			<td>${pic.picnic_no}</td>
			<td>${pic.picnic_name}</td>
			<td>${pic.picnic_desc}</td>
			<td>${pic.picnic_startup}</td>
			<td>${pic.picnic_setup}</td>
			<td>${pic.picnic_chk}</td>
			<td>${pic.picnic_date}</td>
			<td>${pic.picnic_pl}</td>
			<td>${pic.picnic_sta}</td>
			<td>${pic.ord_total}</td>
			<td>${pic.ord_date}</td>
			<td>${pic.ord_dm}</td>
			<td>${pic.ord_sta}</td>
			<td>
				<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/picmem.do" >
					<input type="submit" value="${allList[status.index]}">
		        	<input type="hidden" name="PICNIC_NO" value="${pic.picnic_no}">
		        	<input type="hidden" name="button" value="${allList[status.index]}">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>