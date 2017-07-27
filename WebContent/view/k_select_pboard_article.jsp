<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>留言板</title>
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<style type="text/css">
body{
	background-color: #eff9f9;
}
.navbar, .breadcrumb{
	opacity: 0.8;
}
			h2{
				margin: 28px;
				font-weight: 700;
			}
			.btn-board-newpost{
				background-color: #1d2084;
				color: white;
			}
			.board-topic{
				margin: 16px 0px;
			}
			.board-article{
				margin: 16px 0px;
			}
			.article-row{
				margin: 8px 12px;
			}
			.article-label{
				color: white;
				background-color: #30a;
			}
			.board-topic{
				border-style: solid;
				border-color: #8bdddd;
				border-width: 4px;
				border-radius: 20px;
				padding: 16px;
			}
			.article-title{
				font-size: 18px;
				font-weight: 600;
			}
			#divBoardPostNewpost{
				overflow-y: auto;
			}
			.mem-guest{
				display: none;
			}
		</style>
	</head>
	<body>
		<!-- cr -->
<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-sm-push-2">
 
			<!-- navbar -->
			<nav class="navbar navbar-default" role="navigation">
				<div class="container col-sm-12">
					<div class="navbar-header">
						<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp" style="padding: 4px;"><img src="view/logo.png" style="height: 100%;"></a>
					</div>

						<!-- 右選單 -->
						<ul class="nav navbar-nav navbar-right">
							<li><a href="<%=request.getContextPath()%>/personal/personal.jsp">shyangs</a></li>
						</ul>

						<%--<!-- 搜尋表單 -->
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="請輸入關鍵字">
							</div>
							<button type="submit" class="btn btn-default">搜尋</button>
						</form>
						 --%>
				</div>
			</nav>


			<ol class="breadcrumb">
				<li>
					<a href="<%=request.getContextPath()%>/index.jsp">首頁</a>
				</li>
				<li>
					<a href="#">留言板</a>
				</li>
			</ol>

<c:forEach var="paList" items="${paList}" varStatus="status">
			<div class="col-xs-12 col-sm-12 board-topic board-article">
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-title">
						${paList.article_title}
					</div>
				</div>
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-author">
						${paNaLi[status.index]} (${paList.getArticle_post().toString().replaceFirst(".0$", "")})
					</div>
				</div>
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-content">
						<p>${paList.article_text}</p>
					</div>
				</div>
			</div>
</c:forEach>
		</div>
	</div>
</div>

	</body>
</html>