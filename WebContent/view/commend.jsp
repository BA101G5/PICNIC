<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<script src="${pageContext.request.contextPath}/view/js/sweetalert.min.js"></script>
		<link href="${pageContext.request.contextPath}/view/js/sweetalert.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/view/js/admView.css" rel="stylesheet" type="text/css" />

<style type="text/css">

table{

margin-left:auto; 
margin-right:auto;
text-align: center;
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
<c:if test="${not empty manList or not empty genList}">
	<table>
		<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/commend.do" id="emffff" name="emffff">

		<tr><td>------會員------</td></tr>
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
			<td>
				<section>
					<button type="submit" class="checklistButton" form="emffff" name="button" value="checklist_mem.${genList.MEM_NO}" formaction="${pageContext.request.contextPath}/checklist.do">檢舉</button>
				</section>
			</td>
		</tr>
		</c:forEach>
		<tr><td>------廠商------</td></tr>
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
			<td>
				<section>
					<button type="submit" class="checklistButton" form="emffff" name="button" value="checklist_mem.${manList.MF_NO}" formaction="${pageContext.request.contextPath}/checklist.do">檢舉</button>
				</section>
			</td>
		</tr>	
		</c:forEach>
		</FORM>
	</table>
</c:if>
<button type="submit" form="emffff" value="commendsubmit" name=button id="commendsubmit">submit</button>
</body>
</html>