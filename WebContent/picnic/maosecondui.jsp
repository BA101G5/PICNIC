<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic���\��</title>
<jsp:include page="/mustinclude/head.jsp" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<jsp:useBean id="goods_sellSvc" scope="page"
	class="com.goods_sell.model.Goods_SellService" />



<style>
.banner {
	float: left;
	padding-left: 40px;
	padding: 20px;
}

.banner-top {
	clear: left;
}

.caption {
	text-align: center;
}

.btn {
	margin: 0px;
	margin-bottom: 0px;
}

.thumbnail {
	height: 250px;
	text-align: center;
	line-height: 80px;
	margin-top: 0px;
	bgcolor: white;
}

.breadcrumb {
	margin-top: -20px;
	background: skyblue;
}
</style>
</head>
<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-8 col-sm-push-2">
				<ol class="breadcrumb">
					<li><a href="<%=request.getContextPath()%>/index.jsp">����</a></li>
					<li><a href="#" class="active">�w�ʰӫ~</a></li>

				</ol>
			</div>
			<div class="row">

				<div class="col-sm-12 col-sm-push-1 ">

					<div class="row">
						<div class="col-sm-8 col-sm-push-1">
							<div id="carousel-id" class="carousel slide" data-ride="carousel">
								<ol class="carousel-indicators">
									<li data-target="#carousel-id" data-slide-to="0" class=""></li>
									<li data-target="#carousel-id" data-slide-to="1" class=""></li>
									<li data-target="#carousel-id" data-slide-to="2" class="active"></li>
								</ol>
								<div class="carousel-inner">
									<div class="item">
										<img
											src="https://www.kpm-berlin.com/en/porcelain-shop/media/catalog/category/kurland_2-2800x700.jpg"
											alt="">
									</div>
									<div class="item">
										<img
											src="https://www.kpm-berlin.com/en/porcelain-shop/media/catalog/category/rocaille_2-2800x700.jpg"
											alt="">
									</div>
									<div class="item active">
										<img
											src="https://www.kpm-berlin.com/en/porcelain-shop/media/catalog/category/feldblume_2-2800x700.jpg"
											alt="">
									</div>
								</div>
								<a class="left carousel-control" href="#carousel-id"
									data-slide="prev"><span
									class="glyphicon glyphicon-chevron-left"></span></a> <a
									class="right carousel-control" href="#carousel-id"
									data-slide="next"><span
									class="glyphicon glyphicon-chevron-right"></span></a>
							</div>
						</div>
					</div>
					<div class="col-sm-8 col-sm-push-2">
						<div class="collapse navbar-collapse navbar-ex1-collapse">
							<ul class="nav navbar-nav side-nav">
								<li><a href="#">
										<form METHOD="post" ACTION="<%=request.getContextPath()%>"
											onclick="submit()">
											<p>����</p>
										</form>
								</a></li>
								<li><a href="#">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>"
											onclick="submit()">
											<p>����</p>
										</FORM>
								</a></li>
								<li><a href="#">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>"
											onclick="submit()">
											<p>���\��</p>
										</FORM>
								</a></li>					
								<li><a href="#">
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>"
											onclick="submit()">
											<p>����ӫ~</p>
										</FORM>
								</a></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-8 col-sm-push-2">
						<div class="col-sm-3  ">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">���P�ӫ~</h3>
								</div>
								<table class="table">
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="col-sm-9">
							<c:if test="${not empty goods_sellSvc }">
								<%@ include file="/buycart/page.file"%>
								<c:forEach var="goods_sellVO" items="${goods_sellSvc.getAll()}"
									begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<div class="col-sm-12">
										<div class="thumbnail"  style="display: inline-block;" ;>
											<img
												src="<%=request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.getGs_no()}"
												style="display: inline-block; height: 200px; width: 200px;">
											<div style="display: inline-block;">
												<h2>${goods_sellVO.getGs_name()}</h2>
												<p>
												<table style="display: inline-block; ">
													<tr>
														<td>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
																<button type="submit" class="btn btn-info btn-lg"
																	style="width: 150px; height: 40px; font-size: 20px;">${goods_sellVO.getGs_price()}</button>
																<input type="hidden" name="gsno"
																	value="${goods_sellVO.getGs_no()}"> <input
																	type="hidden" name="action" value="getOne">
															</FORM>
														</td>	
														<td>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
																<button type="submit" class="btn btn-default btn-xs"
																	value="Submit">
																	<span class="glyphicon glyphicon-shopping-cart"
																		aria-hidden="true"></span>
																</button>
																<input type="hidden" name="gs_no"
																	value="${goods_sellVO.getGs_no()}"> <input
																	type="hidden" name="action" value="insertintocartA">
																<input type="hidden" name="amount" value="1">
															</FORM>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
										<%@ include file="/buycart/page2.file"%>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</div>
				<div class="row ">
					<div class="col-sm-10 col-sm-push-1 ">
						<div class="col-sm-10 col-sm-push-1 ">
							<div class="btn-group btn-group-justified ">
								<a href="# " class="btn btn-default " role="button ">�^���D</a>
							</div>
							<div class="col-sm-11 col-sm-push-3 ">
								<jsp:include page="/mustinclude/footer.jsp" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/mustinclude/chatroom.jsp" />
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>