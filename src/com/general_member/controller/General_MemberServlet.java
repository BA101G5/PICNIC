package com.general_member.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

import mail.MailService;
import java.security.MessageDigest;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class General_MemberServlet extends HttpServlet {
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
				String str = req.getParameter("MEM_NO");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("�п�J�@��|���s��");
				}

				if (str.charAt(0) != 'M' && str.charAt(1) != 'G') {
					errorMsgs.add("�|����J���~");
				}
				if (str.length() != 10) {
					errorMsgs.add("�|����J���׿��~");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/general_member/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				GeneralMemberService MemSvc = new GeneralMemberService();
				GeneralMemberVO MemVO = MemSvc.getOneGeneralMember(str);
				if (MemVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/general_member/select_page.jsp");
					failureView.forward(req, res);

					return;
				}

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/
				req.setAttribute("MemVO", MemVO);
				String url = "/general_member/listOneMem.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/general_member/select_page.jsp");
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
				String MEM_NO = new String(req.getParameter("MEM_NO"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				GeneralMemberService MemSvc = new GeneralMemberService();
				GeneralMemberVO MemVO = MemSvc.getOneGeneralMember(MEM_NO);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("MemVO", MemVO);
				System.out.println(MemVO);
				System.out.println(MemVO.getMEM_NO());
				System.out.println(MemVO.getMEM_MAIL());
				System.out.println(MemVO.getMEM_NAME());
				System.out.println(MemVO.getMEM_PHONE());
				System.out.println(MemVO.getMEM_PSW());
				System.out.println(MemVO.getMEM_BIRTH());
				System.out.println(MemVO.getMEM_GEN());
				// ��Ʈw���X��GeneralMemberVO����,�s�Jreq
				String url = "/g_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				// update_general_member_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				System.out.println("----------------");
				RequestDispatcher failureView = req.getRequestDispatcher("/personal.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			Map<String, String> errorMsgs = new HashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/

				String MEM_NO = req.getParameter("MEM_NO").trim();
				String MEM_NAME = req.getParameter("MEM_NAME").trim();

				Character MEM_GEN = null;
				try {
					MEM_GEN = req.getParameter("MEM_GEN").trim().toUpperCase().charAt(0);

					if (!(MEM_GEN.equals('M')) && MEM_GEN != 'F') {
						errorMsgs.put("MEM_GEN", "�ʧO��J���~");
					}
				} catch (Exception e) {
					MEM_GEN = 'M';
					errorMsgs.put("MEM_GEN", "�ʧO���~");
				}

				java.sql.Date MEM_BIRTH = null;
				try {
					MEM_BIRTH = java.sql.Date.valueOf(req.getParameter("MEM_BIRTH").trim());
				} catch (IllegalArgumentException e) {
					MEM_BIRTH = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("MEM_BIRTH", "�п�J���!");
				}
				String MEM_ADDR = req.getParameter("MEM_ADDR").trim();

				String MEM_MAIL = req.getParameter("MEM_MAIL").trim();

				String MEM_PSW = null;
				try {
					MEM_PSW = req.getParameter("MEM_PSW").trim();

				} catch (Exception e) {
					System.out.println("EXCEPTION");
					errorMsgs.put("MEM_PSW", "password wrong");
				}

				String MEM_SELF = req.getParameter("MEM_SELF");
				if (MEM_SELF.trim() == null || MEM_SELF.trim().length() == 0) {
					MEM_SELF = "�S��ۧڤ��Э�";
				}

				Part part = req.getPart("MEM_PIC");
				byte[] MEM_PIC = null;
				try {
					if (getFileNameFromPart(part) != null)
						MEM_PIC = getPictureByteArrayFromWeb(part);
					else {
						GeneralMemberService g = new GeneralMemberService();
						GeneralMemberVO gVO = g.getOneGeneralMember(MEM_NO);
						MEM_PIC = gVO.getMEM_PIC();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				String MEM_PHONE = req.getParameter("MEM_PHONE").trim();
				if (!MEM_PHONE.matches("^[0][9][0-9]{8}$")) {
					errorMsgs.put("MEM_PSW_1", "Phone wrong");
				}

				Integer MEM_COIN = Integer.valueOf(req.getParameter("MEM_COIN").trim());
				Character MEM_STA = req.getParameter("MEM_STA").trim().charAt(0);

				Character MEM_PBOARD = req.getParameter("MEM_PBOARD").charAt(0);
				if (MEM_PBOARD != 'Y' && MEM_PBOARD != 'N') {
					errorMsgs.put("MEM_PBOARD", "�d���O�п�JY or N");
				}

				GeneralMemberVO MemVO = new GeneralMemberVO();
				MemVO.setMEM_NO(MEM_NO);

				MemVO.setMEM_NAME(MEM_NAME);
				MemVO.setMEM_GEN(MEM_GEN);
				MemVO.setMEM_BIRTH(MEM_BIRTH);
				MemVO.setMEM_ADDR(MEM_ADDR);
				MemVO.setMEM_MAIL(MEM_MAIL);
				MemVO.setMEM_PSW(MEM_PSW);
				MemVO.setMEM_SELF(MEM_SELF);
				MemVO.setMEM_PIC(MEM_PIC);
				MemVO.setMEM_COIN(MEM_COIN);
				MemVO.setMEM_STA(MEM_STA);
				MemVO.setMEM_PHONE(MEM_PHONE);
				MemVO.setMEM_PBOARD(MEM_PBOARD);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("MemVO", MemVO); // �t����J�榡���~��MemVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/g_update.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				GeneralMemberService MemSvc = new GeneralMemberService();
				MemVO = MemSvc.updateGeneralMember(MEM_NO, MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL, md5(MEM_PSW),
						MEM_SELF, MEM_PIC, MEM_COIN, MEM_STA, MEM_PHONE, MEM_PBOARD);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/

				req.setAttribute("MemVO", MemVO); // ��Ʈwupdate���\��,���T����MemVO����,�s�Jreq
				HttpSession session1 = req.getSession();
				session1.removeAttribute("gVO");
				session1.setAttribute("gVO", MemVO);

				RequestDispatcher successView = req.getRequestDispatcher("/personal/personal.jsp"); // �ק令�\��,���listOneEmp.jsp

				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {

				errorMsgs.put("error", "error");
				RequestDispatcher failureView = req.getRequestDispatcher("/g_update.jsp");
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
				String MEM_NO = new String(req.getParameter("MEM_NO"));

				/*************************** 2.�}�l�R����� ***************************************/
				GeneralMemberService MemSvc = new GeneralMemberService();
				MemSvc.deleteGeneralMember(MEM_NO);

				/***************************
				 * 3.�R������,�ǳ����(Send the Success view)
				 ***********/
				String url = "/general_member/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/general_member/listAllMem.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			Map<String, String> errorMsgs = new HashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 *************************/

				String MEM_NAME = req.getParameter("MEM_NAME").trim();
				if (MEM_NAME == null || MEM_NAME.trim().length() == 0) {
					errorMsgs.put("MEM_NAME", "* �m�W���i�ť�");
				}
				Character MEM_GEN = null;
				try {
					MEM_GEN = req.getParameter("MEM_GEN").trim().toUpperCase().charAt(0);
					System.out.println(MEM_GEN + "....");
					if (!(MEM_GEN.equals('M')) && MEM_GEN != 'F') {
						errorMsgs.put("MEM_GEN", "* �ʧO��J���~");
					}
				} catch (Exception e) {
					MEM_GEN = 'M';
					errorMsgs.put("MEM_GEN", "* �ʧO���~");
				}

				java.sql.Date MEM_BIRTH = null;
				try {
					MEM_BIRTH = java.sql.Date.valueOf(req.getParameter("MEM_BIRTH").trim());
				} catch (IllegalArgumentException e) {
					MEM_BIRTH = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("MEM_BIRTH", "* �п�J���!");
				}
				String MEM_ADDR = req.getParameter("MEM_ADDR").trim();

				String MEM_MAIL = req.getParameter("MEM_MAIL").trim();
				if (!MEM_MAIL.matches("^\\w+\\.*\\w+@(\\w+\\.){1,5}[a-zA-Z]{2,3}$")) {
					errorMsgs.put("MEM_MAIL", "* Email�榡��J���~");
				}

				List<GeneralMemberVO> list = new ArrayList<GeneralMemberVO>();
				GeneralMemberService gs = new GeneralMemberService();
				list.addAll(gs.getAll());
				for (GeneralMemberVO com : list) {
					if (com.getMEM_MAIL().equals(MEM_MAIL)) {
						errorMsgs.put("MEM_MAIL_1", "* Email�w�g�ϥιL");
					}
				}

				String MEM_PSW = null;
				try {
					MEM_PSW = req.getParameter("MEM_PSW").trim();
					if (MEM_PSW == null || MEM_PSW.trim().length() == 0) {
						errorMsgs.put("MEM_PSW", "* password can't empty!!");
					} else if (MEM_PSW.trim().length() < 8) {
						errorMsgs.put("MEM_PSW_1", "* password less eight words!!");
					}
				} catch (Exception e) {
					errorMsgs.put("MEM_PSW", "* password wrong");
				}

				String MEM_SELF = req.getParameter("MEM_SELF").trim();

				byte[] MEM_PIC = null;
				try {
					Part part = req.getPart("MEM_PIC");
					MEM_PIC = getPictureByteArrayFromWeb(part);
				} catch (Exception e) {
					e.printStackTrace();
				}

				String MEM_PHONE = req.getParameter("MEM_PHONE").trim();
				if (!MEM_PHONE.matches("^[0][9][0-9]{8}$")) {
					errorMsgs.put("MEM_PHONE", "* Phone wrong");
				}

				Integer MEM_COIN = Integer.valueOf(req.getParameter("MEM_COIN").trim());
				Character MEM_STA = req.getParameter("MEM_STA").trim().charAt(0);

				Character MEM_PBOARD = req.getParameter("MEM_PBOARD").charAt(0);

				GeneralMemberVO GeneralMemberVO = new GeneralMemberVO();
				GeneralMemberVO.setMEM_NAME(MEM_NAME);
				GeneralMemberVO.setMEM_GEN(MEM_GEN);
				GeneralMemberVO.setMEM_BIRTH(MEM_BIRTH);
				GeneralMemberVO.setMEM_ADDR(MEM_ADDR);
				GeneralMemberVO.setMEM_MAIL(MEM_MAIL);
				GeneralMemberVO.setMEM_PSW(MEM_PSW);
				GeneralMemberVO.setMEM_SELF(MEM_SELF);
				GeneralMemberVO.setMEM_PIC(MEM_PIC);
				GeneralMemberVO.setMEM_COIN(MEM_COIN);
				GeneralMemberVO.setMEM_PHONE(MEM_PHONE);
				GeneralMemberVO.setMEM_STA(MEM_STA);
				GeneralMemberVO.setMEM_PBOARD(MEM_PBOARD);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GeneralMemberVO", GeneralMemberVO); // �t����J�榡���~��GeneralMemberVO����,�]�s�Jreq

					RequestDispatcher failureView = req.getRequestDispatcher("/general_member/g_member.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				GeneralMemberService MemSvc = new GeneralMemberService();
				GeneralMemberVO = MemSvc.addGeneralMember(MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL,
						md5(MEM_PSW), MEM_SELF, MEM_PIC, MEM_COIN, MEM_STA, MEM_PHONE, MEM_PBOARD);

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				MailService mail = new MailService();

				String subject = "���ҫH�q��";

				String messageText = String.valueOf(
						"<a href='http://localhost:8081/" + req.getContextPath() +"/addMemberEmail.do?account="+MEM_MAIL+"'>���Һ��}</a>") + "\n"
						+ "�K�X: "+MEM_PSW;
				
				mail.sendMail(MEM_MAIL, subject, messageText);
				req.setAttribute("msg","�Хh�H�c����");
				String url = "/signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.put("catch", e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/general_member/g_member.jsp");
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

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // ���ե�
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
