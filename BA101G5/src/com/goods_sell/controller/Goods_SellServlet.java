package com.goods_sell.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.goods_sell.model.*;


public class Goods_SellServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		     
		if ("listEmps_ByDeptno_A".equals(action) || "listEmps_ByDeptno_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				Integer deptno = new Integer(req.getParameter("deptno"));

				
				DeptService deptSvc = new DeptService();
				Set<EmpVO> set = deptSvc.getEmpsByDeptno(deptno);

				
				req.setAttribute("listEmps_ByDeptno", set);    
				String url = null;
				if ("listEmps_ByDeptno_A".equals(action))
					url = "/dept/listEmps_ByDeptno.jsp";        
				else if ("listEmps_ByDeptno_B".equals(action))
					url = "/dept/listAllDept.jsp";              

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("delete_Dept".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				
				Integer deptno = new Integer(req.getParameter("deptno"));
				
				
				DeptService deptSvc = new DeptService();
				deptSvc.deleteDept(deptno);
				
				
				String url = "/dept/listAllDept.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dept/listAllDept.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
