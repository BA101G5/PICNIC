<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.goods_sell.model.Goods_SellService"%>
<%@page import="com.goods_sell.model.*"%>
<%@page import="com.goods_rent.controller.Goods_rentServlet"%>
<%@page import="com.goods_rent.model.*"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<jsp:include page="/mustinclude/head.jsp" />
<title>Picnic野餐網</title>
<%
	Goods_SellService goods_sellSvc = new Goods_SellService();
	List<Goods_SellVO> goods_sellList = goods_sellSvc.getAll();
	Goods_RentService goods_rentSvc = new Goods_RentService();
	List<Goods_RentVO> goods_rentList = goods_rentSvc.getAll();
	pageContext.setAttribute("goods_rentList", goods_rentList);
	pageContext.setAttribute("goods_sellList", goods_sellList);
	session.setAttribute("listGr", request.getParameter("listOrderde_DetailVO"));
	int i = 0;
	int j = 1;
%>

<c:if test="${listOrderde_DetailVO.size( )==1&&not emptylistGs}">
	<%
		i = 0;
			j = 0;
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
				list3[
<%=i%>
	].innerHTML = "NT$ " + grcount;

				list[
<%=i%>
	]
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

									document.getElementsByClassName("lc")[
<%=i%>
	].innerHTML = "NT$ "
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
<%=j%>
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

								document.getElementsByClassName("lc")[
<%=j%>
	].innerHTML = "NT$ "
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
<style>table5_3 table {
 width:100%;
 margin:15px 0;
 border:0;
}
.table5_3 th {
width:100%;
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
.table5_3 tr:last-child td:first-child{
  border-bottom-left-radius: 10px;
}
.table5_3 tr:first-child th:first-child{
  border-top-left-radius: 10px;
}
</style>
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
										<a href="<%=request.getContextPath()%>/buycart/moafirst.jsp"
											class="btn btn-default" role="button">帶我到預購商品區</a>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${not empty ListPicnicVO}">
						<h3>請選擇野餐團</h3>
						<form method="POST" id="myForm"
							action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
							<input type="hidden" name="action" value="get_gr_no"> 
							<select class="selectpicker" name="picnic_no" onchange="submit()">
						<c:if test="${not empty listOrderde_DetailVO||empty requestScope.picnic_no}">
									<option value=" " selected></option>
								</c:if>
								<c:if test="${not empty listOrderde_DetailVO}">
									<c:forEach var="PicnicVO" items="${ListPicnicVO}">

										<c:if test="${PicnicVO.getPicnic_no() eq requestScope.picnic_no}">
											<option value="${PicnicVO.getPicnic_no()} " selected>${PicnicVO.getPicnic_name()}</option>
										</c:if>
										<c:if test="${PicnicVO.getPicnic_no() != requestScope.picnic_no}">
											<option value=" ${PicnicVO.getPicnic_no()}">${PicnicVO.getPicnic_name()}</option>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${empty listOrderde_DetailVO}">
									<c:forEach var="PicnicVO" items="${ListPicnicVO}">
										<option value=" ${PicnicVO.getPicnic_no()}">${PicnicVO.getPicnic_name()}</option>
									</c:forEach>
								</c:if>
							</select>
						</form>
						<br>
					</c:if>

					<form method="POST"
						action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
						<c:if test="${ not empty listOrderde_DetailVO|| not empty listGs}">
							<table class="table5_3">
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
						</c:if>
						<c:if
							test="${not empty listOrderde_DetailVO &&listOrderde_DetailVO.size( )>1}">


							<table class="table5_3">
								<thead>

									<tr>
										<th>租賃商品</th>
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
									<tr>
									</tr>

									<c:forEach var="Orderde_DetailVO"
										items="${listOrderde_DetailVO}" varStatus="loop">
										<c:if test="${Orderde_DetailVO.getGr_no() != null }">
											<tr>
												<c:forEach var="goods_rentVO" items="${goods_rentList}">
													<c:if
														test="${Orderde_DetailVO.getGr_no()==goods_rentVO.getGr_no()}">
														<td>${goods_rentVO.getGr_name() }</td>
													</c:if>
												</c:forEach>
												<td><input type="number" class="grnumber"
													id="gr${loop.index}"
													name="${Orderde_DetailVO.getOrderde_detailno()}amount"
													value="${Orderde_DetailVO.getOd_amount()}" min="1"></td>
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

						<c:if test="${not empty listGs }">

							<table class="table5_3">
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
											<c:forEach var="Goods_sellVO" items="${goods_sellList }">
												<c:if
													test="${ Goods_sellVO.getGs_no()==Orderde_DetailVO.getGs_no()}">
													<td>${ Goods_sellVO.getGs_name()}</td>
												</c:if>
											</c:forEach>
											<td><input type="number" class="gsnumber"
												id="gs${loop.index}"
												name="${Orderde_DetailVO.getOrderde_detailno()}amount"
												value="${Orderde_DetailVO.getOd_amount()}" min="1"></td>
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

						<c:if test="${not empty picnic_no}">
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

							<div class="btn-group btn-group-justified ">
								<div class="btn-group">
									<a href="<%=request.getContextPath()%>/index.jsp"
										class="btn btn-default" role="button">稍後結帳</a>
								</div>
								<div class="btn-group">
									<button type="submit" class="btn btn-default" value="Submit">結帳並成團</button>
								</div>
								<input type="hidden" name="action" value="finishorder">
								<input type="hidden" name="picnic_no" value="${param.picnic_no}">
								<input type="hidden" name="listGr"
									value="${listOrderde_DetailVO}">

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
	<jsp:include page="/mustinclude/chatroom.jsp" />
</body>
</html>