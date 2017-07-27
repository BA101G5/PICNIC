<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.general_member.model.*"%>
<%@ page import="com.manufacturers.model.*"%>
<%
	List<String> persionalpicnic = (List<String>) session.getAttribute("persionalpicnic");
	pageContext.setAttribute("persionalpicnic", persionalpicnic);

	String accountName = "訪客";
	String name = "登入";
	GeneralMemberVO gmVO = (GeneralMemberVO) session.getAttribute("gVO");
	ManufacturersVO mVO = (ManufacturersVO) session.getAttribute("mVO");
	if (gmVO != null) {
		accountName = gmVO.getMEM_NAME();
		name = "登出";
	}
	if (mVO != null) {
		accountName = mVO.getMF_NAME();
		name = "登出";
	}
%>
<div id="left-navigation-bar">
	<ul class="bs-glyphicons-list">
		<li title="member">
			<%
				if (gmVO != null || mVO != null) {
			%> <a href="<%=request.getContextPath()%>/personal/personal.jsp"><span
				class="glyphicon glyphicon-user"></span></a> <%
 	} else {
 %><a href="<%=request.getContextPath()%>/login/logout.do"><span
				class="glyphicon glyphicon-user"></span></a> <%
 	}
 %>
		</li>

		<li title="Picnic">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title" style="height: 36px;">
						<c:if test="${empty sessionScop.persionalpicnic }">
							<form method="post"
								action="<%=request.getContextPath()%>/picnic/picnic.do"
								onclick="submit()">
								<a data-toggle="collapse1" href=""><span
									class="glyphicon glyphicon-star"></span> </a> <input type="hidden"
									name="uri" value="<%=request.getRequestURI()%>"> <input
									type="hidden" name="action" value="persionalpicnic">
							</form>
							</c:if>
							<c:if test="${not empty sessionScop.persionalpicnic }"><a data-toggle="collapse" href=""><span
									class="glyphicon glyphicon-star"></span> </a></c:if>
							
						</h3>
					</div>
					<div id="collapse1" class="panel-collapse collapse in">
						<c:if test="${not empty persionalpicnic }">
							<c:forEach var="PicnicVO" items="${persionalpicnic}">
										<div class="panel-body"><form method="post"
										action="<%=request.getContextPath()%>/picnic/picnic.do"
										onclick="submit()"><P>${PicnicVO.getPicnic_name()}</P>
										<input type="hidden" name="Picnic_no"
											value="${PicnicVO.getPicnic_no()}"> <input
											type="hidden" name="action" value="lookpicnic">
									</form></div>
										
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</li>
		<li title="cart">
			<form method="POST"
				action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do?"
				onclick="submit()">
				<span class="glyphicon glyphicon-shopping-cart"></span> <input
					type="hidden" name="action" value="insert">
			</form>
		</li>

	</ul>
</div>