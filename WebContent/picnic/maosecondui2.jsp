<%@ page contentType="text/html; Charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.picnic.model.*"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<jsp:include page="/mustinclude/head.jsp" />
<title>Picnic野餐網</title>
</head>
<body>


	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container">
		<div class="row">

			<div class="col-sm-12 " style="background: white;">
				<div class="col-sm-8 col-sm-push-2">
					<h1>請重複確認您的資料</h1>
					<p>團名 :  ${sessionScope.picnic_name}</p>
					<p>地址 :  ${sessionScope.totaladdress}</p>
					<p>揪團日期 :  ${sessionScope.picnic_date}</p>
					<p>限制人數  :  ${sessionScope.people}</p>
					<br>

					<div class="btn-group btn-group-justified">
						<div class="btn-group">
							<a href="<%=request.getContextPath()%>/picnic/maosecondui.jsp"
								class="btn btn-default" role="button">修改</a>
						</div>
						<div class="btn-group">
							<form method="post"
								action="<%=request.getContextPath()%>/picnic/picnic.do">
								<button type="submit" class="btn btn-default" value="Submit">確認送出</button>
								<input type="hidden" name="action" value="insert">
							</form>
						</div>
						
					</div>
					<br>
					<br>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-push-2">
				<jsp:include page="/mustinclude/footer.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="/mustinclude/chatroom.jsp" />

</body>
</html>