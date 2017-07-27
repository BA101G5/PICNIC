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
		System.out.println("action : "+action);
		if ("getOne_For_Display".equals(action)) { // select_page.jsp請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			try {
				String str = req.getParameter("AD_NO");
				if (str == null || str.trim().length() == 0) {
					errorMsgs.add("請輸入廠商會員編號");
				}

				if (str.charAt(0) != 'A' && str.charAt(1) != 'D') {
					errorMsgs.add("會員輸入錯誤");
				}
				if (str.length() != 10) {
					errorMsgs.add("會員輸入長度錯誤");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				AdvertisementVO ADVO = new AdvertisementVO();
				ADVO = ADSvc.getOneAdvertisement(str);
				if (ADVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);

					return;
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("ADVO", ADVO);
				String url = "/advertisement/listOneAD.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
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
				/*************************** 1.接收請求參數 ****************************************/

				String AD_NO = new String(req.getParameter("AD_NO"));
			
				/*************************** 2.開始查詢資料 ****************************************/
		
				AdvertisementService ADSvc = new AdvertisementService();
				
				AdvertisementVO ADVO = new AdvertisementVO();
			
				ADVO = ADSvc.getOneAdvertisement(AD_NO);
				
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("ADVO", ADVO); // 資料庫取出的AdvertisementVO物件,存入req
				String url = "/advertisement/update_advertisement_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				System.out.println("---222222");														// update_Advertisement_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("測試 ");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL : " + requestURL);
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
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
					errorMsgs.add("請輸入日期!");
				}

				java.sql.Date DAY_END = null;
				try {
					DAY_END = java.sql.Date.valueOf(req.getParameter("DAY_END").trim());
				} catch (IllegalArgumentException e) {
					DAY_END = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
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
					req.setAttribute("ADVO", ADVO); // 含有輸入格式錯誤的ADVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				System.out.println("12312313");
				AdvertisementService ADSvc = new AdvertisementService();
				ADVO = ADSvc.updateAdvertisement(AD_NO,MF_NO,AD_SELF, AD_PHOTO, DAY_START, DAY_END, AD_CASH, AD_STA);
				System.out.println("99999");
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				
				req.setAttribute("ADVO", ADVO); // 資料庫update成功後,正確的的ADVO物件,存入req

				
				String result = "";
				result = "updateAD";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				
				
				
				//String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("exception");
				System.out.println("31313151");
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String AD_NO = new String(req.getParameter("AD_NO"));

				/*************************** 2.開始刪除資料 ***************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				ADSvc.deleteAdvertisement(AD_NO);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				
//				String result = "";
//				result = "AD_DELETE";
//				req.setAttribute("result",result);
				
				
				
				List<AdvertisementVO> advertisementVO = ADSvc.getAll_U();
				
				req.setAttribute("advertisementVO", advertisementVO);
				
				String AD = "all";
				req.setAttribute("result", AD);
				String url = "/checklist/TEST_HOME.jsp";
				
				
				//String url = "/allAdvertisement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/listAllAD.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_MM".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String MF_NO = req.getParameter("MF_NO").trim();


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				

				/*************************** 2.開始新增資料 ***************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				List<AdvertisementVO> ADVO = new LinkedList<AdvertisementVO>();
				ADVO = ADSvc.getForMM(MF_NO);

				if(ADVO.isEmpty()){
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
					failureView.forward(req, res);

					return;
				}
				
				req.setAttribute("ADVO", ADVO);
				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/advertisement/listForMM.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("546546:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			System.out.println("123132");
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				
//				String AD_NO = req.getParameter("AD_NO").trim();
				String MF_NO = req.getParameter("MF_NO").trim();

				String AD_SELF = req.getParameter("AD_SELF").trim();
				if(AD_SELF .length() == 0){
					errorMsgs.put("AD_SELF","請輸入自我介紹");
				}
				java.sql.Date DAY_START = null;
				try {
					DAY_START = java.sql.Date.valueOf(req.getParameter("DAY_START").trim());
				} catch (IllegalArgumentException e) {
					DAY_START = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("DAY_START","請開始輸入日期!");
				}

				java.sql.Date DAY_END = null;
				try {
					DAY_END = java.sql.Date.valueOf(req.getParameter("DAY_END").trim());
				} catch (IllegalArgumentException e) {
					DAY_END = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.put("DAY_END","請結束輸入日期!");
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
					req.setAttribute("ADVO", ADVO); // 含有輸入格式錯誤的AdvertisementVO物件,也存入req

					RequestDispatcher failureView = req.getRequestDispatcher("/advertisement_buy.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AdvertisementService ADSvc = new AdvertisementService();
				ADVO = ADSvc.addAdvertisement(MF_NO, AD_SELF, AD_PHOTO, DAY_START, DAY_END, AD_CASH, AD_STA);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/personal/personal.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("",e.getMessage());
				System.out.println("546546:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/advertisement_buy.jsp");
				failureView.forward(req, res);
			}
		}
		if("AD".equals(action)){
			System.out.println("進"+action);
			
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
			System.out.println("進"+action);
			
			AdvertisementService ADSvc = new AdvertisementService();
			List<AdvertisementVO> advertisementVO = ADSvc.getAll_Other();
			
			req.setAttribute("advertisementVO", advertisementVO);
			
			String AD = "OTHER";
			req.setAttribute("result", AD);
			String url = "/checklist/TEST_HOME.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("UpdateForSTA".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("測試 ");
			Map<String,Character> errorMsgs = new HashMap<String,Character>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			System.out.println("requestURL : " + requestURL);
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
			
				String AD_NO = req.getParameter("AD_NO").trim();
				
				
				
				Character AD_STA = req.getParameter("AD_STA").charAt(0);
				
				if(AD_STA.equals('U')){
					 AD_STA ='O';
				}else{
					AD_STA ='U';
				}
				

				AdvertisementVO adVO = new AdvertisementVO();
				

				adVO.setAD_NO(AD_NO);
				
				adVO.setAD_STA(AD_STA);
				errorMsgs.put("AD_STA", AD_STA);

				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("advertisementVO", advertisementVO); // 含有輸入格式錯誤的ADVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/advertisement/update_advertisement_input.jsp");
//					failureView.forward(req, res);
//					return; // 程式中斷
//				}
//				AdvertisementService ADSvc = new AdvertisementService();
//				List<AdvertisementVO> advertisementVO = ADSvc.getAll_U();
//				
//				req.setAttribute("advertisementVO", advertisementVO);
//				
//				
//				String AD = "all";
//				req.setAttribute("result", AD);
//				String url = "/checklist/TEST_HOME.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				/*************************** 2.開始修改資料 *****************************************/
				System.out.println("12312313");
				AdvertisementService ADSvc = new AdvertisementService();
				adVO = ADSvc.updateSTA(AD_NO, AD_STA);
				System.out.println("99999");
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				
				List<AdvertisementVO> advertisementVO = ADSvc.getAll_U();
				
				
				req.setAttribute("advertisementVO", advertisementVO); // 資料庫update成功後,正確的的ADVO物件,存入req

				
				String AD = "all";
				req.setAttribute("result", AD);
				String url = "/checklist/TEST_HOME.jsp";
				
				System.out.print(url);
				
				//String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp

				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				System.out.println("exception");
				errorMsgs.put("error",'e');
				RequestDispatcher failureView = req
						.getRequestDispatcher("/checklist/TEST_HOME.jsp");
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
