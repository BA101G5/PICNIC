package com.goods_sell.controller;

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.general_member.model.GeneralMemberService;

import com.general_member.model.GeneralMemberVO;
import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
//import com.orderde_detail.model.Orderde_DetailService;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Goods_SellServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session =req.getSession();
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
	
		if (action.equals("getOne")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			GeneralMemberVO gVO  =(GeneralMemberVO)session.getAttribute("gVO");
			String account = gVO.getMEM_NO();
			
			try {
				String gsno = new String(req.getParameter("gsno"));

				Goods_SellService goods_sellSvc = new Goods_SellService();
				Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gsno);
				

				session.setAttribute("goods_sellVO", goods_sellVO);
				String url = null;
				if ("getOne".equals(action)) {
					 url="/buycart/maothird.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("insert".equals(action)) {

			Map<String,String> errorMsgs = new HashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			

			try {
				String MF_NO = req.getParameter("MF_NO").trim();
System.out.println(MF_NO);
				String GS_NAME =req.getParameter("gs_name").trim();
				if(GS_NAME.equals("")){
					errorMsgs.put("gs_name","*�п�J�ӫ~�W��");
				}
System.out.println(GS_NAME);				
				byte[] GS_IMG = null;
				try {
					Part part = req.getPart("gs_img");
					GS_IMG = getPictureByteArrayFromWeb(part);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				java.sql.Timestamp GS_DATE = null;
			    GS_DATE =  new java.sql.Timestamp(System.currentTimeMillis());
				
				Integer GS_PRICE = Integer.parseInt(req.getParameter("gs_price").trim());
				if(GS_PRICE.equals("")){
					errorMsgs.put("gs_price","*�п�J�ӫ~���");
				}
System.out.println(GS_DATE);				
				String GS_INFO = req.getParameter("gs_info");
				if(GS_INFO.equals("")){
					errorMsgs.put("gs_info","*�п�J�ӫ~��T");
				}
System.out.println(GS_INFO);				
				Character GS_STA = req.getParameter("gs_sta").trim().charAt(0);
				
System.out.println(GS_STA);				
				
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/good_buy.jsp");
				failureView.forward(req, res);

				return;
			}
				


				Goods_SellService gsSvc =  new Goods_SellService();
				Goods_SellVO GSVO = gsSvc.addGoods_Sell(MF_NO, GS_NAME, GS_DATE, GS_PRICE, GS_INFO, GS_IMG, GS_STA);
			
				

				session.setAttribute("GSVO", GSVO);
				String url = null;
				
				url = "/personal/personal.jsp";
				
=======
		
		if (action.equals("selectgoods_sell")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			GeneralMemberVO gVO  =(GeneralMemberVO)session.getAttribute("gVO");
			String account = gVO.getMEM_NO();

			try {
				Orderde_DetailService orderde_detailSvc =new Orderde_DetailService();
				orderde_detailSvc.getNumberByGsNo();
				Goods_SellService goods_sellSvc = new Goods_SellService();
				List<Goods_SellVO> list=goods_sellSvc.getAll();
				
				
				
				String url = null;
				if (action.equals("selectgoods_sell")) {
					 url="/buycart/maosecond2.jsp";
				}

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
				String GS_NO = new String(req.getParameter("GS_NO"));
				
				/*************************** 2.�}�l�d�߸�� ****************************************/
				Goods_SellService gsSvc =  new Goods_SellService();
				Goods_SellVO GSVO = gsSvc.getOne(GS_NO);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("GSVO", GSVO);

				// ��Ʈw��X��GeneralMemberVO����,�s�Jreq
				String url = "/good_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
				// update_general_member_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺��~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k��o�n�ק諸���:" + e.getMessage());
				System.out.println("----------------");
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
				 * 1.�����ШD�Ѽ� - ��J�榡����~�B�z
				 **********************/
				String GS_NO = req.getParameter("gs_no").trim();
				String MF_NO = req.getParameter("MF_NO").trim();
				
				String GS_NAME = req.getParameter("gs_name").trim();
				if(GS_NAME.equals("")){
					errorMsgs.put("gs_name","���i�ť�");
				}
				

				java.sql.Timestamp GS_DATE = null;
			    GS_DATE =  new java.sql.Timestamp(System.currentTimeMillis());
				

				String GS_INFO = req.getParameter("gs_info").trim();

				if(GS_INFO.equals("")){
					errorMsgs.put("gs_info","���i�ť�");
				}

				Integer GS_PRICE = null;
				
				try {//"7011" "aaa"
					GS_PRICE = new Integer(req.getParameter("gs_price").trim());
				} catch (Exception e) {
					errorMsgs.put("gs_price","�п�J���T�����");
				}
				Part part = req.getPart("gs_img");
				byte[] GS_IMG = null;
				try {
					if (getFileNameFromPart(part) != null)
						GS_IMG = getPictureByteArrayFromWeb(part);
					else {
						Goods_SellService g = new Goods_SellService();
						Goods_SellVO gVO = g.getOne(GS_NO);
						GS_IMG = gVO.getGs_img();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				Character GS_STA = req.getParameter("gs_sta").trim().charAt(0);

			System.out.println(GS_STA);
				Goods_SellVO GSVO = new Goods_SellVO();
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GSVO", GSVO); // �t����J�榡��~��MemVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/good_update.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
Goods_SellService gSvc = new Goods_SellService();
				GSVO = gSvc.updateGoods_Sell(GS_NO, MF_NO, GS_NAME, GS_DATE, GS_PRICE, GS_INFO, GS_IMG, GS_STA);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/

				req.setAttribute("GSVO", GSVO); // ��Ʈwupdate���\��,���T����MemVO����,�s�Jreq
//				HttpSession session1 = req.getSession();
//				session1.removeAttribute("gVO");
//				session1.setAttribute("gVO", MemVO);

				RequestDispatcher successView = req.getRequestDispatcher("/personal/personal.jsp"); // �ק令�\��,���listOneEmp.jsp

				successView.forward(req, res);

				/*************************** ��L�i�઺��~�B�z *************************************/
			} catch (Exception e) {
System.out.println("--------------");
				errorMsgs.put("error", "error");
				RequestDispatcher failureView = req.getRequestDispatcher("/good_update.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // ��ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // ��ե�
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

				throw new ServletException(e);
			}
		}

	}
}
