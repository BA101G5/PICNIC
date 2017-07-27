package com.commend.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.commend.model.*;
import com.general_member.model.*;
import com.manufacturers.model.*;
import com.picmem.model.*;
import com.picnic.model.*;

@WebServlet("/commend.do")
public class commendServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String button = req.getParameter("button");
		String mem = (String) session.getAttribute("MEM_NO");
//		if(true){
//			String para = null;
//			Enumeration<String> e = req.getParameterNames();
//	
//			while(e.hasMoreElements()) {
//				para = e.nextElement();
//					if(para != null){
//						System.out.println(para);
//						System.out.println(req.getParameter(para));
//						System.out.println("-----------------");
//					}
//				}
//			}
//		try{
//			for(String ss : req.getParameterValues("mem_com")){
//				System.out.println(ss);
//			}
//			for(String ss : req.getParameterValues("mem_no")){
//				System.out.println(ss);
//			}
//		}catch(NullPointerException e){
//			
//		}
		
		if("commend".equals(button) || "checkCannel".equals(button) || "checkCertain".equals(button)){
			
			ManufacturersService manS = new ManufacturersService();
			GeneralMemberService genS = new GeneralMemberService();
			
			List<GeneralMemberVO> genList = new ArrayList<GeneralMemberVO>();
			List<ManufacturersVO> manList = new ArrayList<ManufacturersVO>();
			
			for(GeneralMemberVO gVO : genS.k_getAll()){
				if(Math.random()*10>5){
					genList.add(gVO);
				}
			}
			for(ManufacturersVO mVO : manS.k_getAll()){
				if(Math.random()*10>5){
					manList.add(mVO);
				}
			}
			
			req.setAttribute("genList", genList);
			req.setAttribute("manList", manList);
			String url = "/view/commend.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if("commendsubmit".equals(button)){
			CommendService comS = new CommendService();
			CommendVO comVO = new CommendVO();
			
			comVO.setComm_cate(1);
			comVO.setComm_grade(1);
			comVO.setComm_date(new Timestamp(System.currentTimeMillis()));
			int i = 0;
			int len = req.getParameterValues("mem_com").length;
			
			String[] mem_com = req.getParameterValues("mem_com");
			String[] mem_no  = req.getParameterValues("mem_no");
			
			while(len>i){
				if(Integer.parseInt(mem_com[i]) > 1){
					comVO.setComm_memno(mem);
					comVO.setComm_be_no(mem_no[i]);
					comVO.setComm_grade(0);
					comS.k_delCommend(mem, mem_no[i]);
					comS.k_addCommend(comVO);
				}else if(Integer.parseInt(mem_com[i]) > 0){
					comVO.setComm_memno(mem);
					comVO.setComm_be_no(mem_no[i]);
					comVO.setComm_grade(1);
					comS.k_delCommend(mem, mem_no[i]);
					comS.k_addCommend(comVO);
				}
				i++;
			}
			
			//---------------------------
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
	}
}
