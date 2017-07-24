<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>

<%
	AnnouncementService announcementSvc = new AnnouncementService();
	List<AnnouncementVO> list = announcementSvc.getAll();
	int lastIdx = list.size() - 1;
	String lastAnnouncement = "";
	if(lastIdx != -1) lastAnnouncement = list.get(lastIdx).getAnn_text();
	
	pageContext.setAttribute("lastAnnouncement", lastAnnouncement);
%>

<div class="container-fulid">
	<div class="row" style="margin-right: 0px;">
		<div class=" col-sm-8 col-sm-push-2">
			<div class="alert alert-success">
				<button type="button" onclick="this.parentNode.parentNode.removeChild(this.parentNode);" class="close" data-dismiss="alert"><span aria-hidden="true">¡Ñ</span><span class="sr-only">Close</span></button>
				<strong><i class="fa fa-info-circle"></i> ³Ì·s®ø®§!</strong>
				<marquee>
					<p style="font-family: Impact; font-size: 16pt">${ lastAnnouncement }</p>
				</marquee>
			</div>
		</div>
	</div>
</div>