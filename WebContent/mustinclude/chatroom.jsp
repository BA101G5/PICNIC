
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.picmem.model.*"%>
<%@ page import="com.picnic.model.*"%>
<%@ page import="com.chatroom_members.model.*"%>
<%@ page import="com.chatroom.model.*"%>
<%@ page import="com.contact_list.model.*"%>
<%@ page import="com.general_member.model.*"%>

<%
// 	ChatroomService pboard_articleSvc = new ChatroomService();
// 	List<ChatroomVO> list = chatroomSvc.getAll();
// 	pageContext.setAttribute("list", list);
// 	String mem_no = "MG00000002";
//  ${sessionScope.gVO.getMEM_NO()}
	String mem_no;
	GeneralMemberVO gVO = (GeneralMemberVO)session.getAttribute("gVO");
	if(gVO == null){
		mem_no = "";
	}else{
		mem_no = gVO.getMEM_NO();
	}
	System.out.println("chatroom.jsp/ mem_no=" + mem_no);


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


// 	// 群組聊天
 	Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
 	//List<Chatroom_MembersVO> listChatroom_MembersVO = chatroom_membersSvc.getAll();
 	ChatroomService chatroomSvc = new ChatroomService();
// 	List<ChatroomVO> listChatroomVO = chatroomSvc.getAll();

// *** bug
// Chatroom_MembersVO2 cmVO2 = chatroom_membersSvc.getOnewCond("MG00000002", "MG00000003");
// System.out.println("chatroom.jsp / cmVO2.getMem_no() = " + cmVO2.getMem_no());
// *** bug
// *** 替代
 	Chatroom_MembersJDBCDAO chmemJDBCDAO = new Chatroom_MembersJDBCDAO();
// 	Chatroom_MembersVO2 cmVO2 = chmemJDBCDAO.getOnewCond("MG00000002", "MG00000003");
// 	System.out.println("chatroom.jsp / chmemJDBCDAO.getMem_no() = " + cmVO2.getChatroom_no());

// 	Map<String, String> mapGroupRoom = new HashMap<String, String>();
// 	for(int idx = 0; idx < listChatroom_MembersVO.size(); idx++){
// 		if(listChatroom_MembersVO.get(idx).getMem_no().equals(mem_no)){
// 			mapGroupRoom.put(
// 				listChatroom_MembersVO.get(idx).getChatroom_no(),
// 				chatroomSvc.getOneChatroom(listChatroom_MembersVO.get(idx).getChatroom_no()).getChatroom_name()
// 			);
// 			//System.out.println(chatroomSvc.getOneChatroom(listChatroom_MembersVO.get(idx).getChatroom_no()).getChatroom_name());
// 		}
// 	}

// 	pageContext.setAttribute("mapGroupRoom", mapGroupRoom);


	GeneralMemberService generalMemberSvc = new GeneralMemberService();
	List<GeneralMemberVO> generalMemberVO = generalMemberSvc.getAll();
	Contact_ListService contactListSvc = new Contact_ListService();
	List<Contact_ListVO> listContactListVO = contactListSvc.getAll(mem_no, "F");
	Map<String, String> mapFriendRoom = new HashMap<String, String>();
	for(int idx = 0; idx < listContactListVO.size(); idx++){
		mapFriendRoom.put(
			listContactListVO.get(idx).getContact_no(),
			generalMemberSvc.getOneGeneralMember(listContactListVO.get(idx).getContact_no()).getMEM_NAME()
		);
		//System.out.println(listContactListVO.get(idx).getContact_no());
	}


	pageContext.setAttribute("mapFriendRoom", mapFriendRoom);

%>


		<div class="col-sm-4 container-fulid chatroom-list-container">
			<div class="row">
				<div class="col-xs-12 col-sm-12" id="chatroom-list">
					<a class="btn btn-primary col-xs-12" role="button" data-toggle="collapse" href="#chatroom-list-body" aria-expanded="false" aria-controls="#chatroom-list-body">
						聊天室
					</a>
					<div class="collapse" id="chatroom-list-body">
						<!-- bs-panel -->
						<div class="bs-panel panel panel-danger">
						
						
<!-- 							<div class="panel-heading"> -->
<!-- 								<h3 class="panel-title">野餐團</h3> -->
<!-- 							</div> -->
<!-- 							bs-list-group -->
<!-- 							<div class="list-group bs-list-group chatroom-list-picnic-group"> -->
<%-- <c:forEach var="entryOfMapPG" items="${ mapPG.entrySet() }"> --%>
<%-- 								<a href="#" class="list-group-item" id="${ entryOfMapPG.getKey() }"><span class="headicon"><img src="https://api.fnkr.net/testimg/24x24/00CED1/FFF/?text=img+placeholder"></span>${ entryOfMapPG.getValue() }</a> --%>
<%-- </c:forEach> --%>
<!-- 							</div> -->
<!-- 							END: bs-list-group -->
							

							<div class="panel-heading">
								<h3 class="panel-title">好友</h3>
							</div>
							<!-- bs-list-group -->
							<div class="list-group bs-list-group chatroom-list-friend-room">
