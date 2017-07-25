<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>買點數</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<style>
.title {
	/*border: solid;*/
	text-align: center;
	margin-top: 20px;
}

p {
	font-size: 16px;
}

img {
	width: 250px;
	height: 150px;
}

.img-div {
	/*border: solid;*/
	margin-top: 20px;
	padding: 10px;
	display: flex;
	justify-content: space-around;
}

hr {
	margin-top: : 20px;
}

.credit {
	margin-top: 20px;
	padding: 10px;
	display: flex;
	justify-content: center;
}

input {
	font-family: Tahoma;
	font-size: 24px;
	text-align: center;
	width: 150px;
}

.circle {
	border: 3px solid #ccc;
	width: 30px;
	height: 30px;
	line-height: 30px;
	margin-top: 20px;
	text-align: left;
	background-position: center;
	background-size: 40px 40px;
	border-radius: 50%;
}

.button {
	display: inline-block;
	padding: 15px 25px;
	font-size: 24px;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	outline: none;
	color: #fff;
	background-color: #4CAF50;
	border: none;
	border-radius: 5px;
	box-shadow: 0 9px #999;
}

.button:hover {
	background-color: #3e8e41
}

.button:active {
	background-color: #3e8e41;
	box-shadow: 0 5px #666;
	transform: translateY(4px);
}
</style>

