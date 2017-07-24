package cc.servlet;

import cc.dao.ChatDao;
import cc.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * servlet implementation class DeleteChatMes
 */
@WebServlet("/deleteChatMes")
public class DeleteChatMes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteChatMes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChatDao cd = new ChatDao();
		MessageService ms = new MessageService();
		String action = request.getParameter("action");
		int myuserId = Integer.valueOf(request.getParameter("myuserId"));
		int chatId = Integer.valueOf(request.getParameter("chatId"));
		if(action.equals("my")){
			cd.deleteMyChatMes(myuserId,chatId);
		}
		else if(action.equals("his")){
			cd.deleteHisChatMes(myuserId,chatId);
		}
		if(action.equals("my")||action.equals("his")){
			ms.chatServiceToDelete(request,response);
			request.getRequestDispatcher("chat.jsp").forward(request, response);
		}
		else if(action.equals("admin")){
			cd.deleteHisChatMes(myuserId,chatId);
			cd.checkdeleteadminMes(myuserId);
			List<String> list = new ChatDao().getAdminChatMessageList(myuserId);
			new ChatDao().isRead(myuserId,myuserId);
			request.setAttribute("adminchatList", list);
			request.getRequestDispatcher("user_Adminchat.jsp").forward(request, response);
		}
	}

}
