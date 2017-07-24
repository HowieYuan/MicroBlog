package cc.servlet;

import cc.dao.UserDao;
import cc.model.Vip;
import cc.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 封号
 * servlet implementation class Freeze
 */
@WebServlet("/freeze")
public class Freeze extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Freeze() {
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
		String action = request.getParameter("action");
		LoginService ls = new LoginService();
		if(action.equals("do")){
			ls.freeze(request,response);
		}
		else{
			ls.cancelFreeze(request,response);
		}
		List<Vip> userList = new UserDao().getUserList();
		request.getSession().setAttribute("userList", userList);
		if(action.equals("cancel_FeezePage")) response.sendRedirect("freezeUserAdmin.jsp");
		else response.sendRedirect("adminHome.jsp");
	}

}
