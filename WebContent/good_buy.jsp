
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>
<%
Goods_SellVO GSVO = (Goods_SellVO) request.getAttribute("GSVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<jsp:include page="/mustinclude/head.jsp" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>購買商品</title>



<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style>
.form-group {
	text-align: left;
}

.form-group label {
	float: left;
}

.title {
	color: red;
}

h3 {
	color: #dd0000;
	font-weight: bolder;
	height: 27px;
	position: relative;
	padding: 7px 0px 0px 32px;
	border-radius: 5px 5px 0px 0px;
	text-shadow: 1px 1px 0px #ccc;
}

body {
	font-size: 13px;
	color: #333;
	font-family: "微軟正黑體", Arial, sans-serif;
}

form {
	padding-top: 60px;
}

li {
	height: 50px;
}

.bb, .cc {
	height: 150px;
}

.dd {
	text-align: center;
	padding-top: 50px;
}

.ui-datepicker-trigger {
	margin-left: 5px;
}

textarea {
	resize: none;
}
#btn {
  color: #fff;
    background-color: #5bc0de;
    border-color: #46b8da;
    display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 16px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    -ms-touch-action: manipulation;
    touch-action: manipulation;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
  
}

#btn:hover {background-color:SkyBlue ;}

#btn:active {
  background-color: 	#40E0D0;
 
  transform: translateY(4px);
}

</style>

</head>
<body>

<jsp:include page="/mustinclude/top_nav.jsp" />

	<!-- 表單 -->

	<form METHOD="post" ACTION="<%=request.getContextPath() %>/goods_sell/goods_sell.do" name="form1"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">新增商品</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item bb">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">商品照片</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="file" name="gs_img" size="45" class="upl"
											value="<%=(GSVO == null) ? "" : GSVO.getGs_img()%>" />
									</div>
									<div class="col-xs-12 col-sm-4">
										<img class="preview"
											style="max-width: 100px; max-height: 100px;">
									</div>
								</div>
							</li>
							

							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">商品名稱</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="gs_name" id="gs_name"
											value="<%=(GSVO == null) ? "" : GSVO.getGs_name()%>" />	<font color='red' id="name"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.gs_name}</b></font>
										</c:if>

									</div>
								</div>
							</li>
							<li class="list-group-item cc">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">商品資訊</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<textarea name="gs_info" rows="7" cols="50" id="gs_info"
											value="<%=(GSVO == null) ? "" : GSVO.getGs_info()%>" />
										</textarea><font color='red' id="info"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.gs_info}</b></font>
										</c:if>

									</div>
								</div>
							</li>
							
						
							
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">商品單價</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="gs_price" id="gs_price"
											value="<%=(GSVO == null) ? "" : GSVO.getGs_price()%>" /><font color='red' id="price"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.gs_price}</b></font>
										</c:if>

									</div>
								</div>
							</li>
							
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">商品分類</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<select name="gs_type">
											<option value='A'>器具</option>
											<option value='B'>食物</option>
											<option value='C'>野餐墊</option>
										</select>
										<font color='red' id="type"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.gs_price}</b></font>
										</c:if>

									</div>
								</div>
							</li>
							
							
							
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">驗證碼</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="text" name="aa" id="aa" placeholder="請輸入右方驗證碼">
										<font color='red' id="number"><b></b></font>
									</div>
									<div class="col-xs-12 col-sm-2">
										<div id="text"
											style="border: 2px dotted; font-size: 16px; display: flex; justify-content: center;"></div>
									</div>
								</div>
							</li>
						</ul>
					</div>




					<!-- button -->
					<p class="text-center">
						<input type="button" value="新增" id="btn">
					</p>

				</div>
			</div>
		</div>
		<input type="hidden" name="MF_NO" value="${mVO.MF_NO}">
		<input type="hidden" name="gs_sta" value="<%=(GSVO == null) ? "U" : GSVO.getGs_sta()%>">	
		<input type="hidden" name="action" value="insert">
	

	</form>

	
	<script>

		
		$(function() {

			function preview(input) {
				if (input.files && input.files[0]) {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('.preview').attr('src', this.result);
					}
					reader.readAsDataURL(input.files[0]);
				}
			}

			$("body").on("change", ".upl", function() {
				preview(this);
			})

		})
		
	
	</script>
	
	
	

<script>
		//產生驗證碼
		var x = new Array(4);
		for (var i = 0; i < x.length; i++) {
			x[i] = Math.floor(Math.random() * 10);
		}
		document.getElementById("text").innerText = x[0] + " " + x[1] + " "
				+ x[2] + " " + x[3];
		var b = false;
		function add() {

			for (var a = 0; a < x.length; a++) {
				if (document.getElementById("text").innerText.split(" ")[a] == document
						.getElementById("aa").value.split("")[a]) {
					b = true;
				} else {
					b = false;
					break;
				}
			}
			<!-- 驗證 -->			
			if(document.getElementById('gs_name').value.trim().length ==0){
				document.getElementById('gs_name').focus();
				document.getElementById('gs_name').select();
					document.getElementById("name").innerText="*請輸入商品名稱"; 
			}else if(document.getElementById('gs_info').value.trim() == ''){
				document.getElementById('gs_info').focus();
				document.getElementById('gs_info').select();
					document.getElementById('info').innerText="*請輸入商品資訊"; 
			}
			else if(document.getElementById("gs_price").value.trim() == ''){
				document.getElementById('gs_price').focus();
					document.getElementById('gs_price').select();
					document.getElementById('price').innerText="*請輸商品單價"; 
			}else if (!b) {
					
					document.getElementById('aa').focus();
					document.getElementById('aa').select();
					document.getElementById('number').innerText = "*請輸入正確";
				} else {
					 
					form1.submit();
				}
			

		}

		document.getElementById("btn").onclick = add;
	</script>
	
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>