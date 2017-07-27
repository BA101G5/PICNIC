
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
<title>�@��|���s�W</title>



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
	font-family: "�L�n������", Arial, sans-serif;
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
				<label><h1  style="color:Azure  ;">�@ �� �| �� �� �U</h1></label>
			</div>
		</div>
	</div>

	<!-- ��� -->

	<form METHOD="post" ACTION="General_Member.do" name="form1"
		enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-10 col-sm-push-1" >
					<!-- �b���]�w -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">�b���]�w</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item">
								<div class="col-xs-12 col-sm-4">
									<label for="aa" class="control-label">�b��(�п�Jemail)</label>
								</div>
								<div class="col-xs-12 col-sm-8">
									<input type="text" name="MEM_MAIL" id="MEM_MAIL"
										placeholder="�п�J���Temail" style="width: 200px;"
										value="<%=(MemVO == null) ? "" : MemVO.getMEM_MAIL()%>" /> <font
										color='red'><b>${errorMsgs.MEM_MAIL_1}</b></font> <font
										color='red' id="MAIL"><b>${errorMsgs.MEM_MAIL}</b></font>

								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">�K�X</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="password" name="MEM_PSW" id="MEM_PSW"
											placeholder="�п�J�K�X" style="width: 200px;"
											value="<%=(MemVO == null) ? "" : MemVO.getMEM_PSW()%>" /> <font
											color='red' id="PSW"><b>${errorMsgs.MEM_PSW}</b></font> <font
											color='red'><b>${errorMsgs.MEM_PSW_1}</b></font>

									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4 aa">
										<label for="cc" class="control-label">�ЦA����J�K�X</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="password" name="" id="MEM_PSW_1"
											placeholder="�A����J�K�X" style="width: 200px;"> <font
											color='red' id="PSW_1"><b></b></font>
									</div>
								</div>
							</li>
						</ul>
					</div>
					<hr>
					<!-- �ӤH��� -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title title">�ӤH�򥻸��</h3>
						</div>
						<ul class="list-group">
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="MEM_NAME" class="control-label">�m�W</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<input type="text" name="MEM_NAME" id="MEM_NAME"
											placeholder="�ж�g�u��m�W"> <font color='red' id="NAME"><b>${errorMsgs.MEM_NAME}</b></font>

									</div>
								</div>
							</li>
							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">�ʧO</label>
									</div>
									<div class="col-xs-12 col-sm-8">
										<div class="radio-inline">
											<label> <input type="radio" name="MEM_GEN" checked
												value="<%=(MemVO == null) ? "M" : MemVO.getMEM_GEN()%>" />
												�k
											</label>
										</div>
										<div class="radio-inline">
											<label> <input type="radio" name="MEM_GEN"
												value="<%=(MemVO == null) ? "F" : MemVO.getMEM_GEN()%>" />
												�k
											</label>
										</div>
									</div>
								</div>
							</li>

							<li class="list-group-item">
								<div class="form-group">
									<div class="col-xs-12 col-sm-4">
										<label for="aa" class="control-label">�ͤ�</label>
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
										<label for="aa" class="control-label">��}</label>
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
										<label for="aa" class="control-label">���</label>
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
										<label for="aa" class="control-label">�ӤH�Ӥ�</label>
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
										<label for="aa" class="control-label">�ۧڤ���</label>
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
										<label for="aa" class="control-label">�O�_�}�үd���O</label>
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
										<label for="aa" class="control-label">���ҽX</label>
									</div>
									<div class="col-xs-12 col-sm-4">
										<input type="text" name="aa" id="aa" placeholder="�п�J�k�����ҽX">
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
						<input type="button" value="�e�X�s�W" class="btn1"id="btn"> <input
							type="button" id="btn_cancel"  class="btn1" value="�v�Ǥp���s">
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
	<!-- ��� & ��ܹϤ� -->
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
	<!-- ���_�p���s -->
	<script>
		document
				.getElementById("btn_cancel")
				.addEventListener(
						'click',
						function() {
							document.getElementById("MEM_MAIL").value = "ba101g5g@gmail.com";
							document.getElementById("MEM_PSW").value = "123456789";
							document.getElementById("MEM_PSW_1").value = "123456789";
							document.getElementById("MEM_NAME").value = "�p�p";
							document.getElementById("MEM_ADDR").value = "���Ϥ��c�Ϥ����G�Q��18��";
							document.getElementById("MEM_PHONE").value = "0975321555";
							document.getElementById("MEM_SELF").innerText = "�j�a�n�ڬO�`�ۤ��V���p�p";
						}, false);
	</script>
	<!-- ���� -->
	<script>
		//�������ҽX
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
			var email = document.getElementById('MEM_MAIL').value;
			var emailRegxp = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;

			//����phone

			var phone = document.getElementById("MEM_PHONE").value;
			var phonetest = /^09[0-9]{8}$/;

			if (email == '') {
				// 				alert('�п�J�q�l�H�c');
				document.getElementById('MEM_MAIL').focus();
				document.getElementById('MEM_MAIL').select();
				document.getElementById("MAIL").innerText = "*�п�J�q�l�H�c";
			} else if (emailRegxp.test(email) != true) {
				// 					alert('�q�l�H�c�榡���~');
				document.getElementById('MEM_MAIL').focus();
				document.getElementById('MEM_MAIL').select();
				document.getElementById("MAIL").innerText = "*�q�l�H�c�榡���~";
			} else if (document.getElementById("MEM_PSW").value.length < 8) {
				document.getElementById("MEM_PSW").focus();
				document.getElementById("MEM_PSW").select();
				document.getElementById('PSW').innerText = "*�K�X�ݿ�J�K�Ӧr�H�W";
			} else if (document.getElementById("MEM_PSW").value != document
					.getElementById("MEM_PSW_1").value) {
				// 				alert("�K�X��J���~");
				document.getElementById("MEM_PSW_1").focus();
				document.getElementById("MEM_PSW_1").select();
				document.getElementById('PSW_1').innerText = "*�K�X��J���~";
			} else if (document.getElementById("MEM_NAME").value == '') {
				// 				alert("�п�J�t�ӦW��");
				document.getElementById("MEM_NAME").focus();
				document.getElementById("MEM_NAME").select();
				document.getElementById("NAME").innerText = "*�п�J�m�W";
			}else if (phone == '') {
				// 				alert("�п�J�q�ܸ��X");
				document.getElementById("MEM_PHONE").focus();
				document.getElementById("MEM_PHONE").select();
				document.getElementById("PHONE").innerText = "*�п�J�q��";
			}else if(document.getElementById("datepicker3").value.length == ''){
				document.getElementById("datepicker3").focus();
				
				document.getElementById("BIRTH").innerText = "*�п�ܥͤ�";
			} else if (phonetest.test(phone) != true) {
				// 					alert("phone��J���~");
				document.getElementById("MEM_PHONE").focus();
				document.getElementById("MEM_PHONE").select();
				document.getElementById("PHONE").innerText = "*�п�J���T�q��";
			}  else if (!b) {
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
<!-- 	<script src="https://code.jquery.com/jquery.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>