<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

<%@ page import="com.general_member.model.*"%>
<%@ page import="com.manufacturers.model.*"%>

<%
	String accountName = "�X��";
	String name="�n�J";
	GeneralMemberVO gmVO = (GeneralMemberVO)session.getAttribute("gVO");
	ManufacturersVO mVO =(ManufacturersVO)session.getAttribute("mVO");
	if(gmVO != null) {accountName = gmVO.getMEM_NAME();name="�n�X";}
	if(mVO != null) {accountName = mVO.getMF_NAME();name="�n�X";}
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

					<!-- ����� -->
					<ul class="nav navbar-nav">
						<li><a
							href="<%=request.getContextPath()%>/picnic/maosecondui.jsp">�}��</a></li>
						<li><a
							href="<%=request.getContextPath()%>/buycart/moafirst.jsp?first=first">�w�ʰӫ~</a></li>
						<li><a href="#"><form method="POST"
								action="<%=request.getContextPath()%>/orderde_detail/orderde_detail.do"
								onclick="submit()">
								<p>����</p>
								<input type="hidden" name="action" value="insert">
							</form></a></li>
						<li>
							<a href="#">
								<form method="POST" action="<%=request.getContextPath()%>/history.do" onclick="submit()" >
									<input type="hidden" name="button" value="history">
									<p>���ξ��v</p>
								</form>
							</a>
						</li>
					</ul>

					<!-- �k��� -->
					<ul class="nav navbar-nav navbar-right">
						
						
						<%
						if(gmVO!=null || mVO!=null){%>
						<li><a href="<%=request.getContextPath()%>/personal/personal.jsp"><%= accountName %></a></li>
						<%}else{%><li><a href="#"><%= accountName %></a></li><%} %>
						
						
						
						
						
						
						<li><a href="<%=request.getContextPath()%>/login/logout.do"><%= name %></a></li>

				
					</ul>

					<!-- �j�M��� -->
					<form class="navbar-form navbar-right" role="search" action="<%=request.getContextPath()%>/view/k_research_choice.jsp">
						<button type="submit" class="btn btn-default">�i���j�M</button>
					</form>
				</div>
			</nav>
		</div>
	</div>
</div>