package com.orderde_detail.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.orderde_detail.model.Orderde_DetailVO;

public class Orderde_detailServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session =req.getSession();
		String action = req.getParameter("action");
		if(action.equals("")){}
		
		if(action.equals("finishpicnic")){}
		
		if(action.equals("insert")){}
		if (action.equals("insertintocart")) {
			
			String gs_no =req.getParameter("gs_no");
			Goods_SellService goods_sellSvc = new Goods_SellService();
			//Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gs_no);
			//String account=(String) session.getAttribute("account");
			String account="M0000000001";
			
			

			Orderde_DetailService Orderde_detailSvc = new Orderde_DetailService();
			//Orderde_detailSvc.addGsOrderde_Detail(account,goods_sellVO.getGs_no(), amount);
			
			String url =null;
			if(action.equals("insert")){
				 url="/buycart/maothird.jsp";
			}
			javax.servlet.RequestDispatcher SuccessView =req.getRequestDispatcher(url);
			SuccessView.forward(req, res);
		}
	}
}