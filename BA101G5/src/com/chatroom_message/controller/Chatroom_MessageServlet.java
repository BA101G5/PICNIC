package com.chatroom_message.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.chatroom_message.model.Chatroom_MessageService;
import com.chatroom_message.model.Chatroom_MessageVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//@WebServlet("/Chatroom_MessageServlet")
public class Chatroom_MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Chatroom_MessageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("cr_msg_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入聊天訊息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_message/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String cr_msg_no = null;
				try {
					cr_msg_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("聊天訊息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_message/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Chatroom_MessageService chatroom_messageSvc = new Chatroom_MessageService();
				Chatroom_MessageVO chatroom_messageVO = chatroom_messageSvc.getOneChatroom_Message(cr_msg_no);

				if (chatroom_messageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_message/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chatroom_messageVO", chatroom_messageVO); // 資料庫取出的chatroom_messageVO物件,存入req
				String url = "/chatroom_message/listOneChatroom_Message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_message/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String cr_msg_no = req.getParameter("cr_msg_no");
				
				/***************************2.開始查詢資料****************************************/
				Chatroom_MessageService chatroom_messageSvc = new Chatroom_MessageService();
				Chatroom_MessageVO chatroom_messageVO = chatroom_messageSvc.getOneChatroom_Message(cr_msg_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chatroom_messageVO", chatroom_messageVO);         // 資料庫取出的chatroom_messageVO物件,存入req
				String url = "/chatroom_message/update_chatroom_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_message/listAllChatroom_Message.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String cr_msg_no = req.getParameter("cr_msg_no").trim();
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();		
				java.sql.Timestamp message_date = null;
				try {
					message_date = java.sql.Timestamp.valueOf(req.getParameter("message_date").trim());
				} catch (IllegalArgumentException e) {
					message_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}			
				String message_text = req.getParameter("message_text");
				Part part=req.getPart("message_img");
				byte[] message_img=getPictureByteArray(part);
				Chatroom_MessageVO chatroom_messageVO = new Chatroom_MessageVO();
				chatroom_messageVO.setCr_msg_no(cr_msg_no);
				chatroom_messageVO.setChatroom_no(chatroom_no);
				chatroom_messageVO.setMem_no(mem_no);
				chatroom_messageVO.setMessage_date(message_date);
				chatroom_messageVO.setMessage_text(message_text);
				chatroom_messageVO.setMessage_img(message_img);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					System.out.println(6);	
					req.setAttribute("chatroom_messageVO", chatroom_messageVO); // 含有輸入格式錯誤的chatroom_messageVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_message/update_chatroom_message_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				Chatroom_MessageService chatroom_messageSvc = new Chatroom_MessageService();
				chatroom_messageVO = chatroom_messageSvc.updateChatroom_Message(cr_msg_no, chatroom_no, mem_no, message_date, message_text, message_img);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chatroom_messageVO", chatroom_messageVO); // 資料庫update成功後,正確的的chatroom_messageVO物件,存入req
				String url = "/chatroom_message/listOneChatroom_Message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_message/update_chatroom_message_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String cr_msg_no = req.getParameter("cr_msg_no").trim();
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				java.sql.Timestamp message_date = null;
				try {
					message_date = java.sql.Timestamp.valueOf(req.getParameter("message_date").trim());
				} catch (IllegalArgumentException e) {
					message_date=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String message_text = req.getParameter("message_text").trim();
				
System.out.println("message_img: "+req.getParameter("message_img"));
				Part part=req.getPart("message_img");
				byte[] message_img=getPictureByteArray(part);
				
				Chatroom_MessageVO chatroom_messageVO = new Chatroom_MessageVO();
				chatroom_messageVO.setChatroom_no(cr_msg_no);
				chatroom_messageVO.setChatroom_no(chatroom_no);
				chatroom_messageVO.setMem_no(mem_no);
				chatroom_messageVO.setMessage_date(message_date);
				chatroom_messageVO.setMessage_text(message_text);
				chatroom_messageVO.setMessage_img(message_img);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatroom_messageVO", chatroom_messageVO); // 含有輸入格式錯誤的chatroom_messageVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_message/addChatroom_Message.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************2.開始新增資料***************************************/
				Chatroom_MessageService chatroom_messageSvc = new Chatroom_MessageService();
				chatroom_messageVO = chatroom_messageSvc.addChatroom_Message(cr_msg_no, chatroom_no, mem_no, message_date, message_text, message_img);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/chatroom_message/listAllChatroom_Message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_message/addChatroom_Message.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String cr_msg_no = req.getParameter("cr_msg_no").trim();
				
				/***************************2.開始刪除資料***************************************/
				Chatroom_MessageService chatroom_messageSvc = new Chatroom_MessageService();
				chatroom_messageSvc.deleteChatroom_Message(cr_msg_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/chatroom_message/listAllChatroom_Message.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_message/listAllChatroom_Message.jsp");
				failureView.forward(req, res);
			}
		}
	}

	private byte[] getPictureByteArray(Part part) throws IOException {

			InputStream in=part.getInputStream();
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			byte[] b=new byte[in.available()];
			int i;
			while((i=in.read(b))!=-1){
				baos.write(b,0,i);
			}

		// TODO Auto-generated method stub
		return baos.toByteArray();
	}

}
