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
<title>Insert title here</title>
		<script src="${pageContext.request.contextPath}/view/js/sweetalert.min.js"></script>
		<link href="${pageContext.request.contextPath}/view/js/sweetalert.css" rel="stylesheet" type="text/css" />

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

section button {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
  display: inline;
  background-color: #eeeeee;
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
  color: #eeeeee;
  border-color: #eeeeee;
}

section button.checklistButton {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
  display: inline;
  background-color: #eeeeee;
  border: 1px solid transparent;
  color: #AA5555;
  font-weight: 300;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section button.checklistButton:hover {
  background-color: #AA5555;
  color: #eeeeee;
  border-color: #eeeeee;
}

section.submittt button {
  margin-left : 1px;
  margin-right : 1px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
  display: inline;
  background-color: #eeeeee;
  border: 1px solid transparent;
  color: #0000ff;
  font-weight: 300;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section.submittt button:hover {
  background-color: #0000ff;
  color: #eeeeee;
  border-color: #eeeeee;
}

</style>
<script type="text/javascript">

			function commend(event){

				var button = event.target;
				var path = button.src;
				if(button instanceof HTMLImageElement){	
					var meValue = button.parentNode.parentNode.children[0].value;
					var pathNumber = path.lastIndexOf("dislike");
					if(meValue == 0){
						if(pathNumber == -1){
							button.parentNode.parentNode.children[0].value = 1;
						}else{
							button.parentNode.parentNode.children[0].value = 2;
						}
					}
					if(meValue == 1){
						if(pathNumber == -1){
							button.parentNode.parentNode.children[0].value = 0;
						}else{
							button.parentNode.parentNode.children[0].value = 2;
						}
					}
					if(meValue == 2){
						if(pathNumber == -1){
							button.parentNode.parentNode.children[0].value = 1;
						}else{
							button.parentNode.parentNode.children[0].value = 0;
						}
					}
					meValue = button.parentNode.parentNode.children[0].value;
					var img = button.parentNode.parentNode.children[1].children[0]
					var img2 = button.parentNode.parentNode.children[2].children[0]
					if(meValue == 0){
						img.src = img.src.replace("like(1).png","like.png")
						img2.src = img2.src.replace("like(1).png","like.png")
					}
					if(meValue == 1){
						img.src = img.src.replace("like.png","like(1).png")
						img2.src = img2.src.replace("like(1).png","like.png")
					}
					if(meValue == 2){
						img.src = img.src.replace("like(1).png","like.png")
						img2.src = img2.src.replace("like.png","like(1).png")
					}
				}
				//alert(button.parentNode.parentNode.children[1].children[0].src);
				//alert(button.parentNode.parentNode.children[2].children[0].src);
				//button.src = path.replace("like(1).png","like.png");

				//button.src = path.replace(/like.png/,"like(1).png");		
			}
		</script>
</head>
<body>

<jsp:include page="/mustinclude/left_nav.jsp" />
<jsp:include page="/mustinclude/top_nav.jsp" />

<div class="k_class">
<c:if test="${not empty manList or not empty genList}">
	<table>
		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/commend.do" id="emffff" name="emffff">

		<tr><td  colspan="5">------會員------</td></tr>
		<c:forEach var="genList" items="${genList}" varStatus="status">
		<tr>
			<td>${genList.MEM_NAME}</td>
			<td>${genList.MEM_GEN}</td>
			<td>${genList.MEM_BIRTH}</td>
			<td>${genList.MEM_MAIL}</td>
			<td>
					<section>
						<input type = "hidden" name = "mem_no" value="${genList.MEM_NO}">
						<div>
							<input type = "hidden" name = "mem_com" value="0">
							<button class="like" type="button" onclick="commend(event)"><img src="${pageContext.request.contextPath}/photo/like.png" width="30"></button>
							<button class="dislike"type="button" onclick="commend(event)"><img src="${pageContext.request.contextPath}/photo/dislike.png" width="30"></button>
						</div>
					</section>			
			</td>
		</tr>
		</c:forEach>
		<tr><td colspan="5">------廠商------</td></tr>
		<c:forEach var="manList" items="${manList}" varStatus="status">
		<tr>
			<td>${manList.MF_NAME}</td>
			<td>${manList.MF_PHONE}</td>
			<td>${manList.MF_MAIL}</td>
			<td>${manList.MF_SELF}</td>
			<td>
				<section>
						<input type = "hidden" name = "man_no" value="${manList.MF_NO}">
						<div>
							<input type = "hidden" name = "man_com" value="0">
							<button class="like" type="button" onclick="commend(event)"><img src="${pageContext.request.contextPath}/photo/like.png" width="30"></button>
							<button class="dislike"type="button" onclick="commend(event)"><img src="${pageContext.request.contextPath}/photo/dislike.png" width="30"></button>
						</div>
				</section>
			</td>
		</tr>	
		</c:forEach>
		</FORM>
		<tr>
			<td colspan="5">
				<section class="submittt">
					<button type="submit" form="emffff" value="commendsubmit" name=button id="commendsubmit">提交</button>
				</section>
			</td>
		</tr>
	</table>
</c:if>
</div>
<jsp:include page="/mustinclude/chatroom.jsp" />
</body>
</html>