package com.orderde_detail.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

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
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		if (action.equals("insert")) {
		}
		if (action.equals("insertintocartA") || action.equals("insertintocartB")) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String gs_no = req.getParameter("gs_no");
			System.out.println("gs_no= "+gs_no);
			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount"));
				System.out.println("amount ="+amount);
			} catch (Exception e) {
				errorMsgs.put("amount", "請輸入正確數量");
			}
            
			
			
			if(amount !=null){
			Goods_SellService goods_sellSvc = new Goods_SellService();
			Goods_SellVO goods_sellVO = goods_sellSvc.getOne(gs_no);
			// String account=(String) session.getAttribute("account");
			String account = "MG00000001";

			Orderde_DetailService Orderde_detailSvc = new Orderde_DetailService();
			Orderde_detailSvc.addGsOrderde_Detail(goods_sellVO, amount, account);
			}
			
			
			String url = null;
			if (action.equals("insertintocartA")) {
				url = "/buycart/maosecond.jsp";
			} else {
				url = "/buycart/maothird.jsp";
			}
			javax.servlet.RequestDispatcher SuccessView = req.getRequestDispatcher(url);
			SuccessView.forward(req, res);
		}
	}
}