<%@ page contentType="text/html; Charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.goods_rent.model.*"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic野餐網</title>
<jsp:include page="/mustinclude/head.jsp" />
</head>
<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container-fulid">
		<div class="row">
			<div class="col-sm-8 col-sm-push-2" style="background: white;">
				<c:if test="${empty list}">
					<h3>恭喜您! 野餐團新增完成囉~</h3>
					<div class="col-sm-push-2">
						<br> <img
							src="<%=request.getContextPath()%>/images/00b12864666b.JPG"><br>
					</div>
				</c:if>
				<c:if test="${not empty list}">
					<h3>恭喜您! 野餐團新增完成囉~我們為為您準備了一些可供租賃的商品</h3>
					<c:forEach var="goods_rentVO" items="${list}">
						<div class="col-sm-4">
							<form action="<%= request.getContextPath() %>/orderde_detail/orderde_detail.do" method="Post">
								 <img src="<%=request.getContextPath( )%>/Image/?table=GOODS_RENT&picturename=${goods_rentVO.getGr_img()}" alt="">
									<div class="caption">
										<h2>${goods_rentVO.getGr_name()}</h2>
										<p>${goods_rentVO.getGr_info()}</p>
										<p>NT ${goods_rentVO.getGr_price()} 元</p>
										<button type="submit" class="btn btn-block btn-xs"
											value="Submit" style="font-size:28px">
											<span class="glyphicon glyphicon-shopping-cart"
												aria-hidden="true"></span>
										</button>
										 <input type="hidden" name="Picnic_no" value="${sessionScope.picnic_no}">
										
										<input type="hidden" name="gr_no" value="${goods_rentVO.getGr_no() }">
										<input type="hidden" name="action" value="insertintocartC">
										<input type="hidden" name="amount" value="1">
									</div>
								
							</form>
						</div>
					</c:forEach>
				</c:if>
			</div>

		</div>

		<div class="row">
			<div class="col-sm-8 col-sm-push-2" style="background: white;">
				<br>
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<a href="<%=request.getContextPath()%>/index.jsp"
							class="btn btn-default" role="button">帶我回首頁</a>
					</div>
					<div class="btn-group">
						<a href="<%=request.getContextPath()%>/buycart/moafirst.jsp?first=first"
							class="btn btn-default" role="button">帶我到預購商品區</a>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-push-4">
				<jsp:include page="/mustinclude/footer.jsp" /></div>
		</div>
	</div>
</body>
</html>