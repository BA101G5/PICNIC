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

import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;

public class Goods_SellServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session =req.getSession();
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		String contextpath=getServletContext().getRealPath("\\images");
		System.out.println(contextpath);
	
		if ("getOne".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer amount =Integer.getInteger(req.getParameter("amount"));
			
			//String account = req.getParameter("account");
			String account ="M000000001";
			
			try {
				String gsno = new String(req.getParameter("gsno"));

				Goods_SellService goods_sellSvc = new Goods_SellService();
				Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gsno,contextpath);
				

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
	}
}
