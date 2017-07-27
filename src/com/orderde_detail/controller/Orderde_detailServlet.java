package com.orderde_detail.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general_member.model.GeneralMemberVO;
import com.goods_rent.model.Goods_RentService;
import com.goods_rent.model.Goods_RentVO;
import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.orderde_detail.model.Orderde_DetailVO;
import com.picmem.model.PicmemService;
import com.picnic.model.PicnicService;
import com.picnic.model.PicnicVO;

public class Orderde_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		if (action.equals("get_gr_no")) {

			String picnic_no = req.getParameter("picnic_no").trim();
			try {

				Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
				List<Orderde_DetailVO> list = orderde_detailSvc.getAllPICNICNO(picnic_no);
				System.out.println(list);
				if (!list.isEmpty()) {
					req.setAttribute("picnic_no", picnic_no);
					req.setAttribute("listOrderde_DetailVO", list);
				}

				String url = null;
				if (action.equals("get_gr_no")) {
					url = "/finishorder/finishorder1.jsp";

				}
				RequestDispatcher SuccessView = req.getRequestDispatcher(url);
				SuccessView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (action.equals("insert")) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				GeneralMemberVO gVO = (GeneralMemberVO) session.getAttribute("gVO");
				String account = gVO.getMEM_NO().trim();
				System.out.println(account);

				PicmemService picmemSvc = new PicmemService();
				List<String> list = picmemSvc.findbymem_no(account);

				PicnicService picnicSvc = new PicnicService();
				List<PicnicVO> ListPicnic = picnicSvc.getByPicnic_Nos(list);

				Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
				List<Orderde_DetailVO> listGS = orderde_detailSvc.getGsByMem(account);

				session.setAttribute("ListPicnicVO", ListPicnic);
				session.setAttribute("listGs", listGS);

				String url = null;
				if (action.equals("insert")) {
					url = "/finishorder/finishorder1.jsp";

				}
				RequestDispatcher SuccessView = req.getRequestDispatcher(url);
				SuccessView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (action.equals("insertintocartA") || action.equals("insertintocartB") || action.equals("insertintocartC")) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String picnic_no = req.getParameter("Picnic_no");
			System.out.println(picnic_no + "Picnic_no");
			String gs_no = req.getParameter("gs_no");

			String gr_no = req.getParameter("gr_no");
			System.out.println(gs_no + "GS_no");
			System.out.println(gr_no + "picnic_no");

			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount"));
			} catch (Exception e) {
				errorMsgs.put("amount", "嚙請選蕭J嚙踝蕭嚙確嚙複量");
			}
			System.out.println(amount);
			if (amount < 0) {
				errorMsgs.put("amount", "嚙請選蕭J嚙踝蕭嚙確嚙複量");
			}
			GeneralMemberVO gVO = (GeneralMemberVO) session.getAttribute("gVO");
			String account = gVO.getMEM_NO();

			Orderde_DetailService orderde_detailSvc = null;
			if (action.equals("insertintocartA") || action.equals("insertintocartB")) {
				Goods_SellService goods_sellSvc = new Goods_SellService();
				Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gs_no);

