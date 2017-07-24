package cc.servlet;

import cc.dao.CollectionDao;
import cc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**收藏微博
 * servlet implementation class Collect
 */
@WebServlet("/collect")
public class Collect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Collect() {
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
		CollectionDao cd = new CollectionDao();
		PrintWriter out = response.getWriter();
		int userId = (int) request.getSession().getAttribute("userId");
		int weiboId = Integer.valueOf(request.getParameter("weiboId"));
		String username = (String) request.getSession().getAttribute("login");
		String isCollected = request.getParameter("hasCollected");
		if(isCollected.equals("false")){
			cd.collect(userId,weiboId);
			out.write("已收藏");
			out.close();
		}
		else{
			cd.cancelCollect(userId,weiboId);
			out.write("收藏它");
			out.close();
		}
		new MessageService().collectionAttribute(userId,username,request,response);
	}

}
