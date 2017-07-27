package com.announcement.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.announcement.model.*;

public class AnnouncementServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if("add_Announcements".equals(action)){
			String result = "";
			result = "add_Announcements";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if("all_Announcements".equals(action)){
			String result = "";
			result = "all_Announcements";
			req.setAttribute("result",result);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if("Announcement".equals(action)){
			String result = "";
			result = "Announcement";
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
				String str = req.getParameter("ann_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String ann_no = null;
				try {
					ann_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(ann_no);
				if (announcementVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫取出的announcementVO物件,存入req
				String result = "";
				result = "getOne_For_Display_Announcement";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAnnouncement.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String ann_no = new String(req.getParameter("ann_no"));
				
				/***************************2.開始查詢資料****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(ann_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("announcementVO", announcementVO);         // 資料庫取出的announcementVO物件,存入req
				String result = "";
				result = "UpdateAnnouncement_";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_announcement_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_announcement_input.jsp的請求
			System.out.println("測試123");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String ann_no = new String(req.getParameter("ann_no").trim());
				String ann_text = req.getParameter("ann_text").trim();
			
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnn_no(ann_no);
				announcementVO.setAnn_text(ann_text);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的announcementVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/update_announcement_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.updateAnnouncement(ann_no, ann_text);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫update成功後,正確的的announcementVO物件,存入req
				String result = "";
				result = "updateFinalAnnouncement";
				req.setAttribute("result",result);
				System.out.println("測試123");
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/update_announcement_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addAnnouncement.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String ann_text = req.getParameter("ann_text").trim();

				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnn_text(ann_text);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的announcementVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/addAnnouncement.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.addAnnouncement(ann_text);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String result = "";
				result = "insertAnnouncement";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAnnouncement.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/addAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllAnnouncement.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String ann_no = new String(req.getParameter("ann_no"));
				
				/***************************2.開始刪除資料***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementSvc.deleteAnnouncement(ann_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/	
				String result = "";
				result = "deleteAnnouncement";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/listAllAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
