<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.login.model.*"%>

<%LogInVO loginVO = (LogInVO) session.getAttribute("loginVO");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>���Ӭ���x�޲z�t��</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style>
			body{
				background-color: #eff9f9;
			}
			img.navbar-brand{
			border-radius: 25%;
		}
		nav a {
			color: #1d2084;
		}
		div.panel a {
			text-align: center;
		}
		.table i {
			margin: 0px 6px;
		}
/*		.body > nav{
			border-bottom: 3px blue solid !important;
		}*/
		.button{
			height:41px;
			width:100%;
			background-color:#FFFFFF;
			text-align:center;
			border: 1px solid #DDDDDD ;
		}
		.test{
			height:41px;
			width:100%;
			border: 1px solid #DDDDDD ;
			background-color:#F5F5F5;
			border:none;
			text-align:center;
		}
		H4{
			text-align:center
		}
		</style>
		
	</head>
	<body>
		<div></div>
		<nav class="navbar " role="navigation" style="background-color: ;opacity: 0.8;">
<!-- 		<div class="col-sm-1" style="background-color: #8bdddd;height: 150px"></div> -->
			<div class="col-sm-12 " style="background-color: #8bdddd; box-shadow:0 0 30px 10px rgba(139,211,211,1) ">
				<div class="navbar-header " >
					<%if (request.getAttribute("result")==null){%>
					<img class="navbar-brand navbar-left" src="LOGO.png" style="height: 150px ">
					<%}%>
					<%if (request.getAttribute("result")!=null){%>
					<img class="navbar-brand navbar-left" src="<%=request.getContextPath()%>/checklist/LOGO.png" style="height: 150px ">
					<%}%>
				</div>
				<div style="font-size: 70px; text-align: center; padding-top: 20px;  "><font  face="DFKai-sb" style="color: #000000">���Ӭ���x�޲z�t��</font>
				<div style="float: right; margin-right:100px">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/logout.do"><ul class="nav navbar-nav navbar-right" style="font-size: 15px; padding-top: 80px; ">
						<li><a><%=loginVO.getAdm_name()%></a></li>
						<li><button type="submit" class="glyphicon glyphicon-log-out" style="background-color:#8bdddd;border:none;padding-top:15px" >�n�X</button></li>
					</ul></FORM>
				</div>
				</div>
			</div>
