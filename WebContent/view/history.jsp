<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- --------------------- -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Picnic野餐網</title>
<meta name="description" content="">
<meta name="keywords" content="">
<jsp:include page="/mustinclude/head.jsp" />
<!-- --------------------- -->
<title>揪團歷史</title>
</head>
<body>

<jsp:include page="/mustinclude/left_nav.jsp" />
<jsp:include page="/mustinclude/top_nav.jsp" />

<div class="k_class">
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
					<td>
						<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/commend.do" >
					        <input type="submit" value="評價">
					        <input type="hidden" name="button" value="commend">
					    </FORM>
					</td>
				</tr>
				
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${not empty plaVOList}">
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
</div>
<jsp:include page="/mustinclude/chatroom.jsp" />
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

div.k_class { 
	background:#FFF;
	margin:auto;
	padding:10px; 
	font:13px "Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, sans-serif;
	width:70%;
	}
	
/* ---- Some Resets ---- */

div.k_class p,
table, caption, td, tr, th {
	margin:0;
	padding:0;
	font-weight:normal;
	}

/* ---- Paragraphs ---- */

div.k_class p {
	margin-bottom:15px;
	}
	
/* ---- Table ---- */

div.k_class table {
	border-collapse:collapse;
	margin:auto;
	margin-bottom:15px;
	width:100%;
	
	}
	
div.k_class 	caption {
		text-align:center;
		font-size:15px;
		padding-bottom:10px;
		}
	
div.k_class 	table td,
	table th {
		padding:5px;
		border:1px solid #fff;
		border-width:0 1px 1px 0;
		}
		
div.k_class 	thead th {
		background:#91c5d4;
		}
			
		thead th[colspan],
		thead th[rowspan] {
			background:#66a9bd;
			}
		
div.k_class 	tbody th,
	tfoot th {
		text-align:center;
		background:#91c5d4;
		}
		
div.k_class 	tbody td,
	tfoot td {
		text-align:center;
		background:#d5eaf0;
		}
		
div.k_class 	tfoot th {
		background:#b0cc7f;
		}
		
div.k_class 	tfoot td {
		background:#d7e1c5;
		font-weight:bold;
		}
			
div.k_class 	tbody tr.odd td { 
		background:#bcd9e1;
		}
div.k_class 	caption{
		text-align:center;
		font-size:36px;
		color:#AA3333;
	}
	
div.k_class  section button {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
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

div.k_class section button:hover {
  background-color: #ffffff;
  color: #009ac9;
  border-color: #009ac9;
}

div.k_class section button.quit {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
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

div.k_class section button.quit:hover {
  background-color: #ffffff;
  color: #D0104C;
  border-color: #D0104C;
}

div.k_class section{
	display: inline
}
</style>