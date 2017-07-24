package cc.servlet;

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

/** 即时聊天功能 检测是否有新信息 并将信息传回jsp页面  （AJAX）
 * servlet implementation class GetChatMessage
 */
@WebServlet("/getChatMessage")
public class GetChatMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Map<String, ChatMessage> waitMap = new HashMap<String, ChatMessage>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChatMessage() {
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
		ChatMessage msg = new ChatMessage();
		String myuserId = String.valueOf(request.getSession().getAttribute("userId"));
		waitMap.put(myuserId, msg);    //不同用户的即时聊天（session不同）
		synchronized (msg) {     //加锁，最多只有一个线程可以执行代码
			try {
				msg.wait();     
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		PrintWriter pw = response.getWriter();
		pw.print(msg.getUserId()+"a"+msg.getBy_userId()+"a"+msg.getDateLine()+"a"+msg.getChatId()+"a"+msg.getMessage());
		pw.flush();
		pw.close();
	}
}
