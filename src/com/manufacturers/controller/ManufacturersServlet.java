package com.manufacturers.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.general_member.model.GeneralMemberService;
import com.general_member.model.GeneralMemberVO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;

import mail.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ManufacturersServlet extends HttpServlet {
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
				String str = req.getParameter("MF_NO");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("�п�J�t�ӷ|���s��");
				}
				

				if (str.charAt(0) != 'M' && str.charAt(1) != 'M') {
					errorMsgs.add("�|����J���~");
				}
				if (str.length() != 10) {
					errorMsgs.add("�|����J���׿��~");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manufacturers/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

			

				/*************************** 2.�}�l�d�߸�� *****************************************/
				ManufacturersService MfSvc = new ManufacturersService();
				ManufacturersVO MfVO = MfSvc.getOneManufacturers(str);
				if (MfVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manufacturers/select_page.jsp");
					failureView.forward(req, res);

					return;
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("MfVO", MfVO);
				String url = "/manufacturers/listOneMF.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manufacturers/select_page.jsp");
				failureView.forward(req, res);
			}
		} // action

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String MF_NO = new String(req.getParameter("MF_NO"));
				/*************************** 2.�}�l�d�߸�� ****************************************/
				ManufacturersService MfSvc = new ManufacturersService();
				ManufacturersVO MfVO = MfSvc.getOneManufacturers(MF_NO);
