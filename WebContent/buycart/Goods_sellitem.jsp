<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.goods_sell.model.*"%>
<jsp:useBean id="goods_sellSvc" scope="page"
	class="com.goods_sell.model.Goods_SellService" />
<%
	
	List<Goods_SellVO> list = (List<Goods_SellVO>) request.getAttribute("typelist");
	List<Goods_SellVO> list2 = goods_sellSvc.getAll();
	List<Goods_SellVO> list3 =(List<Goods_SellVO>)request.getAttribute("list");
	pageContext.setAttribute("Gsbytypelist", list);
	pageContext.setAttribute("totalGrlist", list2);
	pageContext.setAttribute("mftypeGr",list3);
%>


<c:if test="${empty Gsbytypelist && empty mftypeGr }">
	<div class="col-sm-12">
		<%@ include file="/buycart/page.file"%>
		<c:forEach var="goods_sellVO" items="${totalGrlist}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<div class="col-sm-3">
				<div class="thumbnail" style="display: inline-block;";>
					<img
						src="<%=request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.gs_no}"
						style="display: inline-block; height: 200px; width: 200px;">
					<div style="display: inline-block;">
						<h3>${goods_sellVO.gs_name}</h3>
						<p>
						<table style="display: inline-block;">
							<tr>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
										<button type="submit" class="btn btn-info btn-lg"
											style="width: 80px; height: 40px; font-size: 20px;">${goods_sellVO.gs_price}</button>
										<input type="hidden" name="gsno"
											value="${goods_sellVO.gs_no}"> <input
											type="hidden" name="action" value="getOne">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do">
										<button type="submit" class="btn btn-default btn-xs"
											value="Submit"
											style="width: 80px; height: 40px; font-size: 20px;">
											<span class="glyphicon glyphicon-shopping-cart"
												aria-hidden="true"></span>
										</button>
										<input type="hidden" name="gs_no"
											value="${goods_sellVO.gs_no}"> <input
											type="hidden" name="action" value="insertintocartA">
										<input type="hidden" name="amount" value="1">
									</FORM>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</c:forEach>

		<div class="col-md-12 col-md-push-5">
			<%@ include file="/buycart/page2.file"%></div>
	</div>
</c:if>

<div class="col-md-9">

	<c:if test="${Gsbytypelist&& empty mftypeGr }">
		<%@ include file="/buycart/page3.file" %>
		<c:forEach var="goods_sellVO" items="${Gsbytypelist}" 
		begin="<%= pageIndex1 %>" end="<%=pageIndex1+rowsPerPage1-1%>">
			<div class="col-md-4">
				<div class="thumbnail" style="display: inline-block;";>
					<h4>${goods_sellVO.gs_name}</h4>
					<img
						src="<%=request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.gs_no}"
						style="display: inline-block; height: 200px; width: 200px;">
					<div style="display: inline-block;">

						<p>
						<table style="display: inline-block;">
							<tr>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
										<button type="submit" class="btn btn-info btn-lg"
											style="width: 80px; height: 40px; font-size: 20px;">${goods_sellVO.gs_price}</button>
										<input type="hidden" name="gsno"
											value="${goods_sellVO.gs_no}"> <input
											type="hidden" name="action" value="getOne">
									</FORM>
								</td>

							</tr>
						</table>
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="col-md-12 col-md-push-5">
		<%@ include file="/buycart/page4.file" %></div>
		
	</c:if>
</div>
<div class="col-md-9">
	<c:if test="${not empty mftypeGr}">
		
		<c:forEach var="goods_sellVO" items="${mftypeGr}" >
			<div class="col-md-4">
				<div class="thumbnail" style="display: inline-block;";>
					<h4>${goods_sellVO.gs_name}</h4>
					<img
						src="<%=request.getContextPath() %>/Image/?table=GOODS_SELL&picturename=${goods_sellVO.gs_no}"
						style="display: inline-block; height: 200px; width: 200px;">
					<div style="display: inline-block;">

						<p>
						<table style="display: inline-block;">
							<tr>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/goods_sell/goods_sell.do">
										<button type="submit" class="btn btn-info btn-lg"
											style="width: 80px; height: 40px; font-size: 20px;">${goods_sellVO.gs_price}</button>
										<input type="hidden" name="gsno"
											value="${goods_sellVO.gs_no}"> <input
											type="hidden" name="action" value="getOne">
									</FORM>
								</td>

							</tr>
						</table>
					</div>
				</div>
			</div>
		</c:forEach>
		
	</c:if>
</div>