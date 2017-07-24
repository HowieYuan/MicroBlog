package cc.servlet;

import cc.dao.WeiboDao;
import cc.model.Weibo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 微博分页  显示更多微博
 * servlet implementation class MoreMessage
 */
@WebServlet("/moreMessage")
public class MoreMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoreMessage() {
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
		int weiboN = (int) request.getSession().getAttribute("weiboN");
		weiboN = weiboN+3;
		int userId = (int) request.getSession().getAttribute("userId");
		List<Weibo> list2= new WeiboDao().showPartWeibo(userId,request,response,weiboN);
		request.getSession().setAttribute("weiboList2", list2);
        response.sendRedirect("homePage.jsp");
	}

}
