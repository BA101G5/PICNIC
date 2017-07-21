<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>會員資料管理</title>
	<script src="${pageContext.request.contextPath}/view/js/sweetalert.min.js"></script>
	<link href="${pageContext.request.contextPath}/view/js/sweetalert.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/view/js/admView.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table>
<FORM METHOD="post" ACTION="${pageContext.request.contextPath}/k_man_men_data.do" id="title_button">
	<tr>
		<td>
			<section class="select">	
				<button type="submit" form="title_button" value="back" name="button">廠商資料審核</button>
				<button type="submit" form="title_button" value="cho_generalMember" name="button">會員權限維護</button>		
			</section>
		</td>
	</tr>
</FORM>
</table>

<c:if test="${not empty maList}">
<table id="ma_Table">
		<tr>
			<td>廠商名稱</td>
			<td>帳號</td>
			<td>廠商電話</td>
			<td>統一編號</td>
			<td>檢舉次數</td>
			<td>狀態</td>
			<td></td>
		</tr>
	<c:forEach var="maList" items="${maList}" varStatus="status">
		<tr>
			<td>${maList.MF_NAME}</td>
			<td>${maList.MF_ACCO}</td>
			<td>${maList.MF_PHONE}</td>
			<td>${maList.MF_BS}</td>
			<td>${maList.MF_REPORTNUM}</td>
			<td>${staList[status.index]}</td>
			<td><a href="${pageContext.request.contextPath}/k_man_men_data.do?button=detail&argu=${maList.MF_NO}">詳細資料</a></td>
		</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${not empty ma_detail}">
	<table class="form-style-1" id="ma_detailTable">
			<form id="ma_detailForm" action="${pageContext.request.contextPath}/k_man_men_data.do" method="post" name="ma_detailForm" enctype="multipart/form-data">
				<tr>
					<td>廠 商 名 稱<input type="text" name="MF_NAME" class="field-divided" placeholder="${ma_detail.MF_NAME}" /></td>
					<td>廠 商 電 話<input type="text" name="MF_PHONE" class="field-divided" placeholder="${ma_detail.MF_PHONE}" /></td>
				</tr>
				<tr>
					<td colspan="2">廠 商 信 箱<input type="email" name="MF_MAIL" class="field-long" placeholder="${ma_detail.MF_MAIL}" /></td>
				</tr>
				<tr>
					<td>帳 號<input type="text" name="MF_ACCO" class="field-divided" placeholder="${ma_detail.MF_ACCO}" /></td>
					<td>密 碼<input type="text" name="MF_PSW" class="field-divided" placeholder="${ma_detail.MF_PSW}" /></td>
				</tr>
				<tr>
					<td>統 一 編 號<input type="text" name="MF_BS" class="field-divided" placeholder="${ma_detail.MF_BS}" /></td>
					<td>傳 真<input type="text" name="MF_FAX" class="field-divided" placeholder="${ma_detail.MF_FAX}" /></td>
				</tr>
				<tr>
					<td colspan="2">地址<input type="text" name="MF_ADDR" class="field-long" placeholder="${ma_detail.MF_ADDR}" /></td>
				</tr>
				<tr>
					<td>狀 態<input type="text" name="MF_STA" class="field-divided" placeholder="${staList}" disabled/></td>
					<td>檢 舉 次 數<input type="text" name="MF_REPORTNUM" class="field-divided" placeholder="${ma_detail.MF_REPORTNUM}" /></td>
				</tr>
				<tr>
					<td colspan="2">廠 商 介 紹<textarea name="MF_SELF" id="field5" class="field-long field-textarea">${ma_detail.MF_SELF}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						廠商LOGO<br>
						<img src="${picPath}">
						<input type="file" name="logo" accept="image/jpg">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="MF_NO" value="${ma_detail.MF_NO}" />
						<section>	
							<button type="button" form="ma_detailForm" value="ma_mod" 		   name="button" onclick="kkkk(event)">修改</button>
							<button type="button" form="ma_detailForm" value="ma_verification" name="button" onclick="kkkk(event)">認證</button>
							<button type="button" form="ma_detailForm" value="ma_removed" 	   name="button" onclick="kkkk(event)">移除</button>	
							<button type="button" form="ma_detailForm" value="ma_suspension"   name="button" onclick="kkkk(event)">停權</button>	
						</section>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="${pageContext.request.contextPath}/k_man_men_data.do?button=back">返回列表</a>
					</td>
				</tr>
			</form>
	</table>
