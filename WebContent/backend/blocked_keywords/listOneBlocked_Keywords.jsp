<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%
Blocked_KeywordsVO blocked_keywordsVO = (Blocked_KeywordsVO) request.getAttribute("blocked_keywordsVO"); //Blocked_KeywordsServlet.java(Concroller), 存入req的blocked_keywordsVO物件
%>
<html>
<head>
<title>關鍵字資料 - listOneBlocked_Keywords.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>關鍵字資料 - ListOneBlocked_Keywords.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>關鍵字編號</th>
		<th>關鍵字</th>
		<th>取代字</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=blocked_keywordsVO.getKeyword_no()%></td>
		<td><%=blocked_keywordsVO.getKeyword()%></td>
		<td><%=blocked_keywordsVO.getReplacement()%></td>
	</tr>
</table>

</body>
</html>
