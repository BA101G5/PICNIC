package com.goods_rent.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import com.goods_rent.model.Goods_RentService;
import com.goods_rent.model.Goods_RentVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Goods_rentServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session =req.getSession();
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
	
		if ("insert".equals(action)) {

			Map<String,String> errorMsgs = new HashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			

			try {
				String MF_NO = req.getParameter("MF_NO").trim();

				String P_NO =req.getParameter("p_no").trim();
				
				String GR_NAME=req.getParameter("gr_name").trim();
				
				if(GR_NAME.equals("")){
					errorMsgs.put("gr_name","*�п�J�ӫ~�W��");
				}
			
				byte[] GR_IMG = null;
				try {
					Part part = req.getPart("gr_img");
					GR_IMG = getPictureByteArrayFromWeb(part);
				} catch (Exception e) {
					e.printStackTrace();
				}
		
				
				java.sql.Timestamp GR_DATE = null;
			    GR_DATE =  new java.sql.Timestamp(System.currentTimeMillis());
			    Integer GR_PRICE = null;
				try {//"7011" "aaa"
					GR_PRICE = new Integer(req.getParameter("gr_price").trim());
				} catch (Exception e) {
					errorMsgs.put("gr_place","*�п�J���T���");
				}
				
				if(GR_PRICE.equals("")){
					errorMsgs.put("gr_price","*�п�J�ӫ~����");
				}
				Integer GR_COUNT = null;
				try {//"7011" "aaa"
					GR_COUNT = new Integer(req.getParameter("gr_count").trim());
				} catch (Exception e) {
					errorMsgs.put("gr_count","*�п�J���T�s�q");
				}
				
				if(GR_COUNT.equals("")){
					errorMsgs.put("gr_count","*�п�J�ӫ~�s�q");
				}
				
				String GR_INFO = req.getParameter("gr_info");
				if(GR_INFO.equals("")){
					errorMsgs.put("gr_info","*�п�J�ӫ~��T");
				}
				
				String GR_STA = req.getParameter("gr_sta").trim();
				
			
				String GR_PLACE=req.getParameter("gr_place").trim();
				if(GR_PLACE.equals("")){
					errorMsgs.put("gr_place","*�п�J�ӫ~�Ҧb�a�I");
				}
					
					String GR = req.getParameter("gr_until").trim();
					String TIME =req.getParameter("time").trim();
					java.sql.Timestamp GR_UNTIL =   java.sql.Timestamp.valueOf(GR+" "+TIME+":00");		
					
					
					
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/good_rent.jsp");
				failureView.forward(req, res);

				return;
			}
				


				Goods_RentService grSvc =  new Goods_RentService();
				Goods_RentVO GRVO = grSvc.addGoods_Rent(MF_NO, P_NO, GR_NAME, GR_DATE, GR_PRICE, GR_COUNT, GR_INFO, GR_IMG, GR_UNTIL, GR_STA, GR_PLACE);
			
				

				session.setAttribute("GRVO", GRVO);
				String url = null;
				
				url = "/personal/personal.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				
				System.out.println("errorrrrrr");
				throw new ServletException(e);
			}
		}
		
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String GR_NO = new String(req.getParameter("gr_no"));
	
				/*************************** 2.�}�l�d�߸�� ****************************************/
				Goods_RentService grSvc =  new Goods_RentService();
				Goods_RentVO GRVO = grSvc.getOne(GR_NO);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("GRVO", GRVO);
				System.out.println(GRVO.getGr_no());
				System.out.println(GRVO.getMf_no());
				System.out.println(GRVO.getP_no());
				System.out.println(GRVO.getGr_name());
				System.out.println(GRVO.getGr_info());
				System.out.println(GRVO.getGr_date());
				System.out.println(GRVO.getGr_price());
				System.out.println(GRVO.getGr_count());
				System.out.println(GRVO.getGr_until());
				System.out.println(GRVO.getGr_img());
				System.out.println(GRVO.getGr_sta());
				// ��Ʈw���X��GeneralMemberVO����,�s�Jreq
				String url = "/good_rent_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				// update_general_member_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				System.out.println("----------------!!!");
				RequestDispatcher failureView = req.getRequestDispatcher("/personal/personal.jsp");
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
System.out.println("1");
				String GR_NO = req.getParameter("gr_no").trim();
				System.out.println("2");
				String MF_NO = req.getParameter("MF_NO").trim();
				System.out.println("3");
				String P_NO = req.getParameter("p_no").trim();
				System.out.println("4");
				String GR_NAME = req.getParameter("gr_name").trim();
				if(GR_NAME.equals("")){
					errorMsgs.put("gr_name","*�п�J�ӫ~�W��");
				}	
				System.out.println("5");

				java.sql.Timestamp GR_DATE = null;
				GR_DATE = new java.sql.Timestamp(System.currentTimeMillis());
			
				 Integer GR_PRICE = null;
					try {//"7011" "aaa"
						GR_PRICE = new Integer(req.getParameter("gr_price").trim());
					} catch (Exception e) {
						errorMsgs.put("gr_place","*�п�J���T���");
					}
					
					if(GR_PRICE.equals("")){
						errorMsgs.put("gr_price","*�п�J�ӫ~����");
					}
					Integer GR_COUNT = null;
					try {//"7011" "aaa"
						GR_COUNT = new Integer(req.getParameter("gr_count").trim());
					} catch (Exception e) {
						errorMsgs.put("gr_count","*�п�J���T�s�q");
					}
					System.out.println("6");
					if(GR_COUNT.equals("")){
						errorMsgs.put("gr_count","*�п�J�ӫ~�s�q");
					}
				String GR_INFO = req.getParameter("gr_info");
				if(GR_INFO.equals("")){
					errorMsgs.put("gr_info","*�п�J�ӫ~��T");
				}
			

				
				System.out.println("7");

				Part part = req.getPart("gr_img");
				byte[] GR_IMG = null;
				try {
					if (getFileNameFromPart(part) != null)
						GR_IMG = getPictureByteArrayFromWeb(part);
					else {
						Goods_RentService grSvc =  new Goods_RentService();
						Goods_RentVO GRVO = grSvc.getOne(GR_NO);
						
						GR_IMG = GRVO.getGr_img();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("8");
				String GR = req.getParameter("gr_until").trim();
				String TIME =req.getParameter("time").trim();
		java.sql.Timestamp GR_UNTIL =null;
				try{
					GR_UNTIL =   java.sql.Timestamp.valueOf(GR+" "+TIME+":00");	
				}catch(Exception e){
					GR_UNTIL = new java.sql.Timestamp(System.currentTimeMillis());
				}
				
			System.out.println(TIME);	
				String GR_STA = req.getParameter("gr_sta").trim();

				
				String GR_PLACE =req.getParameter("gr_place").trim();
				if(GR_PLACE.equals("")){
					errorMsgs.put("gr_place","*�п�J�ӫ~�Ҧb�a�I");
				}
				Goods_RentVO GRVO = new Goods_RentVO();
				GRVO.setGr_no(GR_NO);
				GRVO.setMf_no(MF_NO);
				GRVO.setP_no(P_NO);
				GRVO.setGr_name(GR_NAME);
				
				
				GRVO.setGr_date(GR_DATE);
				GRVO.setGr_price(GR_PRICE);
				GRVO.setGr_count(GR_COUNT);
				GRVO.setGr_info(GR_INFO);
				GRVO.setGr_img(GR_IMG);
				GRVO.setGr_until(GR_UNTIL);
				GRVO.setGr_sta(GR_STA);
				GRVO.setGr_place(GR_PLACE);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GRVO", GRVO); // �t����J�榡���~��MemVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/good_rent_update.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				Goods_RentService grSvc =  new Goods_RentService();
				GRVO = grSvc.updateGoods_Rent(GR_NO, MF_NO, P_NO, GR_NAME, GR_DATE, GR_PRICE, GR_COUNT, GR_INFO, GR_IMG, GR_UNTIL, GR_STA, GR_PLACE);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/

				req.setAttribute("GRVO", GRVO); // ��Ʈwupdate���\��,���T����MemVO����,�s�Jreq
//				HttpSession session1 = req.getSession();
//				session1.removeAttribute("gVO");
//				session1.setAttribute("gVO", MemVO);

				RequestDispatcher successView = req.getRequestDispatcher("/personal/personal.jsp"); // �ק令�\��,���listOneEmp.jsp

				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				System.out.println("---------");
				errorMsgs.put("error", "error");
				RequestDispatcher failureView = req.getRequestDispatcher("/good_rent_update.jsp");
				failureView.forward(req, res);
			}
		}
		
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
