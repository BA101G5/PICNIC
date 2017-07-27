<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pboard_article.model.*"%>
<%@ page import="com.picnic.model.*"%>

<%
	PicnicService picnicSvc = new PicnicService();
	pageContext.setAttribute("picnicSvc", picnicSvc);

	List<PicnicVO> list = picnicSvc.getAll_sl();
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Picnic野餐網</title>
<meta name="description" content="">
<meta name="keywords" content="">
	<jsp:include page="/mustinclude/head.jsp" />
<%--
<style type="text/css">
html {
	height: 100%;
}
body{
	min-height: 100%;
	/*background-image:url(1-12030R10P4.jpg);*/
	background-image:url(4878670-full-hdtv-wallpapers--ed.jpg);
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
.navbar{
	opacity: 0.9;
}
#left-navigation-bar{
	position: fixed;
	z-index: 6000;
	width: 60px;
	height:100%;
	background-color: #8bdddd;
	opacity: 0.7;
	text-align: center;
	padding: 300px 0px;
}
.bs-glyphicons-list {
    padding-left: 0px;
    list-style: outside none none;
}
.bs-glyphicons-list > li{
	margin: 16px 0px;
}
.glyphicon{
	font-size: 36px;
	color: #091e4c;
}
</style>--%>
</head>
<body>

	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<jsp:include page="/mustinclude/marquee.jsp" />


<!-- .cr -->
<div class="container-fluid" style="margin-bottom: 16px;">
					<div class="row">
						<div class="col-sm-8 col-sm-push-2"  style="padding-left: 0px;">
							<div id="carousel-id" class="carousel slide" data-ride="carousel">
								<ol class="carousel-indicators">
									<li data-target="#carousel-id" data-slide-to="0" class="active"></li>
									<li data-target="#carousel-id" data-slide-to="1" class=""></li>
								</ol>
								<div class="carousel-inner">
									<div class="item active">
										<img
											src="<%=request.getContextPath()%>/images/index_img1.jpg"
											alt="">
									</div>
									<div class="item">
										<img
											src="<%=request.getContextPath()%>/images/index_img2.jpg"
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
</div>




<!-- .cr -->
<div class="container-fluid">
	<div class="row" style="margin-right: 15px;">
		<div class="col-sm-8 col-sm-push-2" style="background-color:white;">
<h2>最新成立的野餐團</h2>
<%@ include file="pages/page1.file" %>
<table border='1' bordercolor='#CCCCFF' width='100%'>
	<tr>
		<th>野餐團名稱</th>
		<th>野餐團敘述</th>
<!-- 		<th>留言板</th> -->
	</tr>

	<c:forEach var="picnicVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center'>
			<td>
<form method="post" action="<%=request.getContextPath()%>/picnic/picnic.do" onclick="submit()">
	<font color="blue">${picnicVO.picnic_name}</font>
	<input type="hidden" name="Picnic_no" value="${picnicVO.getPicnic_no()}">
	<input type="hidden" name="action" value="lookpicnic">
</form>
			
</td>
			<td>${picnicVO.picnic_desc}</td>
<%-- 			<td><a href="<%=request.getContextPath()%>/frontend/pboard_article/pboard_article.jsp">留言板</a></td> --%>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

		</div>
	</div>






</div>

<jsp:include page="/mustinclude/chatroom.jsp" />

</body>
</html>