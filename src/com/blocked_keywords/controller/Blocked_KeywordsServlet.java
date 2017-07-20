package com.blocked_keywords.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.blocked_keywords.model.*;

public class Blocked_KeywordsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("all_Blocked_Keywordss".equals(action)){
			String result = "";
			result = "all_Blocked_Keywordss";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if("add_Blocked_Keywords".equals(action)){
			String result = "";
			result = "add_Blocked_Keywords";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if("Blocked_Keywords".equals(action)){
			String result = "";
			result = "Blocked_Keywords";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("keyword_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String keyword_no = null;
				try {
					keyword_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				Blocked_KeywordsVO blocked_keywordsVO = blocked_keywordsSvc.getOneBlocked_Keywords(keyword_no);
				if (blocked_keywordsVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // ��Ʈw���X��blocked_keywordsVO����,�s�Jreq
				String result = "";
				result = "getOne_For_Display_Keywords";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneBlocked_Keywords.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllBlocked_Keywords.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String keyword_no = new String(req.getParameter("keyword_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				Blocked_KeywordsVO blocked_keywordsVO = blocked_keywordsSvc.getOneBlocked_Keywords(keyword_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("blocked_keywordsVO", blocked_keywordsVO);         // ��Ʈw���X��blocked_keywordsVO����,�s�Jreq
				String result = "";
				result = "Update_Keyword";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_blocked_keywords_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_blocked_keywords_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String keyword_no = new String(req.getParameter("keyword_no").trim());
				String keyword = req.getParameter("keyword").trim();
				String replacement = req.getParameter("replacement").trim();
			
				Blocked_KeywordsVO blocked_keywordsVO = new Blocked_KeywordsVO();
				blocked_keywordsVO.setKeyword_no(keyword_no);
				blocked_keywordsVO.setKeyword(keyword);
				blocked_keywordsVO.setReplacement(replacement);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // �t����J�榡���~��blocked_keywordsVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/update_blocked_keywords_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				blocked_keywordsVO = blocked_keywordsSvc.updateBlocked_Keywords(keyword_no, keyword, replacement);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // ��Ʈwupdate���\��,���T����blocked_keywordsVO����,�s�Jreq
				String result = "";
				result = "updateFinal";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneBlocked_Keywords.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/update_blocked_keywords_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addBlocked_Keywords.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String keyword = req.getParameter("keyword").trim();
				String replacement = req.getParameter("replacement").trim();

				Blocked_KeywordsVO blocked_keywordsVO = new Blocked_KeywordsVO();
				blocked_keywordsVO.setKeyword(keyword);
				blocked_keywordsVO.setReplacement(replacement);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // �t����J�榡���~��blocked_keywordsVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/addBlocked_Keywords.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				blocked_keywordsVO = blocked_keywordsSvc.addBlocked_Keywords(keyword, replacement);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String result = "";
				result = "insertKeyword";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllBlocked_Keywords.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/addBlocked_Keywords.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllBlocked_Keywords.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String keyword_no = new String(req.getParameter("keyword_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				blocked_keywordsSvc.deleteBlocked_Keywords(keyword_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String result = "";
				result = "deleteKeywords";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/listAllBlocked_Keywords.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
