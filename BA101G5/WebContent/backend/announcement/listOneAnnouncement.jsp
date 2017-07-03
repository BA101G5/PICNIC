<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.announcement.model.*"%>
<%
AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO"); //AnnouncementServlet.java(Concroller), 存入req的announcementVO物件
%>
<html>
<head>
<title>最新消息資料 - listOneAnnouncement.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>最新消息資料 - ListOneAnnouncement.jsp</h3>
		<a href="select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>最新消息編號</th>
		<th>最新消息</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=announcementVO.getAnn_no()%></td>
		<td><%=announcementVO.getAnn_text()%></td>
	</tr>
</table>

</body>
</html>
