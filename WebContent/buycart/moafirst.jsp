<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic野餐網</title>
<jsp:include page="/mustinclude/head.jsp" />

<jsp:useBean id="goods_sellSvc" scope="page"
	class="com.goods_sell.model.Goods_SellService" />
<%	String whichPage=request.getParameter("whichPage");
   String first = request.getParameter("first");
if(first == null&& whichPage == null){
	Set<String> typeset = (Set<String>) session.getAttribute("typeSet");
	pageContext.setAttribute("typeset", typeset);}
%>

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
	height: 350px;
	text-align: center;
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
					<li><a href="<%=request.getContextPath()%>/index.jsp">首頁</a></li>
					<li><a href="#" class="active">預購商品</a></li>

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
					<div class="col-sm-8  col-sm-push-1">
						<div class="btn-group btn-group-justified" role="group"
							aria-label="...">
							<div class="btn-group" role="group">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
									<button type="submit" class="btn btn-default">器具</button>
									<input type="hidden" name="action" value="selectByType">
									<input type="hidden" name="type" value="A">
								</form>

							</div>
							<div class="btn-group" role="group">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
									<button type="submit" class="btn btn-inverse">食物</button>
									<input type="hidden" name="action" value="selectByType">
									<input type="hidden" name="type" value="B">
								</FORM>
							</div>
							<div class="btn-group" role="group">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
									<button type="submit" class="btn btn-default">野餐墊</button>
									<input type="hidden" name="action" value="selectByType">
									<input type="hidden" name="type" value="C">
								</FORM>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-8 col-sm-push-2">


					<c:if test="${typeset==null }">
						<div class="col-sm-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">熱門商品</h3>
								</div>
								
							</div>
						</div>
					</c:if>

					<c:if test="${typeset!=null }">
						<div class="col-sm-3">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">提供廠商</h3>
								</div>
								<table class="table">
									<c:forEach var="mfcount" items="${typeset}">
										<tr>
											<td><a href="#"><form method="post"
													action="<%=request.getContextPath()%>/goods_sell/goods_sell.do"
													onclick="submit()">
													<p>${mfcount}</p>
													<input type="hidden" name="action" value="selectByMfype">
													<input type="hidden" name="mfcount" value="${mfcount}">
													<input type="hidden" name="mfcount" value="${mfcount}">
												</form></a></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</c:if>
				<jsp:include page="/buycart/Goods_sellitem.jsp" />
			</div>
				</div>
		</div>

	<div class="row ">
		<div class="col-sm-10 col-sm-push-1 ">
			<div class="col-sm-10 col-sm-push-1 ">
				<div class="btn-group btn-group-justified ">
					<a href="<%=request.getRequestURI()%>?whichPage="
						class="btn btn-default " role="button ">回標題</a>
				</div>
				<div class="col-sm-11 col-sm-push-3 ">
					<jsp:include page="/mustinclude/footer.jsp" />
				</div>
			</div>
		</div>
	</div>
	</div>
<jsp:include page="/mustinclude/chatroom.jsp" />
</body>
</html>