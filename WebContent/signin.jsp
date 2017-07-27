<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>登入</title>
<jsp:include page="/mustinclude/head.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
		
		body {
			    font-size: 16px;
			    color: #333;
			    font-family: "微軟正黑體", Arial, sans-serif;
			    background-image:url(<%=request.getContextPath()%>/personal/grass.jpg);

		}
		.dd{
			/*text-align: center;*/
			display: inline;
			
		}

		input{
			width: 50%;
			
		}
		li {
				height: 50px;
				text-align: center;
		}
		h1{
			text-align: center;
			
		}
		::-webkit-input-placeholder {
   				text-align: center;
		}
		.navbar{
			opacity: 0.9;
		}
		

	</style>
</head>
<body>
	
			<!--
				 account:shyangs@kimo.com 
				 password:123456789
			
			 -->
			<jsp:include page="/mustinclude/top_nav.jsp" />
					

			<div class="container" style="padding-top:10%;opacity: 0.85;">
				<div class="row"  >
					<div class="col-xs-12 col-sm-10 col-sm-push-1">
						
							<form method="post" action="loginhandler">
								<div class="container">
									<div class="row">
										<div class="col-xs-12 col-sm-10 dd" style="width:70%;" >
									
											<div class="panel panel-default" id="container1" >
											  <div class="panel-heading">
											    <h3 class="panel-title"><h1>登入</h1></h3>
											    <c:if test="${not empty msg}"><font color="red">*<%= request.getAttribute("msg") %></font></c:if>
											  </div>
											  <ul class="list-group" style="height: 270px;">
											    <li class="list-group-item">
													<div class="col-xs-12 col-sm-10 col-sm-push-1" style="padding-top: 10px;"> <label>一般會員信箱 / 廠商會員帳號</label></div>
													<div class="col-xs-12 col-sm-10 col-sm-push-1" style="padding-top: 10px;"><input type="text" name="account" id="aa" value="" placeholder="輸入信箱 or 帳號" ></div>
													<div class="col-xs-12 col-sm-10 col-sm-push-1" style="padding-top: 10px;"> <label>密碼</label></div>
													<div class="col-xs-12 col-sm-10 col-sm-push-1" style="padding-top: 10px;">
												    	<input type="password" name="password" id="bb" value="" placeholder="輸入密碼" >	    	
												    </div>
												   
												    <div class="col-xs-12 col-sm-10 col-sm-push-1" style="padding-top: 10px;"> 
														<c:if test="${not empty errorMsgs}">
															<font color="red"><b>${errorMsgs}</b></font><br>
														</c:if>
														<a href="#" id="forget">還沒有帳號 ?</a>
														
													</div>
													<div class="col-xs-12 col-sm-10 col-sm-push-1"  style="padding-top: 10px;">	<input type=submit value="  ok   "></div>
							    
												</li>  			    
											  </ul>
													<button type="button" class="btn btn-default btn-xs" id="btn-login-ww">武</button>
													<button type="button" class="btn btn-default btn-xs" id="btn-login-cc">次</button>
											</div>
											 <!-- 註冊 -->
											 <div class="panel panel-default" id="born" style="display: none;">
												  <div class="panel-heading">
												    <h3 class="panel-title title"><h1>註冊</h1></h3>
												  </div>
												  <ul class="list-group">
												    <li class="list-group-item">
														<div class="col-xs-12 col-sm-6"><label>一般會員</label></div>
														 <div class="col-xs-12 col-sm-6"><label>廠商會員</label></div> 				    
													</li>
													 <li class="list-group-item">
														<div class="col-xs-12 col-sm-6"><a href="general_member/g_member.jsp" class="btn btn-info">送出</a></div>
														 <div class="col-xs-12 col-sm-6"><a href="manufacturers/m_member.jsp" class="btn btn-info">送出</a></div> 				    
													</li>
												   </ul>
											  </div> 	
							</form>

					

					</div>
				</div>
			</div>
				

		<script type="text/javascript">
			document.getElementById("forget").addEventListener("click",function(){
				
				document.getElementById("born").style.display="";
				document.getElementById("container1").style.display="none";


			},false);
			
			$('#btn-login-ww').on('click', function(){
				$('#aa')[0].value='shyangs@gmail.com';
				$('#bb')[0].value='123456789';
			});
			$('#btn-login-cc').on('click', function(){
				$('#aa')[0].value='imyenshen@gmail.com';
				$('#bb')[0].value='123456789';
			});
		</script>
		
		
</body>
</html>