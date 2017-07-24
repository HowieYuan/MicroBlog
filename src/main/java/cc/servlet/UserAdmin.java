package cc.servlet;

import cc.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 管理员页面  进行用户管理
 * servlet implementation class UserAdmin
 */
@WebServlet("/userAdmin")
public class UserAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdmin() {
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
		String action = request.getParameter("action");
		AdminService as = new AdminService();
		if(action.equals("weibo")){
			as.weiboAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("deleteWeibo")){
			as.weiboDeleteAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("deleteWeibo_r")){
			as.weiboDeleteAdmin(request,response);
			as.showRecommendAdmin(request,response);
			request.getRequestDispatcher("allRecommendAdmin.jsp").forward(request, response);
		}
		if(action.equals("comment")){
			as.commentAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("deleteComment")){
			as.commentDeleteAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("deleteReply")){
			as.replyDeleteAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("follow")){
			as.followAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("fans")){
			as.fansAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("information")){
			as.informationAdmin(request,response);
			request.getRequestDispatcher("userAdmin.jsp").forward(request, response);
		}
		if(action.equals("recommend")){
		    as.recommendAdmin(request,response);
		}
		if(action.equals("adminchat")){
			as.adminchat(request,response);
			request.getRequestDispatcher("adminchat.jsp").forward(request, response);
		}
		if(action.equals("showRecommend")){
		    as.showRecommendAdmin(request,response);
		    request.getRequestDispatcher("allRecommendAdmin.jsp").forward(request, response);
		}
	}

}
