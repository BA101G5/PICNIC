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

   //【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
   //【實際上應至資料庫搜尋比對】
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

    // 【取得使用者 帳號(account) 密碼(password)】
    String account = req.getParameter("account");
    String password = md5(req.getParameter("password"));

    // 【檢查該帳號 , 密碼是否有效】
    if (allowUser(account, password) == null && allowUser1(account, password) == null) {          //【帳號 , 密碼無效時】
    	req.setAttribute("errorMsgs"," * 帳密錯誤"); 
    	RequestDispatcher failureView = req.getRequestDispatcher("/signin.jsp");
		failureView.forward(req, res);
     
    }else {                                       //【帳號 , 密碼有效時, 才做以下工作】
    	HttpSession session = req.getSession();
    	HttpSession session1 = req.getSession();
    	if(allowUser(account, password) != null && allowUser1(account, password) == null){
    		//generalmember
        	session1.setAttribute("gVO", allowUser(account, password));
        	session1.setAttribute("MEM_NO",allowUser(account, password).getMEM_NO()); //加入會員編號
          session.setAttribute("account", account);   //*工作1: 才在session內做已經登入過的標識
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
        	 res.sendRedirect(location);//*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
        	
           return;
         }
       }catch (Exception ignored) { }

//      res.sendRedirect(req.getContextPath()+"/general_member/formu.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
      //System.out.println("****** LoginHandle /*****/" + req.getContextPath());
      res.sendRedirect(req.getContextPath()+"/index.jsp");
    }
  }
  public static String md5(String str) {
	    String md5=null;
	    try {
	      MessageDigest md=MessageDigest.getInstance("MD5");
	      byte[] barr=md.digest(str.getBytes());  //將 byte 陣列加密
	      StringBuffer sb=new StringBuffer();  //將 byte 陣列轉成 16 進制
	      for (int i=0; i < barr.length; i++) {sb.append(byte2Hex(barr[i]));}
	      String hex=sb.toString();
	      md5=hex.toUpperCase(); //一律轉成大寫
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