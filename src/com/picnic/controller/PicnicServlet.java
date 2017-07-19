package com.picnic.controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general_member.model.GeneralMemberVO;
import com.goods_rent.model.Goods_RentService;
import com.goods_rent.model.Goods_RentVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.picmem.model.PicmemService;
import com.picnic.model.PicnicService;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

import javax.servlet.ServletException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

public class PicnicServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		if (action.equals("checkbeforeinsert")) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String picnic_name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (picnic_name == null || picnic_name.trim().length() == 0) {
					errorMsgs.put("name", "�����蝛�");
				} else if (!picnic_name.trim().matches(nameReg)) {
					errorMsgs.put("name", "������畾泵��� 銝��2~70摮���");
				}
				String address = null;
				try {
					address = req.getParameter("address").trim();
				} catch (Exception e) {
				}
				String area = req.getParameter("area").trim();

				String addressReg = "^[(\u4e00-\u9fa5)(0-9_)]{6,30}$";
				if (address == null || address.trim().length() == 0) {

					errorMsgs.put("address", "���銝�蝛�");
				} else if (!address.trim().matches(addressReg)) {

					errorMsgs.put("address", "���敹��葉���摮� 銝6~15摮���");
				} else if ((Integer.getInteger(address) != null) || address.substring(1, 2).equals("[^0-9]")) {
					errorMsgs.put("address", "隢撓�甇�蝣箏��");
				}

				Timestamp picnic_date = null;
				String date = null;
				try {
					date = req.getParameter("date");
					String hour = req.getParameter("hour");
					picnic_date = java.sql.Timestamp.valueOf(date + hour);
				} catch (IllegalArgumentException e) {
					errorMsgs.put("date", "隢撓�������");
				}

				Integer picnic_pl = null;
				try {
					picnic_pl = new Integer(req.getParameter("people").trim());
					if (picnic_pl < 0) {
						errorMsgs.put("people", "鈭箸銝撠�");
					}
				} catch (Exception e) {
					errorMsgs.put("people", "隢撓��摮�");
				}

				String tladdress = null;
				System.out.println();

				if (address.contains("市")) {
					tladdress = address;
				} else {
					tladdress = area + address;
				}
				System.out.println("hello");
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
				errorMsgs.put("nodata", "�瘜�����:" + e.getMessage());
				javax.servlet.RequestDispatcher failureView = req.getRequestDispatcher("/picnic/maosecondui.jsp");
				failureView.forward(req, res);
			}
		}

		if (action.equals("insert")) {
		
		
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {/*************** 蝘駁銝������ ***************/
				session.removeAttribute("area");
				session.removeAttribute("address");
				session.removeAttribute("date");
				/******************* ��������� ************************/
				GeneralMemberVO gVO  =(GeneralMemberVO)session.getAttribute("gVO");
				String account = gVO.getMEM_NO();
				String picnic_name = (String) session.getAttribute("picnic_name");

				String tladdress = (String) session.getAttribute("tladdress");

				Timestamp picnic_date = (Timestamp) session.getAttribute("picnic_date");
				
				Integer picnic_pl = (Integer) session.getAttribute("people");

				if (action.equals("insert")) {
					PicnicService picnicSvc = new PicnicService();
					String picnic_no = picnicSvc.addPicnic(picnic_name, picnic_date, picnic_pl);
					PicmemService picmemSvc = new PicmemService();
					picmemSvc.addowner(picnic_no, account);
					PlaceService placeSvc = new PlaceService();
					PlaceVO placeVO = null;
					Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
					
					try {
						placeVO = placeSvc.getOne(tladdress);	
						System.out.println(placeVO+"hello");
						
						if (placeVO.getMf_no() != null) {
							orderde_detailSvc.addPlaceOrderde_Detail(placeVO.getP_price(), placeVO.getP_no(), account,
									picnic_no, tladdress);
							Goods_RentService goods_rentSvc = new Goods_RentService();
							List<Goods_RentVO> list = goods_rentSvc.findbyplace(placeVO.getMf_no(), tladdress);
							System.out.println(list);
							session.setAttribute("picnic_no", picnic_no);
							if (!list.isEmpty()) {
								session.setAttribute("list", list);
							}
						}
					} catch (Exception e) {
						String p_no = placeSvc.insertplace(account, tladdress, picnic_no, picnic_pl);
						Integer P_price = 0;
						orderde_detailSvc.addPlaceOrderde_Detail(P_price, p_no, account, picnic_no, tladdress);
					} finally {
						String url = null;
						System.out.println(action);
						if (action.equals("insert")) {
							url = "/picnic/maosecondui3.jsp";
						}
						javax.servlet.RequestDispatcher SuccessView = req.getRequestDispatcher(url);
						SuccessView.forward(req, res);
					}
				}
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
			}
		}

	}
}