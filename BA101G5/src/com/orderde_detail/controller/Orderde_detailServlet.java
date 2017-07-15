package com.orderde_detail.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goods_rent.model.Goods_RentService;
import com.goods_rent.model.Goods_RentVO;
import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.orderde_detail.model.Orderde_DetailVO;
import com.picmem.model.PicmemService;
import com.picnic.model.PicnicService;
import com.picnic.model.PicnicVO;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

public class Orderde_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
		System.out.println(action);
		if (action.equals("insert")) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// String account = (String)session.getAttribute("account");
			String account = "MG00000002";
			PicmemService picmemSvc = new PicmemService();
			List<String> list = picmemSvc.findbymem_no(account);

			PicnicService picnicSvc = new PicnicService();
			List<PicnicVO> list2 = picnicSvc.getByPicnic_Nos(list);

			Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
			// orderde_detailSvc.getby

			System.out.println(list2);
			session.setAttribute("ListPicnicVO", list2);

			String url = null;
			if (action.equals("insert")) {
				url = "/finishorder/finishorder1.jsp";

			}
			RequestDispatcher SuccessView = req.getRequestDispatcher(url);
			SuccessView.forward(req, res);
		}

		if (action.equals("get_gr_no")) {
			String picnic_no = req.getParameter("picnic");
			System.out.println(picnic_no + "picnic");
			Orderde_DetailService orderde_detailSvc = new Orderde_DetailService();
			List<Orderde_DetailVO> list = orderde_detailSvc.getAllPICNICNO(picnic_no);
			System.out.println(list);
			if (!list.isEmpty()) {
				req.setAttribute("listOrderde_DetailVO", list);
			}
			

			String url = null;
			if (action.equals("get_gr_no")) {
				url = "/finishorder/finishorder1.jsp";

			}
			RequestDispatcher SuccessView = req.getRequestDispatcher(url);
			SuccessView.forward(req, res);
		}

		if (action.equals("insertintocartA") || action.equals("insertintocartB") || action.equals("insertintocartC")) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String picnic_no = req.getParameter("Picnic_no");
			String gs_no = req.getParameter("gs_no");
			String gr_no = req.getParameter("gr_no");
			System.out.println(picnic_no + "picnic_no");

			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount"));
			} catch (Exception e) {
				errorMsgs.put("amount", "�п�J���T�ƶq");
			}
			if (amount < 0) {
				errorMsgs.put("amount", "�п�J���T�ƶq");
			}
			// String account=(String) session.getAttribute("account");
			String account = "MG00000001";

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
				url = "/buycart/maosecond.jsp";
			} else if (action.equals("insertintocartB")) {
				url = "/buycart/maothird.jsp";
			} else {
				url = "/picnic/maosecondui3.jsp";
			}
			javax.servlet.RequestDispatcher SuccessView = req.getRequestDispatcher(url);
			SuccessView.forward(req, res);
		}
	}
}