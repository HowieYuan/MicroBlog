package cc.dao;

import cc.model.ReplyComment;
import cc.util.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ReplyCommentDao {

	//将评论的回复写入数据库
	public void addReplyComment(int userId, int commentId, int commentWeiboId, String content, String dateLine) {
		Connection con=null;
		PreparedStatement pre=null;
		try{
			String sql="insert into replyComment values (null,?,?,?,?,?)";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, commentWeiboId);
			pre.setInt(3, commentId);
			pre.setString(4, content);
			pre.setString(5, dateLine);
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

	//当某评论被回复后，更改该评论的回复数，加1
	public int resetReplyCommentNumber(int commentId, int i) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			con=Connect.getCon();
			String sql ="select replyComment from comment where commentId=?";
			pre=con.prepareStatement(sql);
			pre.setInt(1, commentId);
			ResultSet res=pre.executeQuery();
			res.next();
			int replyComment=res.getInt("replyComment");
			
			String sql2 ="update comment set replyComment=? where commentId=?";
			pre2 =con.prepareStatement(sql2);
			pre2.setInt(1, replyComment+i);
			pre2.setInt(2, commentId);
			pre2.executeUpdate();
			return replyComment+i;
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
		return 0;
	}

	//获得List，放入某评论的所有回复
	public List<ReplyComment> getReplyCommentList(int commentId, int userId) {
		Connection con = null;
		PreparedStatement pre = null,pre2 = null;
		List<ReplyComment> list=new LinkedList<ReplyComment>();
		boolean canDelete=false;
		try{
			String sqL="select userId from comment where commentId=?";
			con=Connect.getCon();
			pre2=con.prepareStatement(sqL);
			pre2.setInt(1, commentId);
			ResultSet res=pre2.executeQuery();
			res.next();
			int userId2 =  res.getInt("userId");
			if(userId2==userId) {
				canDelete=true;       //如果是自己发的回复，则该回复能删除
			}
			
			String sql="select * from vip,replyComment "
					+ "where replyComment.commentId=? and vip.userId = replyComment.userId order by replyCommentId";
			pre=con.prepareStatement(sql);
			pre.setInt(1, commentId);
			res=pre.executeQuery();
			while(res.next()){
				int userId3 = res.getInt("replyComment.userId");
				int replyCommentId = res.getInt("replyComment.replyCommentId");
				String username=res.getString("vip.username");
				int follow = res.getInt("vip.follow");
				int fans = res.getInt("vip.fans");
				int weibo = res.getInt("vip.weibo");
				String content=res.getString("replyComment.content");
				String dateLine=res.getString("replyComment.dateLine");
				if(userId3==userId) {
					canDelete=true;      //如果是自己发的评论，则该评论里所有回复都可以删除
				}

				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
				
		  	  ReplyComment ReplyComment = new ReplyComment(replyCommentId,commentId,userId3,username,content,time,canDelete,follow,fans,weibo);
		        list.add(ReplyComment);
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

	//删除某条回复
	public void deleteReplyComment(int replyCommentId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="delete from replyComment where replyCommentId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, replyCommentId);
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
