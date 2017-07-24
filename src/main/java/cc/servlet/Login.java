package cc.servlet;

import cc.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**登录功能处理
 * servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String url1 = "homePage.jsp";
	private final String url2 = "loginError.jsp"; 
	private final String url3 = "loginError2.jsp"; 
	private final String url4 = "adminHome.jsp"; 
	private final String url5 = "loginFreeze.jsp"; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //Get方法用于实现记住账号或记住密码功能
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("time");
		if(num==null){
			response.sendRedirect("login.jsp");
		}
		Cookie[] cookie = request.getCookies();
		String accountnumber="",password="";
		if(cookie!=null){
			for(int i=0;i<cookie.length;i++){
				if(cookie[i].getName().equals("rememberUser"))   //若选择了记住账号，进入了登录页面时，显示账号
					accountnumber=cookie[i].getValue();   //获得账号的值
				else if(cookie[i].getName().equals("rememberPass"))   //反之
					password=cookie[i].getValue();
			}
		}
		PrintWriter out = response.getWriter();
		out.print(accountnumber+"&"+password);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	//Post方法用于检查是用户登录还是管理员登录，并进行相应的登录检查和登录后的信息的Session存入
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		String person = request.getParameter("admin");
		LoginService logSer = new LoginService();
		if(person==null){
			if(logSer.checkLogin(request, response)&&logSer.checkCode(request, response)){
				if(logSer.checkFreeze(request,response)){
					request.getRequestDispatcher(url5).forward(request, response);
				}
				else{
					try {
						logSer.setLoginRemember(request,response);
						logSer.setUserAttribute(request, response);
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}
					request.getRequestDispatcher(url1).forward(request, response);
					return;
				}
			}
			else if(!logSer.checkCode(request, response)){
				response.sendRedirect(url3);
			}
			else response.sendRedirect(url2);
		}
		
		else {
			if(logSer.checkAdminLogin(request, response)&&logSer.checkCode(request, response)){
				logSer.setLoginRemember(request,response);
				logSer.setAdminAttribute(request, response);
				request.getRequestDispatcher(url4).forward(request, response);
				return;
			}
			else if(!logSer.checkCode(request, response)){
				response.sendRedirect(url3);
			}
			else response.sendRedirect(url2);
		}
		
	}
	

}
