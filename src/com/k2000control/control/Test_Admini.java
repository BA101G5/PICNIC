package com.k2000control.control;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.administrator.model.AdministratorService;
import com.administrator.model.AdministratorVO;
import com.purview_detail.model.Purview_DetailSerice;
import com.purview_detail.model.Purview_DetailVO;
@WebServlet("/k_admini.do")
public class Test_Admini extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String button = req.getParameter("button");
		
		/*if(true){
			String para = null;
			Enumeration<String> e = req.getParameterNames();

			while(e.hasMoreElements()) {
				para = e.nextElement();
				if(para != null){
					System.out.println(para);
					//System.out.println(req.getParameter(para));
				}
			}
		}*/
		
	
		if ("del".equals(button)){
			
			AdministratorService admDAO = new AdministratorService();
			String admNO = req.getParameter("Administrator");
			admDAO.delAdministrator(admNO);
			
			List<AdministratorVO> admVO = admDAO.get_N_Administrator();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				String result = "";
				result = "del";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}	
		
		if ("add".equals(button)){
			
			List<AdministratorVO> admVO = getSearch();
			List<AdministratorVO> add_adm = new ArrayList<AdministratorVO>();
			
			String[] admValue = req.getParameterValues("getAdm_acc");
			try{
				int admValueLen = admValue.length;
				for(int i=0; i<admValueLen; i++){
					AdministratorVO addAdmVO = new AdministratorVO();
					addAdmVO.setAdm_acc(req.getParameterValues("getAdm_acc")[i]);
					addAdmVO.setAdm_pw(req.getParameterValues("getAdm_pw")[i]);
					addAdmVO.setAdm_iden(req.getParameterValues("getAdm_iden")[i]);
					addAdmVO.setAdm_name(req.getParameterValues("getAdm_name")[i]);
					add_adm.add(addAdmVO);
				}
			}catch(NullPointerException e){
				System.out.println("first");
			}finally{
				AdministratorVO addAdmVO = new AdministratorVO();
				add_adm.add(addAdmVO);
			}
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				req.setAttribute("add_adm",add_adm);
				String result = "";
				result = "add_adm";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}	
		
		if (button.contains("addinin")){
			
			List<AdministratorVO> admVO = getSearch();
			List<AdministratorVO> add_adm = new ArrayList<AdministratorVO>();
		
			int deleIndex = Integer.parseInt(button.replace("addinin",""));
			int i = 0;
	
			String[] admValue = req.getParameterValues("getAdm_acc");
			try{
				int admValueLen = admValue.length;
				if(admValueLen != 1){
					if(deleIndex == 0){
						i=1;
						while( i < admValueLen ){
							AdministratorVO addAdmVO = new AdministratorVO();
							addAdmVO.setAdm_acc(req.getParameterValues("getAdm_acc")[i]);
							addAdmVO.setAdm_pw(req.getParameterValues("getAdm_pw")[i]);
							addAdmVO.setAdm_iden(req.getParameterValues("getAdm_iden")[i]);
							addAdmVO.setAdm_name(req.getParameterValues("getAdm_name")[i]);
							add_adm.add(addAdmVO);
							i++;
						}
					}else{
						while( i < admValueLen ){
							AdministratorVO addAdmVO = new AdministratorVO();
							addAdmVO.setAdm_acc(req.getParameterValues("getAdm_acc")[i]);
							addAdmVO.setAdm_pw(req.getParameterValues("getAdm_pw")[i]);
							addAdmVO.setAdm_iden(req.getParameterValues("getAdm_iden")[i]);
							addAdmVO.setAdm_name(req.getParameterValues("getAdm_name")[i]);
							add_adm.add(addAdmVO);
							i++;
							if( i == deleIndex ){
								i++;
							}
						}
					}
				}		
			}catch(NullPointerException e){
				System.out.println("first");
			}
	
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				req.setAttribute("add_adm",add_adm);
				String result = "";
				result = "add_adm";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}	
		
		if ("all_cancel".equals(button)){
			
			List<AdministratorVO> admVO = getSearch();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				String result = "";
				result = "all_cancel";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("add_determine".equals(button)){
			
			AdministratorService admDAO = new AdministratorService();
			List<AdministratorVO> add_adm = new ArrayList<AdministratorVO>();
			List<AdministratorVO> admVO = new ArrayList<AdministratorVO>();
			String err = null;
			int admFlag = admNullVer(req);
			
			if(admFlag > 2){
				err = "管理員身份與姓名為3到30個文字";
			}else if(admFlag > 1){
				err = "管理員帳號密碼為6到20個英文或數字";
			}else{
				err="不得有空格";
			}
			
			String[] admValue = req.getParameterValues("getAdm_acc");
					
			try{
				int admValueLen = admValue.length;
				for(int i=0; i<admValueLen; i++){
					AdministratorVO addAdmVO = new AdministratorVO();
					addAdmVO.setAdm_acc(req.getParameterValues("getAdm_acc")[i]);
					addAdmVO.setAdm_pw(req.getParameterValues("getAdm_pw")[i]);
					addAdmVO.setAdm_iden(req.getParameterValues("getAdm_iden")[i]);
					addAdmVO.setAdm_name(req.getParameterValues("getAdm_name")[i]);
					add_adm.add(addAdmVO);
				}
			}catch(NullPointerException e){
				System.out.println("first");
			}

			if(admFlag>0){
				admVO = getSearch();
				req.setAttribute("add_adm",add_adm);
				req.setAttribute("admVO", admVO);
				req.setAttribute("err", err);
				String result = "";
				result = "add_determine";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else{
				for(AdministratorVO adm : add_adm){
					admDAO.addAdministrator(adm);
				}

				admVO = getSearch();
				req.setAttribute("admVO", admVO);
				String result = "";
				result = "add_determine";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("modify".equals(button)){
			
			AdministratorService admDAO = new AdministratorService();
			AdministratorVO mod_adm = new AdministratorVO();
			String admNO = req.getParameter("Administrator");
			mod_adm = admDAO.getOneAdministrator(admNO);
			
			List<AdministratorVO> admVO = getSearch();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				req.setAttribute("mod_adm", mod_adm);
				String result = "";
				result = "modify";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("mod_determine".equals(button)){
			
			AdministratorService admDAO = new AdministratorService();
			AdministratorVO mod_adm = new AdministratorVO();
			
			String err = null;
			int admFlag = admNullVer(req);
			
			if(admFlag > 2){
				err = "管理員身份與姓名為3到30個文字";
			}else if(admFlag > 1){
				err = "管理員帳號密碼為6到20個英文或數字";
			}else{
				err="不得有空格";
			}
			
			mod_adm.setAdministrator(req.getParameter("getAdministrator"));
			mod_adm.setAdm_acc(req.getParameter("getAdm_acc"));
			mod_adm.setAdm_pw(req.getParameter("getAdm_pw"));
			mod_adm.setAdm_iden(req.getParameter("getAdm_iden"));
			mod_adm.setAdm_name(req.getParameter("getAdm_name"));
			
			if(admFlag>0){
				List<AdministratorVO> admVO = getSearch();
				req.setAttribute("mod_adm", mod_adm);
				req.setAttribute("err", err);
				req.setAttribute("admVO", admVO);
				String result = "";
				result = "mod_determine";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}else{		
				admDAO.modAdministrator(mod_adm);
				List<AdministratorVO> admVO = getSearch();
				req.setAttribute("admVO", admVO);
				String result = "";
				result = "mod_determine";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}	
		}
		
		if ("purview".equals(button)){
			
			//被選取的管理員資料
			AdministratorService admDAO = new AdministratorService();
			AdministratorVO pur_adm = new AdministratorVO();	
			String admNO = req.getParameter("Administrator");
			pur_adm = admDAO.getOneAdministrator(admNO);
			
			//被選取的管理員的權限
			Purview_DetailSerice purDAO = new Purview_DetailSerice();
			List<Purview_DetailVO> pur_purDetail = purDAO.getAll(admNO);
			for(Purview_DetailVO pur : pur_purDetail){
				if(pur.getAdm_no() != null){
					pur.setAdm_no("checked");
				}
			}
			List<AdministratorVO> admVO = getSearch();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				req.setAttribute("pur_adm", pur_adm);
				req.setAttribute("pur_purDetail", pur_purDetail);
				String result = "";
				result = "purview";
				req.setAttribute("result", result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}
		
		if ("pur_determine".equals(button)){
			
			Purview_DetailSerice purDAO = new Purview_DetailSerice();
			String[] pur_no = req.getParameterValues("pur_no");
			String adm_no = req.getParameter("Administrator");
			purDAO.delete(adm_no);
			try{
				for(String purNo : pur_no){
					Purview_DetailVO pur_detailVO = new Purview_DetailVO();
					pur_detailVO.setAdm_no(adm_no);
					pur_detailVO.setPurview_no(purNo);
					purDAO.addPurview(pur_detailVO);
				}
			}catch(NullPointerException e){
				System.out.println("Null");
			}

			List<AdministratorVO> admVO = getSearch();
			
			if(admVO == null){
				System.out.print("err");
			}else{
				req.setAttribute("admVO", admVO);
				String result = "";
				result = "pur_determine";
				req.setAttribute("result",result);
				String url = "/checklist/TEST_HOME.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
		}	
	}
	//提取所有管理員資料
	private List<AdministratorVO> getSearch(){
		AdministratorService admDAO = new AdministratorService();
		List<AdministratorVO> admVO = admDAO.get_N_Administrator();
		return admVO;
	}
	
	//驗證輸入資料
	private int admNullVer(HttpServletRequest req){
		
		int errNumer = 0;
		String para = null;
		Enumeration<String> en = req.getParameterNames();
		
		while(en.hasMoreElements()) {
			para = en.nextElement();
			if(para != null){
				String[] paraS = req.getParameterValues(para);
				for(String s : paraS){
					if("".equals(s)){
						errNumer = 1 ;
						return errNumer;
					}
				}
				
				if("getAdm_acc".equals(para) || "getAdm_pw".equals(para)){
					for( String s : paraS){
						if(!s.matches("[a-zA-Z0-9]{6,20}")){
							errNumer = 2 ;
						}
					}
				}
				
				if("getAdm_iden".equals(para) || "getAdm_name".equals(para)){
					for( String s : paraS){
						if(!s.matches(".{3,30}")){
							errNumer = 3 ;
						}
					}
				}
			}			
		}
		return errNumer;
	}
}