</c:if>
<c:if test="${not empty memList}">
<table id="mem_Table">
		<tr>
			<td>會員姓名</td>
			<td>性別</td>
			<td>會員生日</td>
			<td>電子信箱</td>
			<td>個人留言版</td>
			<td>會員狀態</td>
			<td></td>
		</tr>
	<c:forEach var="memList" items="${memList}" varStatus="status">
		<tr>
			<td>${memList.MEM_NAME}</td>
			<td>${memList.MEM_GEN}</td>
			<td>${memList.MEM_BIRTH}</td>
			<td>${memList.MEM_MAIL}</td>
			<td>${pboList[status.index]}</td>
			<td>${staList[status.index]}</td>
			<td><a href="${pageContext.request.contextPath}/k_man_men_data.do?button=detail&argu=${memList.MEM_NO}">詳細資料</a></td>
		</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${not empty gm_detail}">
	<table class="form-style-1" id="gm_detailTable">
			<form id="gm_detaillForm" action="${pageContext.request.contextPath}/k_man_men_data.do" method="post" name="gm_detailForm" enctype="multipart/form-data">
				<tr>
					<td>會 員 姓 名<input type="text" name="MF_NAME" class="field-divided" placeholder="${gm_detail.MEM_NAME}" /></td>
					<td>會 員 手 機<input type="text" name="MF_PHONE" class="field-divided" placeholder="${gm_detail.MEM_PHONE}" /></td>
				</tr>
				<tr>
					<td colspan="2">電 子 信 箱<input type="email" name="MF_MAIL" class="field-long" placeholder="${gm_detail.MEM_MAIL}" /></td>
				</tr>
				<tr>
					<td>會 員 生 日<input type="text" name="MF_BIRTH" class="field-divided" placeholder="${gm_detail.MEM_BIRTH}" /></td>
					<td>密 碼<input type="text" name="MF_PSW" class="field-divided" placeholder="${gm_detail.MEM_PSW}" /></td>
				</tr>
				<tr>
					<td>野 餐 幣 錢 包<input type="text" name="MF_COIN" class="field-divided" placeholder="${gm_detail.MEM_COIN}" /></td>
					<td>性 別<input type="text" name="MF_GEN" class="field-divided" placeholder="${gm_detail.MEM_GEN}" /></td>
				</tr>
				<tr>
					<td colspan="2">住 址<input type="text" name="MF_ADDR" class="field-long" placeholder="${gm_detail.MEM_ADDR}" /></td>
				</tr>
				<tr>
					<td>狀 態<input type="text" name="MF_STA" class="field-divided" placeholder="${staList}" disabled/></td>
					<td>個 人 留 言 版<br>
						<select name="pboardValue">
						<c:if test="${pbo == '開啟'}">
				　			<option value="N" >關閉</option>
				　			<option value="Y" selected = "true">開啟</option>
						</c:if>
						<c:if test="${pbo == '關閉'}">
				　			<option value="N" selected = "true">關閉</option>
				　			<option value="Y" >開啟</option>
						</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">自 我 介 紹<textarea name="MF_SELF" id="field5" class="field-long field-textarea">${gm_detail.MEM_SELF}</textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						個人照片<br>
						<img src="${picPath}">
						<input type="file" name="logo" accept="image/jpg">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="MF_NO" value="${gm_detail.MEM_NO}" />
						<section>	
							<button type="button" form="gm_detaillForm" value="ma_mod" 		   name="button" onclick="kkkk(event)">修改</button>
							<button type="button" form="gm_detaillForm" value="ma_verification" name="button" onclick="kkkk(event)">認證</button>
							<button type="button" form="gm_detaillForm" value="ma_removed" 	   name="button" onclick="kkkk(event)">移除</button>	
							<button type="button" form="gm_detaillForm" value="ma_suspension"   name="button" onclick="kkkk(event)">停權</button>	
						</section>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="${pageContext.request.contextPath}/k_man_men_data.do?button=cho_generalMember">返回列表</a>
					</td>
				</tr>
			</form>
	</table>