System.out.println(MfVO.getMF_PSW());
				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("MfVO", MfVO); // ��Ʈw���X��ManufacturersVO����,�s�Jreq
				String url = "/m_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				System.out.println("-----FFF");									// update_manufacturers_input.jsp
				successView.forward(req, res);
				System.out.println("--------------");
				return;
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/personal/personal.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
System.out.println("-------------1");	
			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println("--------66666");	
			
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String MF_NO = req.getParameter("MF_NO").trim();
				String MF_NAME = req.getParameter("MF_NAME").trim();
				
				String MF_PHONE = req.getParameter("MF_PHONE").trim();
				if (!MF_PHONE.matches("^[0][9][0-9]{8}$")) {
					errorMsgs.put("MF_PHONE","Phone wrong");
				}

				String MF_MAIL = req.getParameter("MF_MAIL").trim();
				if (!MF_MAIL.matches("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$")) {
					errorMsgs.put("MF_MAIL","Email�榡��J���~");
				}
				

				String MF_ACCO = req.getParameter("MF_ACCO").trim();
				String MF_PSW = null;
				try {
					MF_PSW = req.getParameter("MF_PSW").trim();
				} catch (Exception e) {
					errorMsgs.put("MF_PSW","password wrong");
				}
				String MF_BS =null;
				try{
					  MF_BS = req.getParameter("MF_BS").trim();
						if(!MF_BS.matches("^[0-9]{8}$")){
							errorMsgs.put("MF_BS_1","*length not right");
						}else{
							if(!checkID(MF_BS)){
								errorMsgs.put("MF_BS","*not legal bs");
							}				
						}	
				}catch(Exception e){
					errorMsgs.put("MF_BS","*not right bs");
				}
				String MF_SELF = req.getParameter("MF_SELF");

				Part part = req.getPart("MF_LOGO");
				byte[] MF_LOGO = null;
				try {
					if(getFileNameFromPart(part)!=null)
						MF_LOGO = getPictureByteArrayFromWeb(part);
					else{
						ManufacturersService m=new ManufacturersService ();
						ManufacturersVO mVO =m.getOneManufacturers(MF_NO);
						MF_LOGO =mVO.getMF_LOGO();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				String MF_ADDR = req.getParameter("MF_ADDR");

				String MF_FAX = req.getParameter("MF_FAX").trim();
				Character MF_STA = req.getParameter("MF_STA").trim().charAt(0);

				Integer MF_REPORTNUM = Integer.parseInt(String.valueOf(req.getParameter("MF_REPORTNUM").trim()));
System.out.println(MF_NO);
System.out.println(MF_NAME);
System.out.println(MF_MAIL);
System.out.println(MF_ACCO);
System.out.println(MF_PSW);
System.out.println(MF_SELF);
System.out.println(MF_BS);
System.out.println(MF_ADDR);
System.out.println(MF_FAX);
System.out.println(MF_STA);
System.out.println(MF_REPORTNUM);

				ManufacturersVO MfVO = new ManufacturersVO();
				MfVO.setMF_NO(MF_NO);

				MfVO.setMF_NAME(MF_NAME);
				MfVO.setMF_PHONE(MF_PHONE);
				MfVO.setMF_MAIL(MF_MAIL);
				MfVO.setMF_ACCO(MF_ACCO);
				MfVO.setMF_PSW(MF_PSW);
				MfVO.setMF_LOGO(MF_LOGO);
				MfVO.setMF_SELF(MF_SELF);
				MfVO.setMF_BS(MF_BS);
				MfVO.setMF_ADDR(MF_ADDR);
				MfVO.setMF_FAX(MF_FAX);
				MfVO.setMF_STA(MF_STA);
				MfVO.setMF_REPORTNUM(MF_REPORTNUM);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MfVO", MfVO); // �t����J�榡���~��MfVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/m_update.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				ManufacturersService MfSvc = new ManufacturersService();
				MfVO = MfSvc.updateManufacturers(MF_NO, MF_NAME, MF_PHONE, MF_MAIL, MF_ACCO, md5(MF_PSW), MF_LOGO, MF_SELF,
						MF_BS, MF_ADDR, MF_FAX, MF_STA, MF_REPORTNUM);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
				System.out.println("");
				req.setAttribute("MfVO", MfVO); // ��Ʈwupdate���\��,���T����MfVO����,�s�Jreq
				HttpSession session1 = req.getSession();
		    	session1.removeAttribute("mVO");
				session1.setAttribute("mVO", MfVO );
				
				RequestDispatcher successView = req.getRequestDispatcher("/personal/personal.jsp"); // �ק令�\��,���listOneEmp.jsp

				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.put("error","�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/m_update.jsp");
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
				String MF_NO = new String(req.getParameter("MF_NO"));

				/*************************** 2.�}�l�R����� ***************************************/
				ManufacturersService MfSvc = new ManufacturersService();
				MfSvc.deleteManufacturers(MF_NO);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/manufacturers/listAllMF.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manufacturers/listAllMF.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			Map<String,String> errorMsgs = new HashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/
System.out.println("---");		
				String MF_NAME = req.getParameter("MF_NAME").trim();
				if(MF_NAME.equals("")){
					errorMsgs.put("MF_NAME","*�t�ӦW�٤���ť�");
				}
				String MF_PHONE = req.getParameter("MF_PHONE").trim();
				if (!MF_PHONE.matches("^[0][9][0-9]{8}$")) {
					errorMsgs.put("MF_PHONE","*Phone wrong");
				}

				String MF_MAIL = req.getParameter("MF_MAIL").trim();
				if (!MF_MAIL.matches("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$")) {
					errorMsgs.put("MF_MAIL","*Email�榡��J���~");
				}

				String MF_ACCO = null;
				List<ManufacturersVO> mlist = new ArrayList<ManufacturersVO>();
				ManufacturersService msv = new ManufacturersService();
				mlist.addAll(msv.getAll());
				
				try {
					MF_ACCO = req.getParameter("MF_ACCO").trim();
					if(MF_ACCO ==null || MF_ACCO.length() == 0){
						errorMsgs.put("MF_ACCO", "*Account can't empty");
					}else{
						for(ManufacturersVO m : mlist){
							if(m.getMF_ACCO().equals(MF_ACCO)){
								errorMsgs.put("MF_ACCO_1", "*�b���w�Q�ϥ�");
							}
						}
					}
					
				} catch (Exception e) {
					errorMsgs.put("MF_ACCO","*Account wrong");
				}

				String MF_PSW = null;
				try {
					MF_PSW = req.getParameter("MF_PSW").trim();
					if(MF_PSW ==null || MF_PSW.length() ==0){
						errorMsgs.put("MF_PSW","password can't empty");
					}else if(MF_PSW.length() <8){
						errorMsgs.put("MF_PSW_1", "*password at least eight words");
					}
					
				} catch (Exception e) {
					errorMsgs.put("MF_PSW","*password wrong");
				}
				
				String MF_BS =null;
				try{
					  MF_BS = req.getParameter("MF_BS").trim();
						if(!MF_BS.matches("^[0-9]{8}$")){
							errorMsgs.put("MF_BS_1","*length not right");
						}else{
							if(!checkID(MF_BS)){
								errorMsgs.put("MF_BS","*not legal bs");
							}				
						}	
				}catch(Exception e){
					errorMsgs.put("MF_BS","*not right bs");
				}
				  
						
				String MF_SELF = req.getParameter("MF_SELF");

				byte[] MF_LOGO = null;
				try {
					Part part = req.getPart("MF_LOGO");
					MF_LOGO = getPictureByteArrayFromWeb(part);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String MF_ADDR = req.getParameter("MF_ADDR").trim();

				String MF_FAX = req.getParameter("MF_FAX").trim();
				Character MF_STA = req.getParameter("MF_STA").trim().charAt(0);

				Integer MF_REPORTNUM = Integer.valueOf(req.getParameter("MF_REPORTNUM").trim());

				ManufacturersVO MfVO = new ManufacturersVO();

				MfVO.setMF_NAME(MF_NAME);
				MfVO.setMF_PHONE(MF_PHONE);
				MfVO.setMF_MAIL(MF_MAIL);
				MfVO.setMF_ACCO(MF_ACCO);
				MfVO.setMF_PSW(MF_PSW);
				MfVO.setMF_LOGO(MF_LOGO);
				MfVO.setMF_SELF(MF_SELF);
				MfVO.setMF_BS(MF_BS);
				MfVO.setMF_ADDR(MF_ADDR);
				MfVO.setMF_FAX(MF_FAX);
				MfVO.setMF_STA(MF_STA);
				MfVO.setMF_REPORTNUM(MF_REPORTNUM);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MfVO", MfVO); // �t����J�榡���~��ManufacturersVO����,�]�s�Jreq

					RequestDispatcher failureView = req.getRequestDispatcher("/manufacturers/m_member.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				ManufacturersService MfSvc = new ManufacturersService();
				MfVO = MfSvc.addManufacturers(MF_NAME, MF_PHONE, MF_MAIL, MF_ACCO, md5(MF_PSW), MF_LOGO, MF_SELF, MF_BS,
						MF_ADDR, MF_FAX, MF_STA, MF_REPORTNUM);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				
				MailService mail = new MailService();

				String subject = "�K�X�q��";

				String messageText = String.valueOf(
						"<a href='http://localhost:8081/" + req.getContextPath() +"/addMemberEmail.do?account="+MF_MAIL+"'>���Һ��}</a>") + "\n"
						+ "�K�X: "+MF_PSW;

				mail.sendMail(MF_MAIL, subject, messageText);
				
				
				
				
				String url = "/signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.put("error",e.getMessage());
				System.out.println("546546:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/manufacturers/m_member.jsp");
				failureView.forward(req, res);
			}
		}

	}
	//picture �૬
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
	//�νs check
	public static boolean checkID(String id) {
		int v[] = { 1, 2, 1, 2, 1, 2, 4, 1 };
		int A1[] = new int[8];
		int A2[] = new int[8];
		int B = 0;
		int B1 = 0;
		for (int i = 0; i < v.length; i++) {
			A1[i] = Integer.parseInt(String.valueOf(id.charAt(i))) * v[i];
			// System.out.println("A1[" + i +"]: " + A1[i]);
		}

		for (int i = 0; i < A1.length; i++) {
			if (A1[i] < 10) {
				A2[i] = A1[i];
			} else {
				A2[i] = Integer.parseInt(String.valueOf(A1[i]).substring(0, 1))
						+ Integer.parseInt(String.valueOf(A1[i]).substring(1, 2));
			}
			// System.out.println("A2[" + i +"]: " + A2[i]);
		}

		for (int i = 0; i < A2.length; i++) {
			B = B + A2[i];
		}

		if (B % 10 == 0) {
			return true;

		} else {
			if (id.charAt(6) == 7) {
				B = A2[0] + A2[1] + A2[2] + A2[3] + A2[4] + A2[5] + 0 + A2[7];
				B1 = A2[0] + A2[1] + A2[2] + A2[3] + A2[4] + A2[5] + 1 + A2[7];
				if ((B % 10 == 0) || (B1 % 10 == 0)) {
					return true;
				}
			}
		}
		return false;
	}
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	public static String md5(String str) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] barr = md.digest(str.getBytes()); // �N byte �}�C�[�K
			StringBuffer sb = new StringBuffer(); // �N byte �}�C�ন 16 �i��
			for (int i = 0; i < barr.length; i++) {
				sb.append(byte2Hex(barr[i]));
			}
			String hex = sb.toString();
			md5 = hex.toUpperCase(); // �@���ন�j�g
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static String byte2Hex(byte b) {
		String[] h = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		int i = b;
		if (i < 0) {
			i += 256;
		}
		return h[i / 16] + h[i % 16];
	}
}