<!-- 			<div class="col-sm-1" style="background-color: #8bdddd;height: 150px" ></div> -->

			
		</nav>

		<div class="col-sm-12 col-sm-offset-0.5" >
			<div class="row">
				<div class="col-xs-12 col-sm-2" style="padding-top: 40px ">
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true" >

					  <!-- �϶�1 -->
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel0">
					      <h4 class="panel-title">
					        <a href="backend_index.html" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="000">
					          ��x����
					        </a>
					      </h4>
					    </div>
					  </div>

					  <!-- �϶�1 ���|�t��-->
					  <%if (loginVO.getAdm_iden().equals("�����޲z��")||loginVO.getAdm_iden().equals("�|���޲z��")||loginVO.getAdm_iden().equals("���y�޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel1">
					      <h4 class="panel-title" >
					        <a id="1" href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
					          ���|�t��
					        </a>
					      </h4>
					    </div>
					    <div id="aaa" class="panel-collapse collapse " role="tabpanel" aria-labelledby="panel1">
					    
					      <div class="list-group">
					            
<%-- 						     	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do"> --%>
<!-- 									<button id="11" class="button" type="submit">�峹���|</button> -->
<!-- 									<input type="hidden" name="chli_cate" value="0"> -->
<!-- 									<input type="hidden" name="action" value="test_undone"> -->
<!-- 								</FORM> -->
								 <%if (loginVO.getAdm_iden().equals("�����޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					      	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
									<button id="22" class="button" type="submit">�d�����|</button>
									<input type="hidden" name="chli_cate" value="1">
									<input type="hidden" name="action" value="test_undone">
								</FORM>
								<%}%>
								<%if (loginVO.getAdm_iden().equals("�|���޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
									<button id="33" class="button" type="submit">�������|</button>
									<input type="hidden" name="chli_cate" value="2">
									<input type="hidden" name="action" value="test_undone">
								</FORM>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
									<button id="44" class="button" type="submit">�|�����|</button>
									<input type="hidden" name="chli_cate" value="3">
									<input type="hidden" name="action" value="test_undone">
								</FORM>
								<%}%>
								<%if (loginVO.getAdm_iden().equals("���y�޲z��")||loginVO.getAdm_iden().equals("Master")){%>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/checklist/checklist.do">
									<button id="55" class="button" type="submit">�Ӯa���|</button>
									<input type="hidden" name="chli_cate" value="4">
									<input type="hidden" name="action" value="test_undone">
								</FORM>
								<%}%>
					      </div>
					    </div>
					  </div>
					  <%}%>
					  <!-- �϶�2 �|����ƺ޲z-->
					  <%if (loginVO.getAdm_iden().equals("�|���޲z��")||loginVO.getAdm_iden().equals("���y�޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="panel2">
					      <h4 class="panel-title">
					        <a id="2" href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="true" aria-controls="bbb">
					          �|����ƺ޲z
					        </a>
					      </h4>
					    </div>
					    <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
					      <div class="list-group">
					        <%if (loginVO.getAdm_iden().equals("�|���޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/k_man_men_data.do" id="title_button">
					      	<button class="button" type="submit" form="title_button" value="cho_generalMember" name="button">�|���v�����@</button>
					      	</FORM>
					      	<%}%>
					      	<%if (loginVO.getAdm_iden().equals("���y�޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					      	<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/man_men_data2.do" id="title_button">
						       <button class="button" type="submit" form="title_button" value="back" name="button">�t�Ӹ�Ƽf��</button>
						    </FORM>
					      	<%}%>
					      </div>
					    </div>
					  </div>
					  <%}%>
					  <!-- �϶�3 �|���޲z-->
					  <%if (loginVO.getAdm_iden().equals("���u�޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					  <div class="panel panel-default">
					   <h4 class="panel-title">
					        <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/adm2.do" >
					        <button id="mem" class="test" type="submit">���u�޲z</button>
					        <input type="hidden" name="action" value="get_adm_one_data">
					   		 </FORM> 
					      </h4>
					  </div>
					<%}%>
					  <!-- �϶�4 ���y�޲z-->
					  
<%-- 					  <%if (loginVO.getAdm_iden().equals("���y�޲z��")||loginVO.getAdm_iden().equals("Master")){%> --%>
<!-- 					  <div class="panel panel-default"> -->
<!-- 					    <div class="panel-heading" role="tab" id="panel4"> -->
<!-- 					      <h4 class="panel-title"> -->
<!-- 					        <a id="4" onClick="f4()" style="text-align:center;" href="#ddd" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="ccc"> -->
<!-- 					          ���y�޲z -->
<!-- 					        </a> -->
<!-- 					    </div> -->
<!-- 					    <div id="ddd" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel3"> -->
<!-- 					      <div class="list-group"> -->
<!-- 					      	<a href="#" class="list-group-item">�|��q��ڶ��޲z</a> -->
<!-- 					      	<a href="#" class="list-group-item">�t�Ӵڶ��޲z</a> -->
<!-- 					      </div> -->
<!-- 					    </div> -->
<!-- 					  </div> -->
<%-- 						<%}%> --%>
						
						<%if (loginVO.getAdm_iden().equals("���y�޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					  <!-- �϶�5�s�i�f��-->
					  <div class="panel panel-default">
					    <h4 class="panel-title">
					        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/advertisement/advertisement.do" >
					        <button id="AD" class="test" type="submit">�s�i���f��</button>
					        <input type="hidden" name="action" value="AD">
					   		 </FORM> 
					      </h4>
					  </div>
					  <%}%>
					  <!-- �϶�6����r-->
					   <%if (loginVO.getAdm_iden().equals("�����޲z��")||loginVO.getAdm_iden().equals("Master")){%>
					  <div class="panel panel-default">
					   <h4 class="panel-title">
					     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/blocked_keywords/blocked_keywords.do" >
					         <button id="BK" class="test" type="submit">����r�̽��޲z</button>
					         <input type="hidden" name="action" value="all_Blocked_Keywordss">
					   		 </FORM> 
					      </h4>
					  </div>
					  
 					  <!-- �϶�7�̷s����-->
					   <div class="panel panel-default">
					     <h4 class="panel-title">
					     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/announcement/announcement.do" >
					     	<button id="all" class="test" type="submit">�̷s�����޲z</button>
       						 <input type="hidden" name="action" value="all_Announcements">
					   		 </FORM> 
					      </h4>
					  </div>
					  <%}%>
					</div>
				</div>

				<div class="col-xs-12 col-sm-10">
<!-- 					<div  style="margin-top: 0px;height: 71px"> -->
<!-- 					  <form class="navbar-form navbar-left" role="search" style=""> -->
<!-- 					  <div style="float: left; font-size: 28px">�峹���|</div>  -->
						
<!-- 					</form> -->
<!-- 					</div> -->
				<div>
				<%if (request.getAttribute("result")=="undone"){%>
					<jsp:include page="test_Undone.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="done"){%>
					<jsp:include page="test_Done.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="adm"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="add_adm"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="purview"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="modify"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="add_determine"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="all_cancel"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="del"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="mod_determine"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="pur_determine"){%>
					<jsp:include page="/view/admView.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="back"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="cho_generalMember"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="detail"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="ma_mod"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="ma_verification"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="ma_removed"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="ma_suspension"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="Blocked_Keywords"){%>
					<jsp:include page="/backend/blocked_keywords/select_page.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="getOne_For_Display_Keywords"){%>
					<jsp:include page="/backend/blocked_keywords/listOneBlocked_Keywords.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="Announcement"){%>
					<jsp:include page="/backend/announcement/select_page.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="getOne_For_Display_Announcement"){%>
					<jsp:include page="/backend/announcement/listOneAnnouncement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="all_Announcements"){%>
					<jsp:include page="/backend/announcement/listAllAnnouncement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="add_Announcements"){%>
					<jsp:include page="/backend/announcement/addAnnouncement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="all_Blocked_Keywordss"){%>
					<jsp:include page="/backend/blocked_keywords/listAllBlocked_Keywords.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="add_Blocked_Keywords"){%>
					<jsp:include page="/backend/blocked_keywords/addBlocked_Keywords.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="deleteKeywords"){%>
					<jsp:include page="/backend/blocked_keywords/listAllBlocked_Keywords.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="Update_Keyword"){%>
					<jsp:include page="/backend/blocked_keywords/update_blocked_keywords_input.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="updateFinal"){%>
					<jsp:include page="/backend/blocked_keywords/listAllBlocked_Keywords.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="insertKeyword"){%>
					<jsp:include page="/backend/blocked_keywords/listAllBlocked_Keywords.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="insertAnnouncement"){%>
					<jsp:include page="/backend/announcement/listAllAnnouncement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="deleteAnnouncement"){%>
					<jsp:include page="/backend/announcement/listAllAnnouncement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="UpdateAnnouncement_"){%>
					<jsp:include page="/backend/announcement/update_announcement_input.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="updateFinalAnnouncement"){%>
					<jsp:include page="/backend/announcement/listAllAnnouncement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="all"){%>
					<jsp:include page="/advertisement/allAdvertisement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="OTHER"){%>
					<jsp:include page="/advertisement/allAdvertisement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="AD"){%>
					<jsp:include page="/advertisement/allAdvertisement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="AD_DELETE"){%>
					<jsp:include page="/advertisement/allAdvertisement.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="MM"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="MG"){%>
					<jsp:include page="/view/man_mem_data.jsp"/>
				<%}%>
				<%if (request.getAttribute("result")=="updateAD"){%>
					<jsp:include page="/advertisement/allAdvertisement.jsp"/>
				<%}%>
				</div>
				</div>
			</div>
		</div>
		<div class="col-sm-1 "></div>
			
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
	<script>
console.log("localStorage.getItem="+localStorage.getItem("1"));
document.getElementById("1").addEventListener("click",f1);
document.getElementById("2").addEventListener("click",f2);

document.getElementById("mem").addEventListener("click",f3);
document.getElementById("AD").addEventListener("click",f3);
document.getElementById("BK").addEventListener("click",f3);
document.getElementById("all").addEventListener("click",f3);

function f1() {
    if(typeof(Storage) !== "undefined") {
    	localStorage.setItem("1", "1");
    	console.log("localStorage.getItem="+localStorage.getItem("1"));
        } 
    }
function f2() {
    if(typeof(Storage) !== "undefined") {
    	localStorage.setItem("1", "2");
    	console.log("localStorage.getItem="+localStorage.getItem("1"));
        } 
    }
function f3() {
    if(typeof(Storage) !== "undefined") {
    	localStorage.setItem("1", "0");
    	console.log("localStorage.getItem="+localStorage.getItem("1"));
        } 
    }

function init(){
	console.log("init="+localStorage.getItem("1"));
	if(localStorage.getItem("1")==null){
		localStorage.setItem("1", "1");
		$("#1").click();
		console.log("123");
		$("#22").click();
		
	}else{
			if(localStorage.getItem("1")==1){
				$("#1").click();
				console.log("localStorage.getItem="+localStorage.getItem("1"));
			}else if(localStorage.getItem("1")==2){
				console.log("init else="+localStorage.getItem("1"));
				$("#2").click();
			}else{
				
			}
		}
	}
window.onload=init();
</script>
</html>

