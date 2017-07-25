package com.orderde_detail.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.goods_rent.model.Goods_RentService;
import com.goods_rent.model.Goods_RentVO;
import com.goods_sell.model.Goods_SellService;
import com.goods_sell.model.Goods_SellVO;
import com.orderde_detail.model.Orderde_DetailService;
import com.orderde_detail.model.Orderde_DetailVO;
import com.picmem.model.*;
import com.picnic.model.*;
import com.place.model.PlaceService;
import com.place.model.PlaceVO;

@WebServlet("/history.do")
public class orderDetail extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String button = req.getParameter("button");
		String mem = (String) session.getAttribute("MEM_NO");
		
//		if(true){
//		String para = null;
//		Enumeration<String> e = req.getParameterNames();
//
//		while(e.hasMoreElements()) {
//			para = e.nextElement();
//				if(para != null){
//					System.out.println(para);
//					System.out.println(req.getParameter(para));
//					System.out.println("-----------------");
//				}
//			}
//		}
		
		if("history".equals(button)){
			PicnicService picS =new PicnicService();
			PicmemService picmS = new PicmemService();
			List<PicnicVO> picList = new ArrayList<PicnicVO>();
			
			for(String picN : picmS.k_getMemPicList(mem)){
				picList.add(picS.k_getOne(picN));
			}
			
			req.setAttribute("picList", picList);
			String url = "/view/history.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("his_detail".equals(button)){
			
			Goods_RentService  grS  =new Goods_RentService();
			Goods_SellService gsS = new Goods_SellService();
			PlaceService plaS = new PlaceService();
		
			String contextPath = req.getContextPath();
			String pic_no = req.getParameter("PICNIC_NO");
			
			Set<String> plaList = new LinkedHashSet<String>();
			Set<String> gsList  = new LinkedHashSet<String>();
			Set<String> grList  = new LinkedHashSet<String>();

			List<PlaceVO> 	   plaVOList = new ArrayList<PlaceVO>();
			List<Goods_RentVO> grVOList  = new ArrayList<Goods_RentVO>();
			List<Goods_SellVO> gsVOList  = new ArrayList<Goods_SellVO>();
			
			List<Integer> grN = new ArrayList<Integer>();
			List<Integer> gsN = new ArrayList<Integer>();
			
			Orderde_DetailService ordS = new Orderde_DetailService();
			for(Orderde_DetailVO ordVO : ordS.k_getAllByPicnic(pic_no)){
				if(isNull(ordVO.getP_no())){
					plaList.add(ordVO.getP_no());
				}
				if(isNull(ordVO.getGs_no())){
					gsList.add(ordVO.getGs_no());
				}
				if(isNull(ordVO.getGr_no())){
					grList.add(ordVO.getGr_no());
				}
			}
			
			for(String s : plaList){
				plaVOList.add(plaS.k_getOne(s));
			}
			for(String s : gsList){
				gsVOList.add(gsS.k_getOne(s));
				gsN.add(ordS.k_gsGoodNumber(s, pic_no).getOd_amount());
			}
			for(String s : grList){
				grVOList.add(grS.k_getOne(s));
				grN.add(ordS.k_grGoodNumber(s, pic_no).getOd_amount());
			}
			
			req.setAttribute("grN", grN);
			req.setAttribute("gsN", gsN);
			req.setAttribute("plaVOList", plaVOList);
			req.setAttribute("gsVOList", gsVOList);
			req.setAttribute("grVOList", grVOList);
			String url = "/view/history.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}	
	}
	
	private Boolean isNull(String isNull){
		
		if(isNull == null){
			return false;
		}else{
			return true;
		}
	}
	
	
}
