<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.blocked_keywords.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
    List<Blocked_KeywordsVO> list = blocked_keywordsSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有關鍵字資料 - listAllBlocked_Keywords.jsp</title>
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
					  
					  <div style="float: left; font-size: 50px">關鍵字屏蔽管理</div> 
					 
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
							 <FORM METHOD="post" ACTION="blocked_keywords.do" >
					        <input class="undone" type="submit" value="新增關鍵字">
					        <input type="hidden" name="action" value="add_Blocked_Keywords">
					   		 </FORM>
						</div>
					</div>	
					</form>
					

					<table class="table5_3" width=1100px>
						<thead>
							<tr height="50px">
								<th>編號</th>
								<th>關鍵字</th>
								<th>取代字</th>
								<th>修改</th>
								<th>刪除</th>
							</tr>
							
						</thead>
						
						<tbody>
							<c:forEach var="blocked_keywordsVO" items="${list}" >
								<tr align='center' valign='middle'>
									<td>${blocked_keywordsVO.keyword_no}</td>
									<td>${blocked_keywordsVO.keyword}</td>
									<td>${blocked_keywordsVO.replacement}</td>
									<td>
									  <FORM METHOD="post" ACTION="blocked_keywords.do">
									     <input class="undone" type="submit" value="修改">
									     <input type="hidden" name="keyword_no" value="${blocked_keywordsVO.keyword_no}">
									     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
									</td>
									<td>
									  <FORM METHOD="post" ACTION="blocked_keywords.do">
									    <input class="undone" type="submit" value="刪除">
									    <input type="hidden" name="keyword_no" value="${blocked_keywordsVO.keyword_no}">
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
