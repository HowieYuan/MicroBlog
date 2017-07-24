package cc.servlet;

import cc.dao.ChatDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** 用户页面 查看管理员信息
 * servlet implementation class User_Adminchat
 */
@WebServlet("/user_Adminchat")
public class User_Adminchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Adminchat() {
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
		int userId = (int) request.getSession().getAttribute("userId");
		List<String> list = new ChatDao().getAdminChatMessageList(userId);
		new ChatDao().isRead(userId,userId);
		request.setAttribute("adminchatList", list);
		request.getRequestDispatcher("user_Adminchat.jsp").forward(request, response);
	}

}