<c:forEach var="entryOfMapFriendRoom" items="${ mapFriendRoom.entrySet() }">
								<a href="#" class="list-group-item" id="${ entryOfMapFriendRoom.getKey() }"><span class="headicon"><img src="https://api.fnkr.net/testimg/24x24/00CED1/FFF/?text=img+placeholder"></span>${ entryOfMapFriendRoom.getValue() }</a>
</c:forEach>
							</div>
							<!-- END: bs-list-group -->

							<%--<div class="panel-heading">
								<h3 class="panel-title">黑名單</h3>
							</div>
							<!-- bs-list-group -->
							<div class="list-group bs-list-group">
								<a href="#" class="list-group-item"><span class="headicon"><img src="https://api.fnkr.net/testimg/24x24/00CED1/FFF/?text=img+placeholder"></span>喬巴</a>
							</div>--%>

							<!-- END: bs-list-group -->
						</div><!-- END: .bs-panel -->

					</div><!-- END: #chatroom-list-body -->

				</div>

			</div><!-- END: .row -->
		</div><!-- END: .chatroom-list-container -->



<!-- .aChatroom-container -->
<div class="col-sm-4 container-fulid aChatroom-container" id="aChatroom-container">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span> Chat
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" id="btn-close-aChatroom-container">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
<!--                         <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown"> -->
<!--                             <span class="glyphicon glyphicon-chevron-down"></span> -->
<!--                         </button> -->
<!--                         <ul class="dropdown-menu slidedown"> -->
<!--                             <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-refresh"> -->
<!--                             </span>Refresh</a></li> -->
<!--                             <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-ok-sign"> -->
<!--                             </span>Available</a></li> -->
<!--                             <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-remove"> -->
<!--                             </span>Busy</a></li> -->
<!--                             <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-time"></span> -->
<!--                                 Away</a></li> -->
<!--                             <li class="divider"></li> -->
<!--                             <li><a href="http://www.jquery2dotnet.com"><span class="glyphicon glyphicon-off"></span> -->
<!--                                 Sign Out</a></li> -->
<!--                         </ul> -->
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="chat" id="ulChat">
                        <%--<li class="left clearfix"><span class="chat-img pull-left">
                            <img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" />
                        </span>
                            <div class="chat-body clearfix">
                                <div class="header">
                                    <strong class="primary-font">Jack Sparrow</strong> <small class="pull-right text-muted">
                                        <span class="glyphicon glyphicon-time"></span>52 mins ago</small>
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
                                    <small class=" text-muted"><span class="glyphicon glyphicon-time"></span>43 mins ago</small>
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
                                        <span class="glyphicon glyphicon-time"></span>34 mins ago</small>
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
                                    <small class=" text-muted"><span class="glyphicon glyphicon-time"></span>25 mins ago</small>
                                    <strong class="pull-right primary-font">Bhaumik Patel</strong>
                                </div>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare
                                    dolor, quis ullamcorper ligula sodales.
                                </p>
                            </div>
                        </li>--%>
                    </ul>
                </div>
                <div class="panel-footer">
                    <div class="input-group">
                        <input id="inputCRMessage" type="text" class="form-control input-sm" placeholder="Type your message here..." />
                        <span class="input-group-btn">
                            <button class="btn btn-warning btn-sm" id="btn-chat" onclick="sendMessage();">
                                Send</button>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END: .aChatroom-container -->



		<script src="<%=request.getContextPath()%>/mustinclude/chatroom_resize.js"></script>
		<script>
			var gObjCR = {};
			gObjCR.memNo = '${sessionScope.gVO.getMEM_NO()}';
			gObjCR.memName = '${sessionScope.gVO.getMEM_NAME()}';

            $('.chatroom-list-friend-room .list-group-item').on('click', function(){
                $('#aChatroom-container').css('display', 'block');
                //alert(this.id); //"MG00000003"
                gObjCR.chatWithMemNo = this.id;
                // gObjCR.myRoomNo = 
                onWinResize();
            });
            
            $('#btn-close-aChatroom-container').on('click', function(){
            	$('#aChatroom-container').css('display', 'none');
            });
		</script>
		<script src="<%=request.getContextPath()%>/mustinclude/chatroom_websocket.js"></script>
