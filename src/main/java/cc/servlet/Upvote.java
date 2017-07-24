package cc.servlet;

import cc.dao.UpvoteDao;
import cc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**  点赞
 * servlet implementation class Upvote
 */
@WebServlet("/upvote")
public class Upvote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upvote() {
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
		UpvoteDao ud = new UpvoteDao();
		PrintWriter out = response.getWriter();
		int weiboId = Integer.valueOf(request.getParameter("weiboId"));
		int userId = (int) request.getSession().getAttribute("userId");
		String username = (String) request.getSession().getAttribute("login");
		String isUpvoted = request.getParameter("hasUpvoted");
		if(isUpvoted.equals("false")){
			ud.setUpvote(userId,weiboId);
			int upvote = new UpvoteDao().resetUpvote(weiboId,1);
			out.write("已赞❤"+upvote);
			out.close();
		}
		else if(isUpvoted.equals("true")){
			ud.cancelUpvote(userId,weiboId);
			int upvote = new UpvoteDao().resetUpvote(weiboId,-1);
			out.write("赞❤"+upvote);
			out.close();
		}
		new MessageService().collectionAttribute(userId,username,request,response);
	}

}
