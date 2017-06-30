package com.chatroom_members.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatroom_members.model.Chatroom_MembersService;
import com.chatroom_members.model.Chatroom_MembersVO;


//@WebServlet("/Chatroom_MembersServlet")
public class Chatroom_MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Chatroom_MembersServlet() {
        super();
    }


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("chatroom_no");
				String str1 = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入聊天室編號");
				}
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String chatroom_no = null;
				String mem_no = null;
				try {
					chatroom_no = new String(str);
					mem_no = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				Chatroom_MembersVO chatroom_membersVO = chatroom_membersSvc.getOneChatroom_Members(chatroom_no,mem_no);

				if (chatroom_membersVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chatroom_membersVO", chatroom_membersVO); // 資料庫取出的chatroom_membersVO物件,存入req
				String url = "/chatroom_members/listOneChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/select_page.jsp");
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
				String chatroom_no = req.getParameter("chatroom_no");
				String mem_no = req.getParameter("mem_no");
				
				/***************************2.開始查詢資料****************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				Chatroom_MembersVO chatroom_membersVO = chatroom_membersSvc.getOneChatroom_Members(chatroom_no,mem_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("chatroom_membersVO", chatroom_membersVO);         // 資料庫取出的chatroom_membersVO物件,存入req
				String url = "/chatroom_members/update_chatroom_members_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/listAllChatroom_Members.jsp");
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
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				Character chatroom_role = req.getParameter("chatroom_role").trim().charAt(0);			
				
				java.sql.Timestamp ban_until = null;
				try {
					ban_until = java.sql.Timestamp.valueOf(req.getParameter("ban_until").trim());
				} catch (IllegalArgumentException e) {
					//ban_until=new java.sql.Timestamp(System.currentTimeMillis());
					//errorMsgs.add("請輸入日期!");
					ban_until =null;
				}

				Chatroom_MembersVO chatroom_membersVO = new Chatroom_MembersVO();
				chatroom_membersVO.setChatroom_no(chatroom_no);
				chatroom_membersVO.setMem_no(mem_no);
				chatroom_membersVO.setChatroom_role(chatroom_role);
				chatroom_membersVO.setBan_until(ban_until);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatroom_membersVO", chatroom_membersVO); // 含有輸入格式錯誤的chatroom_membersVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/update_chatroom_members_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				chatroom_membersVO = chatroom_membersSvc.updateChatroom_Members(chatroom_no, mem_no, chatroom_role, ban_until);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("chatroom_membersVO", chatroom_membersVO); // 資料庫update成功後,正確的的chatroom_membersVO物件,存入req
				String url = "/chatroom_members/listOneChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/update_chatroom_members_input.jsp");
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
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				Character chatroom_role = req.getParameter("chatroom_role").trim().charAt(0);
				
				java.sql.Timestamp ban_until = null;
				try {
					ban_until = java.sql.Timestamp.valueOf(req.getParameter("ban_until").trim());
				} catch (IllegalArgumentException e) {
//					ban_until=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
					ban_until=null;
				}
				

				Chatroom_MembersVO chatroom_membersVO = new Chatroom_MembersVO();
				chatroom_membersVO.setChatroom_no(chatroom_no);
				chatroom_membersVO.setMem_no(mem_no);
				chatroom_membersVO.setChatroom_role(chatroom_role);
				chatroom_membersVO.setBan_until(ban_until);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatroom_membersVO", chatroom_membersVO); // 含有輸入格式錯誤的chatroom_membersVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/addChatroom_Members.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				chatroom_membersVO = chatroom_membersSvc.addChatroom_Members(chatroom_no,mem_no,chatroom_role,ban_until);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/chatroom_members/listAllChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/addChatroom_Members.jsp");
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
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				
				/***************************2.開始刪除資料***************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				chatroom_membersSvc.deleteChatroom_Members(chatroom_no,mem_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/chatroom_members/listAllChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/listAllChatroom_Members.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Crno".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String chatroom_no = req.getParameter("chatroom_no");

				
				/***************************2.開始查詢資料*****************************************/

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				String url = "/chatroom_members/listOneChatroom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
