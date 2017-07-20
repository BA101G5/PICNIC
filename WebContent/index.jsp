<%@ page contentType="text/html; charset=UTF-8" pageEncoding="big5"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pboard_article.model.*"%>
<%@ page import="com.picnic.model.*"%>

<%
	PicnicService picnicSvc = new PicnicService();
	pageContext.setAttribute("picnicSvc", picnicSvc);

	List<PicnicVO> list = picnicSvc.getAll();
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>���Ӭ� - ����</title>
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

<%-- 	<jsp:include page="/mustinclude/left_nav.jsp" /> --%>
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<jsp:include page="/mustinclude/marquee.jsp" />

<!-- .cr -->
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-10 col-sm-push-1">


<table border='1' bordercolor='#CCCCFF' width='100%'>
	<tr>
		<th>���\�ΦW��</th>
		<th>���\�αԭz</th>
	</tr>

	<c:forEach var="picnicVO" items="${list}">
		<tr align='center'>
			<td>${picnicVO.picnic_name}</td>
			<td>${picnicVO.picnic_desc}</td>
		</tr>
	</c:forEach>
</table>


		</div>
					    <%--<ol class="carousel-indicators">
					        <li data-target="#carousel-id" data-slide-to="0" class="active"></li>
					        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
					    </ol>--%>
	</div>

<!-- 	<div class="row">
		<div class="col-sm-12" style="overflow: hidden;">
			<img src="hd-wallpaper-nature-hd-wallpapers-nature.jpg" style="opacity: 1;">
		</div>
	</div> -->

					<!-- bs-slider
					<div id="carousel-id" class="carousel slide" data-ride="carousel" style="position: fixed; top: 0; left:0;  z-index: -1;">
					    <!-- �ۿO���p���I�� 
					    <ol class="carousel-indicators">
					        <li data-target="#carousel-id" data-slide-to="0" class=""></li>
					        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
					        <li data-target="#carousel-id" data-slide-to="2" class="active"></li>
					    </ol>
					    <!-- �ۿO���D�ϰ�
					    <div class="carousel-inner">
					        <div class="item">
					            <img src="https://api.fnkr.net/testimg/800x700/aaaaaa" alt="">
					            <div class="container">
					                <div class="carousel-caption">
					                    <h1>CSS�i�֦n�ܶW�n��</h1>
					                    <p>�A�ܹL�F�ܡH</p>
					                    <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p>
					                </div>
					            </div>
					        </div>
					        <div class="item">
					            <img src="https://api.fnkr.net/testimg/2800x700/aaaaaa" alt="">
					            <div class="container">
					                <div class="carousel-caption">
					                    <h1>CSS�i�֪��~���u��K</h1>
					                    <p>�A�w�ˤF�ܡH</p>
					                    <p><a class="btn btn-lg btn-primary" href="#" role="button">��h</a></p>
					                </div>
					            </div>
					        </div>
					        <div class="item active">
					            <img src="https://api.fnkr.net/testimg/2800x700/aaaaaa" alt="">
					            <div class="container">
					                <div class="carousel-caption">
					                    <h1>�ڬO���D���ۤv���קa</h1>
					                    <p>�ڬO�����A�A�i�H��r���b�o����</p>
					                    <p><a class="btn btn-lg btn-primary" href="#" role="button">�ԲӤ��e</a></p>
					                </div>
					            </div>
					        </div>
					    </div>
					    <!-- �W�U�������
					    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
					    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
					</div>
 -->
</div>

<jsp:include page="/mustinclude/chatroom.jsp" />

</body>
</html>