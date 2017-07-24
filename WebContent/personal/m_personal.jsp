<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container"
	style="margin-top: 20px; background-color: white; opacity: 0.8;">
	<div class="row">
		<div class="col-xs-12 col-sm-12"
			style="display: flex; justify-content: space-between;">
			<div class="col-xs-12 col-sm-4" style="padding-top: 10px">
				<img
					src="<%=request.getContextPath()%>/manufacturers/DBGift1.do?MF_NO=${mVO.MF_NO}"
					style="display: block; float: left; padding-top: 10px; border-radius: 25%; width: 150px; height: 150px;">
			</div>
			<div class="col-xs-12 col-sm-4" style="padding-top: 25px;">
				<marquee>
					<h1 style="float: left; font-family: DFKai-sb;">
						<b> Welcome , ${mVO.MF_NAME}</b>
					</h1>
				</marquee>
			</div>
			<div class="col-xs-12 col-sm-4" style="margin-top: 5px;">
				<div class="col-xs-12 col-sm-12">
					<Form method="post" id="form1"
						action="<%=request.getContextPath()%>/manufacturers/Manufacturers.do">
						<li style="padding-top: 5px; float: right" class="list"><a
							href="#" onclick="document.getElementById('form1').submit();">廠商資料修改</a></li>
						<input type="hidden" name="MF_NO" value="${mVO.MF_NO}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</Form>
				</div>
				<div class="col-xs-12 col-sm-12">
					<Form method="post" id="form2"
						action="<%=request.getContextPath()%>/advertisement_buy.jsp">
						<li style="padding-top: 5px; float: right" class="list"><a
							href="#" onclick="document.getElementById('form2').submit();">購買廣告</a></li>

					</Form>
				</div>
				<div class="col-xs-12 col-sm-12">

					<Form method="post" id="form3"
						action="<%=request.getContextPath()%>/good_buy.jsp">
						<li style="padding-top: 5px; float: right" class="list"><a
							href="#" onclick="document.getElementById('form3').submit();">上架商品</a></li>
					</Form>
				</div>
				<div class="col-xs-12 col-sm-12">
					<Form method="post" id="form4"
						action="<%=request.getContextPath()%>/good_rent.jsp">
						<li style="padding-top: 5px; float: right" class="list"><a
							href="#" onclick="document.getElementById('form4').submit();">上架商品出租</a></li>
					</Form>
				</div>

			</div>

		</div>

	</div>
