package cc.service;

import cc.dao.CommentDao;
import cc.dao.ReplyCommentDao;
import cc.dao.WeiboDao;
import cc.model.Comment;
import cc.model.ReplyComment;
import cc.model.Weibo;
import cc.util.Code;
import cc.util.GetDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommentService {
    //发表评论
	public void buildComment(HttpServletRequest request, HttpServletResponse response) {
		CommentDao dao = new CommentDao();
		WeiboDao wd = new WeiboDao();
		int commentWeiboId = Integer.valueOf(request.getParameter("commentWeiboId"));
		String username = (String) request.getSession().getAttribute("login");
		String comment = request.getParameter("comment"+commentWeiboId);
		String dateLine = GetDate.getDateLine();      //取得时间
		String content = Code.htmlEncode(comment);    //字符转义
		int userId = (int) request.getSession().getAttribute("userId");
		
		dao.addComment(userId,commentWeiboId,content,dateLine);
		dao.resetCommentNumber(commentWeiboId,1);
		
		List<Comment> list = dao.getCommentList(commentWeiboId,userId,request,response);
		List<Weibo> list2= wd.getCommentWeibo(commentWeiboId,userId);
		List<Weibo> list3= wd.showWeibo(username,userId,request,response);
		List<Weibo> list4= wd.showOthersWeibo(userId,request,response);
		request.getSession().setAttribute("weiboList2", list4);
		request.getSession().setAttribute("weiboList", list3);
		request.getSession().setAttribute("commentWeibo", list2);
		request.getSession().setAttribute("commentList", list);
	}

	//显示评论，取得评论List
	public void showComment(HttpServletRequest request, HttpServletResponse response) {
		CommentDao dao = new CommentDao();
		int commentWeiboId = Integer.valueOf(request.getParameter("commentWeiboId"));
		int userId = (int) request.getSession().getAttribute("userId");
		
		List<Weibo> list= new WeiboDao().getCommentWeibo(commentWeiboId,userId);
		List<Comment> list2 = dao.getCommentList(commentWeiboId,userId, request, response);
		request.getSession().setAttribute("commentWeibo", list);
		request.getSession().setAttribute("commentList", list2);
	}

	//删除评论
	public void deleteComment(HttpServletRequest request, HttpServletResponse response) {
		WeiboDao wd = new WeiboDao();
		CommentDao dao = new CommentDao();
		int commentWeiboId = Integer.valueOf(request.getParameter("commentWeiboId"));
		int commentId = Integer.valueOf(request.getParameter("commentId"));
		int userId = (int) request.getSession().getAttribute("userId");
		String username = (String) request.getSession().getAttribute("login");
		dao.deleteComment(commentId);
		dao.resetCommentNumber(commentWeiboId,-1);
		
		List<Comment> list = dao.getCommentList(commentWeiboId,userId,request,response);
		List<Weibo> list2= wd.getCommentWeibo(commentWeiboId,userId);
		List<Weibo> list3= wd.showWeibo(username,userId,request,response);
		List<Weibo> list4= wd.showOthersWeibo(userId,request,response);
		request.getSession().setAttribute("weiboList2", list4);
		request.getSession().setAttribute("weiboList", list3);
		request.getSession().setAttribute("commentWeibo", list2);
		request.getSession().setAttribute("commentList", list);
	}

	//发表评论回复
	public void buildReplyComment(HttpServletRequest request, HttpServletResponse response) {
		ReplyCommentDao dao = new ReplyCommentDao();
		int commentId = Integer.valueOf(request.getParameter("commentId"));
		int commentWeiboId = Integer.valueOf(request.getParameter("commentWeiboId"));
		String content = request.getParameter("reply"+commentId);
		String dateLine = GetDate.getDateLine();      //取得时间
		content = Code.htmlEncode(content);    //字符转义
		int userId = (int) request.getSession().getAttribute("userId");
		
		dao.addReplyComment(userId,commentId,commentWeiboId,content,dateLine);
		dao.resetReplyCommentNumber(commentId,1);
		
		List<ReplyComment> list = dao.getReplyCommentList(commentId,userId);
		List<Comment> list2= new CommentDao().getCommentList(commentWeiboId,userId, request, response);
		request.getSession().setAttribute("ReplyComment"+commentId, list);
		request.getSession().setAttribute("commentList", list2);
	}

	//删除评论回复
	public void deleteReplyComment(HttpServletRequest request, HttpServletResponse response) {
		ReplyCommentDao dao = new ReplyCommentDao();
		int commentId = Integer.valueOf(request.getParameter("commentId"));
		int replyCommentId = Integer.valueOf(request.getParameter("replyCommentId"));
		int commentWeiboId = Integer.valueOf(request.getParameter("commentWeiboId"));
		int userId = (int) request.getSession().getAttribute("userId");
		
		dao.deleteReplyComment(replyCommentId);
		dao.resetReplyCommentNumber(commentId,-1);
		
		List<ReplyComment> list = dao.getReplyCommentList(commentId,userId);
		List<Comment> list2= new CommentDao().getCommentList(commentWeiboId,userId, request, response);
		request.getSession().setAttribute("ReplyComment"+commentId, list);
		request.getSession().setAttribute("commentList", list2);
	}
	

}
