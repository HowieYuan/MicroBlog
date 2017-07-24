package cc.servlet;

import cc.service.InformationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**忘记密码
 * servlet implementation class ForgetPassword
 */
@WebServlet("/forgetPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SUCCESS_VIEW = "changeSuccess.jsp";
	private final String ERROR_VIEW1 = "forgetPasswordError.jsp";   
	private final String ERROR_VIEW2 = "forgetPassword_LoginError.jsp";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InformationService inse=new InformationService();
		try {
			if(inse.checkForgetPassword(request,response)){
				response.sendRedirect(SUCCESS_VIEW);
			}
			else if(request.getSession().getAttribute("login")==null){
				response.sendRedirect(ERROR_VIEW2);
			}
			else response.sendRedirect(ERROR_VIEW1);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}

}
