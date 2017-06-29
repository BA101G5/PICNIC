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
								<a href="<%=request.getContextPath()%>/buycart/maothird.jsp" class="btn btn-default">${goods_sellVO.getGs_price()}</a>                  
								  <a href="#" class="btn btn-default btn-xs">
								  
										<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" onclick="submit()">
										<span class="glyphicon glyphicon-shopping-cart" />
											<input type="hidden" name="empno" value="${empVO.empno}">
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
								</a>
						</div>
					</div>
				</div>



			</c:forEach>
		</div>
	</div>
</div>

<div class="row ">
	<div class="col-sm-11 col-sm-push-1 ">
		<div class="btn-group btn-group-justified ">
			<a href="# " class="btn btn-default " role="button ">aoeu</a>
		</div>
		<div class="col-sm-11 col-sm-push-3 ">
			<jsp:include page="/mustinclude/footer.jsp" />
		</div>
	</div>
</div>
</div>