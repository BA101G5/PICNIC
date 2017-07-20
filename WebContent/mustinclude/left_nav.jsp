<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%
	List<String> list = (List<String>) session.getAttribute("persionalpicnic");
	pageContext.setAttribute("list", list);
	System.out.println(list);
%>

<div id="left-navigation-bar">
	<ul class="bs-glyphicons-list">
		<li title=""><span class="glyphicon glyphicon-user"></span></li>
		<li title=""><span class="glyphicon glyphicon-envelope"></span></li>
		<li title=""><form method="post"
										action="<%=request.getContextPath()%>/picnic/picnic.do"
										onclick="submit()">
										<span class="glyphicon glyphicon-question-sign"></span>
										<input type="hidden" name="uri" value="<%= request.getRequestURI() %>">
										<input type="hidden" name="action" value="persionalpicnic">
									</form></li>
	
		<li>
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapse1">Hello
									</a>
						</h4>
					</div>
					<div id="collapse1" class="panel-collapse collapse">
						<c:if test="${not empty pageScope.list }">
							<c:forEach var="PicnicVO" items="${list}">
								<div class="panel-body">${PicnicVO.getPicnic_name()}</div>
								<form method="post"
										action="<%=request.getContextPath()%>/picnic/picnic.do"
										onclick="submit()">
										<span class="glyphicon glyphicon-question-sign"></span>
										<input type="hidden" name="Picnic_no" value="${PicnicVO.getPicnic_no()}">
										<input type="hidden" name="action" value="lookpicnic">
									</form>
							</c:forEach>
						</c:if>
					
					</div>
				</div>
			</div>
		</li>

		<ul>
</div>