package com.goods_sell.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general_member.model.GeneralMemberVO;
import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;
import com.orderde_detail.model.Orderde_DetailService;

public class Goods_SellServlet extends HttpServlet {
	private static final int String = 0;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		System.out.println("action");

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
				throw new ServletException(e);
			}
		}

		if (action.equals("selectByType")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String type = new String(req.getParameter("type"));

				Goods_SellService goods_sellSvc = new Goods_SellService();
				List<Goods_SellVO> list = goods_sellSvc.findByType(type);

				ManufacturersService manufacturersSvc = new ManufacturersService();
				List<ManufacturersVO> list2 = manufacturersSvc.getAll();
				List<String> list3 = goods_sellSvc.getcountbymf(list2);

				req.setAttribute("typelist", list);
				req.setAttribute("countbymf", list3);
				session.setAttribute("type", type);

				String url = null;
				if (action.equals("selectByType")) {
					url = "/buycart/moafirst.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		if (action.equals("selectByMfype")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String type = (java.lang.String) session.getAttribute("type");
				String mf=(java.lang.String) req.getAttribute("mf");
				
				Goods_SellService goods_sellSvc = new Goods_SellService();
				List<Goods_SellVO> list=goods_sellSvc.finBymf(type,mf);
				
				req.setAttribute("typelist", list);
				String url = null;
				if (action.equals("selectByType")) {
					url = "/buycart/moafirst.jsp";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
