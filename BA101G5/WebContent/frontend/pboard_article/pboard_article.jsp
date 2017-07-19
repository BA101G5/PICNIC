<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pboard_article.model.*"%>
<%@ page import="com.general_member.model.*"%>

<%
	GeneralMemberService gmSvc = new GeneralMemberService();
	pageContext.setAttribute("gmSvc", gmSvc);

	Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
	List<Pboard_ArticleVO> list = pboard_articleSvc.getAll();
	Collections.reverse(list);
	pageContext.setAttribute("list",list);
%>
<%
	Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO");
%>
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
						<a class="navbar-brand" href="#" style="padding: 4px;"><img src="logo.png" style="height: 100%;"></a>
					</div>

						<!-- 右選單 -->
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">shyangs</a></li>
						</ul>

						<!-- 搜尋表單 -->
						<form class="navbar-form navbar-right" role="search">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="請輸入關鍵字">
							</div>
							<button type="submit" class="btn btn-default">搜尋</button>
						</form>

				</div>
			</nav>

			<ol class="breadcrumb">
				<li>
					<a href="#">首頁</a>
				</li>
				<li>
					<a href="#">海賊團</a>
				</li>
				<li class="active">留言板</li>
			</ol>


			<h2>留言板</h2>

			<div class="col-xs-12 col-sm-12 board-post-newpost-title mem-guest" style="height: 36px; border: 1px solid grey; margin-bottom: 6px;" contenteditable="true" id="divBoardPostNewpostTitle">
			</div>
			<div class="col-xs-12 col-sm-12 board-post-newpost mem-guest" style="height: 150px; border: 1px solid grey; margin-bottom: 6px;" contenteditable="true" id="divBoardPostNewpost">
			</div>
			<div class="btn-group mem-guest" id="btnInsertImg">
				<a href="#" class="btn btn-default btn-board-newpost" role="button">插入圖片</a>
			</div>
			<div class="btn-group mem-guest" id="btnPostNewPost">
				<a href="#" class="btn btn-default btn-board-newpost" role="button">發表留言</a>
			</div>





<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<div style="display:none">
<FORM METHOD="post" ACTION="pboard_article.do" name="formPost" id="formPost">
<table border="0">

	<tr>
		<td>標題:</td>
		<td><input id="article_title" type="TEXT" name="article_title" size="45" 
			value="<%= (pboard_articleVO==null)? "標題" : pboard_articleVO.getArticle_title()%>" /></td>
	</tr>
	<tr>
		<td>留言:</td>
		<td><input id="article_text" type="TEXT" name="article_text" size="45" 
			value="<%= (pboard_articleVO==null)? "留言" : pboard_articleVO.getArticle_text()%>" /></td>
	</tr>
	<tr>
		<td>作者:</td>
		<td><input id="author_no" type="TEXT" name="author_no" size="45" 
			value="<%= (pboard_articleVO==null)? "MG00000001" : pboard_articleVO.getAuthor_no()%>" /></td>
	</tr>
	<tr>
		<input id="inputFile" type="file" onchange="encodeImageFileAsURL(this)" />
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input id="btnSubmitPostNewPost" type="submit" value="送出新增"></FORM>
</div>




<c:forEach var="pboard_articleVO" items="${list}">
			<div class="col-xs-12 col-sm-12 board-topic board-article">
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-title">
						${pboard_articleVO.article_title}
					</div>
				</div>
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-author">
						${gmSvc.getOneGeneralMember(pboard_articleVO.author_no).getMEM_NAME()} (${pboard_articleVO.getArticle_post().toString().replaceFirst(".0$", "")})
					</div>
				</div>
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-content">
						<p>${pboard_articleVO.article_text}</p>
					</div>
				</div>
			</div>
</c:forEach>

		</div>
	</div>
</div>


		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/encodeImageFileAsURL.js"></script>
		<script>
			// sessionScope.gVO.getMEM_NO(): "${sessionScope.gVO.getMEM_NO()}"
			var memNO = '${sessionScope.gVO.getMEM_NO()}';
			if(memNO !== ''){ $('.mem-guest').css('display', 'block'); }

			$('#btnPostNewPost').on('click', function(){
				$('#article_title')[0].value = $('#divBoardPostNewpostTitle').html();
				$('#article_text')[0].value = $('#divBoardPostNewpost').html();
				$('#author_no')[0].value = memNO;
				$('#formPost').submit();
			});

			$('#btnInsertImg').on('click', function(){
				// alert(encodeImageFileAsURL);
				$('#inputFile').click();
				$('#inputFile').on('change', function(){
					encodeImageFileAsURL(this, function(base64){
						$('#divBoardPostNewpost')[0].innerHTML = $('#divBoardPostNewpost').html() +'<br><img src="' + base64 + '">';
					});
				});
			});
		</script>
	</body>
</html>