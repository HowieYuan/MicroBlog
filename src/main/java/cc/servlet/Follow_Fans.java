package cc.servlet;

import cc.service.FollowAndFansService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 关注和粉丝页面
 * servlet implementation class Follow_Fans
 */
@WebServlet("/follow_Fans")
public class Follow_Fans extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Follow_Fans() {
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
		FollowAndFansService fFSer = new FollowAndFansService();
		int userId = (int) request.getSession().getAttribute("userId");
		String action = request.getParameter("action");
		fFSer.followAndfansAction(userId,action,request,response);
	}

}
