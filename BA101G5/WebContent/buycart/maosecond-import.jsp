<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>

<jsp:useBean id="goods_sellSvc" scope="page"
	class="com.goods_sell.model.Goods_SellService" />

<div class="row">
	<div class="col-sm-11 ">
		<div class="col-sm-2 ">
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
		<div class="col-sm-10 ">
			<c:forEach var="goods_sellVO" items="${goods_sellSvc.getAll()}">
				<div class="col-sm-4 ">
					<div class="thumbnail">
						<img
							src="${request.getContextPath()}/images/${goods_sellVO.getGs_name()}.jpg"
							alt="">

						<div class="caption">
							<h2>${goods_sellVO.getGs_name()}</h2>

							<p>
							<table>
								<tr>
								<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do"
											onclick="submit()">
										<a href="#"
										class="btn btn-default">${goods_sellVO.getGs_price()}</a>
											<input type="hidden" name="gsno" value="${goods_sellVO.getGs_no()}">
											<input type="hidden" name="action" value="getOne">
										</FORM></td>
									
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do"
											onclick="submit()">
											<a href="#" class="btn btn-default btn-xs"> <span
												class="glyphicon glyphicon-shopping-cart" />
											</a><input type="hidden" name="actd" value="${goods_sellVO.gs_no}">
										</FORM></td>
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