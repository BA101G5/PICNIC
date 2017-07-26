 package com.goods_sell.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.general_member.model.GeneralMemberVO;
import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;
import com.orderde_detail.model.Orderde_DetailService;

//import com.orderde_detail.model.Orderde_DetailService;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class Goods_SellServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		System.out.println(action);

		if (action.equals("getOne")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			GeneralMemberVO gVO = (GeneralMemberVO) session.getAttribute("gVO");
			String account = gVO.getMEM_NO();

			try {
				String gsno = new String(req.getParameter("gsno"));

				Goods_SellService goods_sellSvc = new Goods_SellService();
				Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gsno);

				session.setAttribute("goods_sellVO", goods_sellVO);
				String url = null;
				if ("getOne".equals(action)) {
					url = "/buycart/maothird.jsp";
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
					errorMsgs.put("gs_name","*請輸入商品名稱");
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
					errorMsgs.put("gs_price","*請輸入商品價格");
				}
System.out.println(GS_DATE);				
				String GS_INFO = req.getParameter("gs_info");
				if(GS_INFO.equals("")){
					errorMsgs.put("gs_info","*請輸入商品資訊");
				}
System.out.println(GS_INFO);				
				String GS_STA = req.getParameter("gs_sta").trim();
				
System.out.println(GS_STA);				
String GS_TYPE = req.getParameter("gs_type").trim();
System.out.println(GS_TYPE);			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/good_buy.jsp");
				failureView.forward(req, res);

				return;
			}
				


				Goods_SellService gsSvc =  new Goods_SellService();
				Goods_SellVO GSVO = gsSvc.addGoods_Sell(MF_NO, GS_NAME, GS_DATE, GS_PRICE, GS_INFO, GS_IMG, GS_STA, GS_TYPE);
			
				

				session.setAttribute("GSVO", GSVO);
				String url = null;
				
				url = "/personal/personal.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				
				System.out.println("errorrrrrr");
				throw new ServletException(e);
			}
		}

		if (action.equals("selectByType")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String type = new String(req.getParameter("type"));

				ManufacturersService manufacturerSVC = new ManufacturersService();
				List<ManufacturersVO> list = manufacturerSVC.getAll();

				Goods_SellService goods_sellSvc = new Goods_SellService();
				Set<String> set = goods_sellSvc.findByTypeandList(type, list);

				List<Goods_SellVO> goodslist = goods_sellSvc.findByType(type);

				session.setAttribute("type", type);
				req.setAttribute("list", goodslist);
				if (!set.isEmpty()) {
					session.setAttribute("typeSet", set);

					String url = null;
					if (action.equals("selectByType")) {
						url = "/buycart/moafirst.jsp";
					}
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (action.equals("selectgoods_sell")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			GeneralMemberVO gVO = (GeneralMemberVO) session.getAttribute("gVO");
			String account = gVO.getMEM_NO();

			try {
				Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
				orderde_detailSvc.getNumberByGsNo();
				Goods_SellService goods_sellSvc = new Goods_SellService();
				List<Goods_SellVO> list = goods_sellSvc.getAll();

				String url = null;
				if (action.equals("selectgoods_sell")) {
					url = "/buycart/maosecond2.jsp";
				}

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				System.out.println("errorrrrrr");
				throw new ServletException(e);
			}
		}
		if (action.equals("selectByMfype")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String type = new String((String) session.getAttribute("type"));
				
				String Mf_name = req.getParameter("mfcount");
				System.out.println(Mf_name);
				Mf_name = Mf_name.split("\\(")[0];

				ManufacturersService manufacturersScv = new ManufacturersService();
				String Mf_no = manufacturersScv.findByMfname(Mf_name);

				Goods_SellService goods_sellSvc = new Goods_SellService();
				List<Goods_SellVO> list = goods_sellSvc.findByMfType(type, Mf_no);
				System.out.println(list);
				System.out.println("Hello");

				req.setAttribute("list", list);

				String url = null;
				if (action.equals("selectByMfype")) {
					url = "/buycart/moafirst.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String GS_NO = new String(req.getParameter("GS_NO"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				Goods_SellService gsSvc =  new Goods_SellService();
				Goods_SellVO GSVO = gsSvc.getOne(GS_NO);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("GSVO", GSVO);

				// 資料庫取出的GeneralMemberVO物件,存入req
				String url = "/good_update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				// update_general_member_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				System.out.println("----------------");
				RequestDispatcher failureView = req.getRequestDispatcher("/personal/personal.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new HashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String GS_NO = req.getParameter("gs_no").trim();
				String MF_NO = req.getParameter("MF_NO").trim();
				
				String GS_NAME = req.getParameter("gs_name").trim();
				if(GS_NAME.equals("")){
					errorMsgs.put("gs_name","不可空白");
				}
				

				java.sql.Timestamp GS_DATE = null;
			    GS_DATE =  new java.sql.Timestamp(System.currentTimeMillis());
				

				String GS_INFO = req.getParameter("gs_info").trim();

				if(GS_INFO.equals("")){
					errorMsgs.put("gs_info","不可空白");
				}

				Integer GS_PRICE = null;
				
				try {//"7011" "aaa"
					GS_PRICE = new Integer(req.getParameter("gs_price").trim());
				} catch (Exception e) {
					errorMsgs.put("gs_price","請輸入正確的價格");
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

				
			String GS_STA = req.getParameter("gs_sta").trim();

			System.out.println(GS_STA);
			String GS_TYPE = req.getParameter("gs_type").trim();
				Goods_SellVO GSVO = new Goods_SellVO();
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("GSVO", GSVO); // 含有輸入格式錯誤的MemVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/good_update.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
Goods_SellService gSvc = new Goods_SellService();
				GSVO = gSvc.updateGoods_Sell(GS_NO, MF_NO, GS_NAME, GS_DATE, GS_PRICE, GS_INFO, GS_IMG, GS_STA, GS_TYPE);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/

				req.setAttribute("GSVO", GSVO); // 資料庫update成功後,正確的的MemVO物件,存入req
//				HttpSession session1 = req.getSession();
//				session1.removeAttribute("gVO");
//				session1.setAttribute("gVO", MemVO);

				RequestDispatcher successView = req.getRequestDispatcher("/personal/personal.jsp"); // 修改成功後,轉交listOneEmp.jsp

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
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
		// System.out.println("header=" + header); // 嚙踝蕭掍嚙�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 嚙踝蕭掍嚙�
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