package com.administrator.model;

import java.util.List;

public class AdministratorService {
	
	AdministratorDAO_interface dao;

	public AdministratorService() {
		dao = new AdministratorDAO();
	}

	public  AdministratorVO addAdministrator(String Adm_cc,String Adm_pw,String Adm_iden, String Adm_name){
		
		AdministratorVO admVO = new AdministratorVO();
		admVO.setAdm_acc(Adm_cc);
		admVO.setAdm_pw(Adm_pw);
		admVO.setAdm_iden(Adm_iden);
		admVO.setAdm_name(Adm_name);
		dao.insert(admVO);	
		return admVO;
	}
	
	public  AdministratorVO addAdministrator(AdministratorVO admiVO){
		AdministratorVO admVO = new AdministratorVO();
		admVO = admiVO; 
		dao.insert(admVO);	
		return admVO;
	}

	
	public  AdministratorVO modAdministrator(String Administrator,String Adm_cc,String Adm_pw,String Adm_iden, String Adm_name){
		
		AdministratorVO admVO = new AdministratorVO();
		admVO.setAdministrator(Administrator);
		admVO.setAdm_acc(Adm_cc);
		admVO.setAdm_pw(Adm_pw);
		admVO.setAdm_iden(Adm_iden);
		admVO.setAdm_name(Adm_name);
		dao.update(admVO);
		return admVO;
	}
	
	public  AdministratorVO modAdministrator(AdministratorVO admiVO){
		AdministratorVO admVO = new AdministratorVO();
		admVO = admiVO;
		dao.update(admVO);
		return admVO;
	}

	
	public void delAdministrator(String Administrator){
		dao.delete(Administrator);
	}
	
	public List<AdministratorVO> get_N_Administrator(){
		return dao.getAll();
	}
	
	public AdministratorVO getOneAdministrator(String Administrator){
		return dao.findByPrimaryKey(Administrator);
	}
}
