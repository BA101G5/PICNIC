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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("chatroom_no");
				String str1 = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J��ѫǽs��");
				}
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String chatroom_no = null;
				String mem_no = null;
				try {
					chatroom_no = new String(str);
					mem_no = new String(str1);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				Chatroom_MembersVO chatroom_membersVO = chatroom_membersSvc.getOneChatroom_Members(chatroom_no,mem_no);

				if (chatroom_membersVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("chatroom_membersVO", chatroom_membersVO); // ��Ʈw���X��chatroom_membersVO����,�s�Jreq
				String url = "/chatroom_members/listOneChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String chatroom_no = req.getParameter("chatroom_no");
				String mem_no = req.getParameter("mem_no");
				
				/***************************2.�}�l�d�߸��****************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				Chatroom_MembersVO chatroom_membersVO = chatroom_membersSvc.getOneChatroom_Members(chatroom_no,mem_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("chatroom_membersVO", chatroom_membersVO);         // ��Ʈw���X��chatroom_membersVO����,�s�Jreq
				String url = "/chatroom_members/update_chatroom_members_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/listAllChatroom_Members.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				Character chatroom_role = req.getParameter("chatroom_role").trim().charAt(0);			
				
				java.sql.Timestamp ban_until = null;
				try {
					ban_until = java.sql.Timestamp.valueOf(req.getParameter("ban_until").trim());
				} catch (IllegalArgumentException e) {
					//ban_until=new java.sql.Timestamp(System.currentTimeMillis());
					//errorMsgs.add("�п�J���!");
					ban_until =null;
				}

				Chatroom_MembersVO chatroom_membersVO = new Chatroom_MembersVO();
				chatroom_membersVO.setChatroom_no(chatroom_no);
				chatroom_membersVO.setMem_no(mem_no);
				chatroom_membersVO.setChatroom_role(chatroom_role);
				chatroom_membersVO.setBan_until(ban_until);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatroom_membersVO", chatroom_membersVO); // �t����J�榡���~��chatroom_membersVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/update_chatroom_members_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				chatroom_membersVO = chatroom_membersSvc.updateChatroom_Members(chatroom_no, mem_no, chatroom_role, ban_until);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("chatroom_membersVO", chatroom_membersVO); // ��Ʈwupdate���\��,���T����chatroom_membersVO����,�s�Jreq
				String url = "/chatroom_members/listOneChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/update_chatroom_members_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				Character chatroom_role = req.getParameter("chatroom_role").trim().charAt(0);
				
				java.sql.Timestamp ban_until = null;
				try {
					ban_until = java.sql.Timestamp.valueOf(req.getParameter("ban_until").trim());
				} catch (IllegalArgumentException e) {
//					ban_until=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("�п�J���!");
					ban_until=null;
				}
				

				Chatroom_MembersVO chatroom_membersVO = new Chatroom_MembersVO();
				chatroom_membersVO.setChatroom_no(chatroom_no);
				chatroom_membersVO.setMem_no(mem_no);
				chatroom_membersVO.setChatroom_role(chatroom_role);
				chatroom_membersVO.setBan_until(ban_until);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("chatroom_membersVO", chatroom_membersVO); // �t����J�榡���~��chatroom_membersVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/chatroom_members/addChatroom_Members.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				chatroom_membersVO = chatroom_membersSvc.addChatroom_Members(chatroom_no,mem_no,chatroom_role,ban_until);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/chatroom_members/listAllChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/addChatroom_Members.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String chatroom_no = req.getParameter("chatroom_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				
				/***************************2.�}�l�R�����***************************************/
				Chatroom_MembersService chatroom_membersSvc = new Chatroom_MembersService();
				chatroom_membersSvc.deleteChatroom_Members(chatroom_no,mem_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/chatroom_members/listAllChatroom_Members.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/listAllChatroom_Members.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Crno".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String chatroom_no = req.getParameter("chatroom_no");

				
				/***************************2.�}�l�d�߸��*****************************************/

				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				String url = "/chatroom_members/listOneChatroom.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/chatroom_members/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
