<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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
<title>Title Page</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>

	<%
		if (request.getMethod().toUpperCase().equals("POST")) {
			request.setCharacterEncoding("Big5");
			String address = request.getParameter("address");
			String area = request.getParameter("area");
			String tladdress = area + address;
			String date = request.getParameter("date");
			String people = request.getParameter("people");
			String name = request.getParameter("name");
			session.setAttribute("area", area);
			session.setAttribute("tladdress", tladdress);
			session.setAttribute("address", address);
			session.setAttribute("date", date);
			session.setAttribute("people", people);
			session.setAttribute("name", name);
		}
	%>

	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-push-2">
				<h1>請重複確認您的資料</h1>
				<p>團名 : ${name}</p>
				<p>地址 : ${tladdress}</p>
				<p>日期: ${date}</p>
				<p>限制人數 :${people}</p>


				<div class="btn-group btn-group-justified">
					<div class="btn-group">
						<a href="<%=request.getContextPath()%>/picnic/maosecondui.jsp"
							class="btn btn-default" role="button">修改</a>
					</div>
					<div class="btn-group">
						<form method="post"
							action="<%=request.getContextPath()%>/picnic/picnic.do">
							<button type="submit" class="btn btn-default" value="Submit">送出</button>
							<input type="hidden" name="action" value="insert">
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-push-2">
				<jsp:include page="/mustinclude/footer.jsp" />
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>