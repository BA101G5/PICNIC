package com.picmem.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.announcement.model.AnnouncementService;
import com.picmem.model.PicmemService;
import com.picmem.model.PicmemVO;
import com.picnic.model.PicnicService;
import com.picnic.model.PicnicVO;

@WebServlet("/picmem.do")
public class picmem extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String button = req.getParameter("button");
		String pic = req.getParameter("PICNIC_NO");
		String mem = (String) session.getAttribute("MEM_NO");
		System.out.println(pic);
		
		if("memAddThisPG".equals(button)){
//			System.out.println("C: picmem / " + "L37");
			String picnic_no = (String) req.getParameter("picnic_no").trim();
			String mem_no = (String) req.getParameter("mem_no").trim();
			
			
			PicmemVO picmemVO = new PicmemVO();

			picmemVO.setMem_no(mem_no);
			picmemVO.setPicnic_no(picnic_no);
//			picmemVO.setPicmem_iden("團員");
//			picmemVO.setPicmem_sta("A");
//			picmemVO.setMem_longi(121.13);
//			picmemVO.setMem_latit(24.57);
			/***************************2.開始新增資料***************************************/
			PicmemService picmemSvc = new PicmemService();
			picmemSvc.addPicmem(picnic_no, mem_no);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/picnicpersionpage/personalpicnic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}

		if("memExitThisPG".equals(button)){
			String picnic_no = (String) req.getParameter("picnic_no").trim();
			String mem_no = (String) req.getParameter("mem_no").trim();
			
			PicmemService picmemSvc = new PicmemService();
			picmemSvc.k_deletePicmem(picnic_no, mem_no);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/picnicpersionpage/personalpicnic.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
		
		
		
//		if(true){
//			String para = null;
//			Enumeration<String> e = req.getParameterNames();
//	
//			while(e.hasMoreElements()) {
//				para = e.nextElement();
//				if(para != null){
//					System.out.println(para);
//					System.out.println(req.getParameter(para));
//					System.out.println("-----------------");
//				}
//			}
//		}
		if("joutuan_button".equals(button)){
			
			String keyWord = (String) session.getAttribute("keyWord");
			List<PicnicVO> picList = (List<PicnicVO>) session.getAttribute("picniclist");
			if(mem == null){
				req.setAttribute("isNoGm", "yes");
			}
			req.setAttribute("isForSearch", "isForSearch");
			req.setAttribute("allList", getAllandMEM(mem,keyWord));
			req.setAttribute("picniclist", picList);
			String url = "/view/picmem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
			if("加入isForSearch".equals(button)){
			
			PicmemService picmem = new PicmemService();
			PicmemVO picmemVO = new PicmemVO();

			picmemVO.setMem_no(mem);
			picmemVO.setPicnic_no(pic);
			picmemVO.setPicmem_iden("團員");
			picmemVO.setPicmem_sta("U");
			picmemVO.setMem_longi(0.0);
			picmemVO.setMem_latit(0.0);
			picmem.k_addPicmem(picmemVO);
			
			String keyWord = (String) session.getAttribute("keyWord");
			List<PicnicVO> picList = (List<PicnicVO>) session.getAttribute("picniclist");
			if(mem == null){
				req.setAttribute("isNoGm", "yes");
			}
			req.setAttribute("isForSearch", "isForSearch");
			req.setAttribute("allList", getAllandMEM(mem,keyWord));
			req.setAttribute("picniclist", picList);
			String url = "/view/picmem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);		
		}
		if("退出isForSearch".equals(button)){
			
			PicmemService picmem = new PicmemService();
			String keyWord = (String) session.getAttribute("keyWord");
			List<PicnicVO> picList = (List<PicnicVO>) session.getAttribute("picniclist");
			int piclistNumber = 0;
			int piclistNumber2 = 0;
			System.out.println(picmem.k_getOne(pic, mem).getPicmem_iden());
			if("團主".equals(picmem.k_getOne(pic, mem).getPicmem_iden())){
				picmem.k_deleteAllPicmem(pic);
				PicnicService pcS = new PicnicService();
				pcS.k_deletePicnic(pic);
				for(PicnicVO pvo:picList){
					if(pvo.getPicnic_no().equals(pic)){
						piclistNumber2 = piclistNumber;
					}
					piclistNumber++;
				}
				
				picList.remove(piclistNumber2);
			}else{
				picmem.k_deletePicmem(pic,mem);
			}
	
			if(mem == null){
				req.setAttribute("isNoGm", "yes");
			}
			req.setAttribute("isForSearch", "isForSearch");
			req.setAttribute("allList", getAllandMEM(mem,keyWord));
			req.setAttribute("picniclist", picList);
			String url = "/view/picmem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);		
		}
		//--------------------------
		if("enterOrQuit".equals(button)){

			req.setAttribute("allList", getAllandMEM(mem,""));
			req.setAttribute("picniclist", getPicnicList());
			String url = "/view/picmem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
		
		if("加入".equals(button)){
			
			PicmemService picmem = new PicmemService();
			PicmemVO picmemVO = new PicmemVO();

			picmemVO.setMem_no(mem);
			picmemVO.setPicnic_no(pic);
			picmemVO.setPicmem_iden("團員");
			picmemVO.setPicmem_sta("U");
			picmemVO.setMem_longi(0.0);
			picmemVO.setMem_latit(0.0);
			picmem.k_addPicmem(picmemVO);
			
			req.setAttribute("allList", getAllandMEM(mem,""));
			req.setAttribute("picniclist", getPicnicList());
			String url = "/view/picmem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
		if("退出".equals(button)){
			
			PicmemService picmem = new PicmemService();
			picmem.k_deletePicmem(pic,mem);
			
			req.setAttribute("allList", getAllandMEM(mem,""));
			req.setAttribute("picniclist", getPicnicList());
			String url = "/view/picmem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);	
		}
		
	}
	
	private List<PicnicVO> getPicnicList(){
		PicnicService picnic = new PicnicService();
		List<PicnicVO> picniclist = picnic.k_getAll();
		return picniclist;
	}
	private List<String> getAllandMEM(String MEM_NO,String searchWord){
		PicnicService picnic = new PicnicService();
		return picnic.k_getAllandMEM(MEM_NO,searchWord);
	}
	
}
