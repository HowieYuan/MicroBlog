package cc.service;

import cc.dao.*;
import cc.model.Comment;
import cc.model.Vip;
import cc.model.Weibo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

//管理员功能逻辑处理
public class AdminService {

	//进行微博管理，取得某用户的微博List，和某用户被推荐微博List
	public void weiboAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		List<Weibo> list= new WeiboDao().showWeibo(username, userId, request, response);
		List<Weibo> list2= new WeiboDao().showMyRecommendWeibo(userId,userId,request,response);
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("adminList", list);
		request.setAttribute("recommendAdminList", list2);
		request.setAttribute("weiboTopic", true);
	}

	//进行微博删除管理，取得某用户经删除后的微博List
	public void weiboDeleteAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		String weiboId = request.getParameter("weiboId");
		new WeiboDao().deleteWeibo(weiboId,username);
		List<Weibo> list= new WeiboDao().showWeibo(username, userId, request, response);
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum-1);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("adminList", list);
		request.setAttribute("weiboTopic", true);
	}

	//进行微博评论管理，取得某微博的评论List
	public void commentAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		int weiboId =  Integer.valueOf(request.getParameter("weiboId"));
		
		List<Weibo> list= new WeiboDao().getCommentWeibo(weiboId, userId);
		List<Comment> list2 = new CommentDao().getCommentList(weiboId, userId, request, response);
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("adminList", list);
		request.setAttribute("commentAdminList", list2);
		request.setAttribute("commentTopic", true);
	}

	//进行评论删除管理，取得某微博经删除后的评论List
	public void commentDeleteAdmin(HttpServletRequest request, HttpServletResponse response) {
		CommentDao dao = new CommentDao();
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		int weiboId =  Integer.valueOf(request.getParameter("weiboId"));
		int commentId =  Integer.valueOf(request.getParameter("commentId"));
		
		dao.deleteComment(commentId);
		dao.resetCommentNumber(weiboId,-1);
		List<Weibo> list= new WeiboDao().getCommentWeibo(weiboId, userId);
		List<Comment> list2 = new CommentDao().getCommentList(weiboId, userId, request, response);
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("adminList", list);
		request.setAttribute("commentAdminList", list2);
		request.setAttribute("commentTopic", true);
	}

	//进行回复删除管理
	public void replyDeleteAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		int weiboId =  Integer.valueOf(request.getParameter("weiboId"));
		int commentId =  Integer.valueOf(request.getParameter("commentId"));
		int replyCommentId =  Integer.valueOf(request.getParameter("replyCommentId"));
		
		ReplyCommentDao rc = new ReplyCommentDao();
		rc.deleteReplyComment(replyCommentId);
		rc.resetReplyCommentNumber(commentId,-1);
		List<Weibo> list= new WeiboDao().getCommentWeibo(weiboId, userId);
		List<Comment> list2 = new CommentDao().getCommentList(weiboId, userId, request, response);
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("adminList", list);
		request.setAttribute("commentAdminList", list2);
		request.setAttribute("commentTopic", true);
	}

	//查看用户关注列表管理
	public void followAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		
		try {
			new FollowAndFansService().setFollowListAdmin(request, response, userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("follow_fansTopic", true);
	}

	//查看用户粉丝管理
	public void fansAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		
		try {
			new FollowAndFansService().setFansListAdmin(request, response, userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("follow_fansTopic", true);
	}

	//查看用户信息管理
	public void informationAdmin(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		
		try {
			Vip vip = new UserDao().getVip(username, userId);
			request.setAttribute("vip", vip);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("userId", userId);
		request.setAttribute("username", username);
		request.setAttribute("weiboNum", weiboNum);
		request.setAttribute("followNum", followNum);
		request.setAttribute("fansNum", fansNum);
		request.setAttribute("informationTopic", true);
	}

	//管理员推荐微博
	public void recommendAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String hasRecommended = request.getParameter("hasRecommended");
		int weiboId = Integer.valueOf(request.getParameter("weiboId"));
		PrintWriter out = response.getWriter();
		new WeiboDao().recommend(weiboId);
		if(hasRecommended.equals("false")){
			out.write("已荐");
			out.close();
		}
		else{
			out.write("推荐");
			out.close();
		}
	}

	//管理员私信
	public void adminchat(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		int weiboNum = Integer.valueOf(request.getParameter("weiboNum"));
		int followNum = Integer.valueOf(request.getParameter("followNum"));
		int fansNum = Integer.valueOf(request.getParameter("fansNum"));
		
		List<String> list = new ChatDao().getAdminChatMessageList(userId);
		request.setAttribute("chatList", list);
		request.getSession().setAttribute("userId", userId);
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("weiboNum", weiboNum);
		request.getSession().setAttribute("followNum", followNum);
		request.getSession().setAttribute("fansNum", fansNum);
	}

	
	//管理员群信
	public void groupchat(HttpServletRequest request, HttpServletResponse response) {
		List<String> groupList = new ChatDao().getAdminGroupChatMessageList();
		List<String> list = new UserDao().getGroupIdList(request, response);
		
		request.setAttribute("groupList", groupList);
		request.getSession().setAttribute("groupUserIdList", list);
	}

	public void showRecommendAdmin(HttpServletRequest request, HttpServletResponse response) {
		List<Weibo> list= new WeiboDao().showAllRecommendWeibo();
		request.setAttribute("allRecommendAdminList", list);
	}

}
