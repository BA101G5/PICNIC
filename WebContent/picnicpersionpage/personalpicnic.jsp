<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pboard_article.model.*"%>
<%@ page import="com.general_member.model.*"%>
<%@ page import="com.picnic.model.*"%>
<%@ page import="com.picmem.model.*"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%@ page import="java.util.*"%>

<% 
	PicnicVO picnicVO = (PicnicVO)session.getAttribute("picnicVO");
// 	System.out.println("personalpicnic.jsp / picnicVO.getPicnic_no() = " + picnicVO.getPicnic_no());
	PicmemService pmSvc = new PicmemService();
	List<PicmemVO> pmListByP = pmSvc.getAll(picnicVO.getPicnic_no());//"PG00000001"
// 	System.out.println(pmListByP.get(1).getMem_no());
// 	System.out.println(pmListByP.get(2).getMem_no());
	pageContext.setAttribute("pmListByP", pmListByP);
%>

<%
	GeneralMemberService gmSvc = new GeneralMemberService();
	pageContext.setAttribute("gmSvc", gmSvc);

	Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
	List<Pboard_ArticleVO> list = pboard_articleSvc.getAll(picnicVO.getPicnic_no());//"PG00000001"

	// ����r�̽��L�o
	Blocked_KeywordsService bkSvc = new Blocked_KeywordsService();
	List<Blocked_KeywordsVO> bkList = bkSvc.getAll();
	for (Pboard_ArticleVO paVO: list) {
		for (Blocked_KeywordsVO bkVO: bkList) {
			paVO.setArticle_title(
					paVO.getArticle_title().replaceAll(bkVO.getKeyword(), bkVO.getReplacement())
				);
			paVO.setArticle_text(
				paVO.getArticle_text().replaceAll(bkVO.getKeyword(), bkVO.getReplacement())
			);
		}
	}

	
	Collections.reverse(list);
	pageContext.setAttribute("list", list);
%>

<%
	Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO");
%>


