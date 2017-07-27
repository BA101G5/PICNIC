package login;

import java.io.*;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.general_member.model.GeneralMemberDAO;
import com.general_member.model.GeneralMemberService;
import com.general_member.model.GeneralMemberVO;
import com.manufacturers.model.ManufacturersService;
import com.manufacturers.model.ManufacturersVO;
import java.security.MessageDigest;
import javax.servlet.annotation.WebServlet;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

   //�i�ˬd�ϥΪ̿�J���b��(account) �K�X(password)�O�_���ġj
   //�i��ڤW���ܸ�Ʈw�j�M���j
  protected GeneralMemberVO allowUser(String account, String password) {

	GeneralMemberService gs = new GeneralMemberService();
	
    
	List<GeneralMemberVO> list = gs.getAll();
	for(GeneralMemberVO gvo : list){
		if(gvo.getMEM_MAIL().equals(account) == true && gvo.getMEM_PSW().equals(password)== true){
			return gvo;
		}
	}
	return null;
	
	
  }	
  
  protected ManufacturersVO allowUser1(String account, String password) {
	  
	  	ManufacturersService ms =new ManufacturersService();
	      
	  	List<ManufacturersVO> list = ms.getAll();
	  	for(ManufacturersVO mvo : list){
	  		if(mvo.getMF_ACCO().equals(account) == true && mvo.getMF_PSW().equals(password)== true){
	  			return mvo;
	  		}
	  	}
	  	return null;
	  	
 }	
  
  public void doPost(HttpServletRequest req, HttpServletResponse res)
                                throws ServletException, IOException {
    req.setCharacterEncoding("Big5");
    res.setContentType("text/html; charset=Big5");
    PrintWriter out = res.getWriter();

    // �i���o�ϥΪ� �b��(account) �K�X(password)�j
    String account = req.getParameter("account");
    String password = md5(req.getParameter("password"));

    // �i�ˬd�ӱb�� , �K�X�O�_���ġj
    if (allowUser(account, password) == null && allowUser1(account, password) == null) {          //�i�b�� , �K�X�L�Įɡj
    	req.setAttribute("errorMsgs"," * �b�K���~"); 
    	RequestDispatcher failureView = req.getRequestDispatcher("/signin.jsp");
		failureView.forward(req, res);
     
    }else {                                       //�i�b�� , �K�X���Į�, �~���H�U�u�@�j
    	HttpSession session = req.getSession();
    	HttpSession session1 = req.getSession();
    	if(allowUser(account, password) != null && allowUser1(account, password) == null){
    		//generalmember
        	session1.setAttribute("gVO", allowUser(account, password));
        	session1.setAttribute("MEM_NO",allowUser(account, password).getMEM_NO()); //�[�J�|���s��
          session.setAttribute("account", account);   //*�u�@1: �~�bsession�����w�g�n�J�L������
    	}else if(allowUser1(account, password) != null && allowUser(account, password) == null){
    		//manufacturers
    		
        	session1.setAttribute("mVO", allowUser1(account, password));
          session.setAttribute("account", account);
    	}
    	
      
      
       try {                                                        
         String location = (String) session.getAttribute("location");
         //System.out.println("***** LoginHandle /  location = " + req.getContextPath());
       
         
         if (location != null) {   
        	 session.removeAttribute("location");
        	 res.sendRedirect(location);//*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
        	
           return;
         }
       }catch (Exception ignored) { }

//      res.sendRedirect(req.getContextPath()+"/general_member/formu.jsp");  //*�u�@3: (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
      //System.out.println("****** LoginHandle /*****/" + req.getContextPath());
      res.sendRedirect(req.getContextPath()+"/index.jsp");
    }
  }
  public static String md5(String str) {
	    String md5=null;
	    try {
	      MessageDigest md=MessageDigest.getInstance("MD5");
	      byte[] barr=md.digest(str.getBytes());  //�N byte �}�C�[�K
	      StringBuffer sb=new StringBuffer();  //�N byte �}�C�ন 16 �i��
	      for (int i=0; i < barr.length; i++) {sb.append(byte2Hex(barr[i]));}
	      String hex=sb.toString();
	      md5=hex.toUpperCase(); //�@���ন�j�g
	      }
	    catch(Exception e) {e.printStackTrace();}
	    return md5;
	    }
	  public static String byte2Hex(byte b) {
	    String[] h={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	    int i=b;
	    if (i < 0) {i += 256;}
	    return h[i/16] + h[i%16];
	    }
}