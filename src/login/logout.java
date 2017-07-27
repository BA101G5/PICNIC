package login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.general_member.model.GeneralMemberService;
import com.general_member.model.GeneralMemberVO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;



public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		
		try{
			HttpSession session = req.getSession();
			System.out.println("---------");	
			System.out.println(session.getAttribute("gVO"));
			System.out.println(session.getAttribute("mVO"));
			if(session.getAttribute("gVO")!=null ||session.getAttribute("mVO")!=null){
				session.removeAttribute("gVO");
				session.removeAttribute("mVO");
				session.removeAttribute("MEM_NO");
			}else{
				res.sendRedirect(req.getContextPath()+"/signin.jsp");
				return;
			}
		
		System.out.println(session.getAttribute("gVO"));
		System.out.println(session.getAttribute("mVO"));
		System.out.println("---------");	
			RequestDispatcher SuccessView = req.getRequestDispatcher("/index.jsp");
			SuccessView.forward(req, res);
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("wrong !!!");
		}	
			
	}


	

}
