<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.administrator.model.*"
		 import="com.purview_detail.model.*"
		 import="com.purview.model.*"
		 import = "java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>admView</title>
		<script src="${pageContext.request.contextPath}/view/js/sweetalert.min.js"></script>
		<link href="${pageContext.request.contextPath}/view/js/sweetalert.css" rel="stylesheet" type="text/css" />
<%-- 		<link href="${pageContext.request.contextPath}/view/js/admView.css" rel="stylesheet" type="text/css" /> --%>
	</head>
	<body>
	<div class="col-xs-12 col-sm-10">
					 
					  <form class="navbar-form navbar-left" role="search" style="">
					  <div class="col-xs-12 col-sm-10">
					 
					  <div style="float: left; font-size: 50px">員工管理</div> 
					 
					</div>
						
						<div class="col-xs-12 col-sm-2">
						<div  style="float: right;margin-left:	 600px; padding-top: 30px">
							
						</div>
					</div>	
					</form>
					
			<table id="administratorTable" class = "table5_3">
				<thead>
					<tr height="50px">
						<th>管理員編號</th>
						<th>管理員帳號</th>
						<th>管理員密碼</th>
						<th>管理員身份</th>
						<th>管理員姓名</th>
						<th>管理員狀態</th>	
					</tr>
				</thead>
      			<tbody class="adm">
					<form id="administrator" action="<%=request.getContextPath()%>/k_admini.do" method="post" name="del">
					<c:forEach var="adm" items="${admVO}">
						<tr class="colorCon" onclick="colorCon()">	
							<td><input class="radio" type="radio" name="Administrator" value="${adm.administrator}" id="${adm.administrator}">
								${adm.administrator}
							</td>
							<td>${adm.adm_acc}	</td>
							<td>${adm.adm_pw}	</td>
							<td>${adm.adm_iden} </td>
							<td>${adm.adm_name} </td>
							<td>${adm.adm_sta}	</td>
						</tr>	
					</c:forEach>	
					</form>
				</tbody>
		    </table>
		    <section>	
							<table  >
							<tr bgcolor="#FF0000">
								<th style="width:25%"><button type="submit" form="administrator" value="add" name="button">新增</button></th>
								<th style="width:25%"><button type="button" form="administrator" value="del" name="button" id="del" onclick="kkkk()">刪除</button></th>
								<th style="width:25%"><button type="submit" form="administrator" value="modify" name="button">修改</button>	</th>
								<th style="width:25%"><button type="submit" form="administrator" value="purview" name="button">權限</button>	</th>
							</tr>
							</table>
							</section>
		    <c:if test="${not empty add_adm}">
		    	<table class = "center">
		    	<thead>
					<tr>	
						<td>管理員帳號</td>
						<td>管理員密碼</td>
						<td>管理員身份</td>
						<td>管理員姓名</td>
					</tr>
				</thead>
				<tbody>		
					<form id="add_adm" action="<%=request.getContextPath()%>/k_admini.do" method="post" name="add_adm">
					<c:forEach var="addAdm" items="${add_adm}" varStatus="status">
						<tr>	
							<td>
								<section class="close"><button type="submit" form="add_adm" value="${status.index}addinin" name="button">✖</button></section>
								<input type="text" name="getAdm_acc" 	   value= "${addAdm.adm_acc}">
							</td>
							<td><input type="text" name="getAdm_pw" 	   value= "${addAdm.adm_pw}"></td>
							<td><input type="text" name="getAdm_iden" 	   value= "${addAdm.adm_iden}"></td>
							<td><input type="text" name="getAdm_name" 	   value= "${addAdm.adm_name}"></td>
						</tr>
					</c:forEach>
					</form>
				</tbody>
					<tr>
						<td colspan="6">
							<section>	
							<table style="border:none;">
							<tr>
								<th><button type="submit" form="add_adm" value="add" name="button">增加欄數</button></th>
								<th><button type="submit" form="add_adm" value="add_determine" name="button">確定</button></th>
								<th><button type="button" form="add_adm" value="all_cancel" name="button" id="button_add_cancel" onclick="add_buttonCancel()">取消</button>	</th>	
							</tr>
							</table>
							</section>
						</td>
					</tr>
		    	</table>
		    </c:if>
		    <c:if test="${not empty mod_adm}">
		    <table class = "center">
		    	<thead>
					<tr>
						<td>管理員編號</td>
						<td>管理員帳號</td>
						<td>管理員密碼</td>
						<td>管理員身份</td>
						<td>管理員姓名</td>
					</tr>
				</thead>
				<tbody>
					<form id="mod_adm_form" action="<%=request.getContextPath()%>/k_admini.do" method="post" name="mod_adm_form">
						<tr>	
							<td><input type="text" name="getAdministrator" value="${mod_adm.administrator}" disabled>
							  <input type="hidden" name="getAdministrator" value="${mod_adm.administrator}"></td>
							<td><input type="text" name="getAdm_acc" 	   value="${mod_adm.adm_acc}">		</td>
							<td><input type="text" name="getAdm_pw" 	   value="${mod_adm.adm_pw}">		</td>
							<td><input type="text" name="getAdm_iden" 	   value="${mod_adm.adm_iden}">		</td>
							<td><input type="text" name="getAdm_name" 	   value="${mod_adm.adm_name}">		</td>
						</tr>
					</form>
				</tbody>
					<tr>
						<td colspan="6">
							<section>	
								<button type="submit" form="mod_adm_form" value="mod_determine" name="button">確定</button>
								<button type="button" form="mod_adm_form" value="all_cancel" name="button" id="button_mod_cancel" onclick="mod_buttonCancel()">取消</button>		
							</section>
						</td>
					</tr>
		    </table>
		    </c:if>
		    <c:if test="${not empty pur_adm}">
			    <table id="pur_admTable" class = "center">
			    	<thead>
						<tr>
							<td>管理員編號</td>
							<td>管理員帳號</td>
							<td>管理員密碼</td>
							<td>管理員身份</td>
							<td>管理員姓名</td>
							<td>管理員狀態</td>	
						</tr>
						<tr>	
							<td>${pur_adm.administrator}</td>
							<td>${pur_adm.adm_acc}		</td>
							<td>${pur_adm.adm_pw}		</td>
							<td>${pur_adm.adm_iden}		</td>
							<td>${pur_adm.adm_name}		</td>
							<td>${pur_adm.adm_sta}		</td>
						</tr>
					</thead>
					<c:if test="${not empty pur_purDetail}">
						<tbody>
							<form id="pur_adm_form" action="<%=request.getContextPath()%>/k_admini.do" method="post" name="pur_adm_form">
									<tr>
										<td colspan="6">所有權限</td>	
									</tr>
							    	<tr>
							    		<c:forEach var="pur_purDetail" items="${pur_purDetail}" begin="0" end="2">
								    		<td>${pur_purDetail.purview_name}</td>
								    		<td><input type="checkbox" name="pur_no" value="${pur_purDetail.purview_no }" ${pur_purDetail.adm_no} id="toggle"></td>
							    		</c:forEach>
							    	</tr>
							    	<tr>
							    		<c:forEach var="pur_purDetail" items="${pur_purDetail}" begin="3" end="5">
								    		<td>${pur_purDetail.purview_name}</td>
								    		<td><input type="checkbox" name="pur_no" value="${pur_purDetail.purview_no }" ${pur_purDetail.adm_no} id="toggle"></td>
							    		</c:forEach>
							    	</tr>
							    	<tr>
							    		<c:forEach var="pur_purDetail" items="${pur_purDetail}" begin="6" end="8">
								    		<td>${pur_purDetail.purview_name}</td>
								    		<td><input type="checkbox" name="pur_no" value="${pur_purDetail.purview_no }" ${pur_purDetail.adm_no} id="toggle"></td>
							    		</c:forEach>
							    	</tr>
							    	<tr>
							    		<c:forEach var="pur_purDetail" items="${pur_purDetail}" begin="9" end="11" >
								    		<td>${pur_purDetail.purview_name}</td>
								    		<td><input type="checkbox" name="pur_no" value="${pur_purDetail.purview_no }" ${pur_purDetail.adm_no} id="toggle"></td>
							    		</c:forEach>
							    	</tr>
							    	<tr>
							    		<c:forEach var="pur_purDetail" items="${pur_purDetail}" begin="12" end="13" >
								    		<td>${pur_purDetail.purview_name}</td>
								    		<td><input type="checkbox" name="pur_no" value="${pur_purDetail.purview_no }" ${pur_purDetail.adm_no} id="toggle"></td>
							    		</c:forEach>
							    	</tr>
							    	<input type="hidden" name="Administrator" value="${pur_adm.administrator}">
							</form>
				    	</tbody>
				    	<tr>
				    		<td colspan="3">
				    			<section>	
									<button type="submit" form="pur_adm_form" value="pur_determine" name="button">確定</button>
								</section>
				    		</td>
				    		<td colspan="3">
					    		<section>	
									<button type="button" form="pur_adm_form" value="all_cancel" name="button" id="button_pur_cancel" onclick="pur_buttonCancel()">取消</button>		
								</section>
							</td>
				    	</tr>
			    	</c:if>
			    </table>
		    </c:if>
		    <c:if test="${not empty err}">
		    	<table>
		    		<tr>
		    			<td><div class="errWord">錯誤:${err}</div></td>
		    		</tr>
		    	</table>
		    </c:if>
		    </div>
		    <div class="col-sm-1 "></div>
		    
		    <script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>

