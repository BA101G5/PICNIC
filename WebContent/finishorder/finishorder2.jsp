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

				<h3>恭喜您! 訂單結帳完成囉~</h3>
				<div class="col-sm-push-2">
					<br> <img
						src="<%=request.getContextPath()%>/images/00b12864666b.JPG"><br>
				</div>
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