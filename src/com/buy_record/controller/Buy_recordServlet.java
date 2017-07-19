package com.buy_record.controller;

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
import com.general_member.model.GeneralMemberService;
import com.general_member.model.GeneralMemberVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Buy_recordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // select_page.jsp�ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/
			try {
				String str = req.getParameter("BR_ID");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("�п�J�t�ӷ|���s��");
				}

				if (str.charAt(0) != 'B' && str.charAt(1) != 'R') {
					errorMsgs.add("�|����J���~");
				}
				if (str.length() != 10) {
					errorMsgs.add("�|����J���׿��~");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Buy_RecordService BRSvc = new Buy_RecordService();
				Buy_RecordVO BRVO = new Buy_RecordVO();
				BRVO = BRSvc.getOneBuy_record(str);
				if (BRVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/select_page.jsp");
					failureView.forward(req, res);

					return;
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("BRVO", BRVO);
				String url = "/buy_record/listOneBR.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/select_page.jsp");
				failureView.forward(req, res);
			}
		} // action

		if ("getOne_For_Update".equals(action)) {
			System.out.println("123");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			
			System.out.println("-6666");
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/

				String BR_ID = new String(req.getParameter("BR_ID"));
			
				/*************************** 2.�}�l�d�߸�� ****************************************/
		
				Buy_RecordService BRSvc = new Buy_RecordService();
				
				Buy_RecordVO BRVO = new Buy_RecordVO();
			
				BRVO = BRSvc.getOneBuy_record(BR_ID);
				
				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("BRVO", BRVO); // ��Ʈw���X��AdvertisementVO����,�s�Jreq
				String url = "/buy_record/update_buy_record_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				System.out.println("---222222");														// update_Advertisement_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
		
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL : " + requestURL);
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				System.out.println("enter try");
				
				String MEM_NO = req.getParameter("MEM_NO").trim();
				
				String BR_ID = req.getParameter("BR_ID").trim();

				java.sql.Timestamp BR_DATE = null;
				try {
					BR_DATE =  new java.sql.Timestamp(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					BR_DATE = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

			

				Integer BR_CASH = Integer.parseInt(req.getParameter("BR_CASH"));
				
		
				Buy_RecordVO BRVO = new Buy_RecordVO();
		

				
				BRVO.setBR_ID(BR_ID);
				BRVO.setMEM_NO(MEM_NO);
				BRVO.setBR_DATE(BR_DATE);
				BRVO.setBR_CASH(BR_CASH);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("BRVO", BRVO); // �t����J�榡���~��ADVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/buy_record/update_buy_record_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				System.out.println("12312313");
				Buy_RecordService BRSvc = new Buy_RecordService();
				BRVO = BRSvc.updateBuy_record(BR_ID, MEM_NO, BR_DATE, BR_CASH);
				System.out.println("99999");
				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				
				req.setAttribute("BRVO", BRVO); // ��Ʈwupdate���\��,���T����ADVO����,�s�Jreq

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp

				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				System.out.println("exception");
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String BR_ID = new String(req.getParameter("BR_ID"));

				/*************************** 2.�}�l�R����� ***************************************/
				Buy_RecordService BRSvc = new Buy_RecordService();
				BRSvc.deleteBuy_record(BR_ID);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/buy_record/listAllBR.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/listAllBR.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_MG".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				String MEM_NO = req.getParameter("MEM_NO").trim();


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				

				/*************************** 2.�}�l�s�W��� ***************************************/
				Buy_RecordService BRSvc = new Buy_RecordService();
				List<Buy_RecordVO> BRVO = new LinkedList<Buy_RecordVO>();
				BRVO = BRSvc.getForMG(MEM_NO);

				if(BRVO.isEmpty()){
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/select_page.jsp");
					failureView.forward(req, res);

					return;
				}
				
				req.setAttribute("BRVO", BRVO);
				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/buy_record/listForMG.jsp";
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
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/

				
				String MEM_NO = req.getParameter("MEM_NO").trim();

				

				java.sql.Timestamp BR_DATE = new java.sql.Timestamp(System.currentTimeMillis());
				

			

				Integer BR_CASH = Integer.parseInt(req.getParameter("BR_CASH"));
			

				Buy_RecordVO BRVO = new Buy_RecordVO();

			
				BRVO.setMEM_NO(MEM_NO);
				BRVO.setBR_DATE(BR_DATE);
				BRVO.setBR_CASH(BR_CASH);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("BRVO", BRVO); // �t����J�榡���~��AdvertisementVO����,�]�s�Jreq

					RequestDispatcher failureView = req.getRequestDispatcher("/buy_record/addBR.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				GeneralMemberService GRSvc = new GeneralMemberService();
				GeneralMemberVO gvo= GRSvc.getOneGeneralMember(MEM_NO);
				Integer coin = gvo.getMEM_COIN();
				coin = coin + BR_CASH; 
				GRSvc.updatecoin(MEM_NO, coin);
				Buy_RecordService BRSvc = new Buy_RecordService();
				BRVO = BRSvc.addBuy_record(MEM_NO, BR_DATE, BR_CASH);
				GeneralMemberVO gvo1= GRSvc.getOneGeneralMember(MEM_NO);
				
				
				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/personal/personal.jsp";
				HttpSession session1 = req.getSession();
		    	session1.removeAttribute("gVO");
				session1.setAttribute("gVO", gvo1);
				System.out.println(gvo.getMEM_COIN());
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("546546:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/addBR.jsp");
				failureView.forward(req, res);
			}
		}

	}

	public static byte[] getPictureByteArrayFromWeb(Part part) throws IOException {
		InputStream in = part.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[in.available()];
		int i;
		while ((i = in.read(b)) != -1) {
			baos.write(b, 0, i);
		}
		return baos.toByteArray();
	}

}
