package com.contact_list.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.advertisement.model.AdvertisementService;
import com.advertisement.model.AdvertisementVO;
import com.buy_record.model.Buy_RecordService;
import com.buy_record.model.Buy_RecordVO;
import com.contact_list.model.Contact_ListService;
import com.contact_list.model.Contact_ListVO;
import com.general_member.model.GeneralMemberService;
import com.general_member.model.GeneralMemberVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Contact_ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

	
		if ("getOne_For_MG".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				String mem_no = req.getParameter("mem_no").trim();
				String contact_no =req.getParameter("contact_no").trim();
System.out.println(mem_no);
System.out.println(contact_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/personal/personal.jsp");
					failureView.forward(req, res);
					return;
				}
				

				/*************************** 2.�}�l�s�W��� ***************************************/
				Contact_ListService CSvc = new Contact_ListService();
				List<Contact_ListVO> list =  CSvc.getAll();
				list = CSvc.getOneContact_List(mem_no, contact_no);

				if(list.isEmpty()){
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/personal/personal.jsp");
					failureView.forward(req, res);

					return;
				}
				req.setAttribute("contact_no", contact_no);
				req.setAttribute("mem_no", mem_no);
				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/personal/friend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("546546:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

	}

	

}
