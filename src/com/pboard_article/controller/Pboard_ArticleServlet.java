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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("article_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�d���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String article_no = null;
				try {
					article_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�d���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				Pboard_ArticleVO pboard_articleVO = pboard_articleSvc.getOnePboard_Article(article_no);
				if (pboard_articleVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("pboard_articleVO", pboard_articleVO); // ��Ʈw���X��pboard_articleVO����,�s�Jreq
				String url = "listOnePboard_Article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOnePboard_Article.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllPboard_Article.jsp ��  /general_member/listPboard_Articles_ByGeneralMemberno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/pboard_article/listAllPboard_Article.jsp�j ��  �i/general_member/listPboard_Articles_ByGeneralMemberno.jsp�j �� �i /general_member/listAllGeneralMember.jsp�j		
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String article_no = new String(req.getParameter("article_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				Pboard_ArticleVO pboard_articleVO = pboard_articleSvc.getOnePboard_Article(article_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("pboard_articleVO", pboard_articleVO); // ��Ʈw���X��pboard_articleVO����,�s�Jreq
				String url = "update_pboard_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_pboard_article_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_pboard_article_input.jsp���ШD
//System.out.println(">>>1");	
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/pboard_article/listAllPboard_Article.jsp�j ��  �i/general_member/listPboard_Articles_ByGeneralMemberno.jsp�j �� �i /general_member/listAllGeneralMember.jsp�j �� �i /pboard_article/listPboard_Articles_ByCompositeQuery.jsp�j
//System.out.println("requestURL: " + requestURL);			
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String article_no = new String(req.getParameter("article_no").trim());
				String article_title = req.getParameter("article_title").trim();
				String article_text = req.getParameter("article_text").trim();				
//System.out.println("article_no: " + article_no);		
				java.sql.Timestamp article_post = null;
				try {
					article_post = java.sql.Timestamp.valueOf(req.getParameter("article_post").trim());
				} catch (IllegalArgumentException e) {
					article_post=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("pboard_articleVO", pboard_articleVO); // �t����J�榡���~��pboard_articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("update_pboard_article_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
//System.out.println(pboard_articleVO.getArticle_no());
				/***************************2.�}�l�ק���*****************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				pboard_articleVO = pboard_articleSvc.updatePboard_Article(article_no, author_no, picnic_no, article_title, article_text, article_post, article_views, article_kind);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/				
				GeneralMemberService general_memberSvc = new GeneralMemberService();
				if(requestURL.equals("/general_member/listPboard_Articles_ByGeneralMemberno.jsp") || requestURL.equals("/general_member/listAllGeneralMember.jsp"))
					req.setAttribute("listPboard_Articles_ByGeneralMemberno", pboard_articleSvc.getPboard_ArticlesByGeneralMemberno(author_no)); // ��Ʈw���X��list����,�s�Jrequest
				
				if(requestURL.equals("listEmps_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Pboard_ArticleVO> list  = pboard_articleSvc.getAll(map);
					req.setAttribute("listPboard_Articles_ByCompositeQuery",list); //  �ƦX�d��, ��Ʈw���X��list����,�s�Jrequest
				}
                
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("update_pboard_article_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addPboard_Article.jsp���ШD  
//        	System.out.println("getServletPath: " + req.getServletPath()); // /frontend/pboard_article/pboard_article.do
//        	System.out.println("getRequestURI: " + req.getRequestURI()); // /BA101G5/frontend/pboard_article/pboard_article.do
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				
				picnic_no = req.getParameter("picnic_no");
//System.out.println("Pboard_ArticleServlet/ insert / picnic_no=" + picnic_no);
				
				
				String article_title = req.getParameter("article_title").trim();
				if (article_title == null || article_title.trim().length() == 0) {
					errorMsgs.add("���D�ФŪť�");
				}
				
				String article_text = req.getParameter("article_text").trim();
				if (article_text == null || article_text.trim().length() == 0) {
					errorMsgs.add("����ФŪť�");
				}
				
				java.sql.Timestamp article_post = null;
				try {
					article_post=new java.sql.Timestamp(System.currentTimeMillis());
				} catch (Exception e) {
					article_post=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
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
					req.setAttribute("pboard_articleVO", pboard_articleVO); // �t����J�榡���~��pboard_articleVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/picnicpersionpage/personalpicnic.jsp");
//							.getRequestDispatcher(req.getServletPath().indexOf("frontend") == -1  ? "listAllPboard_Article.jsp" : "/frontend/pboard_article/pboard_article.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				//pboard_articleVO = pboard_articleSvc.addPboard_Article(article_title, article_text, article_post,  article_kind, author_no);
				pboard_articleVO = pboard_articleSvc.addPboard_Article(author_no, picnic_no, article_title, article_text, article_post, article_views, article_kind);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/picnicpersionpage/personalpicnic.jsp"; // req.getServletPath().indexOf("frontend") == -1  ? "listAllPboard_Article.jsp" : "/frontend/pboard_article/pboard_article.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllPboard_Article.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/picnicpersionpage/personalpicnic.jsp");
//						.getRequestDispatcher(req.getServletPath().indexOf("frontend") == -1  ? "listAllPboard_Article.jsp" : "/frontend/pboard_article/pboard_article.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // �Ӧ�listAllPboard_Article.jsp ��  /general_member/listPboard_Articles_ByGeneralMemberno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/pboard_article/listAllPboard_Article.jsp�j ��  �i/general_member/listPboard_Articles_ByGeneralMemberno.jsp�j �� �i /general_member/listAllGeneralMember.jsp�j �� �i /pboard_article/listPboard_Articles_ByCompositeQuery.jsp�j

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String article_no = new String(req.getParameter("article_no"));
				
				/***************************2.�}�l�R�����***************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				Pboard_ArticleVO pboard_articleVO = pboard_articleSvc.getOnePboard_Article(article_no);
				pboard_articleSvc.deletePboard_Article(article_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				GeneralMemberService general_memberSvc = new GeneralMemberService();
				if(requestURL.equals("/general_member/listPboard_Articles_ByGeneralMemberno.jsp") || requestURL.equals("/general_member/listAllGeneralMember.jsp"))
					req.setAttribute("listPboard_Articles_ByGeneralMemberno",pboard_articleSvc.getPboard_ArticlesByGeneralMemberno(pboard_articleVO.getAuthor_no())); // ��Ʈw���X��list����,�s�Jrequest
				
				if(requestURL.equals("listPboard_Articles_ByCompositeQuery.jsp")){
					HttpSession session = req.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<Pboard_ArticleVO> list  = pboard_articleSvc.getAll(map);
					req.setAttribute("listPboard_Articles_ByCompositeQuery",list); //  �ƦX�d��, ��Ʈw���X��list����,�s�Jrequest
				}
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		if ("listPboard_Articles_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
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
				
				/***************************2.�}�l�ƦX�d��***************************************/
				Pboard_ArticleService pboard_articleSvc = new Pboard_ArticleService();
				List<Pboard_ArticleVO> list  = pboard_articleSvc.getAll(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listPboard_Articles_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("listEmps_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}