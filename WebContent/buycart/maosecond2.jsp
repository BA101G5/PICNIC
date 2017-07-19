<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>
<%@ page import="java.util.*"%>

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
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

<style>
.collapse {
	margin: -15px;
}

.list-group {
	margin-top: 15px;
}

.btn {
	width: 50px;
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
					<li><a href="#" class="active">�ӫ~</a></li>
				</ol>
			</div>

			<%-- ���~��C --%>
			<c:if test="${not empty errorMsgs}">
				<font color='red'>�Эץ��H�U���~:
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
				</font>
			</c:if>

			<div class="col-sm-8 col-sm-push-2">
				<div class="collapse navbar-collapse navbar-ex1-collapse">
					<ul class="nav navbar-nav side-nav">
						<li><a href="#">
								<form METHOD="post" ACTION="<%=request.getContextPath()%>"
									onclick="submit()">
									<p>���\</p>
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
									<p>����</p>
								</FORM>
						</a></li>
						<li><a href="#">
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>"
									onclick="submit()">
									<p>��L</p>
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
				<div class="col-sm-9 ">
				
					<c:if test="${not empty goods_sellSvc }">

						<c:forEach var="goods_sellVO" items="${goods_sellSvc.getAll()}" >
							<div class="col-sm-12 ">
								<div class="thumbnail">
									<img
										src="<%=request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.getGs_no()}"
										style="display: inline-block; height: 200px; width: 200px;">
									<div style="display: inline-block;">
										<h2>${goods_sellVO.getGs_name()}</h2>
										<p>
										<table>
											<tr>
												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
														<button type="submit" class="btn btn-info btn-lg"
															style="width: 250px; height: 40px; font-size: 20px;">${goods_sellVO.getGs_price()}</button>
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


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>