</head>
<body>
	<Form method="post" id="form"
		action="<%=request.getContextPath()%>/buy_record/buy_record.do">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-10 col-sm-push-1 title">
					<h1>
						<b>輸入信用卡卡號及選擇購買野餐幣點數</b>
					</h1>
					<p>
						提醒您，此頁面所販售的「點數」以台幣 1 : 1 換算，<br>請適量購買。<br>
					</p>
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1 img-div">
					<img src="images/visa.png"> <img src="images/master.png">
					<img src="images/jcb.png">
				</div>


				<div class="col-xs-12 col-sm-10 col-sm-push-1 credit">
					<input type="text" maxlength="4" id="card1">&nbsp;-&nbsp; <input
						type="text" maxlength="4" id="card2">&nbsp;-&nbsp; <input
						type="text" maxlength="4" id="card3">&nbsp;-&nbsp; <input
						type="text" maxlength="4" id="card4"> <font color="red"
						id="errorMsgs"></font>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1"
					style="display: flex; justify-content: center;">
					<div class="col-xs-12 col-sm-6 col-sm-offset-1 circle"></div>
					<div class="col-xs-12 col-sm-6"
						style="text-align: center; font-size: 24px; padding-top: 15px;">
						<b>$ 100</b>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1"
					style="display: flex; justify-content: center;">
					<div class="col-xs-12 col-sm-6 col-sm-offset-1 circle"></div>
					<div class="col-xs-12 col-sm-6"
						style="text-align: center; font-size: 24px; padding-top: 15px;">
						<b>$ 200</b>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1"
					style="display: flex; justify-content: center;">
					<div class="col-xs-12 col-sm-6 col-sm-offset-1 circle"></div>
					<div class="col-xs-12 col-sm-6"
						style="text-align: center; font-size: 24px; padding-top: 15px;">
						<b>$ 500</b>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1"
					style="display: flex; justify-content: center;">
					<div class="col-xs-12 col-sm-6 col-sm-offset-1 circle"></div>
					<div class="col-xs-12 col-sm-6"
						style="text-align: center; font-size: 24px; padding-top: 15px;">
						<b>$ 1000</b>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1"
					style="display: flex; justify-content: center;">
					<div class="col-xs-12 col-sm-6 col-sm-offset-1 circle"></div>
					<div class="col-xs-12 col-sm-6"
						style="text-align: center; font-size: 24px; padding-top: 15px;">
						<b>$ 2000</b>
					</div>
				</div>
				<div class="col-xs-12 col-sm-10 col-sm-push-1">
					<hr>
				</div>
				<div class="col-xs-12 col-sm-10"
					style="padding-left: 50px; margin-bottom: 10%; text-align: center;">
					<input type="button" name="" value="送出" class="button">
				</div>
				<input type="hidden" name="BR_CASH" value="BR_CASH" id="BR_CASH">
				<input type="hidden" name="BR_DATE" value="BR_DATE "id="BR_DATE">
				<input type="hidden" name="MEM_NO" value="${gVO.MEM_NO}" id="MEM_NO">
				<input type="hidden" name="action" value="insert">
				
			</div>
		</div>
	</Form>
	<script>
		$('input').keyup(function(e) {
			if ($(this).val().length == $(this).attr('maxlength'))
				$(this).next(':input').focus();
		});
	</script>
	<script>
		document.getElementsByClassName("circle")[0]
				.addEventListener(
						"click",
						function() {
							document.getElementsByClassName("circle")[0].style.backgroundImage = "url('images/coco.jpg')";
							document.getElementsByClassName("circle")[1].style.backgroundImage = "";
							document.getElementsByClassName("circle")[2].style.backgroundImage = "";
							document.getElementsByClassName("circle")[3].style.backgroundImage = "";
							document.getElementsByClassName("circle")[4].style.backgroundImage = "";
						}, false);

		document.getElementsByClassName("circle")[1]
				.addEventListener(
						"click",
						function() {
							document.getElementsByClassName("circle")[1].style.backgroundImage = "url('images/coco.jpg')";
							document.getElementsByClassName("circle")[0].style.backgroundImage = "";
							document.getElementsByClassName("circle")[2].style.backgroundImage = "";
							document.getElementsByClassName("circle")[3].style.backgroundImage = "";
							document.getElementsByClassName("circle")[4].style.backgroundImage = "";
						}, false);

		document.getElementsByClassName("circle")[2]
				.addEventListener(
						"click",
						function() {
							document.getElementsByClassName("circle")[2].style.backgroundImage = "url('images/coco.jpg')";
							document.getElementsByClassName("circle")[1].style.backgroundImage = "";
							document.getElementsByClassName("circle")[0].style.backgroundImage = "";
							document.getElementsByClassName("circle")[3].style.backgroundImage = "";
							document.getElementsByClassName("circle")[4].style.backgroundImage = "";
						}, false);

		document.getElementsByClassName("circle")[3]
				.addEventListener(
						"click",
						function() {
							document.getElementsByClassName("circle")[3].style.backgroundImage = "url('images/coco.jpg')";
							document.getElementsByClassName("circle")[1].style.backgroundImage = "";
							document.getElementsByClassName("circle")[2].style.backgroundImage = "";
							document.getElementsByClassName("circle")[0].style.backgroundImage = "";
							document.getElementsByClassName("circle")[4].style.backgroundImage = "";
						}, false);

		document.getElementsByClassName("circle")[4]
				.addEventListener(
						"click",
						function() {
							document.getElementsByClassName("circle")[4].style.backgroundImage = "url('images/coco.jpg')";
							document.getElementsByClassName("circle")[1].style.backgroundImage = "";
							document.getElementsByClassName("circle")[2].style.backgroundImage = "";
							document.getElementsByClassName("circle")[3].style.backgroundImage = "";
							document.getElementsByClassName("circle")[0].style.backgroundImage = "";
						}, false);
	</script>
	<!-- credit card-->
	<script>
		function creditnumber() {
			var card = document.getElementById("card1").value;
			var c1 = new Array(16);
			var card2 = document.getElementById("card2").value;

			var card3 = document.getElementById("card3").value;

			var card4 = document.getElementById("card4").value;
			var card1 = card + card2 + card3 + card4;
			var sum = 0;
			for (var i = 0; i < c1.length; i++) {

				c1[i] = parseInt(card1.charAt(i));
				//分機數偶數
				if (i % 2 == 0) {
					c1[i] = c1[i] * 2;
				} else {
					c1[i] = c1[i] * 1;
				}
				if (c1[i] >= 10) {
					c1[i] = Math.floor(c1[i] / 10) + Math.floor(c1[i] % 10);
				}
				sum = sum + c1[i];
			}
			if (card.charAt(0) == '0') {
				return false;
			}
			if (card.charAt(0) == '1' && card == '1800') {
				return true;
			} else if (card.charAt(1) == '2' && card == '2131') {
				return true;
			} else if (card.charAt(1) == '3' && parseInt(card) >= 300
					&& parseInt(card) <= 399) {
				return true;
			} else if (card.charAt(1) == '5'
					&& parseInt(card.substring(0, 2)) >= 51
					&& parseInt(card.substring(0, 2)) <= 55) {
				return true;
			}

			console.log(sum);
			if (sum % 10 != 0) {
				return false;
			} else {
				return true;
			}

		}

		document.getElementsByClassName("button")[0]
				.addEventListener(
						'click',
						function() {

							if (document.getElementsByClassName("circle")[0].style.backgroundImage != '') {
								document.getElementById("BR_CASH").value = 100;
							}
							if (document.getElementsByClassName("circle")[1].style.backgroundImage != '') {
								document.getElementById("BR_CASH").value = 200;
							}
							if (document.getElementsByClassName("circle")[2].style.backgroundImage != '') {
								document.getElementById("BR_CASH").value = 500;
							}
							if (document.getElementsByClassName("circle")[3].style.backgroundImage != '') {
								document.getElementById("BR_CASH").value = 1000;
							}
							if (document.getElementsByClassName("circle")[4].style.backgroundImage != '') {
								document.getElementById("BR_CASH").value = 2000;
							}
							console
									.log(document.getElementById("BR_CASH").value);

							if (creditnumber() != true) {
								document.getElementById("errorMsgs").innerText = " * 信用卡號輸入錯誤";
							} else {
								
								form.submit();
							}
						}, false);
	</script>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
