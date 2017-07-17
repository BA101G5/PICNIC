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
						<p>請選擇野餐團</p>
						<form method="POST"
							action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
							<select name="picnic" id="input" class="form-control"
								required="required" onchange="submit()">
								<c:forEach var="PicnicVO" items="${ListPicnicVO}">
									<option value=" ${PicnicVO.getPicnic_no()}">${PicnicVO.getPicnic_name()}</option>
								</c:forEach>
							</select> <input type="hidden" name="action" value="get_gr_no">
						</form>
					</c:if>
					<c:if test="${not empty listOrderde_DetailVO}">
					<c:forEach var="Orderde_DetailVO" items="${listOrderde_DetailVO}">
					<p>${Orderde_DetailVO.getPicnic_no()}</P>
					</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-8 col-sm-push-4">
		<jsp:include page="/mustinclude/footer.jsp" />
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
