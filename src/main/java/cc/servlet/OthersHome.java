package cc.servlet;

import cc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/** 进入他人的个人主页
 * servlet implementation class OthersHome
 */
@WebServlet("/othersHome")
public class OthersHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OthersHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String hisUsername = request.getParameter("username");
			String myUsername = (String) request.getSession().getAttribute("login");
			if(hisUsername.equals(myUsername)){
				response.sendRedirect("home.jsp");
			}
			else{
				new MessageService().setOthersHomeAttribute(request,response,hisUsername);
				request.getRequestDispatcher("othersHome.jsp").forward(request, response);
		    }
		  } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		  }
	}

}
