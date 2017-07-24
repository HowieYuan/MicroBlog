package cc.dao;

import cc.model.Comment;
import cc.model.ReplyComment;
import cc.util.Connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CommentDao {

	//将用户评论写入数据库
	public void addComment(int userId, int commentWeiboId, String content, String dateLine) {
		Connection con=null;
		PreparedStatement pre=null;
		try{
			String sql="insert into comment values (null,?,?,?,?,0)";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, commentWeiboId);
			pre.setString(3, content);
			pre.setString(4, dateLine);
			pre.executeUpdate();
		}catch(ClassNotFoundException | SQLException e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				Connect.closeCon(pre, con);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    }
	}

	//当微博获得评论后，更改某条微博的评论数量，加1
	public int resetCommentNumber(int commentWeiboId, int i) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			con=Connect.getCon();
			String sql ="select comment from weibo where weiboId=?";
			pre=con.prepareStatement(sql);
			pre.setInt(1, commentWeiboId);
			ResultSet res=pre.executeQuery();
			res.next();
			int comment=res.getInt("comment");
			
			String sql2 ="update weibo set comment=? where weiboId=?";
			pre2 =con.prepareStatement(sql2);
			pre2.setInt(1, comment+i);
			pre2.setInt(2, commentWeiboId);
			pre2.executeUpdate();
			return comment+i;
	    }catch(ClassNotFoundException | SQLException e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				Connect.closeCon(pre, con);
				Connect.closeCon(pre2, con);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	    }
		return 0;

	}

	//获取某条微博的所有评论，放入List
	public List<Comment> getCommentList(int commentWeiboId, int userId, HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pre = null,pre2 = null;
		List<Comment> list=new LinkedList<Comment>();
		boolean canDelete=false;
		try{
			String sqL="select userId from weibo where weiboId=?";
			con=Connect.getCon();
			pre2=con.prepareStatement(sqL);
			pre2.setInt(1, commentWeiboId);
			ResultSet res=pre2.executeQuery();
			res.next();
			int userId2 =  res.getInt("userId");
			if(userId2==userId) {
				canDelete=true;       //如果是自己发的微博，则评论能删除
			}
			
			String sql="select * from vip,comment "
					+ "where comment.weiboId=? and vip.userId = comment.userId order by commentId";
			pre=con.prepareStatement(sql);
			pre.setInt(1, commentWeiboId);
			res=pre.executeQuery();
			while(res.next()){
				int userId3 = res.getInt("comment.userId");
				int commentId = res.getInt("comment.commentId");
				String username=res.getString("vip.username");
				int follow = res.getInt("vip.follow");
				int fans = res.getInt("vip.fans");
				int weibo = res.getInt("vip.weibo");
				String content=res.getString("comment.content");
				String dateLine=res.getString("comment.dateLine");
				int replyComment = res.getInt("comment.replyComment");
				if(userId3==userId) {
					canDelete=true;      //如果是自己发的评论，则该评论也可以删除
				}

				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
				
		  	    List<ReplyComment> list3 = new ReplyCommentDao().getReplyCommentList(commentId,userId);
		  	    request.setAttribute("ReplyComment"+commentId, list3);
		        
		  	    Comment comment = new Comment(commentId,userId3,username,content,time,canDelete,replyComment,follow,fans,weibo);
		        list.add(comment);
			}
			}catch(ClassNotFoundException | SQLException e){
		         e.printStackTrace();
	           }finally{
		            try {
			             Connect.closeCon(pre, con);
			             Connect.closeCon(pre2, con);
		                } catch (Exception e) {
			                   e.printStackTrace();
		                   }
	                   }		
		Collections.reverse(list);
		return list;
	}

	//删除某条评论
	public void deleteComment(int commentId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="delete from comment where commentId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, commentId);
			pre.executeUpdate();
		}catch(ClassNotFoundException | SQLException e){
	         e.printStackTrace();
        }finally{
	            try {
		             Connect.closeCon(pre, con);
	                } catch (Exception e) {
		                   e.printStackTrace();
	                   }
                }		
	}
	

}
