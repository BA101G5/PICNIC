<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.pboard_article.model.*"%>
<%@ page import="com.picnic.model.*"%>
<%-- �����m�߱ĥ� Script ���g�k���� --%>

<%-- ���X Concroller Pboard_ArticleServlet.java�w�s�Jrequest��Pboard_ArticleVO����--%>
<%Pboard_ArticleVO pboard_articleVO = (Pboard_ArticleVO) request.getAttribute("pboard_articleVO");%>

<%-- ���X ������PicnicVO����--%>
<%--
//   PicnicService picnicSvc = new PicnicService();
//   PicnicVO picnicVO = picnicSvc.getOnePicnic(pboard_articleVO.getPicnic_no());
--%>
<html>
<head>
<title>���u��� - listOnePboard_Article.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>�����m�߱ĥ� Script ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>���u��� - ListOnePboard_Article.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>�d���s��</th>
		<th>���\�νs��</th>
		<th>���D</th>
		<th>����</th>
		<th>�o��ɶ�</th>
		<th>�s����</th>
		<th>kind</th>
		<th>�@��</th>
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
<%-- 		<td><%=pboard_articleVO.getPicnic_no()%>�i<%=picnicVO.getPicnic_name()%></td> --%>
	</tr>
</table>

</body>
</html>
