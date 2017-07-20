//package com.websocket;
//
//import java.io.*;
//import java.util.*;
//
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import javax.websocket.Session;
//import javax.websocket.OnOpen;
//import javax.websocket.OnMessage;
//import javax.websocket.OnError;
//import javax.websocket.OnClose;
//import javax.websocket.CloseReason;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import jdk.nashorn.internal.parser.JSONParser;
//
//@ServerEndpoint("/MyWebSocketServer/{name}/{room}")    //要用會員ID當做房間
//public class MyWebSocketServer2 {
//
////	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
////	private Map<String, Set<Map<String, Session>>> chatroom_no = new HashMap<String, Set<Map<String, Session>>>();
////	private Map<String, Map<String, Session>> chatroom_no = new HashMap<String, Map<String, Session>>();
////	private Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<Session>());
////  Map<Chatroom_NO, SET<SESSION>>
//
//	private static final Set<Session> allWebsocktConnectedSessions = Collections.synchronizedSet(new HashSet<Session>());
//	
//	private String room;
//	private static final Map<String, Set<Session>> mapChatroomNo = new HashMap<String, Set<Session>>();
//
//	@OnOpen                                            // 創一個會員物件，存會員ID和SESSION 可能用SET 或 MAP
//	public void onOpen(@PathParam("name") String name, @PathParam("room") String room, Session userSession) throws IOException {
////     老師的範例
//		System.out.println("MYWebSocket name : " + name);
//		System.out.println("MYWebSocket room : " + room);
////		connectedSessions.add(userSession);
//		String text = String.format("MYWebSocket Session ID = %s, connected; name = %s; room = %s",
//				userSession.getId(), name, room);
//		System.out.println(text);
//
//		this.room = room;
//
////      測試 二
//
//
//		if (mapChatroomNo.containsKey(room)){
//			System.out.println("有近來麻1 // 當前使用者 ("+ room +") 在 mapChatroomNo 裡");
//			Set<Session> session = mapChatroomNo.get(room);
//			session.add(userSession);
//			mapChatroomNo.put(room, session);
//
//		}else{
//			System.out.println("有近來麻2 // 當前使用者 ("+ room +") 不在 mapChatroomNo 裡");
//			allWebsocktConnectedSessions.add(userSession);
//			System.out.println("MyWebSocketServer / onOpen / allWebsocktConnectedSessions.size(): " + allWebsocktConnectedSessions.size());
//			mapChatroomNo.put(room, allWebsocktConnectedSessions);
//		}
//
//		System.out.println("MYWebSocket name : " + name);
//		System.out.println("MYWebSocket room : " + room);
//
//
//	}
//
//	@OnMessage  // 從會員物件比對你要發送訊息的人的ID，找到SESSION傳送
//	public void onMessage(Session userSession, String message) {
////		for (Session session : connectedSessions) {
////			if (session.isOpen())
////				session.getAsyncRemote().sendText(message);
////		}
////		System.out.println("Message received: " + message);
//
//		// 確認Map
//		// if( mapChatroomNo.containsKey(room)){
//			System.out.println("有近來麻3 //// " + message);
//			Set<Session> set = mapChatroomNo.get(room);
//			String people= String.valueOf(set.size());
//
//			// 把android那端傳來的message加入連線人數
//			Gson gson = new Gson();
//			JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
//			jsonObject.addProperty("people", people);
//			System.out.println("測試 message 加人數 : "+jsonObject);
//			message = jsonObject.toString();
//			//
//
//			
//			//for(Session session:set){
//			System.out.println("MyWebSocketServer / onMessage / allWebsocktConnectedSessions.size(): " + allWebsocktConnectedSessions.size());
//			for(Session session:allWebsocktConnectedSessions){	
//				session.getAsyncRemote().sendText(message);
//				System.out.println("有近來麻4 /// session.getId() = " + session.getId());
//
//			}
//			System.out.println("Message received: " + message);
//			System.out.println("連線人數 : " + people);
//		// }
//	}
//
//	@OnError
//	public void onError(Session userSession, Throwable e) {
//		System.out.println("Error: " + e.toString());
//	}
//
//	@OnClose
//	public void onClose(Session userSession, CloseReason reason) {
////		connectedSessions.remove(userSession);
//
//		Set<Session> set = mapChatroomNo.get(room);
//		set.remove(userSession);
//
////		map.remove(userSession);
//		String text = String.format("session ID = %s, disconnected; close code = %d", userSession.getId(),
//				reason.getCloseCode().getCode());
//		System.out.println(text);
//	}
//}