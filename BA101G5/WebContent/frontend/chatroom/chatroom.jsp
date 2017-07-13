
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.picmem.model.*"%>
<%@ page import="com.picnic.model.*"%>
<%@ page import="com.chatroom_members.model.*"%>
<%@ page import="com.chatroom.model.*"%>

<%
// 	ChatroomService pboard_articleSvc = new ChatroomService();
// 	List<ChatroomVO> list = chatroomSvc.getAll();
// 	pageContext.setAttribute("list", list);
	String mem_no = "MG00000001";

	PicmemService picmemSvc = new PicmemService();
	List<PicmemVO> listPicmemVO = picmemSvc.getAll();
	PicnicService picnicSvc = new PicnicService();
	List<PicnicVO> listPicnicVO = picnicSvc.getAll();
	
	Map<String, String> mapPG = new HashMap<String, String>();
	for(int idx = 0; idx < listPicmemVO.size(); idx++){
		if(listPicmemVO.get(idx).getMem_no().equals(mem_no)){
			mapPG.put(
				listPicmemVO.get(idx).getPicnic_no(),
				picnicSvc.getOnePicnic(listPicmemVO.get(idx).getPicnic_no()).getPicnic_name()
			);
			//System.out.println(picnicSvc.getOnePicnic(listPicmemVO.get(idx).getPicnic_no()).getPicnic_name());
		}
	}

    pageContext.setAttribute("mapPG", mapPG);
    
//     for(Map.Entry<String, String> entry : mapPG.entrySet()){
//         System.out.println(entry.getKey() + "/" + entry.getValue());
//     }
	

	Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
	List<Chatroom_MembersVO> listChatroom_MembersVO = chatroom_membersSvc.getAll();
	ChatroomService chatroomSvc = new ChatroomService();
	List<ChatroomVO> listChatroomVO = chatroomSvc.getAll();
	
	Map<String, String> mapFriendRoom = new HashMap<String, String>();
	for(int idx = 0; idx < listChatroom_MembersVO.size(); idx++){
		if(listChatroom_MembersVO.get(idx).getMem_no().equals(mem_no)){
			mapFriendRoom.put(
				listChatroom_MembersVO.get(idx).getChatroom_no(),
				chatroomSvc.getOneChatroom(listChatroom_MembersVO.get(idx).getChatroom_no()).getChatroom_name()
			);
			//System.out.println(chatroomSvc.getOneChatroom(listChatroom_MembersVO.get(idx).getChatroom_no()).getChatroom_name());
		}
	}
	
    pageContext.setAttribute("mapFriendRoom", mapFriendRoom);
%>


<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>chatroom</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->

		<link rel="stylesheet" href="chatroom.css">
	</head>
	<body>

		<div class="col-sm-4 container chatroom-container">
			<div class="row">
				<div class="col-xs-12 col-sm-12" id="chatroom">
					<a class="btn btn-primary col-xs-12" role="button" data-toggle="collapse" href="#chatroom-body" aria-expanded="false" aria-controls="#chatroom-body">
						聊天室
					</a>
					<div class="collapse" id="chatroom-body">
						<!-- bs-panel -->
						<div class="bs-panel panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">野餐團</h3>
							</div>
							<!-- bs-list-group -->
							<div class="list-group bs-list-group">
<c:forEach var="entryOfMapPG" items="${ mapPG.entrySet() }">
								<a href="#" class="list-group-item" id="${ entryOfMapPG.getKey() }"><span class="headicon"><img src="https://api.fnkr.net/testimg/24x24/00CED1/FFF/?text=img+placeholder"></span>${ entryOfMapPG.getValue() }</a>
</c:forEach>
							</div>
							<!-- END: bs-list-group -->

							<div class="panel-heading">
								<h3 class="panel-title">好友</h3>
							</div>
							<!-- bs-list-group -->
							<div class="list-group bs-list-group">
<c:forEach var="entryOfMapFriendRoom" items="${ mapFriendRoom.entrySet() }">
								<a href="#" class="list-group-item" id="${ entryOfMapFriendRoom.getKey() }"><span class="headicon"><img src="https://api.fnkr.net/testimg/24x24/00CED1/FFF/?text=img+placeholder"></span>${ entryOfMapFriendRoom.getValue() }</a>
</c:forEach>
							</div>
							<!-- END: bs-list-group -->

							<div class="panel-heading">
								<h3 class="panel-title">黑名單</h3>
							</div>
							<!-- bs-list-group -->
							<div class="list-group bs-list-group">
								<a href="#" class="list-group-item"><span class="headicon"><img src="https://api.fnkr.net/testimg/24x24/00CED1/FFF/?text=img+placeholder"></span>喬巴</a>
							</div>
							<!-- END: bs-list-group -->
						</div><!-- END: .bs-panel -->

					</div><!-- END: #chatroom-body -->

				</div>

			</div><!-- END: .row -->
		</div><!-- END: .chatroom-container -->



<!-- .aChat-container -->
<div class="col-sm-4 container aChat-container">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span> Chat
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </button>
                        <ul class="dropdown-menu slidedown">
                            <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-refresh">
                            </span>Refresh</a></li>
                            <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-ok-sign">
                            </span>Available</a></li>
                            <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-remove">
                            </span>Busy</a></li>
                            <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-time"></span>
                                Away</a></li>
                            <li class="divider"></li>
                            <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-off"></span>
                                Sign Out</a></li>
                        </ul>
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="chat">
                        <li class="left clearfix"><span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="primary-font">Jack Sparrow</strong> <small class="pull-right text-muted">
                                        <span class="glyphicon glyphicon-time"></span>12 mins ago</small>
                                </div>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula sodales.
                                </p>
                            </div>
                        </li>
                        <li class="right clearfix"><span class="chat-img pull-right">
                            <img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <small class=" text-muted"><span class="glyphicon glyphicon-time"></span>13 mins ago</small>
                                    <strong class="pull-right primary-font">Bhaumik Patel</strong>
                                </div>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula sodales.
                                </p>
                            </div>
                        </li>
                        <li class="left clearfix"><span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="primary-font">Jack Sparrow</strong> <small class="pull-right text-muted">
                                        <span class="glyphicon glyphicon-time"></span>14 mins ago</small>
                                </div>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula sodales.
                                </p>
                            </div>
                        </li>
                        <li class="right clearfix"><span class="chat-img pull-right">
                            <img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <small class=" text-muted"><span class="glyphicon glyphicon-time"></span>15 mins ago</small>
                                    <strong class="pull-right primary-font">Bhaumik Patel</strong>
                                </div>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula sodales.
                                </p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="btn-input" type="text" class="form-control input-sm" placeholder="Type your message here..." />
                        <span class="input-group-btn">
                            <button class="btn btn-warning btn-sm" id="btn-chat">
                                Send</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END: .aChat-container -->


		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script>
			var intViewportHeight = window.innerHeight;
			document.querySelector('#chatroom').style.maxHeight = (intViewportHeight - 100) + 'px';
		</script>

	</body>
</html>