<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>個人頁面 - Picnic野餐網</title>
<jsp:include page="/mustinclude/head.jsp" />

<style type="text/css">
h3 {
	text-align: center;
	padding: 15px;
}

body {
	font-family: "微軟正黑體", Arial, sans-serif;
}

li+a {
	padding: 0 10px;
	color: #595959;
	cursor: pointer;
	border-bottom: 2px solid transparent;
	line-height: 58px;
	margin-right: 20px;
	text-decoration: none;
}

.list {
	list-style: none;
}

.bb {
	font-size: 16px;

	/*display: flex;*/
	/*justify-content: center;*/
}

#tab4 {
	margin-top: 10px;
}

#btn {
	color: #fff;
	background-color: #5bc0de;
	border-color: #46b8da;
	display: inline-block;
	padding: 6px 12px;
	margin-bottom: 0;
	font-size: 16px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	-ms-touch-action: manipulation;
	touch-action: manipulation;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

#btn:hover {
	background-color: SkyBlue;
}

#btn:active {
	background-color: #40E0D0;
	transform: translateY(4px);
}
</style>
</head>
<jsp:useBean id="GeneralMemberSvc" scope="page"
	class="com.general_member.model.GeneralMemberService" />
<jsp:include page="/mustinclude/left_nav.jsp" />
<jsp:include page="/mustinclude/top_nav.jsp" />
<div class="container" style="background: white; opacity: 0.8">
	<div class="row">

		<div class="col-xs-12 col-sm-12">

			<div class="col-xs-12 col-sm-9 col-sm-offset-1" >
				<img
					src="<%=request.getContextPath()%>/general_member/DBGift.do?MEM_NO=${contact_no}"
					style="display: block; margin: auto; border-radius: 25%; width: 200px; height: 200px;">

			</div>
<div class="col-xs-12 col-sm-2">
				<div>
					<ul class="list">
						<li style="padding-top: 5px;"><a href="<%=request.getContextPath()%>/checklist/addChecklist.jsp?chli_cate=3&chli_be_num=${contact_no}">檢舉會員</a></li>
					</ul>
				</div>
			</div>



		</div>
		<div class="col-xs-12 col-sm-11" style="background: white;opacity:0.8;">
			<h3 style="font-family: DFKai-sb;">
				<b>${GeneralMemberSvc.getOneGeneralMember(contact_no).MEM_NAME}</b>
			</h3>
		</div>
	</div>
</div>
</div>
<div class="container" style="background: white; opacity: 0.8">
	<div class="row">
		<div class="col-xs-12 col-sm-11 bb" style="background: white;opacity:0.8;">
			<div role="tabpanel">
				<!-- 標籤面板：標籤區 -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab1"
						aria-controls="tab1" role="tab" data-toggle="tab" class="li1">個人資料</a>
					</li>

          
          
					<li role="presentation"><a href="#tab4" aria-controls="tab4"
						role="tab" data-toggle="tab" class="li1">朋友</a></li>

				</ul>

				<!-- 標籤面板：內容區 -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab1">
						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;">性別 :
							${GeneralMemberSvc.getOneGeneralMember(contact_no).MEM_GEN}</div>
						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;">生日 :
							${GeneralMemberSvc.getOneGeneralMember(contact_no).MEM_BIRTH}</div>
						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;">自我介紹 :
							${GeneralMemberSvc.getOneGeneralMember(contact_no).MEM_SELF}</div>



					</div>


          
          

					<jsp:useBean id="Contact_ListSvc" scope="page"
						class="com.contact_list.model.Contact_ListService" />


					<c:forEach var="Contact_ListVO" items="${Contact_ListSvc.all}">
						<div role="tabpanel" class="tab-pane" id="tab4">

							<c:if test="${contact_no == Contact_ListVO.mem_no}">
								<form method="post" id="form3"
									action="<%=request.getContextPath()%>/contact_list/contact_list.do">
									<a
										href="<%= request.getContextPath()%>/contact_list/contact_list.do?action=getOne_For_MG&mem_no=${Contact_ListVO.mem_no}&contact_no=${Contact_ListVO.contact_no}">
										<div class="col-sm-4" style="border: solid">
											<div class="news">
												<div class="img-figure">


													<img
														src="<%=request.getContextPath()%>/general_member/DBGift.do?MEM_NO=${Contact_ListVO.contact_no}"
														style="display: block; margin: auto; border-radius: 25%; width: 200px; height: 200px;"
														class="img-responsive">

												</div>

												<div class="title">
													<h3 align="center">${GeneralMemberSvc.getOneGeneralMember(Contact_ListVO.contact_no).MEM_NAME}</h3>
												</div>
												<input type="hidden" name="mem_no"
													value="${Contact_ListVO.mem_no}"> <input
													type="hidden" name="contact_no"
													value="${Contact_ListVO.contact_no}"> <input
													type="hidden" name="action" value="getOne_For_MG">
											</div>
										</div>
									</a>
								</form>
							</c:if>
							</div>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>
</div>
<div class="col-sm-11 col-sm-push-3">
	<jsp:include page="/mustinclude/footer.jsp" />
</div>