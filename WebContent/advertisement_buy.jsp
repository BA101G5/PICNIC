
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertisement.model.*"%>
<%
	AdvertisementVO ADVO = (AdvertisementVO) request.getAttribute("ADVO");
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
<title>購買廣告</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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

	<form METHOD="post" ACTION="<%=request.getContextPath() %>/advertisement/advertisement.do" name="form1"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">廣告購買資料</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item bb">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">廣告照片</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="file" name="AD_PHOTO" size="45" class="upl"
											value="<%=(ADVO == null) ? "" : ADVO.getAD_PHOTO()%>" />
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
										<label for="aa" class="control-label">開始日期</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="DAY_START" id="datepicker"
											value="<%=(ADVO == null) ? "" : ADVO.getDAY_START()%>" /><font color='red' id="START"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.DAY_START}</b></font>
										</c:if>

									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">結束日期</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="DAY_END" id="datepicker3"
											value="<%=(ADVO == null) ? "" : ADVO.getDAY_END()%>" /><font color='red' id="END"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.DAY_END}</b></font>
										</c:if>

									</div>
								</div>
							</li>
							<li class="list-group-item cc">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">廣告介紹</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<textarea name="AD_SELF" rows="7" cols="50" id="AD_SELF"
											value="<%=(ADVO == null) ? "廣告" : ADVO.getAD_SELF()%>" />
										</textarea><font color='red' id="SELF"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.AD_SELF}</b></font>
										</c:if>
									</div>

								</div>
							</li>
						
							
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">金額</label>
									</div>
									<div class="col-xs-12 col-sm-8" id="AD_CASH">
										
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
		<input type="hidden" name="AD_CASH" value="" id="cashes">
		<input type="hidden" name="AD_STA" value="<%=(ADVO == null) ? "U" : ADVO.getAD_STA()%>">
		<input type="hidden" name="MF_NO" value="${mVO.MF_NO}">
		<input type="hidden" name="action" value="insert">

	</form>
	<!-- 月曆 & 顯示圖片 -->
	<script>
	$("#datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : 'yy-mm-dd',
		maxDate : "0D"
	});
		$("#datepicker3").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd',
			minDate: +30,
		});
		
	
	
	</script>
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
	function CalculateDate(start,end){
		start = new Date(start);
		end = new Date(end);;
		return (end - start)/ 86400000 ;
	
	}
	function Date_substr()
	{
	//定義起始 年月日
	    var StartDate=document.getElementById("datepicker").value;
	//定義結束 年月日
	    var EndDate=document.getElementById("datepicker3").value;


	   document.getElementById("AD_CASH").innerText = parseInt(CalculateDate(StartDate,EndDate)) * 1000;
	   document.getElementById("cashes").value = parseInt(CalculateDate(StartDate,EndDate)) * 1000;
	} 


document.getElementsByClassName("list-group-item")[3].addEventListener('mouseover',Date_substr,false);
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
			if(document.getElementById('datepicker').value == ''){
				document.getElementById('datepicker').focus();
				document.getElementById('datepicker').select();
					document.getElementById('START').innerText="*請輸入開始日期"; 
			}else if(document.getElementById('datepicker3').value == ''){
				document.getElementById('datepicker3').focus();
				document.getElementById('datepicker3').select();
					document.getElementById('END').innerText="*請輸入結束日期"; 
			}
			else if(document.getElementById('AD_SELF').value == ''){
				document.getElementById('AD_SELF').focus();
					document.getElementById('AD_SELF').select();
					document.getElementById('SELF').innerText="*請輸入自我介紹"; 
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
</body>
</html>
