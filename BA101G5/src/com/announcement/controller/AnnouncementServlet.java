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
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("ann_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���u�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String ann_no = null;
				try {
					ann_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("���u�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(ann_no);
				if (announcementVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // ��Ʈw���X��announcementVO����,�s�Jreq
				String url = "/backend/announcement/listOneAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllAnnouncement.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String ann_no = new String(req.getParameter("ann_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				AnnouncementVO announcementVO = announcementSvc.getOneAnnouncement(ann_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("announcementVO", announcementVO);         // ��Ʈw���X��announcementVO����,�s�Jreq
				String url = "/backend/announcement/update_announcement_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_announcement_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_announcement_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String ann_no = new String(req.getParameter("ann_no").trim());
				String ann_text = req.getParameter("ann_text").trim();
			
				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnn_no(ann_no);
				announcementVO.setAnn_text(ann_text);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // �t����J�榡���~��announcementVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/update_announcement_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.updateAnnouncement(ann_no, ann_text);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("announcementVO", announcementVO); // ��Ʈwupdate���\��,���T����announcementVO����,�s�Jreq
				String url = "/backend/announcement/listOneAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneAnnouncement.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/update_announcement_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addAnnouncement.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String ann_text = req.getParameter("ann_text").trim();
				String job = req.getParameter("job").trim();
				
				java.sql.Date hiredate = null;
				try {
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				Double sal = null;
				try {
					sal = new Double(req.getParameter("sal").trim());
				} catch (NumberFormatException e) {
					sal = 0.0;
					errorMsgs.add("�~���ж�Ʀr.");
				}
				
				Double comm = null;
				try {
					comm = new Double(req.getParameter("comm").trim());
				} catch (NumberFormatException e) {
					comm = 0.0;
					errorMsgs.add("�����ж�Ʀr.");
				}
				
				Integer deptno = new Integer(req.getParameter("deptno").trim());

				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnn_text(ann_text);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // �t����J�榡���~��announcementVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/announcement/addAnnouncement.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementVO = announcementSvc.addAnnouncement(ann_text);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/backend/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllAnnouncement.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/addAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllAnnouncement.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String ann_no = new String(req.getParameter("ann_no"));
				
				/***************************2.�}�l�R�����***************************************/
				AnnouncementService announcementSvc = new AnnouncementService();
				announcementSvc.deleteAnnouncement(ann_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/backend/announcement/listAllAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/announcement/listAllAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
