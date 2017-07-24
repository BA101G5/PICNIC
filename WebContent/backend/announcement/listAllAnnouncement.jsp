<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AnnouncementService announcementSvc = new AnnouncementService();
    List<AnnouncementVO> list = announcementSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有最新消息資料 - listAllAnnouncement.jsp</title>
	<style>
	body{
			background-color: #eff9f9;
			}
			.undone {
  margin-left : 10px;
  margin-right : 10px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
  display: inline;
  background-color: #009ac9;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 100;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

.table5_3 table {
	width:100%;
	margin:15px 0;
	border:0;
}
.table5_3 th {
	background-color:#87CEFA;
	color:#000000
}
.table5_3,.table5_3 th,.table5_3 td {
	font-size:0.95em;
	text-align:center;
	padding:4px;
	border-collapse:collapse;
}
.table5_3 th,.table5_3 td {
	border: 1px solid #ffffff;
	border-width:1px 0 1px 0
}
.table5_3 tr {
	border: 1px solid #ffffff;
}
.table5_3 tr:nth-child(odd){
	background-color:#d7eefd;
}
.table5_3 tr:nth-child(even){
	background-color:#ffffff;
}
/* .table5_3 tr:last-child td:last-child{ */
/*   border-bottom-right-radius: 10px; */
/* } */
.table5_3 tr:first-child th:last-child{
  border-top-right-radius: 10px;
}
.table5_3 tr:last-child td:first-child{
  border-bottom-left-radius: 10px;
}
.table5_3 tr:first-child th:first-child{
  border-top-left-radius: 10px;
}
	</style>
</head>
<body>
				<div class="col-xs-12 col-sm-10">
					 
					  <form class="navbar-form navbar-left" role="search" style="">
					  <div class="col-xs-12 col-sm-10">
					  
					  <div style="float: left; font-size: 50px">最新消息管理</div> 
					 
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
						<div class="col-xs-12 col-sm-2">
						<div  style="float: right;margin-left:	 600px; padding-top: 30px">
							<FORM METHOD="post" ACTION="announcement.do" >
					        <input class="undone" type="submit" value="新增最新消息">
					        <input type="hidden" name="action" value="add_Announcements">
					   		 </FORM>
						</div>
					</div>	
					</form>
					

					<table class="table5_3" width=1065px>
						<thead>
							<tr height="50px">
								<th>編號</th>
								<th>最新消息</th>
								<th>修改</th>
								<th>刪除</th>
							</tr>
							
						</thead>
						
						<tbody>
							<c:forEach var="announcementVO" items="${list}" >
								<tr align='center' valign='middle' height="40px">
									<td>${announcementVO.ann_no}</td>
									<td>${announcementVO.ann_text}</td>
									<td>
									  <FORM METHOD="post" ACTION="announcement.do">
									     <input class="undone" type="submit" value="修改">
									     <input type="hidden" name="ann_no" value="${announcementVO.ann_no}">
									     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
									</td>
									<td>
									  <FORM METHOD="post" ACTION="announcement.do">
									    <input class="undone" type="submit" value="刪除">
									    <input type="hidden" name="ann_no" value="${announcementVO.ann_no}">
									    <input type="hidden" name="action"value="delete"></FORM>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			
		
		<div class="col-sm-1 "></div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>
