<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>

<jsp:useBean id="goods_sellSvc" scope="page"
	class="com.goods_sell.model.Goods_SellService" />

<div class="row">
	<div class="col-sm-8 col-sm-push-2">
		<div class="col-sm-3  ">
			<ul class="list-group">
				<li class="list-group-item">aeu</li>
				<li class="list-group-item">aeou</li>
				<li class="list-group-item">aeou</li>
				<li class="list-group-item">aeou</li>
				<li class="list-group-item">aeou</li>
				<li class="list-group-item">aeou</li>
				<li class="list-group-item">aoeu</li>
			</ul>
		</div>
		<div class="col-sm-9 ">
			<c:forEach var="goods_sellVO" items="${goods_sellSvc.getAll()}">
				<div class="col-sm-12 ">
					<div class="thumbnail">
						<img
							src="<%=request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.getGs_no()}"
							style="display: inline-block; height: 200px; width: 200px;">
						<div style="display: inline-block;">
							<h2>${goods_sellVO.getGs_name()}</h2>

							<p>
							<table>
								<tr>

									<td>

										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
											<button type="submit" class="btn btn-info btn-lg"
												style="width: 250px; height: 40px; font-size: 20px;">${goods_sellVO.getGs_price()}</button>
											<input type="hidden" name="gsno" value="${goods_sellVO.getGs_no()}"> <input
												type="hidden" name="action" value="getOne">
										</FORM>
									</td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do"
											onclick="submit()">
											<button type="submit" class="btn btn-default btn-xs"
												value="Submit">
												<span class="glyphicon glyphicon-shopping-cart"
													aria-hidden="true"></span>
											</button>
											<input type="hidden" name="gs_no" value="${goods_sellVO.getGs_no()}">
                                            <input	type="hidden" name="action" value="insertintocartA">
												<input type="hidden" name="amount" value="1">
										</FORM>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
</div>


</div>