<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Picnic���\��</title>
<jsp:include page="/mustinclude/head.jsp" />
<style>
body{
	background-color: #eff9f9;
}
.breadcrumb{
	opacity: 0.8;
}
			h2{
				margin: 28px;
				font-weight: 700;
			}
			.btn-board-newpost{
 				background-color: #1d2084;
 				margin: 0px 8px;
				color: #555;
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

.article-content img{
	max-width: 250px;
	height: auto;
}


.breadcrumb {
	margin: 0px;
	background: skyblue;
}


</style>

</head>
<body>
	<jsp:include page="/mustinclude/left_nav.jsp" />
	<jsp:include page="/mustinclude/top_nav.jsp" />
	<div class="container" style="background-color: white;">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="<%=request.getContextPath()%>/index.jsp">����</a></li>
				<li>���\���骩��</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-md-8">
<!-- 				<h1 class="page-header"> -->
<!-- 					Heading <small>Text</small> -->
<!-- 				</h1> -->
				<h2>
					${ picnicVO.picnic_name }
				</h2>
				<hr>
				<div class="container-fluid">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-sm-push-2">

			<h2>�d���O</h2>

			<div class="col-xs-12 col-sm-12 board-post-newpost-title mem-guest" style="height: 36px; border: 1px solid grey; margin-bottom: 6px;" contenteditable="true" id="divBoardPostNewpostTitle">
			</div>
			<div class="col-xs-12 col-sm-12 board-post-newpost mem-guest" style="height: 150px; border: 1px solid grey; margin-bottom: 6px;" contenteditable="true" id="divBoardPostNewpost">
			</div>
			<div class="btn-group mem-guest" id="btnInsertImg">
				<a href="#" class="btn btn-default btn-board-newpost" role="button">���J�Ϥ�</a>
			</div>
			<div class="btn-group mem-guest" id="btnPostNewPost">
				<a href="#" class="btn btn-default btn-board-newpost" role="button">�o��d��</a>
			</div>





<%-- ���~��C --%>
<div>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
</div>

<div style="display:none">
<FORM METHOD="post" ACTION="pboard_article.do" name="formPost" id="formPost">
<table border="0">

	<tr>
		<td>���D:</td>
		<td><input id="article_title" type="TEXT" name="article_title" size="45" 
			value="<%= (pboard_articleVO==null)? "���D" : pboard_articleVO.getArticle_title()%>" /></td>
	</tr>
	<tr>
		<td>�d��:</td>
		<td><input id="article_text" type="TEXT" name="article_text" size="45" 
			value="<%= (pboard_articleVO==null)? "�d��" : pboard_articleVO.getArticle_text()%>" /></td>
	</tr>
	<tr>
		<td>�@��:</td>
		<td><input id="author_no" type="TEXT" name="author_no" size="45" 
			value="<%= (pboard_articleVO==null)? "MG00000001" : pboard_articleVO.getAuthor_no()%>" /></td>
	</tr>
	<tr>
		<td>���\��:</td>
		<td><input id="picnic_no" type="TEXT" name="picnic_no" size="45" 
			value="<%= (picnicVO==null)? "PG00000001" : picnicVO.getPicnic_no() %>" /></td>
	</tr>
	<tr>
		<input id="inputFile" type="file" onchange="encodeImageFileAsURL(this)" />
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input id="btnSubmitPostNewPost" type="submit" value="�e�X�s�W"></FORM>
</div>




<c:forEach var="pboard_articleVO" items="${list}">
			<div class="col-xs-12 col-sm-12 board-topic board-article">
				<div class="row article-row">
					<div class="col-xs-2 col-sm-2">
						<span class="headicon"><img src="<%=request.getContextPath()%>/general_member/DBGift.do?MEM_NO=${ pboard_articleVO.author_no }" style="width:56px;"></span>
					</div>
					<div class="col-xs-10 col-sm-10">
						<div class="col-xs-12 col-sm-12 article-title">
							${pboard_articleVO.article_title}
						</div>
						<div class="col-xs-12 col-sm-12 article-author">
							${gmSvc.getOneGeneralMember(pboard_articleVO.author_no).getMEM_NAME()} (${pboard_articleVO.getArticle_post().toString().replaceFirst(".0$", "")})
						</div>
					</div>
				</div>
				<div class="row article-row">

					<div class="col-xs-10 col-sm-10 article-content">
						<p>${pboard_articleVO.article_text}</p>
					</div>
				</div>

				<div class="row article-row">
					<div class="btn-group mem-guest" id="btnPostChecklist">
						<a href="<%=request.getContextPath()%>/checklist/addChecklist.jsp?chli_cate=1&chli_be_num=${pboard_articleVO.article_no}" class="btn btn-default btn-board-post-checklist" role="button">���|�d��</a>
					</div>
				</div>
				
			</div>
</c:forEach>

		</div>
	</div>
</div>


		
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
<!-- 				<ul class="pager"> -->
<!-- 					<li class="previous"><a href="#">&larr; Older</a></li> -->
<!-- 					<li class="next"><a href="#">Newer &rarr;</a></li> -->
<!-- 				</ul> -->
				
				
				
			</div>
			<div class="col-md-4">
				<div class="well">
					<div class="row">
						<div style="text-align:right;">
							<a href="<%=request.getContextPath()%>/checklist/addChecklist.jsp?chli_cate=2&chli_be_num=${ picnicVO.picnic_no }">���|���\��</a>
						</div>
						<div class="well">
							<strong>���\�Τ���</strong>
							<p>${ picnicVO.picnic_desc }
								<form method="post" action="" class="btn btn-default">
									<a href="#">�ק�</a>
								</form>
							</p>

						</div>
						
<c:forEach var="picmemVO" items="${pmListByP}">
						<div class="col-lg-6">
							<ul class="list-unstyled">
<%-- 								<li><a href="#">���� ${ picmemVO.mem_no }</a></li> --%>
								<li><a href="#">���� ${gmSvc.getOneGeneralMember(picmemVO.mem_no).getMEM_NAME()}</a></li>
							</ul>
						</div>
</c:forEach>	

					</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	<div class="row">
	<div class="col-md-12">
		<jsp:include page="/mustinclude/footer.jsp" />
	</div></div>
	
</body>
</html>