				orderde_detailSvc = new Orderde_DetailService();
				orderde_detailSvc.addGsOrderde_Detail(goods_sellVO, amount, account);
			} else if (action.equals("insertintocartC")) {
				Goods_RentService goods_rentSvc = new Goods_RentService();
				Goods_RentVO goods_rentVO = goods_rentSvc.getOne(gr_no);
				orderde_detailSvc = new Orderde_DetailService();
				orderde_detailSvc.addGrOrderde_Detail(goods_rentVO, amount, account, picnic_no);
			}

			String url = null;
			if (action.equals("insertintocartA")) {
				url = "/buycart/moafirst.jsp";
			} else if (action.equals("insertintocartB")) {
				url = "/buycart/maothird.jsp";
			} else {
				url = "/picnic/maosecondui3.jsp";
			}
			javax.servlet.RequestDispatcher SuccessView = req.getRequestDispatcher(url);
			SuccessView.forward(req, res);
		}

		if (action.equals("delete")) {
			String picnic_no = (String) session.getAttribute("picnic_no");
			String delete = req.getParameter("delete");
			GeneralMemberVO gVO = (GeneralMemberVO) session.getAttribute("gVO");
			String account = gVO.getMEM_NO();

			Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
			orderde_detailSvc.deleteOrderde_Detail(delete);
			List<Orderde_DetailVO> listGr = orderde_detailSvc.getAllPICNICNO(picnic_no);
			List<Orderde_DetailVO> listGS = orderde_detailSvc.getGsByMem(account);
	

			session.setAttribute("listGs", listGS);
			if (!listGr.isEmpty()) {
				session.setAttribute("listGr", listGr);
			}

			String url = null;
			if (action.equals("delete")) {
				url = "/finishorder/finishorder1.jsp";
			}
			res.sendRedirect(req.getContextPath() + url);
		}

		if (action.equals("finishorder")) {
			GeneralMemberVO gVO = (GeneralMemberVO) session.getAttribute("gVO");
			String account = gVO.getMEM_NO();
			String picnic_no = req.getParameter("picnic_no").trim();
			System.out.println(picnic_no);
			String address = req.getParameter("address").trim();
			System.out.println(address);
			Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
			List<Orderde_DetailVO> listGr = orderde_detailSvc.getAllPICNICNO(picnic_no);
			List<Orderde_DetailVO> listGs = ((List<Orderde_DetailVO>) session.getAttribute("listGs"));
			System.out.println(listGr);
			int tlprice=0;
			try {
				System.out.println(tlprice);
				
					for (Orderde_DetailVO orderde_detailVO : listGr) {
						if(orderde_detailVO.getGr_no()!= null){
						Integer preamount = orderde_detailVO.getOd_amount();
						System.out.println(preamount);
						Integer price = orderde_detailVO.getOd_price();
						System.out.println(price);
						price = price / preamount;
						System.out.println(price);
						Integer amount = Integer.valueOf(
								req.getParameter(orderde_detailVO.getOrderde_detailno().toString() + "amount"));
						System.out.println(amount);
						price = amount * price;
						orderde_detailVO.setOd_price(price);
						System.out.println(orderde_detailVO.getOd_price());
						orderde_detailVO.setOd_amount(amount);
						System.out.println(orderde_detailVO.getOd_amount());
						orderde_detailVO.setPicnic_no(picnic_no);
						System.out.println(orderde_detailVO.getPicnic_no());
						orderde_detailVO.setOd_place(address);
						System.out.println(address);
						tlprice=tlprice+price;
						System.out.println(tlprice+"2");
					}
					}
			} catch (Exception e) {
			}
			try {
				if (!listGs.isEmpty()) {
					for (Orderde_DetailVO orderde_detailVO : listGs) {
						Integer preamount = orderde_detailVO.getOd_amount();
						Integer price = orderde_detailVO.getOd_price();
						price = price / preamount;
						Integer amount = Integer.valueOf(
								req.getParameter(orderde_detailVO.getOrderde_detailno().toString() + "amount"));
						price = amount * price;
						orderde_detailVO.setOd_price(price);
						orderde_detailVO.setOd_amount(amount);
						orderde_detailVO.setPicnic_no(picnic_no);
						orderde_detailVO.setOd_place(address);
						tlprice=tlprice+price;
						System.out.println(tlprice+"1");
					}

				}
			} catch (Exception e) {
				System.out.println("沒有預購商品");
			}
			System.out.println(tlprice);
			orderde_detailSvc.updateOrderde_Detail(listGr, listGs);
			PicnicService picnicSvc = new PicnicService();
			picnicSvc.updatepicnic(picnic_no,tlprice);
			String url = null;
			if (action.equals("finishorder")) {
				url = "/finishorder/finishorder2.jsp";

			}
			RequestDispatcher SuccessView = req.getRequestDispatcher(url);
			SuccessView.forward(req, res);

		}
	}
}