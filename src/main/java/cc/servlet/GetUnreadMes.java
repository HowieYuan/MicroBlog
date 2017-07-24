package cc.servlet;

import cc.dao.ChatDao;
import cc.model.ChatMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/** 未读信息功能  进入页面时，数据库查找未读信息，之后检测有无新的未读信息
 * servlet implementation class GetUnreadMes
 */
@WebServlet("/getUnreadMes")
public class GetUnreadMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Map<String, ChatMessage> waitMap = new HashMap<String, ChatMessage>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUnreadMes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //AJAX传入的servlet
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int i = Integer.valueOf(request.getParameter("i"));
		int userId = (int) request.getSession().getAttribute("userId");
		if(i==1){
			int num = new ChatDao().getUnreadNum(userId);
			if(num!=0){
				out.print("("+num+")");    //登陆后第一次进入页面，通过AJAX进行第一次数据库检查，得到还没有读的信息数
			}
			else out.print("(0)");
			out.flush();
			out.close();
		}
		else {          
			ChatMessage msg = new ChatMessage();
			String myuserId= String.valueOf(userId);
			waitMap.put(myuserId, msg);    
			synchronized (msg) {     //加锁，最多只有一个线程可以执行代码
				try {
					msg.wait();     //第一次检查后，再次调用AJAX中的函数，将一个ChatMessage对象处于等待状态
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			PrintWriter pw = response.getWriter();
			pw.print("("+msg.getUnread()+")");  //当有新信息发给当前用户，将再次调用检查数据库的方法，得到当前数目，并返回到页面
			pw.flush();
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
