package com.orderde_detail.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.orderde_detail.model.Orderde_DetailVO;

public class Orderde_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		if (action.equals("insert")) {
			
			Goods_SellVO goods_sellVO= (Goods_SellVO) req.getAttribute("goods_sellVO");
			System.out.println(goods_sellVO.getGs_name());
			System.out.println("Hello");

		}
	}
}