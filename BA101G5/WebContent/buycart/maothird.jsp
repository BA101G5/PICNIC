<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>
<%
	Goods_SellVO goods_sellVO = (Goods_SellVO) request.getAttribute("goods_sellVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<jsp:include page="/mustinclude/head.jsp" />
<title>Title Page</title>
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
<p>${goods_sellVO.getGs_name()}</p><br>
<p>${goods_sellVO.getGs_price()}</p><br>
<p>${goods_sellVO.getGs_info()}</p><br>
</div>
<div class="col-sm-4 ">
<form method="post" action="<%request.getContextPath(); %>/orderde_detail/orderde_detail.do" >
	<Button type="submit">放入購物車</Button>
	<input type="hidden" name="action" values="insertA">
	<input type="hidden" name="aoeuaoeuaoeu " values=" aoeu">
</form>
<form method="post" action="<%request.getContextPath(); %>/orderde_detail/orderde_detail.do" >
	<Button type="submit">立即結帳</Button>
	<input type="hidden" name="action" values="insertB">
	<input type="hidden" name="aoeuaoeu " values=" aoeu">
</form>
</div>
</div>
</div>
<div class="col-sm-8 col-sm-push-4">
<jsp:include page="/mustinclude/footer.jsp" />
</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>