<style type="text/css">
/* body { */
/* 	background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png); */
/* 	color: #444; */
/* 	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif; */
/* 	text-shadow: 0 1px 0 #fff; */
/* } */

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
/* 	background: #f5f5f5; */
	border-collapse: separate;
/* 	box-shadow: inset 0 1px 0 #fff; */
	font-size: 16px;
	line-height: 24px;
	margin: 10px auto;
	text-align: center;
	width: 1200px;
}	

thead{
	font-size: 18px;
	color:#0000AA;
}

td {
	border-right: 0px solid #fff;
	border-left: 0px solid #e8e8e8;
	border-top: 0px solid #fff;
	border-bottom: 0px solid #e8e8e8;
	padding: 1px 15px;
	position: relative;
/* 	transition: all 300ms; */
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
/* 	border-right: 1px solid #e8e8e8; */
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: url(https://jackrugile.com/images/misc/noise-diagonal.png);	
}

/* tbody.adm:hover td { */
/* 	color: transparent; */
/* 	text-shadow: 0 0 3px #aaa; */
/* } */

tbody.adm:hover tr:hover td {
	color: #444;
	text-shadow: 0 1px 0 #fff;
}

section button {
  margin-left : 50px;
  margin-right : 50px;
  font-size: 1.0rem;
  padding: 0.01rem 2.5rem;
  display: inline;
  background-color: #009ac9;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 300;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section button:hover {
  background-color: #ffffff;
  color: #009ac9;
  border-color: #009ac9;
}

section.close button {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.1rem 0.1rem;
  display: inline;
  background-color: #D0104C;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 300;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section.close button:hover {
  background-color: #ffffff;
  color: #D0104C;
  border-color: #D0104C;
}

section{
	display: inline
}

.radio{
	display:none;
}
	
input[type="text"]{
	text-align: center;
}


  	#toggle{
			width:150px;
			height:20px;
			border-radius:40px;
			position:absolute;
			-webkit-appearance: none;
			position:absolute;
			top:165%;
			left:60%;
			margin:-39px 0 0 -89px;
			box-shadow: inset 1px 1px 1px rgba(0, 0, 0, 0.4);
			background: -webkit-linear-gradient(#c6c6c6,#e3e3e3);
		}
		#toggle:checked{
			background: -webkit-linear-gradient(#bfe09c,#c8eca0);
			box-shadow: inset 1px 1px 3px rgba(0, 0, 0, 0.4);
		}
		#toggle:before {
			content:"";
			width:13px;
			height:13px;
			padding:2px;
			top:1.2px;
			left:9px;
			position:absolute;
			border-radius:40px;
			background: -webkit-linear-gradient(#ebebeb,#f1f1f1);
			box-shadow: -2px 2px 8px rgba(0, 0, 0, 0.2), 
						-1px 1px 2px rgba(0, 0, 0, 0.3), 
						inset 1px 1px 1px #ffffff;
			-webkit-transition: all 1s;
			
		}
		#toggle:checked:before {
			left:125px;
			background:#f1f1f1;
		}

