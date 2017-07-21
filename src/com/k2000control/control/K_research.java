package com.k2000control.control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pboard_article.model.Pboard_ArticleService;
import com.pboard_article.model.Pboard_ArticleVO;
import com.picnic.model.PicnicService;
import com.picnic.model.PicnicVO;

@WebServlet("/research.kdo")
public class K_research extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String button = req.getParameter("button");
//			if(true){
//				String para = null;
//				Enumeration<String> e = req.getParameterNames();
//	
//					while(e.hasMoreElements()) {
//						para = e.nextElement();
//						if(para != null){
//							System.out.println(para);
//							System.out.println(req.getParameter(para));
//						}
//					}
//			}
		
		if("liouyan_button".equals(button)){
			
			Pboard_ArticleService paS = new Pboard_ArticleService();
			List<Pboard_ArticleVO> paList = new ArrayList<Pboard_ArticleVO>();
						
			String keyWord = "";
			
			if(req.getParameter("liouyan_name") != ""){
				keyWord = keyWord + " and gen.MEM_NAME = " + addQuotation(req.getParameter("liouyan_name"));
			}
			if(req.getParameter("liouyan_mail") != ""){
				keyWord = keyWord + " and gen.MEM_MAIL = " + addQuotation(req.getParameter("liouyan_mail"));
			}		
			if(req.getParameter("liouyan_title") != ""){
				keyWord = keyWord + " and pbo.ARTICLE_TITLE like " + addQuotation(addPercent(req.getParameter("liouyan_title")));
			}
			if(req.getParameter("liouyan_text") != ""){
				keyWord = keyWord + " and pbo.ARTICLE_TEXT like " + addQuotation(addPercent(req.getParameter("liouyan_text")));
			}
			if(req.getParameter("liouyan_time") != ""){
				if(req.getParameter("liouyan_time").indexOf("~") != -1){
					String beingDate = req.getParameter("liouyan_time").substring(0, req.getParameter("liouyan_time").indexOf("~"));
					String endDate = req.getParameter("liouyan_time").substring(req.getParameter("liouyan_time").indexOf("~")+1);
					try {
						beingDate = dateChange(beingDate);
						endDate = dateChange(endDate);
						keyWord = keyWord + " and ARTICLE_POST between "
								+  addQuotation(beingDate) + " and " + addQuotation(endDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					String beingDate = req.getParameter("liouyan_time");
					try {
						beingDate = dateChange(beingDate);
						keyWord = keyWord + " and ARTICLE_POST between "
								+ addQuotation(beingDate) + " and (select max(ARTICLE_POST)from PBOARD_ARTICLE) ";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}	
			System.out.println(keyWord);
			paList = paS.k_getPboard(keyWord);
			List<String> paNaLi = new ArrayList<String>();
			
			for(Pboard_ArticleVO pVO : paList){
				paNaLi.add(paS.k_getPboardName(pVO.getArticle_no()));
			}
			req.setAttribute("paNaLi",paNaLi);
			req.setAttribute("paList", paList);

			String url = "/view/k_select_pboard_article.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("joutuan_button".equals(button)){

			PicnicService piS = new PicnicService();
			List<PicnicVO> picList = new ArrayList<PicnicVO>();
			String keyWord = "";
			
			if(req.getParameter("joutuan_name") != ""){
				keyWord = keyWord + " and PICNIC_NAME = " + addQuotation(req.getParameter("joutuan_name"));
			}
			if(req.getParameter("joutuan_limit") != ""){
				keyWord = keyWord + "  and PICNIC_PL <= " + (req.getParameter("joutuan_limit"));
			}
			if(req.getParameter("joutuan_date") != ""){
				if(req.getParameter("joutuan_date").indexOf("~") != -1){
					String beingDate = req.getParameter("joutuan_date").substring(0, req.getParameter("joutuan_date").indexOf("~"));
					String endDate = req.getParameter("joutuan_date").substring(req.getParameter("joutuan_date").indexOf("~")+1);
					try {
						beingDate = dateChange(beingDate);
						endDate = dateChange(endDate);
						keyWord = keyWord + " and  PICNIC_STARTUP between "
								+  addQuotation(beingDate) + " and " + addQuotation(endDate);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					String beingDate = req.getParameter("joutuan_date");
					try {
						beingDate = dateChange(beingDate);
						keyWord = keyWord + " and  PICNIC_STARTUP between "
								+ addQuotation(beingDate) + "  and (select max(PICNIC_SETUP)from PICNIC) ";
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}	
			
			System.out.println(keyWord);
			picList = piS.k_getAllSearch(keyWord);
			for(PicnicVO pVO : picList){
				System.out.println(pVO.getPicnic_name());
			}
			
			HttpSession session = req.getSession();
			
			session.setAttribute("keyWord", keyWord);
			session.setAttribute("picniclist", picList);
			String url = "/picmem.do";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);		
		}

	}
	private String addQuotation(String keyWord){
		String quotation = "'";
		return quotation+keyWord+quotation;
	}
	
	private String addPercent(String keyWord){
		String percent = "%";
		return percent+keyWord+percent;
	}
	private String dateChange(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M¤ë -yy");
		SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd");
		
		return (sdf.format(str.parse(date)));
	}
}
