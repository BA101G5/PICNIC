<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<jsp:include page="/mustinclude/head.jsp" />
<title>Picnic野餐網</title>
<style>
table5_3 table {
	width: 100%;
	margin: 15px 0;
	border: 0;
}

.table5_3 th {
	width: 100%;
	background-color: #87CEFA;
	color: #000000
}

.table5_3, .table5_3 th, .table5_3 td {
	font-size: 0.95em;
	text-align: center;
	padding: 4px;
	border-collapse: collapse;
}

.table5_3 th, .table5_3 td {
	border: 1px solid #ffffff;
	border-width: 1px 0 1px 0
}

.table5_3 tr {
	border: 1px solid #ffffff;
}

.table5_3 tr:nth-child(odd) {
	background-color: #d7eefd;
}

.table5_3 tr:nth-child(even) {
	background-color: #ffffff;
}
/* .table5_3 tr:last-child td:last-child{ */
/*   border-bottom-right-radius: 10px; */
/* } */
.table5_3 tr:first-child th:last-child {
	border-top-right-radius: 10px;
}

.table5_3 tr:last-child td:first-child {
	border-bottom-left-radius: 10px;
}

.table5_3 tr:first-child th:first-child {
	border-top-left-radius: 10px;
}
</style>
</head>

<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container">
		<div class="row">
			<table class="table5_3">
				<div class="col-sm-8 col-sm-push-4">
				<tr>
					<td>
						<div class="col-sm-8 col-sm-push-2">
							<div style="background: white;">
								<img
									src="<%= request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.getGs_no()}"
									height="420px" width="420px">
								<p>${goods_sellVO.getGs_name()}</p>
								<br>
								<p>價格: ${goods_sellVO.getGs_price()}</p>
								<br>
								<p>${goods_sellVO.getGs_info()}</p>
								<br>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td><br>
						<div class="list-group">
							<a href="#" class="list-group-item"><form method="post"
									action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
									<p>
										<input type=text name="amount" size="2">數量
										<c:if test="${not empty errorMsgs}">
											<font color="red">${errorMsgs.amount}<font></font>
										</c:if>
									</p>
									<Button type="submit" class="btn btn-default">放入購物車</Button>
									<input type="hidden" name="gs_no"
										value="${goods_sellVO.getGs_no()}"> <input
										type="hidden" name="action" value="insertintocartB">

								</form></a> <a href="#" class="list-group-item"><form method="post"
									action="<%=request.getContextPath()%>/buycart/maosecond.jsp">
									<Button type="submit" class="btn btn-default">下次再買</Button>
								</form></a>

						</div></td>
                 	</tr>
				</div>
			</table>
		</div>
	</div>
	<div class="col-sm-8 col-sm-push-4">
		<jsp:include page="/mustinclude/footer.jsp" />
	</div>
	<jsp:include page="/mustinclude/chatroom.jsp" />
</body>
</html>
