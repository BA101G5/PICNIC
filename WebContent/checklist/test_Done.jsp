<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.checklist.model.*" %>
<%@ page import="java.util.*" %>
<%List<ChecklistVO> checklistVO = (List<ChecklistVO>) request.getAttribute("checklistVO"); %>
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
	</style>
	<body>

		

		<div class="col-sm-10 col-sm-offset-1">
			<div class="row">
				<div class="col-xs-12 col-sm-10">
					<div  style="margin-top: 0px">
					 
					  <form class="navbar-form navbar-left" role="search" style="">
					  <div class="col-xs-12 col-sm-10">
					  <%if (request.getAttribute("testdone")=="0"){%>
					  <div style="float: left; font-size: 50px">�w�B�z�峹���|</div> 
					  <%}%>
					  <%if (request.getAttribute("testdone")=="1"){%>
					  <div style="float: left; font-size: 50px">�w�B�z�d�����|</div> 
					  <%}%>
					  <%if (request.getAttribute("testdone")=="2"){%>
					  <div style="float: left; font-size: 50px">�w�B�z�������|</div> 
					  <%}%>
					  <%if (request.getAttribute("testdone")=="3"){%>
					  <div style="float: left; font-size: 50px">�w�B�z�|�����|</div> 
					  <%}%>
					  <%if (request.getAttribute("testdone")=="4"){%>
					  <div style="float: left; font-size: 50px">�w�B�z�Ӯa���|</div> 
					  <%}%>
					</div>
						
						<div class="col-xs-12 col-sm-2">
						<div  style="float: right;margin-left:	 800px; padding-top: 30px">
							<%if (request.getAttribute("testdone")=="0"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="���B�z�峹���|">
				<input type="hidden" name="chli_cate" value="0">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="1"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="���B�z�d�����|">
				<input type="hidden" name="chli_cate" value="1">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="2"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="���B�z�������|">
				<input type="hidden" name="chli_cate" value="2">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="3"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="���B�z�|�����|">
				<input type="hidden" name="chli_cate" value="3">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
			<%if (request.getAttribute("testdone")=="4"){%>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
				<input type="submit" value="���B�z�Ӯa���|">
				<input type="hidden" name="chli_cate" value="4">
				<input type="hidden" name="action" value="test_undone"></FORM><%}%>
						</div>
					</div>	
					</form>
					</div>

					<table class="table table-hover">
						<thead>
							<tr>
								<th width="100px">���|��s��</th>
								<th width="100px">���|���O</th>
								<th width="100px">�Q���|��H</th>
								<th width="100px">���|�|��</th>
								<th width="120px">�g�@�}�l���</th>
								<th width="120px">�g�@�������</th>
								<th width="100px">�g�@�Ѽ�</th>
								<th width="100px">�g�B����</th>
								<th width="100px">���|�ƥ�</th>
								<th width="100px">���|���</th>
								<th width="100px">�B�z���A</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="checklistVO" items="${checklistVO}">
							<tr>
								<td>${checklistVO.chli_no}</td>
			
				<c:if test="${checklistVO.chli_cate eq 0}" var="0" scope="page"><td>�峹���|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 1}" var="0" scope="page"><td>�d�����|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 2}" var="0" scope="page"><td>�������|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 3}" var="0" scope="page"><td>�|�����|</td></c:if>
				<c:if test="${checklistVO.chli_cate eq 4}" var="0" scope="page"><td>�Ӯa���|</td></c:if>
				
				<td style="width:100px">${checklistVO.chli_be_num}</td>
				<td style="width:100px">${checklistVO.chli_memno}</td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_start}"/> </td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_end}"/></td>
				<td>${checklistVO.chli_day}</td>
				
				<c:if test="${checklistVO.chli_pun eq 0}" var="0" scope="page"><td>�T��}��</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 1}" var="0" scope="page"><td>�T��ѹ�</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 2}" var="0" scope="page"><td>�T��d��</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 3}" var="0" scope="page"><td>�T��o�峹</td></c:if>
				<c:if test="${checklistVO.chli_pun eq 4}" var="0" scope="page"><td>�ӫ~�U�[</td></c:if>
				
				<td>${checklistVO.chli_reason}</td>
				<td><fmt:formatDate  pattern="yyyy-MM-dd" value="${checklistVO.chli_date}"/></td>
				<td>${checklistVO.chli_sta}</td>
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