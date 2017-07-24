package cc.servlet;

import cc.service.InformationService;
import cc.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**修改个人信息
 * servlet implementation class ChangeInformation
 */
@WebServlet("/changeInformation")
public class ChangeInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SUCCESS_VIEW = "changeSuccess.jsp";
	private final String ERROR_VIEW = "changeInformationError.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInformation() {
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
		if(inse.checkInformation(request,response)){
			request.getSession().setAttribute("login",request.getParameter("username"));  //将新用户名设置到session中
			try {
				new LoginService().setUserAttribute2(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			response.sendRedirect(SUCCESS_VIEW);
		}
		else response.sendRedirect(ERROR_VIEW);				
	}
}
