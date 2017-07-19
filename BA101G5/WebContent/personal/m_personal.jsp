<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12" style="background: white;">
			<div class="col-xs-12 col-sm-6">
				<img
					src="<%=request.getContextPath()%>/manufacturers/DBGift1.do?MF_NO=${mVO.MF_NO}"
					style="display: block; float: left; margin: auto; border-radius: 25%; width: 200px; height: 200px;">
				<div class="col-xs-12 col-sm-6" style="padding-top: 7%;">
					<marquee>
						<h3 style="float: left; font-family: DFKai-sb;">
							<b> Welcome , ${mVO.MF_NAME}</b>
						</h3>
					</marquee>
				</div>

			</div>





		</div>

	</div>
</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-11 bb">
			<div role="tabpanel" style="">
				<!-- ���ҭ��O�G���Ұ� -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" style="float: right"><a href="#tab1"
						aria-controls="tab1" role="tab" data-toggle="tab" class="li1">�ӫ~</a>
					</li>
					<li role="presentation" style="float: right"><a href="#tab2"
						aria-controls="tab2" role="tab" data-toggle="tab" class="li1">�s�i</a></li>
					<li role="presentation" style="float: right"><a href="#tab3"
						aria-controls="tab3" role="tab" data-toggle="tab" class="li1">�峹</a></li>
					<li role="presentation" class="active" style="float: right"><a
						href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab"
						class="li1">�t�Ӧۤ�</a></li>

				</ul>
				<jsp:useBean id="ADSvc" scope="page"
					class="com.advertisement.model.AdvertisementService" />
				<!-- ���ҭ��O�G���e�� -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane" id="tab1"></div>
					<div role="tabpanel" class="tab-pane" id="tab2">
						<Form method="post" id="form2"
							action="<%=request.getContextPath()%>/advertisement_buy.jsp">
							<li style="padding-top: 2px;"><a href="#"
								onclick="document.getElementById('form2').submit();">�ʶR�s�i</a></li>

						</Form>
						<table border="1" width='800'>

							<tr>
								<th>�s�i�s��</th>
								<th>�s�i�Ӥ�</th>
								<th>�s�i����</th>
								<th>�}�l���</th>
								<th>�������</th>
								<th>���B</th>
								<th>���A</th>
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
										<td>${ADVO.AD_STA}</td>

									</tr>

								</c:if>
							</c:forEach>

						</table>
					</div>
					<div role="tabpanel" class="tab-pane" id="tab3">�峹���Ҫ����e</div>
					<div role="tabpanel" class="tab-pane active" id="tab4">
						<div class="col-xs-12 col-sm-12">



							<Form method="post" id="form1"
								action="<%=request.getContextPath()%>/manufacturers/Manufacturers.do">
								<li style="padding-top: 2px;"><a href="#"
									onclick="document.getElementById('form1').submit();">�t�Ӹ�ƭק�</a></li>
								<input type="hidden" name="MF_NO" value="${mVO.MF_NO}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</Form>




						</div>
						<div class="col-xs-12 col-sm-6"
							style="margin-top: 20px; text-align: right;">�H�c :</div>
						<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
							${mVO.MF_MAIL}</div>
						<div class="col-xs-12 col-sm-6"
							style="margin-top: 20px; text-align: right;">�q�� :</div>
						<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
							${mVO.MF_PHONE}</div>
						<div class="col-xs-12 col-sm-6"
							style="margin-top: 20px; text-align: right;">�ǯu :</div>
						<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
							${mVO.MF_FAX}</div>
						<div class="col-xs-12 col-sm-6"
							style="margin-top: 20px; text-align: right;">�Τ@�s�� :</div>
						<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
							${mVO.MF_BS}</div>
						<div class="col-xs-12 col-sm-6"
							style="margin-top: 20px; text-align: right;">�a�} :</div>
						<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
							${mVO.MF_ADDR}</div>
						<div class="col-xs-12 col-sm-6"
							style="margin-top: 20px; text-align: right;">�ۧڤ��� :</div>
						<div class="col-xs-12 col-sm-6" style="margin-top: 20px;">
							${mVO.MF_SELF}</div>
					</div>

				</div>
			</div>

		</div>
	</div>
</div>
<div class="col-sm-11 col-sm-push-3">
	<jsp:include page="/mustinclude/footer.jsp" />
</div>