.errWord{
	font-size:24px;
	color:#FF0000;
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
/* .table5_3 tr:last-child td:first-child{ */
/*   border-bottom-left-radius: 10px; */
/* } */
.table5_3 tr:first-child th:first-child{
  border-top-left-radius: 10px;
}
table{
 border: px solid #eff9f9;
}
</style>


<script type="text/javascript">

	function add_buttonCancel(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, cancel it!",
			  closeOnConfirm: false
		},
		function(){
				var button_cancel = document.getElementById("button_add_cancel")
				button_cancel.type = "submit";
				button_cancel.click(); 	
		});
	}
	
	function pur_buttonCancel(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, cancel it!",
			  closeOnConfirm: false
		},
		function(){
				var button_cancel = document.getElementById("button_pur_cancel")
				button_cancel.type = "submit";
				button_cancel.click(); 	
		});
	}
	
	function mod_buttonCancel(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, cancel it!",
			  closeOnConfirm: false
		},
		function(){
				var button_cancel = document.getElementById("button_mod_cancel")
				button_cancel.type = "submit";
				button_cancel.click(); 	
		});
	}
	
	function kkkk(){
		swal({
			  title: "Are you sure?",
			  text: "",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, delete it!",
			  closeOnConfirm: false
		},
		function(){
				var delButton = document.getElementById("del")
				delButton.type = "submit";
			  	delButton.click();  	
		});
	}

	function colorCon(e){
		
		
		   var obj = e? e.target : window.event.srcElement;
		   obj.parentNode.children[0].firstChild.checked="true"
		   var len = obj.parentNode.children.length;
		   
		   var objj = document.getElementsByClassName("colorCon");
		   var objjlen = objj.length;
		   var objjChLen = objj[0].children.length;
		
		   for(var i=0; i<objjlen; i++){
			   for(var j=0;j<objjChLen;j++){
				   objj[i].children[j].style.backgroundColor = "#f5f5f5";
			   }
		   }
		   for( var i=0; i<len; i++){
			   obj.parentNode.children[i].style.backgroundColor = "#E5E5E5";
			}	
		   
	}
</script>