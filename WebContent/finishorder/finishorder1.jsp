<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<jsp:include page="/mustinclude/head.jsp" />
<title>Picnic野餐網</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<%
	int i = 0;
%>
<c:if test="${not empty listOrderde_DetailVO}">
	<%
		i = i + 1;
	%>

</c:if>
<script type="text/javascript">
	var grcount = 0;
	var gscount = 0;
	var tlcount = 0;
	var plcount = 0;
	function doFirst() {

		// 建立每個按鈕的事件聆聽器
		var list = document.getElementsByClassName("grnumber");
		var list2 = document.getElementsByClassName("gsnumber");
		var list3 = document.getElementsByClassName("lc");
		try {
			var placep = document.getElementsByClassName("adc")[0].innerHTML;
			plcount = Number(placep.trim().split(" ", 3)[1]);

		} catch (err) {
		}
		try {
			for (var i = 0; i < list.length; i++) {
				var number = list[i].value;
				var price = list[i].parentNode.nextSibling.nextSibling.innerHTML;
				price = price.trim().split(" ", 3)[1];
				list[i].parentNode.nextSibling.nextSibling.innerHTML = "NT$ "
						+ price / number;
				price = price / number;
				var count = price * number;
				grcount = grcount + count;
				list[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML = "NT$ "
						+ count;
				list3[0].innerHTML = "NT$ " + grcount;

				list[i]
						.addEventListener(
								"click",
								function() {
									var preprice = document.querySelector("#"
											+ this.id).parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML
									preprice = preprice.trim().split(" ", 3)[1];
									grcount = grcount - preprice;

									var amount = document.querySelector("#"
											+ this.id).value;
									var price = document.querySelector("#"
											+ this.id).parentNode.nextSibling.nextSibling.innerHTML;
									price = price.trim().split(" ", 3)[1];
									price = price * amount;
									grcount = grcount + price;
									document.querySelector("#" + this.id).parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML = "NT$ "
											+ price;

									document.getElementsByClassName("lc")[0].innerHTML = "NT$ "
											+ grcount;
									tlcount = gscount + grcount + plcount;
									document.getElementsByClassName("tlc")[0].innerHTML = "總計NT$"
											+ tlcount;
								}, false);

			}
		} catch (err) {
		}
		for (var i = 0; i < list2.length; i++) {
			var number = list2[i].value;
			var price = list2[i].parentNode.nextSibling.nextSibling.innerHTML;
			price = price.trim().split(" ", 3)[1];
			list2[i].parentNode.nextSibling.nextSibling.innerHTML = "NT$ "
					+ price / number;
			price = price / number;
			var count = price * number;
			gscount = gscount + count;
			list2[i].parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML = "NT$ "
					+ count;
			list3[
<%=i%>
	].innerHTML = "NT$ " + gscount;
			list2[i]
					.addEventListener(
							"click",
							function() {
								var preprice = document.querySelector("#"
										+ this.id).parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML
								preprice = preprice.trim().split(" ", 3)[1];
								gscount = gscount - preprice;
								var amount = document.querySelector("#"
										+ this.id).value;
								var price = document.querySelector("#"
										+ this.id).parentNode.nextSibling.nextSibling.innerHTML;
								price = price.trim().split(" ", 3)[1];
								price = price * amount;
								gscount = gscount + price;
								document.querySelector("#" + this.id).parentNode.nextSibling.nextSibling.nextSibling.nextSibling.innerHTML = "NT$ "
										+ price;

								document.getElementsByClassName("lc")[1].innerHTML = "NT$ "
										+ gscount;
								tlcount = gscount + grcount + plcount;
								document.getElementsByClassName("tlc")[0].innerHTML = "總計NT$"
										+ tlcount;
							}, false);
		}
		tlcount = gscount + grcount + plcount;
		document.getElementsByClassName("tlc")[0].innerHTML = "總計NT$" + tlcount;

	}

	window.addEventListener('load', doFirst, false);
</script>
</head>

<body>


	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container-fluid">
		<div class="row ">

			<div class="col-sm-8 col-sm-push-2">
				<div style="background: white;">
					<c:if test="${empty ListPicnicVO}"> 您並沒有能完成訂單的野餐團喔~
				
				
					<div class="row">
							<div class="col-sm-8 col-sm-push-2" style="background: white;">
								<br>
								<div class="btn-group btn-group-justified">
									<div class="btn-group">
										<a href="<%=request.getContextPath()%>/index.jsp"
											class="btn btn-default" role="button">直接成團</a>
									</div>
									<div class="btn-group">
										<a href="<%=request.getContextPath()%>/buycart/moafirst.jsp"
											class="btn btn-default" role="button">帶我到預購商品區</a>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty ListPicnicVO}">
						<h1>請選擇野餐團</h1>

						<form method="POST" id="myForm"
							action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
							<select name="picnic" id="input" class="form-control"
								required="required" onchange="submit()">
								<option value=" "></option>
								<c:forEach var="PicnicVO" items="${ListPicnicVO}">
									<option value=" ${PicnicVO.getPicnic_no()}"
										<c:if test="${PicnicVO.getPicnic_no() == sessionScope.picnic_no}"></c:if>>${PicnicVO.getPicnic_name()}</option>
								</c:forEach>
							</select> <input type="hidden" name="action" value="get_gr_no">

						</form>
						<br>
					</c:if>
					<form method="POST"
						action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
						<c:if test="${not empty listOrderde_DetailVO}">
							<table class="table table-hover">
								<caption>購物清單</caption>
								<thead>
									<tr>
										<th>地點名稱</th>
										<th></th>
										<th></th>
										<th></th>

										<th>價格</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="Orderde_DetailVO"
										items="${listOrderde_DetailVO}">
										<c:if test="${Orderde_DetailVO.getGr_no() == null}">
											<tr>
												<td>${Orderde_DetailVO.getOd_place()}<input
													type="hidden" name="address"
													value="${Orderde_DetailVO.getOd_place()}"></td>
												<td></td>
												<td></td>
												<td></td>
												<td class="adc">NT$ ${Orderde_DetailVO.getOd_price()}</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<c:forEach var="Orderde_DetailVO" items="${listOrderde_DetailVO}">
								<c:if
									test=" ${Orderde_DetailVO.getOd_price()==0 && Orderde_DetailVO.getGr_no()==null}">
									<table class="table table-hover">
										<thead>

											<tr>
												<th>租賃商品</th>
											</tr>
											<tr>
												<th>商品名稱</th>
												<th>數量</th>
												<th>單價</th>
												<th>小計</th>
												<th><th>
											
											</tr>
										</thead>
										<tbody>
											<tr>
											</tr>

											<c:forEach var="Orderde_DetailVO"
												items="${listOrderde_DetailVO}" varStatus="loop">
												<c:if test="${Orderde_DetailVO.getGr_no() != null }">
													<tr>
														<td>${Orderde_DetailVO.getGr_no()}</td>

														<td><input type="number" class="grnumber"
															id="gr${loop.index}"
															name="${Orderde_DetailVO.getGr_no()}amount"
															value="${Orderde_DetailVO.getOd_amount()}" min="0"></td>
														<td>NT$ ${Orderde_DetailVO.getOd_price()}</td>
														<td>NT$ ${Orderde_DetailVO.getOd_price()}</td>
														<td><a
															href="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do?action=delete&delete=${Orderde_DetailVO.getOrderde_detailno()}"
															class="btn btn-default" role="button"
															onmouseOver="window.status='none'return true">取消</a></td>
														<td>
													
													</tr>
												</c:if>
											</c:forEach>
										</tbody>

										<thead>
											<tr>
												<th></th>
												<th></th>
												<th></th>
												<th>合計</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<td></td>
												<td></td>
												<td></td>
												<td class="lc">NT$ 0</td>
											
											<tr>
										
										</tbody>
									</table>

								</c:if>
							</c:forEach>
						</c:if>

						<c:if test="${not empty listGs }">

							<table class="table table-hover">
								<thead>
									<tr>
										<th>預購商品</th>
									</tr>
									<tr>
										<th>商品名稱</th>
										<th>數量</th>
										<th>單價</th>
										<th>小計</th>
										<th>
										
										<th>
									
									</tr>
								</thead>
								<tbody>
									<c:forEach var="Orderde_DetailVO" items="${listGs}"
										varStatus="loop">
										<tr>

											<td>${Orderde_DetailVO.getGs_no()}</td>
											<td><input type="number" class="gsnumber"
												id="gs${loop.index}"
												name="${Orderde_DetailVO.getOrderde_detailno()}amount"
												value="${Orderde_DetailVO.getOd_amount()}" min="0"></td>
											<td>NT$ ${Orderde_DetailVO.getOd_price()}</td>
											<td>NT$ ${Orderde_DetailVO.getOd_price()}</td>
											<td><a
												href="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do?action=delete&delete=${Orderde_DetailVO.getOrderde_detailno()}"
												class="btn btn-default" role="button"
												onmouseOver="window.status='none'return true">取消</a>
											
											<td>
										
										</tr>
									</c:forEach>


									<tr>
										<th></th>
										<th></th>
										<th></th>
										<th>合計</th>

									</tr>


									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td class="lc">NT$ 0</td>
									</tr>
									<tr>
									</tr>


								</tbody>
							</table>
						</c:if>

						<c:if test="${not empty ListPicnicVO}">
							<table class="table table-hover">
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>

									<td class="tlc">總計NT$ 0</td>
								</tr>

							</table>
						</c:if>


						<c:if test="${not empty ListPicnicVO}">
							<div class="btn-group btn-group-justified ">
								<div class="btn-group">
									<a href="<%=request.getContextPath()%>/index.jsp"
										class="btn btn-default" role="button">稍後結帳</a>
								</div>
								<div class="btn-group">
									<button type="submit" class="btn btn-default" value="Submit">結帳並成團</button>
								</div>
								<input type="hidden" name="action" value="finishorder">
							</div>
							<br>
						</c:if>
					</form>
				</div>

			</div>
		</div>
	</div>
	<div class="col-sm-8 col-sm-push-4">
		<jsp:include page="/mustinclude/footer.jsp" />
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js">
		
	</script></
												body>
</html>
