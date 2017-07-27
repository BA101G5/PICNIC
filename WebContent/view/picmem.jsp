<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- --------------------- -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Picnic野餐網</title>
<meta name="description" content="">
<meta name="keywords" content="">
<jsp:include page="/mustinclude/head.jsp" />
<!-- --------------------- -->

<style type="text/css">
div.k_class { 
	background:#FFF;
	margin:auto;
	padding:20px; 
	font:13px "Lucida Grande", "Lucida Sans Unicode", Helvetica, Arial, sans-serif;
	width:75%;
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
	width:70%;
	
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

<script type="text/javascript">

window.onload=buttonColor;

	function buttonColor(){
		
		var buttons = document.getElementsByName("button");
		for(i=0; i<=buttons.length; i++){
			if (buttons[i].innerHTML == "退出") {
				buttons[i].className = "quit";
			}
		}
	}


	function changeButtonColor(e){
		var obj = e? e.target : window.event.srcElement;
		if (obj.innerHTML == "退出") {
			buttons[i].className = "quit";
			alert(buttons[i].className);
		}
	}
</script>
</head>
<body>

<jsp:include page="/mustinclude/left_nav.jsp" />
<jsp:include page="/mustinclude/top_nav.jsp" />

<div class="k_class">
<div class="container">
  <table class="responsive-table">
    <caption>野餐團列表</caption>
    <thead>
      <tr>
        <th scope="col">野餐團名稱</th>
        <th scope="col">揪團日期</th>
        <th scope="col">人數上限</th>
        <th scope="col">野餐團敘述</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="pic" items="${picniclist}" varStatus="status">
      <tr>
			<td>${pic.picnic_name}</td>
			<td>${pic.picnic_startup}</td>
			<td>${pic.picnic_pl}</td>
			<td>${pic.picnic_desc}</td>
      
      	<c:if test="${empty isNoGm}">
			<td>
				<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/picmem.do" >
		        	<input type="hidden" name="PICNIC_NO" value="${pic.picnic_no}">
		        	<section>	
		        		<button type="submit" value="${allList[status.index]}${isForSearch}" name="button" class="button" >${allList[status.index]}</button>
		        	<section>	
				</FORM>
			</td>
		</c:if> 
		</tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</div>
<jsp:include page="/mustinclude/chatroom.jsp" />
</body>
</html>

