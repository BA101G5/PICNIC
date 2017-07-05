package com.picnic.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.picmem.model.PicmemService;
import com.picnic.model.PicnicService;
import com.place.model.PlaceService;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.LinkedHashMap;

public class PicnicServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if (action.equals("checkbeforeinsert")) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String picnic_name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (picnic_name == null || picnic_name.trim().length() == 0) {
					errorMsgs.put("name", "團名不能為空");
				} else if (!picnic_name.trim().matches(nameReg)) {
					errorMsgs.put("name",
							"團名不能為特殊符號 且必須在2~70字之間");
				}
				String address = req.getParameter("address");
				String area = req.getParameter("area");
				String tladdress = area + address;
				String addressReg = "^[(\u4e00-\u9fa5)(0-9_)]{6,15}$";
				if (address == null || address.trim().length() == 0) {
					errorMsgs.put("address", "地址不能為空");
				} else if (!address.trim().matches(addressReg)) {
					errorMsgs.put("address",
							"地址必須文中文和數字 且在6~15字之間");
				}

				Timestamp picnic_date = null;
				String date = null;
				try {
					date = req.getParameter("date");
					String hour = req.getParameter("hour");
					picnic_date = java.sql.Timestamp.valueOf(date + hour);
				} catch (IllegalArgumentException e) {
					errorMsgs.put("date", "請輸入日期資料");
				}
		
				
				Integer picnic_pl = null;
				try {
					picnic_pl = new Integer(req.getParameter("people").trim());
					if(picnic_pl<0){errorMsgs.put("people", "人數不能小於零");}
				} catch (Exception e) {
					errorMsgs.put("people", "請輸入數字");
				}
				session.setAttribute("picnic_name", picnic_name);
				session.setAttribute("area", area);
				session.setAttribute("tladdress", tladdress);
				session.setAttribute("address", address);
				session.setAttribute("date", date);
				session.setAttribute("picnic_date", picnic_date);
				session.setAttribute("people", picnic_pl);

				String url = null;
				if (!errorMsgs.isEmpty()) {
					url = "/picnic/maosecondui.jsp";
				} else if (action.equals("checkbeforeinsert")) {
					url = "/picnic/maosecondui2.jsp";
				}
				javax.servlet.RequestDispatcher successView = req.getRequestDispatcher(url);

				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorMsgs.put("nodata", "無法取得資料:" + e.getMessage());
				javax.servlet.RequestDispatcher failureView = req.getRequestDispatcher("/picnic/maosecondui.jsp");
				failureView.forward(req, res);
			}
		}

		if (action.equals("insert")) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {/***************移除不必要的資訊***************/
				session.removeAttribute("area");
				session.removeAttribute("address");
				session.removeAttribute("date");
				/*******************取得必要的資訊************************/
				//String account=(String)session.getAttribute("accouent");
				String account="MG00000002";
				String picnic_name=(String) session.getAttribute("picnic_name");
				String tladdress =(String) session.getAttribute("tladdress");
				Timestamp picnic_date = (Timestamp) session.getAttribute("picnic_date");
				Integer picnic_pl = (Integer) session.getAttribute("people");


				if (action.equals("insert")) {
					 PicnicService picnicSvc = new PicnicService();
					 String picnic_no= picnicSvc.addPicnic(picnic_name, picnic_date, picnic_pl);
					 PicmemService picmemSvc = new PicmemService();
					 picmemSvc.addowner(picnic_no, account);
					 PlaceService placeSvc =new PlaceService();
					
				}

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());

			}

		}
	}
}
