package com.orderde_detail.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.orderde_detail.model.Orderde_DetailVO;

public class Orderde_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session =req.getSession();
		String action = req.getParameter("action");
		if (action.equals("insert")) {
			
			String gs_no =req.getParameter("gs_no");

			Goods_SellService goods_sellSvc = new Goods_SellService();
			Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gs_no);
			
			//String account=(String) session.getAttribute("account");
			String account="";

		

		}
	}
}