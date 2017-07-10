<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.goods_sell.model.*"%>

<jsp:useBean id="goods_sellSvc" scope="page"
	class="com.goods_sell.model.Goods_SellService" />

<div class="row">
	<div class="col-sm-12 ">
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
				<div class="col-sm-4 ">
					<div class="thumbnail">
						<img src="${goods_sellVO.getUrl()}" alt="">

						<div class="caption">
							<h2>${goods_sellVO.getGs_name()}</h2>

							<p>
							<table>
								<tr>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
											<button type="submit" class="btn btn-default" value="Submit">${goods_sellVO.getGs_price()}</button>
											<input type="hidden" name="gsno"
												value="${goods_sellVO.getGs_no()}"> <input
												type="hidden" name="action" value="getOne">
										</FORM></td>
									<td>&nbsp&nbsp&nbsp&nbsp</td>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do"
											onclick="submit()">
											<button type="submit" class="btn btn-xs btn-default" value="Submit">
												<span class="glyphicon glyphicon-shopping-cart"></span>
											</button>
											<input type="hidden" name="gsno"
												value="${goods_sellVO.gs_no}"> <input type="hidden"
												name="action" value="insert">
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