package cc.dao;

import cc.util.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpvoteDao {
	//判断某条微博是否被某用户点赞
	public boolean isUpvoted(int userId, int weiboId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="select userId from upvote where weiboId=?";
			con= Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, weiboId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int userId2 = res.getInt("userId");
				if(userId2==userId) return true; 
			}
		}catch(ClassNotFoundException | SQLException e){
	         e.printStackTrace();
        }finally{
	            try {
		             Connect.closeCon(pre, con);
	                } catch (Exception e) {
		                   e.printStackTrace();
	                   }
          }	
		return false;
	}

	//点赞后，将该点赞写入数据库
	public void setUpvote(int userId, int weiboId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con= Connect.getCon();
			String sql ="insert into upvote values (null,?,?)";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, weiboId);
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

	//取消点赞
	public void cancelUpvote(int userId, int weiboId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con= Connect.getCon();
			String sql ="delete from upvote where userId=? and weiboId=?";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, weiboId);
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

	//点赞以后，更改该微博的点赞数，加1
	public int resetUpvote(int weiboId, int i) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			
			con= Connect.getCon();
			String sql ="select upvote from weibo where weiboId=?";
			pre=con.prepareStatement(sql);
			pre.setInt(1, weiboId);
			ResultSet res=pre.executeQuery();
			res.next();
			int upvote=res.getInt("upvote");
			
			String sql2 ="update weibo set upvote=? where weiboId=?";
			pre2 =con.prepareStatement(sql2);
			pre2.setInt(1, upvote+i);
			pre2.setInt(2, weiboId);
			pre2.executeUpdate();
			return upvote+i;
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

}
