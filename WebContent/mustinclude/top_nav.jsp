<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ page import="com.general_member.model.*"%>
<%@ page import="com.manufacturers.model.*"%>

<%
	String accountName = "訪客";
	String name="登入";
	GeneralMemberVO gmVO = (GeneralMemberVO)session.getAttribute("gVO");
	ManufacturersVO mVO =(ManufacturersVO)session.getAttribute("mVO");
	if(gmVO != null) {accountName = gmVO.getMEM_NAME();name="登出";}
	if(mVO != null) {accountName = mVO.getMF_NAME();name="登出";}
%>

<div class="container-fulid">
	<div class="row" style="margin-right: 0px;">
		<div class=" col-sm-8 col-sm-push-2">
			<nav class="navbar navbar-default " role="navigation">
				<div class="col-sm-12">
					<div class="navbar-header">
						<a class="navbar-brand"
							href="<%=request.getContextPath()%>/index.jsp"
							style="padding: 4px;"><img
							src="<%=request.getContextPath()%>/mustinclude/logo.png"
							style="height: 100%;"></a>
					</div>

					<!-- 左選單 -->
					<ul class="nav navbar-nav">
						<li><a
							href="<%=request.getContextPath()%>/picnic/maosecondui.jsp">開團</a></li>
						<li><a
							href="<%=request.getContextPath()%>/buycart/moafirst.jsp?first=first">預購商品</a></li>
						<li><a href="#"><form method="POST"
								action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do"
								onclick="submit()">
								<p>成團</p>
								<input type="hidden" name="action" value="insert">
							</form></a></li>
						<li>
							<a href="#">
								<form method="POST" action="<%=request.getContextPath()%>/history.do" onclick="submit()" >
									<input type="hidden" name="button" value="history">
									<p>揪團歷史</p>
								</form>
							</a>
						</li>
					</ul>

					<!-- 右選單 -->
					<ul class="nav navbar-nav navbar-right">
						
						
						<%
						if(gmVO!=null || mVO!=null){%>
						<li><a href="<%=request.getContextPath()%>/personal/personal.jsp"><%= accountName %></a></li>
						<%}else{%><li><a href="#"><%= accountName %></a></li><%} %>
						
						
						
						
						
						
						<li><a href="<%=request.getContextPath()%>/login/logout.do"><%= name %></a></li>

				
					</ul>

					<!-- 搜尋表單 -->
					<form class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/view/k_research_choice.jsp">
						<button type="submit" class="btn btn-default">進階搜尋</button>
					</form>
				</div>
			</nav>
		</div>
	</div>
</div>