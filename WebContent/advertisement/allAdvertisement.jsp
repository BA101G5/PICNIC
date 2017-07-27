<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.advertisement.model.*" %>
<%@ page import="java.util.*" %>
<%--
 	AdvertisementService adSvc = new AdvertisementService();
 	List<AdvertisementVO> list = adSvc.getAll();
 	pageContext.setAttribute("list",list);
--%>
<%List<AdvertisementVO> advertisementVO = (List<AdvertisementVO>) request.getAttribute("advertisementVO"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�Ҧ��s�i - listAdvertisement.jsp</title>
</head>
<style>
<style>
	body{
			background-color: #eff9f9;
			}
			.undone {
  margin-left : 10px;
  margin-right : 10px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
  display: inline;
  background-color: #009ac9;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 100;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

.table5_3 table {
	width:100%;
	margin:15px 0;
	border:0;
}
.table5_3 th {
	background-color:#87CEFA;
	color:#000000
}
.table5_3,.table5_3 th,.table5_3 td {
	font-size:0.95em;
	text-align:center;
	padding:4px;
	border-collapse:collapse;
}
.table5_3 th,.table5_3 td {
	border: 1px solid #ffffff;
	border-width:1px 0 1px 0
}
.table5_3 tr {
	border: 1px solid #ffffff;
}
.table5_3 tr:nth-child(odd){
	background-color:#d7eefd;
}
.table5_3 tr:nth-child(even){
	background-color:#ffffff;
}
/* .table5_3 tr:last-child td:last-child{ */
/*   border-bottom-right-radius: 10px; */
/* } */
.table5_3 tr:first-child th:last-child{
  border-top-right-radius: 10px;
}
.table5_3 tr:last-child td:first-child{
  border-bottom-left-radius: 10px;
}
.table5_3 tr:first-child th:first-child{
  border-top-left-radius: 10px;
}
</style>		
<body>
<div class="col-xs-12 col-sm-10">
					 
					  <form class="navbar-form navbar-left" role="search" style="">
					  <div class="col-xs-12 col-sm-10">
					   <%if (request.getAttribute("result")=="OTHER"){%>
				 <div style="float: left; font-size: 50px">�ӽгq�L�s�i</div> 
				<%}%>
				 <%if ((request.getAttribute("result")=="all")){%>
				 <div style="float: left; font-size: 50px">�s�s�i�ӽ�</div> 
				<%}%>
					</div>
						
						<div class="col-xs-12 col-sm-2">
						<div  style="float: right;margin-left:	 600px; padding-top: 30px">
							<table>
				<tr>
				 <%if (request.getAttribute("result")=="OTHER"){%>
				<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
				<input class="undone" type="submit" value="���ݽT�{">
				<input type="hidden" name="AD_STA" value="W">
				<input type="hidden" name="action" value="AD"></FORM></td>
				<%}%>
				 <%if (request.getAttribute("result")=="all"){%>
				<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
				<input class="undone" type="submit" value="��e��/���ݩ�e�s�i">
				<input type="hidden" name="AD_STA" value="O">
				<input type="hidden" name="action" value="OTHER"></FORM></td>
				<%}%>
				</tr>
				</table>
						</div>
					</div>	
					</form>
					</div>
				
				
				
	<table class="table5_3" width=1050px>
						<thead>
							<tr height="50px">
								<th>�s�i�s��</th>
								<th>�t�ӽs��</th>
								<th>�s�i���e</th>
								<th>�s�i�Ϥ�</th>
								<th>�}�l�ɶ�</th>
								<th>�����ɶ�</th>
								<th>�s�i���B</th>
								 <%if (request.getAttribute("result")=="all"){%>
								<th>���A</th>
								<th>�T�{�W�[</th>
								<th>�R��</th>
								<%}%>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="advertisementVO" items="${advertisementVO}">
	
		<tr>
		<td>${advertisementVO.AD_NO}</td>
		<td>${advertisementVO.MF_NO}</td>
		<td>${advertisementVO.AD_SELF}</td>
		<td align="center"><img src="<%=request.getContextPath()%>/advertisement/DBGift2.do?AD_NO=${advertisementVO.AD_NO}" style="max-width: 50px; max-height: 50px; cursor: pointer;"
		 onclick="window.open('<%=request.getContextPath()%>/advertisement/DBGift2.do?AD_NO=${advertisementVO.AD_NO}','addChecklist', config='height=500,width=500');"></td>
		<td>${advertisementVO.DAY_START}</td>
		<td>${advertisementVO.DAY_END}</td>
		<td>${advertisementVO.AD_CASH}</td>
		 <%if (request.getAttribute("result")=="all"){%>
		<td>${advertisementVO.AD_STA}</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
			     <input class="undone" type="submit" value="�T�{">
			     <input type="hidden" name="AD_NO" value="${advertisementVO.AD_NO}">
			     <input type="hidden" name="AD_STA" value="${advertisementVO.AD_STA}">
			     <input type="hidden" name="action"	value="UpdateForSTA">
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
			     </FORM>		
		
		</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do">
			    <input class="undone" type="submit" value="�R��">
			    <input type="hidden" name="AD_NO" value="${advertisementVO.AD_NO}">
			    <input type="hidden" name="action" value="delete">
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller--> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller--> --%>
			    
			    </FORM>
		</td>
		<%}%>
		</tr>
	</c:forEach>
						</tbody>
					</table>
	</div>
		<div class="col-sm-1 "></div>
<%-- <%@ include file="pages/page2.file" %> --%>
</body>
</html>