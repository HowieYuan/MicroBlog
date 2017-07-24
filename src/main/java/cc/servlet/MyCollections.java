package cc.servlet;

import cc.dao.CollectionDao;
import cc.dao.WeiboDao;
import cc.model.Weibo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 显示收藏的微博
 * servlet implementation class MyCollections
 */
@WebServlet("/myCollections")
public class MyCollections extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyCollections() {
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
		int userId = Integer.valueOf(request.getParameter("userId"));
		List<Integer> idList = new CollectionDao().getCollectionWeiboIdList(userId);
		List<Weibo> list = new WeiboDao().getCollectionWeiboList(userId,idList);
		request.getSession().setAttribute("collectionWeiboList", list);
		response.sendRedirect("myCollections.jsp");
	}

}
