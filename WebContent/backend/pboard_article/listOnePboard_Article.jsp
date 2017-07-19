<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pboard_article.model.*"%>
<%@ page import="com.picnic.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller Pboard_ArticleServlet.java已存入request的Pboard_ArticleVO物件--%>
<%Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO");%>

<%-- 取出 對應的PicnicVO物件--%>
<%--
//   PicnicService picnicSvc = new PicnicService();
//   PicnicVO picnicVO = picnicSvc.getOnePicnic(pboard_articleVO.getPicnic_no());
--%>
<html>
<head>
<title>員工資料 - listOnePboard_Article.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料 - ListOnePboard_Article.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>留言編號</th>
		<th>野餐團編號</th>
		<th>標題</th>
		<th>內文</th>
		<th>發表時間</th>
		<th>瀏覽數</th>
		<th>kind</th>
		<th>作者</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=pboard_articleVO.getArticle_no()%></td>
		<td><%=pboard_articleVO.getPicnic_no()%></td>
		<td><%=pboard_articleVO.getArticle_title()%></td>
		<td><%=pboard_articleVO.getArticle_text()%></td>
		<td><%=pboard_articleVO.getArticle_post()%></td>
		<td><%=pboard_articleVO.getArticle_views()%></td>
		<td><%=pboard_articleVO.getArticle_kind()%></td>
		<td><%=pboard_articleVO.getAuthor_no()%></td>
<%-- 		<td><%=pboard_articleVO.getPicnic_no()%>【<%=picnicVO.getPicnic_name()%></td> --%>
	</tr>
</table>

</body>
</html>
