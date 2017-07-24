package cc.servlet;

import cc.service.FollowAndFansService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**  关注他人
 * servlet implementation class FollowPeople
 */
@WebServlet("/followPeople")
public class FollowPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowPeople() {
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
		int followN = Integer.valueOf(request.getParameter("followN"));
		new FollowAndFansService().followPeople(request,response);
		if(followN==0) response.sendRedirect("othersHome.jsp");        //经判断，选择进入的相应页面
		else if(followN==1) response.sendRedirect("hisfollow_Fans.jsp");
		else response.sendRedirect("hisInformation.jsp");
	}

}
