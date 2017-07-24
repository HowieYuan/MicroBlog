package cc.servlet;

import cc.service.MessageService;
import cc.util.GetDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 聊天功能 发信息
 * servlet implementation class SendChatMessage
 */
@WebServlet("/sendChatMessage")
public class SendChatMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendChatMessage() {
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
	
	//聊天功能  发新信息时，先将信息写入数据库，再将新信息内容和时间放入 等待状态中的ChatMessage对象 并唤醒以传回JSP页面
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chatmsg = (String) request.getAttribute(("chatmsg"));
		String dateLine = GetDate.getDateLine();
		String action = request.getParameter("action");
		MessageService ms = new MessageService();
		if(action.equals("user")){  //用户间聊天
			ms.userChat(request,chatmsg,dateLine);
			ms.checkUnreadNum();//用于用户显示未读信息
		}
		else if(action.equals("admin")){          //管理员私信
			ms.adminChat(request,chatmsg,dateLine);
			ms.checkUnreadNum();//用于用户显示未读信息
		}
		else {       //管理员群信
			ms.adminGroupChat(request,chatmsg,dateLine);
			ms.checkUnreadNum();//用于用户显示未读信息
		}
		
	}

}
