<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="print_parts">
	<c:if test="${not empty picList}">
		<table>
				<tr>
					<td>野餐團名稱</td>
					<td>揪團日期</td>
					<td>活動日期</td>
					<td>人數上限</td>
				</tr>
			<c:forEach var="pic" items="${picList}" varStatus="status">
				
				<tr>
					<td>${pic.picnic_name}</td>
					<td>${pic.picnic_startup}</td>
					<td>${pic.picnic_date}</td>
					<td>${pic.picnic_pl}</td>
					<td>
						<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/history.do" name="historyTable">
							<input type="submit" value="訂單記錄">
				        	<input type="hidden" name="PICNIC_NO" value="${pic.picnic_no}">
				        	<input type="hidden" name="button" value="his_detail">
						</FORM>
					</td>
				</tr>
				
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty plaVOList}">
	<h1>場地</h1>
		<table>
				<tr>
					<td>場地名稱</td>
					<td>場地地點</td>
					<td>容納人數</td>
				</tr>
			<c:forEach var="pla" items="${plaVOList}" varStatus="status">
				<tr>
					<td>${pla.p_name }</td>
					<td>${pla.p_place }</td>
					<td>${pla.p_pop }</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="3">場地資訊</td>
				</tr>
			<c:forEach var="pla" items="${plaVOList}" varStatus="status">
				<tr>
					<td colspan="3">${pla.p_info }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty gsVOList}">
	<h1>一般商品</h1>
		<table>
				<tr>
					<td>商品名稱</td>
					<td>商品資訊</td>
					<td>商品單價</td>
					<td>數量</td>
				</tr>
			<c:forEach var="gs" items="${gsVOList}" varStatus="status">
				<tr>
					<td>${gs.gs_name }</td>
					<td>${gs.gs_info }</td>
					<td>${gs.gs_price }</td>
					<td>${gsN[status.index] }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty grVOList}">
	<h1>租借商品</h1>
		<table>
				<tr>
					<td>物品名稱</td>
					<td>物品資訊</td>
					<td>物品單價</td>
					<td>可租時間</td>
					<td>數量</td>
				</tr>
			<c:forEach var="gr" items="${grVOList}" varStatus="status">
				<tr>
					<td>${gr.gr_name }</td>
					<td>${gr.gr_info }</td>
					<td>${gr.gr_price }</td>
					<td>${gr.gr_until }</td>
					<td>${grN[status.index] }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>
<a href="#" onclick="printScreen(print_parts);" >Printing ... </a>
</body>
</html>

<script type="text/javascript">
	function printScreen(printlist) 
	  {
	     var value = printlist.innerHTML;
	     var printPage = window.open("", "Printing...", "");
	     printPage.document.open();
	     printPage.document.write("<HTML><head></head><BODY onload='window.print();window.close()'>");
	     printPage.document.write("<PRE>");
	     printPage.document.write(value);
	     printPage.document.write("</PRE>");
	     printPage.document.close("</BODY></HTML>");
	  }
</script>

<style type="text/css">
body {
	background: #fafafa url(https://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
	background: #f5f5f5;
	border-collapse: separate;
	box-shadow: inset 0 1px 0 #fff;
	font-size: 16px;
	line-height: 24px;
	margin: 10px auto;
	text-align: center;
	width: 1200px;
}	

thead{
	font-size: 18px;
	color:#0000AA;
}

td {
	border-right: 1px solid #fff;
	border-left: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #e8e8e8;
	padding: 1px 15px;
	position: relative;
	transition: all 300ms;
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
	border-right: 1px solid #e8e8e8;
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: url(https://jackrugile.com/images/misc/noise-diagonal.png);	
}

tbody.adm:hover td {
	color: transparent;
	text-shadow: 0 0 3px #aaa;
}

tbody.adm:hover tr:hover td {
	color: #444;
	text-shadow: 0 1px 0 #fff;
}

section button {
  margin-left : 50px;
  margin-right : 50px;
  font-size: 1.0rem;
  padding: 0.01rem 2.5rem;
  display: inline;
  background-color: #009ac9;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 300;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section button:hover {
  background-color: #ffffff;
  color: #009ac9;
  border-color: #009ac9;
}

section.close button {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.1rem 0.1rem;
  display: inline;
  background-color: #D0104C;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 300;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section.close button:hover {
  background-color: #ffffff;
  color: #D0104C;
  border-color: #D0104C;
}

section{
	display: inline
}

.radio{
	display:none;
}
	
input[type="text"]{
	text-align: center;
}


  	#toggle{
			width:150px;
			height:20px;
			border-radius:40px;
			position:absolute;
			-webkit-appearance: none;
			position:absolute;
			top:165%;
			left:60%;
			margin:-39px 0 0 -89px;
			box-shadow: inset 1px 1px 1px rgba(0, 0, 0, 0.4);
			background: -webkit-linear-gradient(#c6c6c6,#e3e3e3);
		}
		#toggle:checked{
			background: -webkit-linear-gradient(#bfe09c,#c8eca0);
			box-shadow: inset 1px 1px 3px rgba(0, 0, 0, 0.4);
		}
		#toggle:before {
			content:"";
			width:13px;
			height:13px;
			padding:2px;
			top:1.2px;
			left:9px;
			position:absolute;
			border-radius:40px;
			background: -webkit-linear-gradient(#ebebeb,#f1f1f1);
			box-shadow: -2px 2px 8px rgba(0, 0, 0, 0.2), 
						-1px 1px 2px rgba(0, 0, 0, 0.3), 
						inset 1px 1px 1px #ffffff;
			-webkit-transition: all 1s;
			
		}
		#toggle:checked:before {
			left:125px;
			background:#f1f1f1;
		}

.errWord{
	font-size:24px;
	color:#FF0000;
}		

</style>