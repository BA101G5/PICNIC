package login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.general_member.model.GeneralMemberService;
import com.general_member.model.GeneralMemberVO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;



public class addMemberEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		
		try{
			String account = req.getParameter("account").trim();
			
//			if(account == null || account.length() == 0){
//				RequestDispatcher failureView = req.getRequestDispatcher("/wrong.jsp");
//				failureView.forward(req, res);
//				return; 
//			}
			/*-----------------------------------------------------------------*/
			Character MEM_STA ='E';
			Character MF_STA ='E';
			GeneralMemberService gSvc = new GeneralMemberService();
			List<GeneralMemberVO>  glist = gSvc.getAll();
			for(GeneralMemberVO gvo :glist){
				if(gvo.getMEM_MAIL().equals(account)){
					 gSvc.updateforSTA(account, MEM_STA);
				}
			}
			
			
			ManufacturersService mSvc = new ManufacturersService();
			List<ManufacturersVO>  mlist = mSvc.getAll();
			for(ManufacturersVO mvo :mlist){
				if(mvo.getMF_ACCO().equals(account)){
					 mSvc.updateforSTA(account, MF_STA);
				}
			}
			
			
			
			
			
			RequestDispatcher SuccessView = req.getRequestDispatcher("/index.jsp");
			SuccessView.forward(req, res);
			
			
		}catch(Exception e){
			System.out.println("wrong !!!");
		}	
			
	}


	

}
