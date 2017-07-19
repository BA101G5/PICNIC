package com.login.model;

public class LogInService {
	private LogIn_interface dao;
	public LogInService(){
		dao = new LogInDAO();
	}
	
	public LogInVO getOnelogin(String adm_acc){
		return dao.findByACC(adm_acc);
	}
}
