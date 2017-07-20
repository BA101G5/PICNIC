<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.advertisement.model.*" %>
<%@ page import="java.util.*" %>
<% 
	AdvertisementService adSvc = new AdvertisementService();
	List<AdvertisementVO> list = adSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ��s�i - listAdvertisement.jsp</title>
</head>
				
<body>
				<table>
				<tr>
				<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
				<input type="submit" value="���ݽT�{">
				<input type="hidden" name="AD_STA" value="W">
				<input type="hidden" name="action" value="AD"></FORM></td>
				<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
				<input type="submit" value="�W�[��">
				<input type="hidden" name="AD_STA" value="O">
				<input type="hidden" name="action" value="AD"></FORM></td>
				<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
				<input type="submit" value="���ݤW�[">
				<input type="hidden" name="AD_STA" value="U">
				<input type="hidden" name="action" value="AD"></FORM></td>
				<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
				<input type="submit" value="�w�U�[">
				<input type="hidden" name="AD_STA" value="E">
				<input type="hidden" name="action" value="AD"></FORM></td>
				</tr>
				</table>
				
				
				 
				
				
				
				
				
	<table border='1' bordercolor='#CCCCFF'>
		<tr>
			<th>�s�i�s��</th>
			<th>�t�ӽs��</th>
			<th>�s�i���e</th>
			<th>�s�i�Ϥ�</th>
			<th>�}�l�ɶ�</th>
			<th>�����ɶ�</th>
			<th>�s�i���B</th>
			<th>���A</th>
		</tr>
<%-- 	<%@ include file="pages/page1.file" %> --%>
	<c:forEach var="advertisementVO" items="${list}">
	
		<tr>
		<td>${advertisementVO.AD_NO}</td>
		<td>${advertisementVO.MF_NO}</td>
		<td>${advertisementVO.AD_SELF}</td>
		<td align="center"><img src="<%=request.getContextPath()%>/advertisement/DBGift2.do?AD_NO=${advertisementVO.AD_NO}" style="max-width: 50px; max-height: 50px; cursor: pointer;"
		 onclick="window.open('<%=request.getContextPath()%>/advertisement/DBGift2.do?AD_NO=${advertisementVO.AD_NO}','addChecklist', config='height=500,width=500');"></td>
		<td>${advertisementVO.DAY_START}</td>
		<td>${advertisementVO.DAY_END}</td>
		<td>${advertisementVO.AD_CASH}</td>
		<td>${advertisementVO.AD_STA}</td>
		
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="AD_NO" value="${advertisementVO.AD_NO}">
			     <input type="hidden" name="AD_STA" value="${advertisementVO.AD_STA}">
			     <input type="hidden" name="action"	value="UpdateForSTA">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
			     </FORM>		
		
		</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="AD_NO" value="${advertisementVO.AD_NO}">
			    <input type="hidden" name="action" value="delete">
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
			    
			    </FORM>
		</td>
		
		</tr>
	</c:forEach>
	</table>
<%-- <%@ include file="pages/page2.file" %> --%>
</body>
</html>