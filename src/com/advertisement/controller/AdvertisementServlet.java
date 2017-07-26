package com.advertisement.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.advertisement.model.AdvertisementService;
import com.advertisement.model.AdvertisementVO;
import com.checklist.model.ChecklistService;
import com.checklist.model.ChecklistVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AdvertisementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if ("getOne_For_Display".equals(action)) { // select_page.jsp�ШD
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/
			try {
				String str = req.getParameter("AD_NO");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("�п�J�t�ӷ|���s��");
				}

				if (str.charAt(0) != 'A' && str.charAt(1) != 'D') {
					errorMsgs.add("�|����J���~");
				}
				if (str.length() != 10) {
					errorMsgs.add("�|����J���׿��~");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				AdvertisementVO ADVO = new AdvertisementVO();
				ADVO = ADSvc.getOneAdvertisement(str);
				if (ADVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);

					return;
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("ADVO", ADVO);
				String url = "/advertisement/listOneAD.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
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

				String AD_NO = new String(req.getParameter("AD_NO"));
			
				/*************************** 2.�}�l�d�߸�� ****************************************/
		
				AdvertisementService ADSvc = new AdvertisementService();
				
				AdvertisementVO ADVO = new AdvertisementVO();
			
				ADVO = ADSvc.getOneAdvertisement(AD_NO);
				
				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("ADVO", ADVO); // ��Ʈw���X��AdvertisementVO����,�s�Jreq
				String url = "/advertisement/update_advertisement_input.jsp";
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
				String AD_NO = req.getParameter("AD_NO").trim();
				System.out.println("3611");
				String MF_NO = req.getParameter("MF_NO").trim();
				System.out.println("3612");
				String AD_SELF = req.getParameter("AD_SELF").trim();

				java.sql.Date DAY_START = null;
				try {
					DAY_START = java.sql.Date.valueOf(req.getParameter("DAY_START").trim());
				} catch (IllegalArgumentException e) {
					DAY_START = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				java.sql.Date DAY_END = null;
				try {
					DAY_END = java.sql.Date.valueOf(req.getParameter("DAY_END").trim());
				} catch (IllegalArgumentException e) {
					DAY_END = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				System.out.println(DAY_START);
				System.out.println(DAY_END);
				byte[] AD_PHOTO = null;
				try {
					Part part = req.getPart("AD_PHOTO");
					AD_PHOTO = getPictureByteArrayFromWeb(part);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Integer AD_CASH = Integer.parseInt(req.getParameter("AD_CASH"));
				
				Character AD_STA = req.getParameter("AD_STA").trim().charAt(0);

				AdvertisementVO ADVO = new AdvertisementVO();
		

				ADVO.setAD_NO(AD_NO);
				ADVO.setMF_NO(MF_NO);
				ADVO.setAD_SELF(AD_SELF);
				ADVO.setAD_PHOTO(AD_PHOTO);
				ADVO.setDAY_START(DAY_START);
				ADVO.setDAY_END(DAY_END);
				ADVO.setAD_CASH(AD_CASH);
				ADVO.setAD_STA(AD_STA);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ADVO", ADVO); // �t����J�榡���~��ADVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				System.out.println("12312313");
				AdvertisementService ADSvc = new AdvertisementService();
				ADVO = ADSvc.updateAdvertisement(AD_NO,MF_NO,AD_SELF, AD_PHOTO, DAY_START, DAY_END, AD_CASH, AD_STA);
				System.out.println("99999");
				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				
				req.setAttribute("ADVO", ADVO); // ��Ʈwupdate���\��,���T����ADVO����,�s�Jreq

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
				String AD_NO = new String(req.getParameter("AD_NO"));

				/*************************** 2.�}�l�R����� ***************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				ADSvc.deleteAdvertisement(AD_NO);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				
				String result = "";
				result = "AD_DELETE";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				
				
				
				
				//String url = "/allAdvertisement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/listAllAD.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_MM".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				String MF_NO = req.getParameter("MF_NO").trim();


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				

				/*************************** 2.�}�l�s�W��� ***************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				List<AdvertisementVO> ADVO = new LinkedList<AdvertisementVO>();
				ADVO = ADSvc.getForMM(MF_NO);

				if(ADVO.isEmpty()){
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);

					return;
				}
				
				req.setAttribute("ADVO", ADVO);
				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/advertisement/listForMM.jsp";
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
			System.out.println("123132");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
				
//				String AD_NO = req.getParameter("AD_NO").trim();
				String MF_NO = req.getParameter("MF_NO").trim();

				String AD_SELF = req.getParameter("AD_SELF").trim();
				if(AD_SELF .length() == 0){
					errorMsgs.put("AD_SELF","�п�J�ۧڤ���");
				}
				java.sql.Date DAY_START = null;
				try {
					DAY_START = java.sql.Date.valueOf(req.getParameter("DAY_START").trim());
				} catch (IllegalArgumentException e) {
					DAY_START = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("DAY_START","�ж}�l��J���!");
				}

				java.sql.Date DAY_END = null;
				try {
					DAY_END = java.sql.Date.valueOf(req.getParameter("DAY_END").trim());
				} catch (IllegalArgumentException e) {
					DAY_END = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("DAY_END","�е�����J���!");
				}

				byte[] AD_PHOTO = null;
				try {
					Part part = req.getPart("AD_PHOTO");
					AD_PHOTO = getPictureByteArrayFromWeb(part);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Integer AD_CASH = Integer.parseInt(req.getParameter("AD_CASH"));
				
				Character AD_STA = req.getParameter("AD_STA").trim().charAt(0);

				
	System.out.println(MF_NO);
	System.out.println(AD_SELF);
	System.out.println(DAY_START);
	System.out.println(DAY_END);
	System.out.println(AD_CASH);
	System.out.println(AD_STA);
				AdvertisementVO ADVO = new AdvertisementVO();

//				ADVO.setAD_NO(AD_NO);
				ADVO.setMF_NO(MF_NO);
				ADVO.setAD_SELF(AD_SELF);
				ADVO.setAD_PHOTO(AD_PHOTO);
				ADVO.setDAY_START(DAY_START);
				ADVO.setDAY_END(DAY_END);
				ADVO.setAD_CASH(AD_CASH);
				ADVO.setAD_STA(AD_STA);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ADVO", ADVO); // �t����J�榡���~��AdvertisementVO����,�]�s�Jreq

					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement_buy.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				ADVO = ADSvc.addAdvertisement(MF_NO, AD_SELF, AD_PHOTO, DAY_START, DAY_END, AD_CASH, AD_STA);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				String url = "/personal/personal.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.put("",e.getMessage());
				System.out.println("546546:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement_buy.jsp");
				failureView.forward(req, res);
			}
		}
		if("AD".equals(action)){
			System.out.println("�i"+action);
			
			AdvertisementService ADSvc = new AdvertisementService();
			List<AdvertisementVO> advertisementVO = ADSvc.getAll_U();
			
			req.setAttribute("advertisementVO", advertisementVO);
			
			
			String AD = "all";
			req.setAttribute("result", AD);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("OTHER".equals(action)){
			System.out.println("�i"+action);
			
			AdvertisementService ADSvc = new AdvertisementService();
			List<AdvertisementVO> advertisementVO = ADSvc.getAll_Other();
			
			req.setAttribute("advertisementVO", advertisementVO);
			
			String AD = "OTHER";
			req.setAttribute("result", AD);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("UpdateForSTA".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			Map<String,Character> errorMsgs = new HashMap<String,Character>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL : " + requestURL);
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
			
				String AD_NO = req.getParameter("AD_NO").trim();
				
				
				
				Character AD_STA = req.getParameter("AD_STA").charAt(0);
				
				if(AD_STA.equals('U')){
					 AD_STA ='O';
				}else{
					AD_STA ='U';
				}
				

				AdvertisementVO advertisementVO = new AdvertisementVO();
				

				advertisementVO.setAD_NO(AD_NO);
				
				advertisementVO.setAD_STA(AD_STA);
				errorMsgs.put("AD_STA", AD_STA);

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("advertisementVO", advertisementVO); // �t����J�榡���~��ADVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
//					failureView.forward(req, res);
//					return; // �{�����_
//				}

				/*************************** 2.�}�l�ק��� *****************************************/
				System.out.println("12312313");
				AdvertisementService ADSvc = new AdvertisementService();
				advertisementVO = ADSvc.updateSTA(AD_NO, AD_STA);
				System.out.println("99999");
				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				
				req.setAttribute("advertisementVO", advertisementVO); // ��Ʈwupdate���\��,���T����ADVO����,�s�Jreq

				String result = "";
				result = "AD";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				
				
				
				//String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp

				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				System.out.println("exception");
				errorMsgs.put("error",'e');
				RequestDispatcher failureView = req
						.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
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