</div>
</div>
<div class="container"
	style="background-color: white; height: 80%; opacity: 0.8;">
	<div class="row">
		<div class="col-xs-12 col-sm-11 bb">
			<div role="tabpanel" style="">
				<!-- 標籤面板：標籤區 -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" style="float: right"><a href="#tab1"
						aria-controls="tab1" role="tab" data-toggle="tab" class="li1">商品</a>
					</li>
					<li role="presentation" style="float: right"><a href="#tab2"
						aria-controls="tab2" role="tab" data-toggle="tab" class="li1">廣告</a></li>
					<li role="presentation" style="float: right"><a href="#tab3"
						aria-controls="tab4" role="tab" data-toggle="tab" class="li1">商品出租</a></li>
					<li role="presentation" class="active" style="float: right"><a
						href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab"
						class="li1">廠商自介</a></li>

				</ul>
				<jsp:useBean id="ADSvc" scope="page"
					class="com.advertisement.model.AdvertisementService" />
				<jsp:useBean id="GSSvc" scope="page"
					class="com.goods_sell.model.Goods_SellService" />
				<jsp:useBean id="GRSvc" scope="page"
					class="com.goods_rent.model.Goods_RentService" />
				<!-- 標籤面板：內容區 -->
				<div class="tab-content">

					<div role="tabpanel" class="tab-pane" id="tab1">

						<div class="col-xs-12 col-sm-12" style="text-align: center;">
							<h1>
								<b>目前商品</b>
							</h1>

						</div>

						<c:forEach var="goods_sellVO" items="${GSSvc.all}">


							<c:if test="${mVO.MF_NO == goods_sellVO.mf_no}">



								<div class="col-sm-4">
									<div class="news" style="height: 250px;">
										<div class="img-figure">


											<img
												src="<%=request.getContextPath()%>/good_sell/DBGift4.do?GS_NO=${goods_sellVO.gs_no}"
												style="display: block; margin: auto; border-radius: 25%; width: 200px; height: 200px;"
												class="img-responsive">

										</div>

										<div class="title">
											<h3 align="center">
												<FORM METHOD="post"
													ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
													<input type="submit" value="修改" id="btn"> <input
														type="hidden" name="GS_NO" value="${goods_sellVO.gs_no}">
													<input type="hidden" name="action"
														value="getOne_For_Update">
												</FORM>
											</h3>
										</div>

									</div>
								</div>


							</c:if>
						</c:forEach>







					</div>

					<div role="tabpanel" class="tab-pane" id="tab2">

						<div class="col-xs-12 col-sm-12" style="text-align: center;">
							<h1>
								<b>目前購買之廣告</b>
							</h1>

						</div>
						<div class="col-xs-12 col-sm-12">
							<table border="1" width='800' align="center"
								style="margin-top: 5px;"
								class="table table-hover table-bordered table-condensed table-striped">

								<tr>
									<th>廣告編號</th>
									<th>廣告照片</th>
									<th>廣告介紹</th>
									<th>開始日期</th>
									<th>結束日期</th>
									<th>金額</th>
									<th>狀態</th>
								</tr>

								<c:forEach var="ADVO" items="${ADSvc.all}">
									<c:if test="${mVO.MF_NO == ADVO.MF_NO}">

										<tr align='center' valign='middle'>

											<td>${ADVO.AD_NO}</td>
											<td><img
												src="<%=request.getContextPath()%>/advertisement/DBGift2.do?AD_NO=${ADVO.AD_NO}"
												style="max-width: 50px; max-height: 50px;"></td>
											<td>${ADVO.AD_SELF}</td>
											<td>${ADVO.DAY_START}</td>
											<td>${ADVO.DAY_END}</td>
											<td>${ADVO.AD_CASH}</td>
												<c:set var="sta" value="U" scope="page"></c:set>
												<c:set var="sta1" value="O" scope="page"></c:set>
											
											
											<td>${ADVO.AD_STA.equals(sta.charAt(0))?"待審中":""}
												${ADVO.AD_STA.equals(sta1.charAt(0))?"審核完畢":""}
											
											</td>
						
										</tr>
									</c:if>

								</c:forEach>

							</table>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="tab3">
						<div class="col-xs-12 col-sm-12" style="text-align: center;">
							<h1>
								<b>目前上架出租商品</b>
							</h1>

						</div>
						<div class="col-lg-12">
							<table class="table" id="table">
								<thead>
									<tr>
										<th>物品圖片</th>
										<th>租借編號</th>
										<th>物品名稱</th>
										<th>更新時間</th>
										<th>物品單價</th>
										<th>物品存量</th>
										<th>物品資訊</th>

										<th>可租時間</th>
										<th>物品狀態</th>

									</tr>
								</thead>

								<tbody>
									<c:forEach var="GRVO" items="${GRSvc.all}">
										<c:if test="${mVO.MF_NO == GRVO.mf_no}">
											<form METHOD="post"
												ACTION="<%=request.getContextPath()%>/goods_rent/goods_rent.do"
												enctype="multipart/form-data">
												<tr>
													<td><img
														src="<%=request.getContextPath()%>/goods_rent/DBGift5.do?GR_NO=${GRVO.gr_no}"
														style="display: block; margin: auto; border-radius: 25%; width: 50px; height: 50px;"
														class="img-responsive"></td>
													<td>${GRVO.gr_no}</td>
													<td>${GRVO.gr_name}</td>
													<td>${GRVO.gr_date}</td>
													<td>${GRVO.gr_price}</td>
													<td>${GRVO.gr_count}</td>
													<td>${GRVO.gr_info}</td>

													<td>${GRVO.gr_until}</td>
													<c:set var="a" value="U" scope="page"></c:set>
												<c:set var="a1" value="A" scope="page"></c:set>
													<td>
														${GRVO.gr_sta.equals(a)?"上架中":""}
														${GRVO.gr_sta.equals(a1)?"下架":""}
													</td>
													<td><input type="submit" value="修改" class="btn">
														<input type="hidden" name="gr_no" value="${GRVO.gr_no}">
														<input type="hidden" name="action"
														value="getOne_For_Update"></td>
												</tr>
											</form>
										</c:if>
									</c:forEach>
								</tbody>
							</table>

						</div>

					</div>
					<div role="tabpanel" class="tab-pane active" id="tab4">

						<div class="col-xs-12 col-sm-12" style="margin-bottom: 40px;">
							<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
								<div class="col-xs-12 col-sm-12">
									<div class="col-xs-12 col-sm-6">信箱 :</div>
									<div class="col-xs-12 col-sm-6">${mVO.MF_MAIL}</div>
								</div>
								<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
									<div class="col-xs-12 col-sm-6">電話 :</div>
									<div class="col-xs-12 col-sm-6">${mVO.MF_PHONE}</div>
								</div>
								<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
									<div class="col-xs-12 col-sm-6">傳真 :</div>
									<div class="col-xs-12 col-sm-6">${mVO.MF_FAX}</div>
								</div>
								<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
									<div class="col-xs-12 col-sm-6">統一編號 :</div>
									<div class="col-xs-12 col-sm-6">${mVO.MF_BS}</div>
								</div>
								<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
									<div class="col-xs-12 col-sm-6">地址 :</div>
									<div class="col-xs-12 col-sm-6">${mVO.MF_ADDR}</div>
								</div>



							</div>

							<div class="col-xs-12 col-sm-6"
								style="margin-top: 20px; text-align: center;">

								<div class="col-xs-12 col-sm-12" style="margin-top: 20px;">
									<div class="col-xs-12 col-sm-6">自我介紹 :</div>
									<div class="col-xs-12 col-sm-6">${mVO.MF_SELF}</div>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
<div class="col-sm-11 col-sm-push-3">
	<jsp:include page="/mustinclude/footer.jsp" />
</div>