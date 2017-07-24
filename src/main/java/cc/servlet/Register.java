package cc.servlet;

import cc.dao.UserDao;
import cc.model.Vip;
import cc.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**注册功能处理
 * servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String SUCCESS_VIEW = "registerSuccess.jsp";
	private final String ERROR_VIEW = "registerError.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultPage=ERROR_VIEW;
		Vip vip=new Vip();
		UserDao u=new UserDao();
	    vip.setEmail(request.getParameter("email"));
	    vip.setPhonenumber(request.getParameter("phonenumber"));
		vip.setUsername(request.getParameter("username"));
		vip.setPassword(request.getParameter("password"));
		
		//得到注册时输出数据的错误
		List<String> errors=new RegisterService().getErrors(vip);
		
		if(!errors.isEmpty()){
			request.setAttribute("errors", errors);
		}
		else {
			resultPage=SUCCESS_VIEW;
		    u.createUser(vip);          //将用户注册数据写入数据库
	    }
		
		//注册成功跳转到注册成功页面  Success.view
		request.getRequestDispatcher(resultPage).forward(request, response);
		return;
	}

		
}


