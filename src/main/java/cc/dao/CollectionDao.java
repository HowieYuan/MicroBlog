package cc.dao;

import cc.util.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionDao {

	//将某用户收藏的微博写入数据库
	public void collect(int userId,int weiboId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con= Connect.getCon();
			String sql ="INSERT INTO collection VALUES (NULL,?,?)";
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

	//判断某用户是否收藏了某微博
	public boolean isCollected(int userId, int weiboId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="select userId from collection where weiboId=?";		
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

	//取消收藏微博，从数据库删除
	public void cancelCollect(int userId, int weiboId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con= Connect.getCon();
			String sql ="delete from collection where userId=? and weiboId=?";
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

	//获得List，装入某用户收藏的所有微博的微博Id
	public List<Integer> getCollectionWeiboIdList(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Integer> list = new LinkedList<Integer>();
		try{
			String sql="select weiboId from collection where userId=?";		
			con= Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId = res.getInt("weiboId");
				list.add(weiboId);
			}
			return list;
		}catch(ClassNotFoundException | SQLException e){
	         e.printStackTrace();
        }finally{
	            try {
		             Connect.closeCon(pre, con);
	                } catch (Exception e) {
		                   e.printStackTrace();
	                   }
          }
		Collections.reverse(list);
		return list;	
	}
}
