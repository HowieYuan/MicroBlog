package cc.dao;

import cc.model.Vip;
import cc.util.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class ChatDao {
    //获得两人聊天的聊天记录
	public List<String> getChatMessageList(int myuserId, int hisuserId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<String> list = new LinkedList<String>(); 
		try{
			String sql ="select * from chat where (userId=? or userId=?) "
					+ "and (by_userId=? or by_userId=?) and adminchat=0 "
					+ "and delete_user<>? and delete_by_user<>?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, myuserId);
			pre.setInt(2, hisuserId);
			pre.setInt(3, myuserId);
			pre.setInt(4, hisuserId);
			pre.setInt(5, myuserId);
			pre.setInt(6, myuserId);
		    ResultSet res=pre.executeQuery();
			while(res.next()){
				int chatId = res.getInt("chatId");
                int userId = res.getInt("userId");
                String content = res.getString("message");
                String dateLine = res.getString("dateLine");
                
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    dateLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp); 

		  	    if(userId == myuserId){   //若是自己的聊天记录
    				content = "<img class='myhead' src='./headimages/"+myuserId+".jpg'><div class='myarrow'></div>"
    						+ "<div class='mydate'>"+dateLine+"</div>"
    						+ "<div class='mymessage' onclick='deletechatms("+chatId+")'>"+content+"</div>"
    					    + "<form method='post' action='deleteChatMes?action=my&hisuserId="+hisuserId+"&chatId="+chatId+"&myuserId="+myuserId+"'>"
    					    + "<button class='deletemychatms' id='deletechatms"+chatId+"' style='display:none'>删除</button></form>";
                }//若是对方的聊天记录
                else content = "<img class='hishead' src='./headimages/"+hisuserId+".jpg'><div class='hisarrow'></div>"
                		     + "<div class='hisdate'>"+dateLine+"</div>"
                		     + "<div class='hismessage' onclick='deletechatms("+chatId+")'>"+content+"</div>"
                		     + "<form method='post' action='deleteChatMes?action=his&hisuserId="+hisuserId+"&chatId="+chatId+"&myuserId="+myuserId+"'>"
                		     + "<button class='deletehischatms' id='deletechatms"+chatId+"' style='display:none'>删除</button></form>";
                list.add(content);
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
		return null;
	}

	//获得管理员发送给用户的信息记录
		public List<String> getAdminChatMessageList(int userId) {
			Connection con = null;
			PreparedStatement pre = null;
			List<String> list = new LinkedList<String>(); 
			try{
				String sql ="select chatId,message,dateLine from chat where by_userId=? and (adminchat=1 or adminchat=2)"
						   + "and delete_user<>? and delete_by_user<>?";
				con=Connect.getCon();		
			    pre=con.prepareStatement(sql);
			    pre.setInt(1, userId);
				pre.setInt(2, userId);
				pre.setInt(3, userId);
				ResultSet res=pre.executeQuery();
				while(res.next()){
					int chatId = res.getInt("chatId");
					String content = res.getString("message");
	                String dateLine = res.getString("dateLine");
	                
	                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
			  	    dateLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp); 
	                
					content = "<img class='myhead' src='./headimages/admin.jpg'><div class='myarrow'></div>"
							+ "<div class='mydate'>"+dateLine+"</div>"
							+ "<div class='mymessage' onclick='deletechatms("+chatId+")'>"+content+"</div>"
					        + "<form method='post' action='deleteChatMes?action=admin&hisuserId="+userId+"&chatId="+chatId+"&myuserId="+userId+"'>"
       		                + "<button class='deletehischatms' id='deletechatms"+chatId+"' style='display:none'>删除</button></form>";
	                list.add(content);
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
			return null;
		}
	
	//将用户聊天记录写入数据库，并标记为未读，记为1，用户的聊天记录记为0
	public void addChatMessage(int myuserId, int hisuserId, String chatmsg, String dateLine) {
		Connection con=null;
		PreparedStatement pre=null;
		try{
			String sql="insert into chat values (null,?,?,?,?,0,1,0,0)";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, myuserId);
			pre.setInt(2, hisuserId);
			pre.setString(3, chatmsg);
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

	

	//将管理员发给用户的信息记录写入数据库，记为1，并标记为未读状态，记为1
	public void addAdminChatMessage(int hisuserId, String chatmsg, String dateLine) {
		Connection con=null;
		PreparedStatement pre=null;
		try{
			String sql="insert into chat values (null,?,?,?,?,1,1,0,0)";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, hisuserId);
			pre.setInt(2, hisuserId);
			pre.setString(3, chatmsg);
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

	//获得管理员群发给用户的信息记录
	public List<String> getAdminGroupChatMessageList() {
		Connection con = null;
		PreparedStatement pre = null;
		List<String> list = new LinkedList<String>(); 
		try{
			String sql ="select distinct message,dateLine from chat where adminchat=2";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
                String content = res.getString("message");
                String dateLine = res.getString("dateLine");
                
                java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(dateLine);
		  	    dateLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp); 
                
				content = "<img class='myhead' src='./headimages/admin.jpg'><div class='myarrow'></div>"
						+ "<div class='mydate'>"+dateLine+"</div>"
						+ "<div class='mymessage'>"+content+"</div>";
                list.add(content);
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
		return null;
	}

	//将管理员群发的信息写入数据库，记为2，标记为未读，记为1
	public void addGroupChatMessage(int hisuserId, String chatmsg, String dateLine) {
		Connection con=null;
		PreparedStatement pre=null;
		try{
			String sql="insert into chat values (null,?,?,?,?,2,1,0,0)";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, hisuserId);
			pre.setInt(2, hisuserId);
			pre.setString(3, chatmsg);
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

	//获得某用户未读信息数，同一个人发的信息同记为1
	public int getUnreadNum(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		int num=0;
		try{
			String sql ="select distinct userId,unread from chat where unread=1 and by_userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				num++;
			}
			return num;
		   }catch(ClassNotFoundException | SQLException e){
	         e.printStackTrace();
            }finally{
			    try {
				  Connect.closeCon(pre, con);
			    } catch (Exception e) {
				   e.printStackTrace();
			    }
	         }
		return num;
	}

	//获得List，装入未读信息的用户
	public List<Vip> getUnreadList(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Vip> list = new LinkedList<Vip>();
		try{
			String sql ="select distinct userId,unread from chat where unread=1 and by_userId=? and userId<>?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, userId);
			pre.setInt(2, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int hisuserId = res.getInt("userId");
				Vip user = new UserDao().getVip(null, hisuserId);
				list.add(user);
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
		return null;
	}

	//当用户读取了信息，原信息标记改为已读，记为0
	public void isRead(int userId,int hisuserId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			con=Connect.getCon();
			String sql ="update chat set unread=0 where by_userId=? and userId=?";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, hisuserId);
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

	public int getChatId(int myuserId, int hisuserId, String chatmsg, String dateLine) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql ="select chatId from chat where by_userId=? and userId=? and message=? and dateLine=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setInt(1, hisuserId);
			pre.setInt(2, myuserId);
			pre.setString(3, chatmsg);
			pre.setString(4, dateLine);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int chatId = res.getInt("chatId");
				return chatId;
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
		return 0;
	}

	public void deleteMyChatMes(int userId, int chatId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			con=Connect.getCon();
			String sql ="update chat set delete_user=? where chatId=?";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, chatId);
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

	public void deleteHisChatMes(int userId, int chatId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			con=Connect.getCon();
			String sql ="update chat set delete_by_user=? where chatId=?";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setInt(2, chatId);
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

	public void checkdeleteMes(int myuserId, int hisuserId) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			String sql ="select chatId,delete_by_user,delete_user from chat";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int delete_user = res.getInt("delete_user");
				int delete_by_user = res.getInt("delete_by_user");
				int chatId = res.getInt("chatId");
				if( (delete_user==myuserId || delete_by_user==myuserId) && (delete_user==hisuserId || delete_by_user==hisuserId)){
					String sql2="delete from chat where chatId="+chatId;
					pre2=con.prepareStatement(sql2);
					pre2.executeUpdate();
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
	}

	public void checkdeleteadminMes(int myuserId) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			String sql ="select chatId,delete_by_user,delete_user from chat";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int delete_user = res.getInt("delete_user");
				int delete_by_user = res.getInt("delete_by_user");
				int chatId = res.getInt("chatId");
				if( delete_user==myuserId && delete_by_user==myuserId){
					String sql2="delete from chat where chatId="+chatId;
					pre2=con.prepareStatement(sql2);
					pre2.executeUpdate();
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
	}

}
