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
		<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<style type="text/css">

table{
margin-top:50px;
margin-left:auto; 
margin-right:auto;
text-align: center;
}

section button {
  margin-left : 10px;
  margin-right : 10px;
  font-size: 1.0rem;
  padding: 0.01rem 0.5rem;
  display: inline;
  background-color: #2EA9DF;
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
  color: #2EA9DF;
  border-color: #2EA9DF;
}

td.reason{
	font-size: 18px;
	font-weight: bold;
	color:#CC543A;
}

h1{
	margin-top:150px;
	color:#CC543A;
	font-weight: bold;
	text-align:center;
	
}
</style>
<script type="text/javascript">
function checked(event){
	
	var delButton = event.target;
	
	swal({
		  title: "Are you sure?",
		  text: "",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "Yes, do it!",
		  closeOnConfirm: false
	},
	function(){

			delButton.type = "submit";
		  	delButton.click();  	
	});
	
}
</script>
</head>
<body>
<c:if test="${not empty gmVO}">
	<h1>檢舉對象    :  ${gmVO.MEM_NAME}</h1>
	<table>
		<tr>
			<td>會員姓名</td>
			<td>性別</td>
			<td>會員生日</td>
			<td>住址</td>
			<td>電子信箱</td>
		</tr>
		<tr>
			<td>${gmVO.MEM_NAME}</td>
			<td>${gmVO.MEM_GEN}</td>
			<td>${gmVO.MEM_BIRTH}</td>
			<td>${gmVO.MEM_ADDR}</td>
			<td>${gmVO.MEM_MAIL}</td>
		</tr>
		<tr>
			<td colspan=6 class="reason">
				檢舉原因
			</td>
		</tr>
		<tr>
			<td colspan=6>
				<form action="${pageContext.request.contextPath}/checklist.do" method='post'>
					<textarea name="content" id="content" rows="10" cols="80"></textarea>
					<script>
						CKEDITOR.replace( 'content', {

						});
					</script>
					<section>
						<input type = "hidden" name = "number_no" value="${gmVO.MEM_NO}">
						<button type="button" onclick="checked(event)" name="button" value="checkCannel">取消</button>
						<button type="button" onclick="checked(event)" name="button" value="checkCertain">確定</button>
					</section>
				</form>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${not empty mfVO}">
	<h1>檢舉對象    :  ${mfVO.MF_NAME}</h1>
	<table>
		<tr>
			<td>廠商名稱</td>
			<td>廠商電話</td>
			<td>廠商信箱</td>
			<td>帳號</td>
			<td>統一編號</td>
		</tr>
		<tr>
			<td>${mfVO.MF_NAME}</td>
			<td>${mfVO.MF_PHONE}</td>
			<td>${mfVO.MF_MAIL}</td>
			<td>${mfVO.MF_ACCO}</td>
			<td>${mfVO.MF_BS}</td>
		</tr>
		<tr>
			<td colspan=6 class="reason">
				檢舉原因
			</td>
		</tr>
		<tr>
			<td colspan=6>
				<form action="${pageContext.request.contextPath}/checklist.do" method='post'>
					<textarea name="content" id="content" rows="10" cols="80"></textarea>
					<script>
						CKEDITOR.replace( 'content', {

						});
					</script>
					<section>
						<input type = "hidden" name = "number_no" value="${mfVO.MF_NO}">
						<button type="button" onclick="checked(event)" name="button" value="checkCannel">取消</button>
						<button type="button" onclick="checked(event)" name="button" value="checkCertain">確定</button>
					</section>
				</form>
			</td>
		</tr>
	</table>
</c:if>
</body>
</html>


