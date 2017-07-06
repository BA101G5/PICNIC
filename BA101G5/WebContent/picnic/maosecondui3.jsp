<%@ page contentType="text/html; Charset=UTF-8" pageEncoding="Big5"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<jsp:include page="/mustinclude/head.jsp" />
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
	<div class="Container">
		<div class="row">
			<div class="col-sm-8 col-sm-push-2">
				<P>新增完成.我們為為您準備了一些可供租賃的商品</P>
				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<a href="<%=request.getContextPath()%>/index.jsp"
							class="btn btn-default" role="button">帶我回首頁</a>
					</div>
					<div class="btn-group">
						<a href="<%=request.getContextPath()%>/buycart/moafirst.jsp"
							class="btn btn-default" role="button">帶我到預購商品區</a>
					</div>
					<div class="btn-group">
						<form method="post"
							action="<%=request.getContextPath()%>/goods_rent/goods_rent.do">
							<button type="submit" class="btn btn-default" role="button">租賃這些商品</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-push-4">
				<jsp:include page="/mustinclude/footer.jsp" /></div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>