package cc.dao;

import cc.model.Vip;
import cc.model.Weibo;
import cc.util.Connect;
import cc.util.Text;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class WeiboDao {
	//将微博内容写入数据库
	public void addWeibo(String username, String content,String dateLine){
		Connection con=null;
		PreparedStatement pre=null;
		PreparedStatement pre2=null;
		try{
			String sql="insert into weibo values (null,?,?,?,0,0,0)";
			String sql2="select userId from vip where username=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre2=con.prepareStatement(sql2);
			pre2.setString(1, username);
			ResultSet res=pre2.executeQuery();
			res.next();
			pre.setInt(1, res.getInt("userId"));
			pre.setString(2, content);
			pre.setString(3, dateLine);
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
	
	//取出某用户的所有非推荐微博，将其内容，发表日期，微博ID，已经该是否存在图片的布尔值放入Weibo对象  将对象放入List集合中
	public List<Weibo> showWeibo(String name, int userId, HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			int id=new UserDao().getUserId(name);			
			String sql="select weiboId,content,dateLine,upvote,comment from weibo where recommend=0 and userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId=res.getInt("weiboId");
				String content=res.getString("content");
				String dateLine=res.getString("dateLine");
				int upvote=res.getInt("upvote");
				int comment=res.getInt("comment");
		  	    
				//将微博发表时间的格式进行更改，并检查该微博是否含有图片
				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
		        
		        Weibo weibo = new Weibo(weiboId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted);
		        list.add(weibo);
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
		Collections.reverse(list);
		return list;
	}
	
	//进入首页时，只显示部分微博（分页处理）
	public List<Weibo> showPartWeibo(int userId, HttpServletRequest request, HttpServletResponse response, int weiboN){
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			String sql="select * from vip,weibo where weibo.recommend=0 and vip.userId = weibo.userId "
					+ "order by weiboId DESC limit ?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, weiboN);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId=res.getInt("weibo.weiboId");
				int weibouserId=res.getInt("weibo.userId");
				String username=res.getString("vip.username");
				String content=res.getString("weibo.content");
				String dateLine=res.getString("weibo.dateLine");
				int upvote=res.getInt("weibo.upvote");
				int comment=res.getInt("weibo.comment");
				
				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
				
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
		        
		        Weibo weibo = new Weibo(weiboId,weibouserId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted,username);
		        list.add(weibo);
			}
			request.getSession().setAttribute("weiboN", weiboN);
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
	
	//将非推荐微博放入List中
	public List<Weibo> showOthersWeibo(int userId, HttpServletRequest request, HttpServletResponse response){
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			String sql="select * from vip,weibo where weibo.recommend=0 and vip.userId = weibo.userId "
					+ "order by weiboId DESC limit 3";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId=res.getInt("weibo.weiboId");
				int weibouserId=res.getInt("weibo.userId");
				String username=res.getString("vip.username");
				String content=res.getString("weibo.content");
				String dateLine=res.getString("weibo.dateLine");
				int upvote=res.getInt("weibo.upvote");
				int comment=res.getInt("weibo.comment");
				

				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
				
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		                //该微博是否被登录用户所收藏或点赞
		        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
		        
		        Weibo weibo = new Weibo(weiboId,weibouserId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted,username);
		        list.add(weibo);
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
	
	//根据微博内容和发表日期取出相应的微博ID
	public int showWeiboId(int userId,String content, String dateLine) throws ClassNotFoundException, SQLException {
		System.out.println(userId+content+dateLine);
		String sql="select weiboId from weibo where userId=? and content=? and dateLine=?";		
		Connection con=Connect.getCon();
		PreparedStatement pre=con.prepareStatement(sql);
		pre.setInt(1, userId);
		pre.setString(2, content);
		pre.setString(3, dateLine);
		ResultSet res=pre.executeQuery();
		res.next();
		int weiboId=res.getInt("weiboId");
		return weiboId;
	}
	
	//根据微博ID删除掉指定微博
	public void deleteWeibo(String weiboId, String username) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="delete from weibo where weiboId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setString(1, weiboId);
			pre.executeUpdate();
			new UserDao().resetWeiboNum(username,-1);
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

	//更新用户微博数
	public void setWeiboNumber(HttpServletRequest request, HttpServletResponse response, int userId, String username) {
		int i=0;
		Connection con = null;
		PreparedStatement pre = null,pre2 = null;
		try{
			String sql ="select weiboId from weibo where userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				i++;
			}
			String sql2 ="update vip set weibo=? where userId=?";
			pre2=con.prepareStatement(sql2);
			pre2.setInt(1, i);
			pre2.setInt(2, userId);
			pre2.executeUpdate();
			Vip vip=new UserDao().getVip(username,userId);
			
			request.getSession().setAttribute("vip", vip);
			request.getSession().setAttribute("myWeiboNumber", i);
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

	//根据放有微博Id的List，将对于的微博放入List中，这些微博为某用户的收藏的微博
	public List<Weibo> getCollectionWeiboList(int userId, List<Integer> idList) {
		Connection con = null;
		PreparedStatement pre = null;
		Iterator<Integer> it = idList.iterator();
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			while(it.hasNext()){
				int weiboId = it.next();
				String sql="select * from vip,weibo "
						+ "where weibo.weiboId=? and vip.userId = weibo.userId order by weibo.weiboId";
				con=Connect.getCon();
				pre=con.prepareStatement(sql);
				pre.setInt(1, weiboId);
				ResultSet res=pre.executeQuery();
				while(res.next()){
					int weibouserId=res.getInt("weibo.userId");
					String username=res.getString("vip.username");
					String content=res.getString("weibo.content");
					String dateLine=res.getString("weibo.dateLine");
					int upvote=res.getInt("weibo.upvote");
					int comment=res.getInt("weibo.comment");
					
					java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
			  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
					
			  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
			        boolean pictureExist = file.exists();
			        
			        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
			        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
			        
			        Weibo weibo = new Weibo(weiboId,weibouserId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted,username);
			        list.add(weibo);
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
		Collections.reverse(list);
		return list;
	}

	//获得某微博的所有评论，放入List中
	public List<Weibo> getCommentWeibo(int commentWeiboId,int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			String sql="select * from vip,weibo "
					+ "where weibo.weiboId=? and vip.userId = weibo.userId order by weiboId";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, commentWeiboId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId=res.getInt("weibo.weiboId");
				int weibouserId=res.getInt("weibo.userId");
				String username=res.getString("vip.username");
				String content=res.getString("weibo.content");
				String dateLine=res.getString("weibo.dateLine");
				int upvote=res.getInt("weibo.upvote");
				int comment=res.getInt("weibo.comment");

				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
				
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
		        
		        Weibo weibo = new Weibo(weiboId,weibouserId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted,username);
		        list.add(weibo);
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

	//管理员将某微博设为推荐微博时，将该微博在数据库中的recommend字段设为1
	public void recommend(int weiboId) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			con=Connect.getCon();
			String sql ="select recommend from weibo where weiboId=?";
			pre=con.prepareStatement(sql);
			pre.setInt(1, weiboId);
			ResultSet res=pre.executeQuery();
			res.next();
			int recommend=res.getInt("recommend");
			
			String sql2 ="update weibo set recommend=? where weiboId=?";
			pre2 =con.prepareStatement(sql2);
			pre2.setInt(2, weiboId);
            if(recommend==0){
            	pre2.setInt(1, 1);
			}
            else pre2.setInt(1, 0);
			pre2.executeUpdate();
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

	//将被推荐的微博放入List中
	public List<Weibo> showRecommendWeibo(int userId, HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			String sql="select * from vip,weibo where weibo.recommend=1 and vip.userId = weibo.userId order by weiboId";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId=res.getInt("weibo.weiboId");
				int weibouserId=res.getInt("weibo.userId");
				String username=res.getString("vip.username");
				String content=res.getString("weibo.content");
				String dateLine=res.getString("weibo.dateLine");
				int upvote=res.getInt("weibo.upvote");
				int comment=res.getInt("weibo.comment");

				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
				
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
		        
		        Weibo weibo = new Weibo(weiboId,weibouserId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted,username);
		        list.add(weibo);
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
		Collections.reverse(list);
		return list;
	}

	//将某用户被推荐的微博放入List中
	public List<Weibo> showMyRecommendWeibo(int myuserId,int hisuserId, HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			String sql="select weiboId,content,dateLine,upvote,comment from weibo where recommend=1 and userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, hisuserId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int weiboId=res.getInt("weiboId");
				String content=res.getString("content");
				String dateLine=res.getString("dateLine");
				int upvote=res.getInt("upvote");
				int comment=res.getInt("comment");
		  	    
				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        boolean hasCollected = new CollectionDao().isCollected(myuserId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(myuserId,weiboId);
		        
		        Weibo weibo = new Weibo(weiboId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted);
		        list.add(weibo);
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
		Collections.reverse(list);
		return list;
	}

	//进行微博搜索时，将搜索出来的微博放入List中
	public List<Weibo> weiboSearchList(String searchKey, int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list = new LinkedList<Weibo>();
		try{
			String sql="select * from weibo,vip where content like '%?%' and vip.userId = weibo.userId";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setString(1, searchKey);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String username = res.getString("vip.username");
				int weiboId=res.getInt("weibo.weiboId");
				int weibouserId=res.getInt("weibo.userId");
				String content=res.getString("weibo.content");
				String dateLine=res.getString("weibo.dateLine");
				int upvote=res.getInt("weibo.upvote");
				int comment=res.getInt("weibo.comment");
				
				String searchContent = Text.textRed(content, searchKey);
				
				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        boolean hasCollected = new CollectionDao().isCollected(userId,weiboId);
		        boolean hasUpvoted = new UpvoteDao().isUpvoted(userId,weiboId);
		        Weibo weibo = new Weibo(weiboId,weibouserId,content,time,upvote,comment,pictureExist,hasCollected,hasUpvoted,username,searchContent);
		        list.add(weibo);
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
		Collections.reverse(list);
		return list;
	}

	public List<Weibo> showAllRecommendWeibo() {
		Connection con = null;
		PreparedStatement pre = null;
		List<Weibo> list=new LinkedList<Weibo>();
		try{
			String sql="select * from vip,weibo where recommend=1 and vip.userId=weibo.userId";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String username = res.getString("username");
				int userId = res.getInt("userId");
				int weiboId=res.getInt("weiboId");
				String content=res.getString("content");
				String dateLine=res.getString("dateLine");
				int upvote=res.getInt("upvote");
				int comment=res.getInt("comment");
				int followNum = res.getInt("follow");
				int fansNum = res.getInt("fans");
				int weiboNum = res.getInt("weibo");
		  	    
				java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    String time = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(timestamp); 
		  	    File file=new File("D:/Swit Project/MicroBlog/WebContent/weiboimages/"+weiboId+".jpg");
		        boolean pictureExist = file.exists();
		        
		        Vip vip = new Vip(followNum,fansNum,weiboNum);
		        Weibo weibo = new Weibo(userId,username,weiboId,content,time,upvote,comment,pictureExist,vip);
		        list.add(weibo);
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
		Collections.reverse(list);
		return list;
	}

}

