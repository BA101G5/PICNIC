package com.websocket;

import java.io.*;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jdk.nashorn.internal.parser.JSONParser;


@ServerEndpoint("/MyWebSocketServer/{name}/{room}")    //name �|��ID // room �ж�
public class MyWebSocketServer {

//	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
//	private Map<String, Set<Map<String, Session>>> chatroom_no = new HashMap<String, Set<Map<String, Session>>>();
//	private Map<String, Map<String, Session>> chatroom_no = new HashMap<String, Map<String, Session>>();
//	private Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<Session>());
//  Map<Chatroom_NO, SET<SESSION>>
	private String room;
	private static final Map<String, Set<Session>> mapChatroomNo = new HashMap<String, Set<Session>>();//< "room �ж�", >

	@OnOpen                                            // �Ф@�ӷ|������A�s�|��ID�MSESSION �i���SET �� MAP
	public void onOpen(@PathParam("name") String name, @PathParam("room") String room, Session userSession) throws IOException {
//     �Ѯv���d��
//		System.out.println("MYWebSocket name : " + name);
//		System.out.println("MYWebSocket room : " + room);
//		connectedSessions.add(userSession);
//		String text = String.format("Session ID = %s, connected; name = %s; room = %s",
//				userSession.getId(), name, room);
//		System.out.println(text);

		this.room = room;

//      ���� �G
		Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<Session>());


		if (mapChatroomNo.containsKey(room)){
			System.out.println("����ӳ�1");
			Set<Session> session = mapChatroomNo.get(room);
			session.add(userSession);
			mapChatroomNo.put(room, session);

		}else{
			System.out.println("����ӳ�2");
			connectedSessions.add(userSession);
			mapChatroomNo.put(room, connectedSessions);
		}

		System.out.println("MYWebSocket name : " + name);
		System.out.println("MYWebSocket room : " + room);


	}

	@OnMessage  // �q�|��������A�n�o�e�T�����H��ID�A���SESSION�ǰe
	public void onMessage(Session userSession, String message) {
//		for (Session session : connectedSessions) {
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(message);
//		}
//		System.out.println("Message received: " + message);

		// �T�{Map
		if( mapChatroomNo.containsKey(room)){
			System.out.println("����ӳ�3");
			Set<Session> set = mapChatroomNo.get(room);
			String people= String.valueOf(set.size());

			// ��android���ݶǨӪ�message�[�J�s�u�H��
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
			jsonObject.addProperty("people", people);
			System.out.println("���� message �[�H�� : "+jsonObject);
			message = jsonObject.toString();
			//

			for(Session session:set){

				session.getAsyncRemote().sendText(message);
				System.out.println("����ӳ�4");

			}
			System.out.println("Message received: " + message);
			System.out.println("�s�u�H�� : " + people);
		}
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
//		connectedSessions.remove(userSession);

		Set<Session> set = mapChatroomNo.get(room);
		set.remove(userSession);

//		map.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
				reason.getCloseCode().getCode());
		System.out.println(text);
	}
}
