package com.pboard_article.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general_member.model.GeneralMemberService;
import com.pboard_article.model.Pboard_ArticleService;
import com.pboard_article.model.Pboard_ArticleVO;

public class Pboard_ArticleServlet extends HttpServlet {
	String picnic_no = "PG00000001";
	Integer article_views = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("article_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String article_no = null;
				try {
					article_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				Pboard_ArticleVO pboard_articleVO = pboard_articleSvc.getOnePboard_Article(article_no);
				if (pboard_articleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pboard_articleVO", pboard_articleVO); // 資料庫取出的pboard_articleVO物件,存入req
				String url = "listOnePboard_Article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOnePboard_Article.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllPboard_Article.jsp 或  /general_member/listPboard_Articles_ByGeneralMemberno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/pboard_article/listAllPboard_Article.jsp】 或  【/general_member/listPboard_Articles_ByGeneralMemberno.jsp】 或 【 /general_member/listAllGeneralMember.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String article_no = new String(req.getParameter("article_no"));
				
				/***************************2.開始查詢資料****************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				Pboard_ArticleVO pboard_articleVO = pboard_articleSvc.getOnePboard_Article(article_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("pboard_articleVO", pboard_articleVO); // 資料庫取出的pboard_articleVO物件,存入req
				String url = "update_pboard_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_pboard_article_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_pboard_article_input.jsp的請求
//System.out.println(">>>1");	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/pboard_article/listAllPboard_Article.jsp】 或  【/general_member/listPboard_Articles_ByGeneralMemberno.jsp】 或 【 /general_member/listAllGeneralMember.jsp】 或 【 /pboard_article/listPboard_Articles_ByCompositeQuery.jsp】
//System.out.println("requestURL: " + requestURL);			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String article_no = new String(req.getParameter("article_no").trim());
				String article_title = req.getParameter("article_title").trim();
				String article_text = req.getParameter("article_text").trim();				
//System.out.println("article_no: " + article_no);		
				java.sql.Timestamp article_post = null;
				try {
					article_post = java.sql.Timestamp.valueOf(req.getParameter("article_post").trim());
				} catch (IllegalArgumentException e) {
					article_post=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
//System.out.println("article_post: " + article_post);

				Integer article_kind = null;
				try {
					article_kind = new Integer(req.getParameter("article_kind").trim());
				} catch (NumberFormatException e) {
					article_kind = 0;
					errorMsgs.add("Exception: article_kind.");
				}

				String author_no = new String(req.getParameter("author_no").trim());
//System.out.println("author_no: " + author_no);	
				Pboard_ArticleVO pboard_articleVO = new Pboard_ArticleVO();
				pboard_articleVO.setArticle_no(article_no);
				pboard_articleVO.setArticle_title(article_title);
				pboard_articleVO.setArticle_text(article_text);
				pboard_articleVO.setArticle_post(article_post);
				pboard_articleVO.setArticle_kind(article_kind);
				pboard_articleVO.setAuthor_no(author_no);

//System.out.println(">>>>2:  "+pboard_articleVO.getArticle_no());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pboard_articleVO", pboard_articleVO); // 含有輸入格式錯誤的pboard_articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("update_pboard_article_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
//System.out.println(pboard_articleVO.getArticle_no());
				/***************************2.開始修改資料*****************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				pboard_articleVO = pboard_articleSvc.updatePboard_Article(article_no, author_no, picnic_no, article_title, article_text, article_post, article_views, article_kind);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
				GeneralMemberService general_memberSvc = new GeneralMemberService();
				if(requestURL.equals("/general_member/listPboard_Articles_ByGeneralMemberno.jsp") || requestURL.equals("/general_member/listAllGeneralMember.jsp"))
					req.setAttribute("listPboard_Articles_ByGeneralMemberno", pboard_articleSvc.getPboard_ArticlesByGeneralMemberno(author_no)); // 資料庫取出的list物件,存入request
				
				if(requestURL.equals("listEmps_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Pboard_ArticleVO> list  = pboard_articleSvc.getAll(map);
					req.setAttribute("listPboard_Articles_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
                
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("update_pboard_article_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addPboard_Article.jsp的請求  
//        	System.out.println("getServletPath: " + req.getServletPath()); // /frontend/pboard_article/pboard_article.do
//        	System.out.println("getRequestURI: " + req.getRequestURI()); // /BA101G5/frontend/pboard_article/pboard_article.do
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				picnic_no = req.getParameter("picnic_no");
//System.out.println("Pboard_ArticleServlet/ insert / picnic_no=" + picnic_no);
				
				
				String article_title = req.getParameter("article_title").trim();
				if (article_title == null || article_title.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				
				String article_text = req.getParameter("article_text").trim();
				if (article_text == null || article_text.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				java.sql.Timestamp article_post = null;
				try {
					article_post=new java.sql.Timestamp(System.currentTimeMillis());
				} catch (Exception e) {
					article_post=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				Integer article_kind = null;
				try {
//					article_kind = new Integer(req.getParameter("article_kind").trim());
					article_kind = new Integer(1);
				} catch (Exception e) {
					article_kind = 0;
					errorMsgs.add("Exception: article_kind.");
				}
				
				String author_no = new String(req.getParameter("author_no").trim());

				Pboard_ArticleVO pboard_articleVO = new Pboard_ArticleVO();
				pboard_articleVO.setArticle_title(article_title);
				pboard_articleVO.setArticle_text(article_text);
				pboard_articleVO.setArticle_post(article_post);
				pboard_articleVO.setArticle_kind(article_kind);
				pboard_articleVO.setAuthor_no(author_no);
				pboard_articleVO.setPicnic_no(picnic_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pboard_articleVO", pboard_articleVO); // 含有輸入格式錯誤的pboard_articleVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(req.getServletPath().indexOf("backend") == -1  ? "/picnicpersionpage/personalpicnic.jsp" : "listAllPboard_Article.jsp");
//							.getRequestDispatcher("/picnicpersionpage/personalpicnic.jsp");
//							.getRequestDispatcher(req.getServletPath().indexOf("frontend") == -1  ? "listAllPboard_Article.jsp" : "/frontend/pboard_article/pboard_article.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				//pboard_articleVO = pboard_articleSvc.addPboard_Article(article_title, article_text, article_post,  article_kind, author_no);
				pboard_articleVO = pboard_articleSvc.addPboard_Article(author_no, picnic_no, article_title, article_text, article_post, article_views, article_kind);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/picnicpersionpage/personalpicnic.jsp";
				String url = req.getServletPath().indexOf("backend") == -1  ? "/picnicpersionpage/personalpicnic.jsp"  : "listAllPboard_Article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllPboard_Article.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
//						.getRequestDispatcher("/picnicpersionpage/personalpicnic.jsp");
						.getRequestDispatcher(req.getServletPath().indexOf("backend") == -1  ? "/picnicpersionpage/personalpicnic.jsp"  : "listAllPboard_Article.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllPboard_Article.jsp 或  /general_member/listPboard_Articles_ByGeneralMemberno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/pboard_article/listAllPboard_Article.jsp】 或  【/general_member/listPboard_Articles_ByGeneralMemberno.jsp】 或 【 /general_member/listAllGeneralMember.jsp】 或 【 /pboard_article/listPboard_Articles_ByCompositeQuery.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				String article_no = new String(req.getParameter("article_no"));
				
				/***************************2.開始刪除資料***************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				Pboard_ArticleVO pboard_articleVO = pboard_articleSvc.getOnePboard_Article(article_no);
				pboard_articleSvc.deletePboard_Article(article_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				GeneralMemberService general_memberSvc = new GeneralMemberService();
				if(requestURL.equals("/general_member/listPboard_Articles_ByGeneralMemberno.jsp") || requestURL.equals("/general_member/listAllGeneralMember.jsp"))
					req.setAttribute("listPboard_Articles_ByGeneralMemberno",pboard_articleSvc.getPboard_ArticlesByGeneralMemberno(pboard_articleVO.getAuthor_no())); // 資料庫取出的list物件,存入request
				
				if(requestURL.equals("listPboard_Articles_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Pboard_ArticleVO> list  = pboard_articleSvc.getAll(map);
					req.setAttribute("listPboard_Articles_ByCompositeQuery",list); //  複合查詢, 資料庫取出的list物件,存入request
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listPboard_Articles_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = (HashMap<String, String[]>)req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>)map1.clone();
					session.setAttribute("map",map2);
					map = (HashMap<String, String[]>)req.getParameterMap();
				} 
				
				/***************************2.開始複合查詢***************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				List<Pboard_ArticleVO> list  = pboard_articleSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listPboard_Articles_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}