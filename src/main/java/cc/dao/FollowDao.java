package cc.dao;

import cc.model.Vip;
import cc.util.Connect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FollowDao {

	//当用户关注了另一用户，则将该状态写入数据库
	public void setFollow(int userId, int by_userId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con=Connect.getCon();
			String sql ="insert into follow values (null,?,?)";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, by_userId);
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

	//取消关注，从数据库删除
    public void cancelFollow(int userId, int by_userId) {
    	Connection con = null;
		PreparedStatement pre = null;
		try{		
			con=Connect.getCon();
			String sql ="delete from follow where userId=? and by_userId=?";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, by_userId);
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
	
    //检查某用户是否被另一用户所关注
	public boolean checkFollow(HttpServletRequest request, HttpServletResponse response, int by_userId) {
		int userId = (int) request.getSession().getAttribute("userId");
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql ="select by_userId from follow where userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int by_userId2 = res.getInt("by_userId");
				if(by_userId == by_userId2){
					return true;
				}			
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

	//关注别人以后，重置用户关注数，和别人的粉丝数
	public void setFollowAndfansNumber(HttpServletRequest request, HttpServletResponse response, int userId, int by_userId) {
		int i=0,j=0;
		Connection con = null;
		PreparedStatement pre = null,pre2 = null,pre3 = null,pre4 = null;
		try{
			String sql ="select by_userId from follow where userId=?";
			String sql2 ="select userId from follow where by_userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre2=con.prepareStatement(sql2);
		    pre.setInt(1, userId);
			pre2.setInt(1, by_userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				i++;
			}
			ResultSet res2=pre2.executeQuery();
			while(res2.next()){
				j++;
			}
			String sql3 ="update vip set follow="+i+" where userId=?";
			String sql4 ="update vip set fans="+j+" where userId=?";
			pre3=con.prepareStatement(sql3);
		    pre4=con.prepareStatement(sql4);
		    pre3.setInt(1, userId);
			pre4.setInt(1, by_userId);
			pre3.executeUpdate();
			pre4.executeUpdate();
			Vip vip=new UserDao().getVip(null,userId);
			Vip hisVip=new UserDao().getVip(null,by_userId);
			
			request.getSession().setAttribute("vip", vip);
			request.getSession().setAttribute("hisVip", hisVip);
			request.getSession().setAttribute("hisFansNumber", j);
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

	//获得List，装入某用户所有关注的用户的userId
	public List<Integer> getFollowUserIdList(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Integer> list = new LinkedList<Integer>();
		try{
			String sql ="select by_userId from follow where userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int by_userId = res.getInt("by_userId");
				list.add(by_userId);
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
		return list;
	}

	////获得List，装入某用户所有粉丝的userId
	public List<Integer> getFansUserIdList(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Integer> list = new LinkedList<Integer>();
		try{
			String sql ="select userId from follow where by_userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int fansId = res.getInt("userId");
				list.add(fansId);
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
		return list;
	}

}
