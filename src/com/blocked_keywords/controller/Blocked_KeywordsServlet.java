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
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("keyword_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String keyword_no = null;
				try {
					keyword_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				Blocked_KeywordsVO blocked_keywordsVO = blocked_keywordsSvc.getOneBlocked_Keywords(keyword_no);
				if (blocked_keywordsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // 資料庫取出的blocked_keywordsVO物件,存入req
				String result = "";
				result = "getOne_For_Display_Keywords";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneBlocked_Keywords.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllBlocked_Keywords.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String keyword_no = new String(req.getParameter("keyword_no"));
				
				/***************************2.開始查詢資料****************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				Blocked_KeywordsVO blocked_keywordsVO = blocked_keywordsSvc.getOneBlocked_Keywords(keyword_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("blocked_keywordsVO", blocked_keywordsVO);         // 資料庫取出的blocked_keywordsVO物件,存入req
				String result = "";
				result = "Update_Keyword";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_blocked_keywords_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_blocked_keywords_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String keyword_no = new String(req.getParameter("keyword_no").trim());
				String keyword = req.getParameter("keyword").trim();
				String replacement = req.getParameter("replacement").trim();
			
				Blocked_KeywordsVO blocked_keywordsVO = new Blocked_KeywordsVO();
				blocked_keywordsVO.setKeyword_no(keyword_no);
				blocked_keywordsVO.setKeyword(keyword);
				blocked_keywordsVO.setReplacement(replacement);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // 含有輸入格式錯誤的blocked_keywordsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/update_blocked_keywords_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				blocked_keywordsVO = blocked_keywordsSvc.updateBlocked_Keywords(keyword_no, keyword, replacement);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // 資料庫update成功後,正確的的blocked_keywordsVO物件,存入req
				String result = "";
				result = "updateFinal";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneBlocked_Keywords.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/update_blocked_keywords_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addBlocked_Keywords.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String keyword = req.getParameter("keyword").trim();
				String replacement = req.getParameter("replacement").trim();

				Blocked_KeywordsVO blocked_keywordsVO = new Blocked_KeywordsVO();
				blocked_keywordsVO.setKeyword(keyword);
				blocked_keywordsVO.setReplacement(replacement);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("blocked_keywordsVO", blocked_keywordsVO); // 含有輸入格式錯誤的blocked_keywordsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/blocked_keywords/addBlocked_Keywords.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				blocked_keywordsVO = blocked_keywordsSvc.addBlocked_Keywords(keyword, replacement);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String result = "";
				result = "insertKeyword";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllBlocked_Keywords.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/addBlocked_Keywords.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllBlocked_Keywords.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String keyword_no = new String(req.getParameter("keyword_no"));
				
				/***************************2.開始刪除資料***************************************/
				Blocked_KeywordsService blocked_keywordsSvc = new Blocked_KeywordsService();
				blocked_keywordsSvc.deleteBlocked_Keywords(keyword_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String result = "";
				result = "deleteKeywords";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/blocked_keywords/listAllBlocked_Keywords.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
