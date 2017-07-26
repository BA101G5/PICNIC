<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container" style="background-color:white;opacity:0.8;">
	<div class="row">
		<div class="col-xs-12 col-sm-12" style="background: white;">

			<div class="col-xs-12 col-sm-9 col-sm-offset-1">
				<img
					src="<%=request.getContextPath()%>/general_member/DBGift.do?MEM_NO=${gVO.MEM_NO}"
					style="display: block; margin: auto; border-radius: 25%; width: 200px; height: 200px;"
					class="img-responsive">

			</div>
			<div class="col-xs-12 col-sm-2">
				<div>
					<ul class="list" >

						<Form method="post" id="form1"
							action="<%=request.getContextPath()%>/general_member/General_Member.do">
							<li style="padding-top: 5px;"><a href="#"
								onclick="document.getElementById('form1').submit();">個人資料修改</a></li>
							<input type="hidden" name="MEM_NO" value="${gVO.MEM_NO}">
							<input type="hidden" name="action" value="getOne_For_Update">

						</Form>

					<li style="padding-top: 5px;"><a
							href="<%=request.getContextPath()%>/buy_coin.jsp">購買點數</a></li>


					</ul>
				</div>
			</div>



		</div>
		<div class="col-xs-12 col-sm-11">
			<h3 style="font-family: DFKai-sb;">
				<b>${gVO.MEM_NAME}</b>
			</h3>
		</div>
	</div>
</div>
</div>
<div class="container"style="background-color:white;height:50%;opacity:0.8;">
	<div class="row">
		<div class="col-xs-12 col-sm-11 bb">
			<div role="tabpanel">
				<!-- 標籤面板：標籤區 -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab1"
						aria-controls="tab1" role="tab" data-toggle="tab" class="li1">個人資料</a>
					</li>
					<li role="presentation"><a href="#tab2" aria-controls="tab2"
						role="tab" data-toggle="tab" class="li1">點數</a></li>
					
					<li role="presentation"><a href="#tab4" aria-controls="tab4"
						role="tab" data-toggle="tab" class="li1">朋友</a></li>

				</ul>
				<jsp:useBean id="Buy_recordSvc" scope="page"
					class="com.buy_record.model.Buy_RecordService" />
				<!-- 標籤面板：內容區 -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab1">
						
						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;">性別 :
							${gVO.MEM_GEN}</div>
						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;">生日 :
							${gVO.MEM_BIRTH}</div>
						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;margin-bottom:40px;">自我介紹 :
							${gVO.MEM_SELF}</div>

						
						
					</div>

					<div role="tabpanel" class="tab-pane" id="tab2">




						


						<div class="col-xs-12 col-sm-12"
							style="margin-top: 20px; text-align: center;">野餐幣 : $
							${gVO.MEM_COIN}</div>


						<table border="1" width="800" align='center'
							style="margin-top: 5px;" class="table table-hover table-bordered table-condensed table-striped">
							<thead>
							<tr align='center' valign='middle'>
								<th style="text-align: center;">交易編號</th>
								<th style="text-align: center;">紀錄時間</th>
								<th style="text-align: center;">儲值金額</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach var="Buy_recordVO" items="${Buy_recordSvc.all}">
								<c:if test="${Buy_recordVO.MEM_NO == gVO.MEM_NO}">
									<tr align='center' valign='middle'>

										<td>${Buy_recordVO.BR_ID}</td>
										<td>${Buy_recordVO.BR_DATE}</td>
										<td>${Buy_recordVO.BR_CASH}</td>

									</tr>
								</c:if>
							</c:forEach>
							</tbody>
						</table>
					</div>
					

					<jsp:useBean id="Contact_ListSvc" scope="page"
						class="com.contact_list.model.Contact_ListService" />
					<jsp:useBean id="GeneralMemberSvc" scope="page"
						class="com.general_member.model.GeneralMemberService" />

					
						<div role="tabpanel" class="tab-pane" id="tab4">
<c:forEach var="Contact_ListVO" items="${Contact_ListSvc.all}">
							<c:if test="${gVO.MEM_NO == Contact_ListVO.mem_no}">

								<a
									href="<%= request.getContextPath()%>/contact_list/contact_list.do?action=getOne_For_MG&mem_no=${Contact_ListVO.mem_no}&contact_no=${Contact_ListVO.contact_no}">
									<div class="col-sm-4">
										<div class="news" style="height:250px;">
											<div class="img-figure">


												<img
													src="<%=request.getContextPath()%>/general_member/DBGift.do?MEM_NO=${Contact_ListVO.contact_no}"
													style="display: block; margin: auto; border-radius: 25%; width: 200px; height: 200px;"
													class="img-responsive">

											</div>

											<div class="title">
												<h3 align="center">${GeneralMemberSvc.getOneGeneralMember(Contact_ListVO.contact_no).MEM_NAME}</h3>
											</div>

										</div>
									</div>
								</a>

							</c:if>
					</c:forEach>
				</div>
			</div>

		</div>
	</div>
	</div>
</div>



<div class="col-sm-11 col-sm-push-3">
	<jsp:include page="/mustinclude/footer.jsp" />
</div>

