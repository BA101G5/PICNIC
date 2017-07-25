package com.k2000control.control;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.administrator.model.*;
import com.manufacturers.model.*;
import com.picnic.model.PicnicService;
import com.picnic.model.PicnicVO;
import com.purview.model.PurviewDAO;
import com.purview.model.PurviewVO;
import com.purview_detail.model.*;
@WebServlet("/adm.do")
public class Test_Control extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String button = req.getParameter("button");
		String no = req.getParameter("admno");
		if ("get_adm_one_data".equals(button)){
			
			AdministratorDAO admDAO = new AdministratorDAO();
			List<AdministratorVO> admVO = admDAO.getAll();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				String url = "/view/admView.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}

		if("man_men".equals(button)){
			
			ManufacturersService maS = new ManufacturersService();
			List<ManufacturersVO> maList = maS.k_getAll();
			
			req.setAttribute("maList", maList);
			String url = "/view/man_mem_data.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
		//¥[¹Î/°h¹Î
		if("enterOrQuit".equals(button)){
			HttpSession session = req.getSession();
			session.setAttribute("MEM_NO", req.getParameter("MEM_NO"));
			
		
			req.setAttribute("button", button);
			String url = "/picmem.do";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
			return;
		}
		
		if("history".equals(button)){
			HttpSession session = req.getSession();
			session.setAttribute("MEM_NO", req.getParameter("MEM_NO"));
			req.setAttribute("button", button);
			String url = "/history.do";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
			return;
		}
		
		if("commend".equals(button)){
			HttpSession session = req.getSession();
			session.setAttribute("MEM_NO", req.getParameter("MEM_NO"));
			req.setAttribute("button", button);
			
			String url = "/commend.do";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
			return;
		}
		
		if("coin".equals(button)){
			HttpSession session = req.getSession();
			session.setAttribute("MEM_NO", req.getParameter("MEM_NO"));
			
			String url = "/view/coin.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
			return;
		}
	}
}