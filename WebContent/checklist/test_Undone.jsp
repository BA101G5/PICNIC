<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.checklist.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.picnic.model.*"%>
<%@ page import="java.util.*"%>
<%List<ChecklistVO> checklistVO = (List<ChecklistVO>) request.getAttribute("checklistVO"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	
	</head>
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
	width:1200;
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
					  <%if (request.getAttribute("testundone").equals("0")){%>
					  <div style="float: left; font-size: 50px">���B�z�峹���|</div> 
					  <%}%>
					  <%if (request.getAttribute("testundone").equals("1")){%>
					  <div style="float: left; font-size: 50px">���B�z�d�����|</div> 
					  <%}%>
					  <%if (request.getAttribute("testundone").equals("2")){%>
					  <div style="float: left; font-size: 50px">���B�z�������|</div> 
					  <%}%>
					  <%if (request.getAttribute("testundone").equals("3")){%>
					  <div style="float: left; font-size: 50px">���B�z�|�����|</div> 
					  <%}%>
					  <%if (request.getAttribute("testundone").equals("4")){%>
					  <div style="float: left; font-size: 50px">���B�z�Ӯa���|</div> 
					  <%}%>
					</div>
						
						<div class="col-xs-12 col-sm-2">
						<div  style="float: right;margin-left: 600px; padding-top: 30px">
							<%if (request.getAttribute("testundone").equals("0")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input class="undone" type="submit" value="�w�B�z�峹���|">
				<input type="hidden" name="chli_cate" value="0">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("1")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input class="undone" type="submit" value="�w�B�z�d�����|">
				<input type="hidden" name="chli_cate" value="1">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("2")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input class="undone" type="submit" value="�w�B�z�������|">
				<input type="hidden" name="chli_cate" value="2">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("3")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input class="undone" type="submit" value="�w�B�z�|�����|">
				<input type="hidden" name="chli_cate" value="3">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
			<%if (request.getAttribute("testundone").equals("4")){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input class="undone" type="submit" value="�w�B�z�Ӯa���|">
				<input type="hidden" name="chli_cate" value="4">
				<input type="hidden" name="action" value="test_done"></FORM><%}%>
						</div>
						</div>
					</form>
					

					<table class="table5_3">
						<thead>
							<tr height="50px">
								<th width="100px">���|��s��</th>
								<th width="120px">���|���O</th>
								<th width="100px">�Q���|��H</th>
								<th width="100px">���|�|��</th>
								<th width="100px">�g�@�Ѽ�</th>
								<th width="100px">�g�B����</th>
								<th width="180px">���|�ƥ�</th>
								<th width="100px">���|���</th>
								<th width="100px">�B�z���A</th>
								<th width="100px">����</th>
								<th width="100px">�R��</th>
							</tr>
						</thead>
						
						<jsp:useBean id="now" scope="page" class="java.util.Date" /> 
						<tbody>
						<c:forEach var="checklistVO" items="${checklistVO}" >	
							<tr height="40px">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">	
								<td>${checklistVO.chli_no}</td>
								<c:if test="${checklistVO.chli_cate eq 0}" var="0" scope="page"><td>�峹���|</td></c:if>
								<c:if test="${checklistVO.chli_cate eq 1}" var="0" scope="page"><td>�d�����|</td></c:if>
								<c:if test="${checklistVO.chli_cate eq 2}" var="0" scope="page"><td>�������|</td></c:if>
								<c:if test="${checklistVO.chli_cate eq 3}" var="0" scope="page"><td>�|�����|</td></c:if>
								<c:if test="${checklistVO.chli_cate eq 4}" var="0" scope="page"><td>�Ӯa���|</td></c:if>
				<jsp:useBean id="picinSvc" scope="page" class="com.picnic.model.PicnicService"/>
				
							
								 <c:if test="${testundone eq 2}">
					 			 <td>${picinSvc.getByPicnic_No(checklistVO.chli_be_num).picnic_name}</td> 
								  </c:if>
								  <%if (!((String)request.getAttribute("testundone")).equals("2")){%>
					 			 <td>${checklistVO.chli_be_num}</td> 
								  <%}%>
								
								
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
								<td width="100px">${checklistVO.chli_reason}</td>
								<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_date}"/></td>
								<td>���ݳB�z</td>
								<td>	
								
						<input type="hidden" name="chli_no" value="${checklistVO.chli_no}">	
						<input type="hidden" name="chli_cate" value="${checklistVO.chli_cate}">	
						<input type="hidden" name="chli_be_num" value="${checklistVO.chli_be_num}">	
						<input type="hidden" name="undone" value="<%=request.getAttribute("testundone")%>">	
						<input type="hidden" name="action" value="update">
						<input class="undone" type="submit" value="����">
				</FORM>
				</td>
								<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
						<input class="undone" type="submit" value="�R��">
						<input type="hidden" name="chli_no" value="${checklistVO.chli_no}">
						<input type="hidden" name="chli_cate" value="${checklistVO.chli_cate}">	
						<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						<input type="hidden" name="undone" value="<%=request.getAttribute("testundone")%>">	
						<input class="undone" type="hidden" name="action" value="delete">
					</FORM>
				</td>
							</tr>
						</c:forEach>
						</tbody>	
					</table>
					
				</div>
			</div>
		</div>
		<div class="col-sm-1 "></div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>