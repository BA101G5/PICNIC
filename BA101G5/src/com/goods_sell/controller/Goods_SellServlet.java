package com.goods_sell.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;

public class Goods_SellServlet extends HttpServlet {
	public void DoGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		if ("getOne".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String gsno = new String(req.getParameter("gsno"));

				Goods_SellService goods_sellSvc = new Goods_SellService();
				Goods_SellVO findone = goods_sellSvc.getOne(gsno);

				req.setAttribute("soods_sellVO", findone);
				String url = null;
				if ("getOne".equals(action)) {
					 url="Servelet3/goods_sell/goods_sell.do";
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
