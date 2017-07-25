<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manufacturers.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>Title Page</title>
<jsp:include page="/mustinclude/head.jsp" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<style type="text/css">
/*
			label{
				margin-right: 50px;
				border-right: thick ; 
			}*/
.form-group {
	text-align: left;
}

.form-group label {
	float: left;
}

.title {
	color: red;
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

.btn1:hover {
	background-color: SkyBlue;
}

.btn1:active {
	background-color: #40E0D0;
	transform: translateY(4px);
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
	font-family: "�L�n������", Arial, sans-serif;
}

form {
	padding-top: 50px;
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

textarea {
	resize: none;
}
</style>
</head>
<body>
	<jsp:include page="/mustinclude/top_nav.jsp" />


	<!-- title -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-push-1 dd">
				<label><h1 style="color:Azure  ;"> �t �� �| �� �� �� </h1></label>
			</div>
		</div>
	</div>





	<form METHOD="post" ACTION="Manufacturers.do" name="form1"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">

				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<!-- �b���]�w -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">�b���]�w</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item">
								<div class="col-xs-12 col-sm-4">
									<font color='red'><b>*</b></font> <label for="aa"
										class="control-label">�b��</label>
								</div>
								<div class="col-xs-12 col-sm-8">${MfVO.MF_ACCO}</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="bb" class="control-label">�K�X</label>

									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="password" name="MF_PSW" id="MF_PSW"
											placeholder="�п�J�K�X" style="width: 200px;"
											value=""><font color='red'><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red' id="PSW"><b>${errorMsgs.MF_PSW}</b></font>
											<font color='red'><b>${errorMsgs.MF_PSW_1}</b></font>
										</c:if>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4 aa">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="cc" class="control-label">�ЦA����J�K�X</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="password" name="MF_PSW_1" id="MF_PSW_1"
											style="width: 200px;" placeholder="�A����J�K�X"> <font
											color='red' id="PSW_1"><b></b></font>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4 aa">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="cc" class="control-label">�H�c</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MF_MAIL" id="MF_MAIL"
											placeholder="�H�c�ݿ�J���T" style="width: 200px;"
											value="${MfVO.MF_MAIL}"><font color='red' id="MAIL"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.MF_MAIL}</b></font>
										</c:if>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4 aa">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="cc" class="control-label">�q��</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MF_PHONE" id="MF_PHONE"
											placeholder="�q�ܻݿ�J���T" style="width: 200px;"
											value="${MfVO.MF_PHONE}"><font color='red' id="PHONE"><b></b></font>
										<c:if test="${not empty errorMsgs}">
											<font color='red'><b>${errorMsgs.MF_PHONE}</b></font>
										</c:if>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<hr>
					<!-- �t�Ӹ�� -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">�t�Ӱ򥻸��</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="aa" class="control-label">�t�ӦW��</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MF_NAME" id="MF_NAME"
											placeholder="�ж�g�t�ӦW��" value="${MfVO.MF_NAME}">
										<c:if test="${not empty errorMsgs}"><font color='red' id="NAME"><b></b></font>
											<font color='red'><b>${errorMsgs.MF_NAME}</b></font>
										</c:if>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label"> �ǯu</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MF_FAX" id="MF_FAX"
											value="${MfVO.MF_FAX}">
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">�a�}</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MF_ADDR" id="MF_ADDR"
											style="width: 300px;" value="${MfVO.MF_ADDR}">
									</div>

								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="aa" class="control-label">�Τ@�s��</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MF_BS" id="MF_BS"
											value="${MfVO.MF_BS}">
										<c:if test="${not empty errorMsgs}">
											<font color='red' id="BS"><b>${errorMsgs.MF_BS}</b></font>
											<font color='red'><b>${errorMsgs.MF_BS_1}</b></font>
										</c:if>
									</div>
								</div>
							</li>
							<li class="list-group-item bb">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">�t��Logo</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="file" name="MF_LOGO" size="45" class="upl"
											value="${MfVO.MF_LOGO}" />
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
										<label for="aa" class="control-label">�t�Ӥ���</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<textarea rows="7" cols="50" name="MF_SELF" id="MF_SELF"
											value="${MfVO.MF_SELF}">${MfVO.MF_SELF}</textarea>
									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<font color='red' style="float: left;"><b>*</b></font> <label
											for="aa" class="control-label">���ҽX</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="text" name="aa" id="aa" placeholder="�п�J�k�����ҽX">
										<font color='red' id="number"><b></b></font>
									</div>
									<div class="col-xs-12 col-sm-2">
										<div id="text"
											style="border: 2px dotted; font-size: 16px; display: flex; justify-content: center;">
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<!-- button -->

					<p class="text-center">
						<input type="button" value="�ק�" class="btn1" id="btn">
					</p>
				</div>
			</div>
		</div>
		<input
			type="hidden" name="MF_ACCO" size="45"
			value="${MfVO.MF_ACCO}" />
		<input
			type="hidden" name="MF_NO" size="45"
			value="${MfVO.MF_NO}" />
		<input type="hidden" name="MF_REPORTNUM" size="45"
			value="${MfVO.MF_REPORTNUM}" /> <input
			type="hidden" name="MF_STA" size="45"
			value="${MfVO.MF_STA}" /> <input
			type="hidden" name="action" value="update">
	</form>

	<script>
		//�Ϥ����
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
		//javascript����
		//���ҽX
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
			//����email
			var email = document.getElementById('MF_MAIL').value.trim();
			var emailRegxp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;

			//����phone

			var phone = document.getElementById("MF_PHONE").value.trim();
			var phonetest = /^09[0-9]{8}$/;

			if (document.getElementById("MF_PSW").value.trim().length < 8) {
				document.getElementById("MF_PSW").focus();
				document.getElementById("MF_PSW").select();
				document.getElementById('PSW').innerText = "*�K�X�ݿ�J�K�Ӧr�H�W";
			} else if (document.getElementById("MF_PSW").value.trim() != document
					.getElementById("MF_PSW_1").value.trim()) {
				
				document.getElementById("MF_PSW_1").focus();
				document.getElementById("MF_PSW_1").select();
				document.getElementById('PSW_1').innerText = "*�K�X��J���~";
			} else if (email.trim() == '') {
				// 				alert('�п�J�q�l�H�c');
				document.getElementById('MF_MAIL').focus();
				document.getElementById('MF_MAIL').select();
				document.getElementById('MAIL').innerText = "*�п�J�q�l�H�c";
			} else if (emailRegxp.test(email) != true) {
				// 					alert('�q�l�H�c�榡���~');
				document.getElementById('MF_MAIL').focus();
				document.getElementById('MF_MAIL').select();
				document.getElementById('MAIL').innerText = "*�q�l�H�c�榡���~";
			} else if (phone == '') {
				// 				alert("�п�J�q�ܸ��X");
				document.getElementById("MF_PHONE").focus();
				document.getElementById("MF_PHONE").select();
				document.getElementById("PHONE").innerText = "*�п�J�q��";
			} else if (phonetest.test(phone) != true) {
				// 					alert("phone��J���~");
				document.getElementById("MF_PHONE").focus();
				document.getElementById("MF_PHONE").select();
				document.getElementById("PHONE").innerText = "*�п�J���T�q��";
			} else if (document.getElementById("MF_NAME").value.trim() == '') {
				// 				alert("�п�J�t�ӦW��");
				document.getElementById("MF_NAME").focus();
				document.getElementById("MF_NAME").select();
				document.getElementById("NAME").innerText = "*�п�J�t�ӦW��";
			} else if (!b) {
				// 				alert("���ҽX��J���~ !!");
				document.getElementById('aa').focus();
				document.getElementById('aa').select();
				document.getElementById('number').innerText = "*�п�J���T";
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