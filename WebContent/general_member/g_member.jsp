
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.general_member.model.*"%>
<%
	GeneralMemberVO MemVO = (GeneralMemberVO) request.getAttribute("GeneralMemberVO");
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
<!-- <script src="http://code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>一般會員新增</title>



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
.btn1 {
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

.btn1:hover {background-color:SkyBlue;}

.btn1:active {
  background-color:	#40E0D0;
 
  transform: translateY(4px);
}
</style>

</head>
<body>
<jsp:include page="/mustinclude/top_nav.jsp" />
	
	<!-- title -->

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-push-1 dd">
				<label><h1  style="color:Azure  ;">一 般 會 員 註 冊</h1></label>
			</div>
		</div>
	</div>

	<!-- 表單 -->

	<form METHOD="post" ACTION="General_Member.do" name="form1"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-10 col-sm-push-1" >
					<!-- 帳號設定 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">帳號設定</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item">
								<div class="col-xs-12 col-sm-4">
									<label for="aa" class="control-label">帳號(請輸入email)</label>
								</div>
								<div class="col-xs-12 col-sm-8">
									<input type="text" name="MEM_MAIL" id="MEM_MAIL"
										placeholder="請輸入正確email" style="width: 200px;"
										value="<%=(MemVO == null) ? "" : MemVO.getMEM_MAIL()%>" /> <font
										color='red'><b>${errorMsgs.MEM_MAIL_1}</b></font> <font
										color='red' id="MAIL"><b>${errorMsgs.MEM_MAIL}</b></font>

								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">密碼</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="password" name="MEM_PSW" id="MEM_PSW"
											placeholder="請輸入密碼" style="width: 200px;"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_PSW()%>" /> <font
											color='red' id="PSW"><b>${errorMsgs.MEM_PSW}</b></font> <font
											color='red'><b>${errorMsgs.MEM_PSW_1}</b></font>

									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4 aa">
										<label for="cc" class="control-label">請再次輸入密碼</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="password" name="" id="MEM_PSW_1"
											placeholder="再次輸入密碼" style="width: 200px;"> <font
											color='red' id="PSW_1"><b></b></font>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<hr>
					<!-- 個人資料 -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">個人基本資料</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="MEM_NAME" class="control-label">姓名</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MEM_NAME" id="MEM_NAME"
											placeholder="請填寫真實姓名"> <font color='red' id="NAME"><b>${errorMsgs.MEM_NAME}</b></font>

									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">性別</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<div class="radio-inline">
											<label> <input type="radio" name="MEM_GEN" checked
												value="<%=(MemVO == null) ? "M" : MemVO.getMEM_GEN()%>" />
												男
											</label>
										</div>
										<div class="radio-inline">
											<label> <input type="radio" name="MEM_GEN"
												value="<%=(MemVO == null) ? "F" : MemVO.getMEM_GEN()%>" />
												女
											</label>
										</div>
									</div>
								</div>
							</li>

							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">生日</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MEM_BIRTH" id="datepicker3"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_BIRTH()%>" />

										<font color='red' id="BIRTH"><b>${errorMsgs.MEM_BIRTH}</b></font>

									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">住址</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="TEXT" name="MEM_ADDR" size="45" id="MEM_ADDR"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_ADDR()%>" />
									</div>

								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">手機</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MEM_PHONE" id="MEM_PHONE"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_PHONE()%>" />

										<font color='red' id="PHONE"><b>${errorMsgs.MEM_PHONE}</b></font>

									</div>
								</div>
							</li>
							<li class="list-group-item bb">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">個人照片</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="file" name="MEM_PIC" size="45" class="upl"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_PIC()%>" />
									</div>
									<div class="col-xs-12 col-sm-4">
										<img class="preview"
											style="max-width: 100px; max-height: 100px;">
									</div>
								</div>
							</li>
							<li class="list-group-item cc">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">自我介紹</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<textarea name="MEM_SELF" rows="7" cols="50" id="MEM_SELF"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_SELF()%>" /></textarea>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">是否開啟留言板</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<div class="radio-inline">
											<label> <input type="radio" name="MEM_PBOARD" checked
												value="<%=(MemVO == null) ? "Y" : MemVO.getMEM_GEN()%>" />
												Y
											</label>
										</div>
										<div class="radio-inline">
											<label> <input type="radio" name="MEM_PBOARD"
												value="<%=(MemVO == null) ? "N" : MemVO.getMEM_GEN()%>" />
												N
											</label>
										</div>
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
						<input type="button" value="送出新增" class="btn1"id="btn"> <input
							type="button" id="btn_cancel"  class="btn1" value="治傑小按鈕">
					</p>

				</div>
			</div>
		</div>
		<input type="hidden" name="MEM_COIN" size="45"
			value="<%=(MemVO == null) ? 0 : MemVO.getMEM_COIN()%>" /> <input
			type="hidden" name="MEM_STA" size="45"
			value="<%=(MemVO == null) ? "U" : MemVO.getMEM_STA()%>" /> <input
			type="hidden" name="action" value="insert">

	</form>
	<!-- 月曆 & 顯示圖片 -->
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
		$("#datepicker3").datepicker({
			showOn : "button",
			buttonImage : "../images/calendar.gif",
			buttonImageOnly : true,
			yearRange: "-40:+0",
			changeMonth : true,
			changeYear : true,
			dateFormat : 'yy-mm-dd',
			maxDate : "0D"
		});
	</script>
	<!-- 神奇小按鈕 -->
	<script>
		document
				.getElementById("btn_cancel")
				.addEventListener(
						'click',
						function() {
							document.getElementById("MEM_MAIL").value = "ba101g5g@gmail.com";
							document.getElementById("MEM_PSW").value = "123456789";
							document.getElementById("MEM_PSW_1").value = "123456789";
							document.getElementById("MEM_NAME").value = "喵喵";
							document.getElementById("MEM_ADDR").value = "桃園區中壢區中央二十路18號";
							document.getElementById("MEM_PHONE").value = "0975321555";
							document.getElementById("MEM_SELF").innerText = "大家好我是害羞內向的喵喵";
						}, false);
	</script>
	<!-- 驗證 -->
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
			//驗證email
			var email = document.getElementById('MEM_MAIL').value;
			var emailRegxp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;

			//驗證phone

			var phone = document.getElementById("MEM_PHONE").value;
			var phonetest = /^09[0-9]{8}$/;

			if (email == '') {
				// 				alert('請輸入電子信箱');
				document.getElementById('MEM_MAIL').focus();
				document.getElementById('MEM_MAIL').select();
				document.getElementById("MAIL").innerText = "*請輸入電子信箱";
			} else if (emailRegxp.test(email) != true) {
				// 					alert('電子信箱格式錯誤');
				document.getElementById('MEM_MAIL').focus();
				document.getElementById('MEM_MAIL').select();
				document.getElementById("MAIL").innerText = "*電子信箱格式錯誤";
			} else if (document.getElementById("MEM_PSW").value.length < 8) {
				document.getElementById("MEM_PSW").focus();
				document.getElementById("MEM_PSW").select();
				document.getElementById('PSW').innerText = "*密碼需輸入八個字以上";
			} else if (document.getElementById("MEM_PSW").value != document
					.getElementById("MEM_PSW_1").value) {
				// 				alert("密碼輸入錯誤");
				document.getElementById("MEM_PSW_1").focus();
				document.getElementById("MEM_PSW_1").select();
				document.getElementById('PSW_1').innerText = "*密碼輸入錯誤";
			} else if (document.getElementById("MEM_NAME").value == '') {
				// 				alert("請輸入廠商名稱");
				document.getElementById("MEM_NAME").focus();
				document.getElementById("MEM_NAME").select();
				document.getElementById("NAME").innerText = "*請輸入姓名";
			}else if (phone == '') {
				// 				alert("請輸入電話號碼");
				document.getElementById("MEM_PHONE").focus();
				document.getElementById("MEM_PHONE").select();
				document.getElementById("PHONE").innerText = "*請輸入電話";
			}else if(document.getElementById("datepicker3").value.length == ''){
				document.getElementById("datepicker3").focus();
				
				document.getElementById("BIRTH").innerText = "*請選擇生日";
			} else if (phonetest.test(phone) != true) {
				// 					alert("phone輸入錯誤");
				document.getElementById("MEM_PHONE").focus();
				document.getElementById("MEM_PHONE").select();
				document.getElementById("PHONE").innerText = "*請輸入正確電話";
			}  else if (!b) {
				// 				alert("驗證碼輸入錯誤 !!");
				document.getElementById('aa').focus();
				document.getElementById('aa').select();
				document.getElementById('number').innerText = "*請輸入正確";
			} else {
				form1.submit();
			}

		}

		document.getElementById("btn").onclick = add;
	</script>
<!-- 	<script src="https://code.jquery.com/jquery.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>