</c:if>

<c:if test="${empty maList and empty ma_detail and empty gm_detail and empty memList}">
	資料為空
</c:if>
</body>
</html>
<style type="text/css">



.form-style-1 {
    margin:1px auto;
    max-width: 400px;
    padding: 1px 1px 1px 1px;
    font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}


.form-style-1 input[type=text],
.form-style-1 input[type=password],
.form-style-1 input[type=date],
.form-style-1 input[type=datetime],
.form-style-1 input[type=number],
.form-style-1 input[type=search],
.form-style-1 input[type=time],
.form-style-1 input[type=url],
.form-style-1 input[type=email],
textarea, 
select{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    border:1px solid #BEBEBE;
    padding: 7px;
    margin:0px;
    -webkit-transition: all 0.30s ease-in-out;
    -moz-transition: all 0.30s ease-in-out;
    -ms-transition: all 0.30s ease-in-out;
    -o-transition: all 0.30s ease-in-out;
    outline: none;  
}
.form-style-1 input[type=text]:focus,
.form-style-1 input[type=date]:focus,
.form-style-1 input[type=datetime]:focus,
.form-style-1 input[type=number]:focus,
.form-style-1 input[type=search]:focus,
.form-style-1 input[type=time]:focus,
.form-style-1 input[type=url]:focus,
.form-style-1 input[type=email]:focus,
.form-style-1 textarea:focus, 
.form-style-1 select:focus{
    -moz-box-shadow: 0 0 8px #88D5E9;
    -webkit-box-shadow: 0 0 8px #88D5E9;
    box-shadow: 0 0 8px #88D5E9;
    border: 1px solid #88D5E9;
}
.form-style-1 .field-divided{
    width: 90%;
}

.form-style-1 .field-long{
    width: 100%;
}
.form-style-1 .field-select{
    width: 100%;
}
.form-style-1 .field-textarea{
    height: 100px;
}
.form-style-1 input[type=submit], .form-style-1 input[type=button]{
    background: #4B99AD;
    padding: 8px 15px 8px 15px;
    border: none;
    color: #fff;
}
.form-style-1 input[type=submit]:hover, .form-style-1 input[type=button]:hover{
    background: #4691A4;
    box-shadow:none;
    -moz-box-shadow:none;
    -webkit-box-shadow:none;
}
.form-style-1 .required{
    color:red;
}


section button {
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

section button:hover {
  background-color: #ffffff;
  color: #009ac9;
  border-color: #009ac9;
}

section.select button {
  margin-left : 50px;
  margin-right : 50px;
  font-size: 1.0rem;
  padding: 0.5rem 0.5rem;
  display: inline;
  background-color: #D0104C;
  border: 1px solid transparent;
  color: #ffffff;
  font-weight: 100;
  -webkit-border-radius: 3px;
  border-radius: 3px;
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  transition: all 0.3s ease-in-out;
}

section.select button:hover {
  background-color: #ffffff;
  color: #D0104C;
  border-color: #D0104C;
}

section{
	display: inline
}


#ma_detailTable ,#ma_Table{
	margin-top:30px; 
}

img {
	max-width:300px;
	myimg:expression(onload=function(){
	this.style.width=(this.offsetWidth > 600)?"600px":"auto"});
}

</style>

<script type="text/javascript">


function kkkk(event){
	
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

