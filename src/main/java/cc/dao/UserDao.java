package cc.dao;

import cc.model.Vip;
import cc.util.Code;
import cc.util.Connect;
import cc.util.Text;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserDao {
    //获得Vip对象，内有该用户基本信息，粉丝关注微博数，userId，用户名等
	public Vip getVip(String username2, int userId2) throws SQLException, ClassNotFoundException{
		Connection con = null;
		PreparedStatement pre = null;
	    String Sql="select * from vip where username=? or userId=?";		
		con=Connect.getCon();
		pre=con.prepareStatement(Sql);
		pre.setString(1, username2);
		pre.setInt(2, userId2);
		ResultSet res=pre.executeQuery();
		res.next();
		int userId = res.getInt("userId");
		String username=res.getString("username");
		String phonenumber=res.getString("phonenumber");
		String email=res.getString("email");
		String password=res.getString("password");
		String realname=res.getString("realname");
		String sex=res.getString("sex");
		String birthday=res.getString("birthday");
		String qq=res.getString("qq");
		String address=res.getString("address");
		int follow=res.getInt("follow");
		int fans=res.getInt("fans");
		int weibo=res.getInt("weibo");
		Vip vip=new Vip(userId,phonenumber,username,email,password,realname,sex,birthday,qq,address,follow,fans,weibo);
		try {
			Connect.closeCon(pre, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vip;
	}
	
	//将用户信息写入数据库
	public void createUser(Vip vip) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con=Connect.getCon();
			String sql ="insert into vip values (null,?,?,?,?,'','','','','',0,0,0,0)";
			pre =con.prepareStatement(sql);
			String encryptionPassword = Code.encryption(vip.getPassword());
			pre.setString(1, vip.getPhonenumber());
			pre.setString(2, vip.getUsername());
			pre.setString(3, vip.getEmail());
			pre.setString(4, encryptionPassword);
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
	
	//检查用户名是否已存在于数据库
	public boolean checkName(String username) {
		Connection con = null;
		PreparedStatement pre = null;	
		try{
			String sql ="select username from vip";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String name = res.getString("username");
				if(username.equals(name)){
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
	
	//更改用户名时检查该用户名是否已被他人使用（忽略用户原本的用户名），是则返回true
	public boolean checkName2(String username,String oldname) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			int id =getUserId(oldname);
			System.out.println("原用户名的ID是"+id);
			String sql ="select username from vip where userId not in (?)";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
			pre.setInt(1, id);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String name = res.getString("username");
				if(username.equals(name)){
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
	
	//检查邮箱地址是否已存在于数据库
	public boolean checkEmail(String email) {
		Connection con = null;
		PreparedStatement pre = null;	
		try{
			String sql ="select email from vip";
			con=Connect.getCon();		
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String email2=res.getString("email");
				if(email.equals(email2)){
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
	
	
	//登录时，检查输入的用户名和密码是否正确
	public boolean checkAccountnumberAndPassword(String accountnumber, String password){
		Connection con = null;
		PreparedStatement pre = null;
		String encryptionPassword = Code.encryption(password);   //加密密码   
		try{
			String sql="select phonenumber,email,password from vip";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String email = res.getString("email");
				String phonenumber = res.getString("phonenumber");
				String Password = res.getString("password");
				if((accountnumber.equals(phonenumber)||accountnumber.equals(email))&&encryptionPassword.equals(Password)){
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
	
	//根据用户名取得用户ID
	public int getUserId(String name) throws ClassNotFoundException, SQLException{
		Connection con = null;
		PreparedStatement pre = null;
	    String Sql="select userId from vip where username=?";		
		con=Connect.getCon();
		pre=con.prepareStatement(Sql);
		pre.setString(1, name);
		ResultSet res=pre.executeQuery();
		res.next();
		int id=res.getInt("userId");		
		try {
			Connect.closeCon(pre, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;       
	}

	//注册时，检查输入的电话号码是否已被他人注册
	public boolean checkPhonenumber(String phonenumber) {
		Connection con = null;
		PreparedStatement pre = null;	
		try{
			String sql ="select phonenumber from vip";
			con=Connect.getCon();		
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String number=res.getString("phonenumber");
				if(phonenumber.equals(number)){
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

	//通过输入的邮箱地址或手机号码，从数据库获取用户名
	public String getUsername(String accountnumber) throws SQLException, ClassNotFoundException {
		String sql=null;
		if(accountnumber.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")){
			sql="select username from vip where phonenumber=?";
		}
		else sql="select username from vip where email=?";
	    Connection con=Connect.getCon();
		PreparedStatement pre=con.prepareStatement(sql);
		pre.setString(1, accountnumber);
		ResultSet res=pre.executeQuery();
		res.next();
		String username=res.getString("username");		
		try {
			Connect.closeCon(pre, con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	//改变用户基本信息
	public void changeInformation(Vip vip,String name) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			int id=getUserId(name);
			System.out.println("用户ID为"+id);
			con=Connect.getCon();
			String sql ="update vip set username=?,realname=?,sex=?,birthday=?,qq=?,address=? where userId=?";
			pre =con.prepareStatement(sql);
			pre.setString(1, vip.getUsername());
			pre.setString(2, vip.getRealname());
			pre.setString(3, vip.getSex());
			pre.setString(4, vip.getBirthday());
			pre.setString(5, vip.getQq());
			pre.setString(6, vip.getAddress());
			pre.setInt(7, id);
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

	//将新密码替换旧密码
	public void changePassword(String newpassword, String username) {
		Connection con = null;
		PreparedStatement pre = null;
		String encryptionPassword = Code.encryption(newpassword);
		try{
			con=Connect.getCon();
			System.out.println(newpassword);
			String sql ="update vip set password=? where username=?";
			pre =con.prepareStatement(sql);
			pre.setString(1, encryptionPassword);
			pre.setString(2, username);
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

	//更改密码时检查输入的原密码是否正确
	public boolean checkPassword(String oldpassword, String username) {
		Connection con = null;
		PreparedStatement pre = null;	
		String encryptionPassword = Code.encryption(oldpassword);
		try{
			String sql ="select password from vip where username=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre.setString(1, username);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String password = res.getString("password");
				if(encryptionPassword.equals(password)){
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

	//检查该邮箱地址和手机号码是否存在于同一用户中
	public boolean checkEmailAndPhonenumber(String email, String phonenumber) {
		Connection con = null;
		PreparedStatement pre = null;		
		try{
			String sql="select phonenumber,email from vip";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String email2 = res.getString("email");
				String phonenumber2 = res.getString("phonenumber");
				if( phonenumber.equals(phonenumber2) && email.equals(email2) ){
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

	//校准用户微博数，粉丝数，关注数
	public void calibrateNum(int userId) {
		int i=0,j=0,k=0;
		Connection con = null;
		PreparedStatement pre = null,pre2 = null,pre3 = null,pre4 = null;
		try{
			String sql ="select weiboId from weibo where userId=?";
			String sql2 ="select by_userId from follow where userId=?";
			String sql3 ="select userId from follow where by_userId=?";
			con=Connect.getCon();		
		    pre=con.prepareStatement(sql);
		    pre2=con.prepareStatement(sql2);
		    pre3=con.prepareStatement(sql3);
		    pre.setInt(1, userId);
		    pre2.setInt(1, userId);
		    pre3.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				i++;
			}
			ResultSet res2=pre2.executeQuery();
			while(res2.next()){
				j++;
			}
			ResultSet res3=pre3.executeQuery();
			while(res3.next()){
				k++;
			}
			String sql4 ="update vip set follow=?,fans=?,weibo=? where userId=?";
			pre4=con.prepareStatement(sql4);
			pre4.setInt(1, j);
			pre4.setInt(2, k);
			pre4.setInt(3, i);
			pre4.setInt(4, userId);
			pre4.executeUpdate();
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

	//管理员登录时，检查管理员账号密码是否正确
	public boolean checkAdminnumberAndPassword(String adminnumber, String password) {
		Connection con = null;
		PreparedStatement pre = null;
		String encryptionPassword = Code.encryption(password);   //加密密码   
		try{
			String sql="select adminnumber,password from admin";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String adminnumber2 = res.getString("adminnumber");
				String password2 = res.getString("password");
				if(adminnumber.equals(adminnumber2)&&encryptionPassword.equals(password2)){
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

	//获得List，放入所有已注册的用户对象
	public List<Vip> getUserList() {
		Connection con = null;
		PreparedStatement pre = null,pre2 = null;
		List<Vip> list = new LinkedList<Vip>();
		boolean isFreeze;
		boolean login;
		int userId;
		try{                            //vip left join freeze
			String sql="select vip.userId from vip left join freeze on vip.userId=freeze.userId";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				userId = res.getInt("userId");
				new UserDao().calibrateNum(userId);  //校准用户粉丝，微博等数目
			}	
			String sql2="select * from vip left join freeze on vip.userId=freeze.userId";
			pre2=con.prepareStatement(sql2);
			ResultSet res2=pre2.executeQuery();
			while(res2.next()){
				userId = res2.getInt("userId");
				String username = res2.getString("username");
				String realname = res2.getString("realname");
				String sex = res2.getString("sex");
				String birthday = res2.getString("birthday");
				String qq = res2.getString("qq");
				String address = res2.getString("address");
				int follow = res2.getInt("follow");
				int fans =res2.getInt("fans");
				int weibo = res2.getInt("weibo");
				int loginstatus = res2.getInt("login");
				
				if(loginstatus==1) login = true;
				else login = false;
				
				String endTime = res2.getString("endTime");
				if(endTime!=null){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date end = df.parse(endTime);
				endTime = new SimpleDateFormat("yyyy年MM月dd日HH时").format(end); 
				isFreeze = true;
				}
				else isFreeze=false;          //作判断，判断改用户是否被封号，若是则将封号结束时间放入该对象中，否则则无
				
				Vip vip = new Vip(userId,username,realname,sex,birthday,qq,address,follow,fans,weibo,endTime,isFreeze,login);
				list.add(vip);
			}
			return list;
		}catch(ClassNotFoundException | SQLException | ParseException e){
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

	//当用户发微博后，更改该用户的微博数，加1
	public int resetWeiboNum(String username,int i) {
		Connection con = null;
		PreparedStatement pre = null,pre2=null;
		try{
			
			con=Connect.getCon();
			String sql ="select weibo from vip where username=?";
			pre=con.prepareStatement(sql);
			pre.setString(1, username);
			ResultSet res=pre.executeQuery();
			res.next();
			int weibo=res.getInt("weibo");
			
			String sql2 ="update vip set weibo=? where username=?";
			pre2 =con.prepareStatement(sql2);
			pre2.setString(1, username);
			pre2.setInt(1, weibo+i);
			pre2.executeUpdate();
			return weibo+i;
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

	//进行用户搜索时，将搜索出来的用户对象放入List中
	public List<Vip> userSearchList(String searchKey, int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		List<Vip> list = new LinkedList<Vip>();
		try{
			String sql="select * from vip where username like '%?%'";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setString(1, searchKey);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int hisuserId = res.getInt("userId");
				String username=res.getString("username");
				int follow=res.getInt("follow");
				int fans=res.getInt("fans");
				int weibo=res.getInt("weibo");
				String searchUsername = Text.textRed(username, searchKey);
				
				Vip vip = new Vip(hisuserId,username,searchUsername,follow,fans,weibo);
				list.add(vip);
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

	//封号，写入数据库
	public void freeze(int userId, String startTime, String endTime) {
		Connection con = null;
		PreparedStatement pre = null;
		try{		
			con=Connect.getCon();
			String sql ="insert into freeze values (null,?,?,?)";
			pre =con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.setString(2, startTime);
			pre.setString(3, endTime);
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

	//如果登录用户已被封号，则返回封号截止时间
	public String[] getFreezeEndTime(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="select startTime,endTime from freeze where userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String startTime=res.getString("startTime");
				String endTime=res.getString("endTime");
				String[] date = {startTime,endTime};
				return date;
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
		return null;
	}

	//用户登录时，检查该用户是否被封号
	public boolean checkFreeze(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="select userId from freeze";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				int userId2 = res.getInt("userId");
				if(userId2==userId){
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

	//取消封号，解封
	public void cancelFreeze(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="delete from freeze where userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
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

	//管理员发起群信时，根据管理员选择的用户，将加入群信的用户的userId放入List中
	public List<String> getGroupIdList(HttpServletRequest request, HttpServletResponse response) {
		Connection con = null;
		PreparedStatement pre = null;
		String userId;
		List<String> list = new LinkedList<String>();
		try{
			String sql="select userId from vip";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				userId = res.getString("userId");    //为了便于后面的处理，这里的userId为String类型
				userId = request.getParameter("chatselectbox2"+userId);
				if(!(userId == null)){
					list.add(userId);
				}
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

	//当用户登录以后 表中login字段变为1
	public void beingLogin(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql ="update vip set login=1 where userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.executeUpdate();
		   }catch(SQLException | ClassNotFoundException e){
	         e.printStackTrace();
            }finally{
			    try {
				  Connect.closeCon(pre, con);
			    } catch (Exception e) {
				   e.printStackTrace();
			    }
	         }
	}

	//当用户下线以后 表中login字段变为0
	public void beingOutLogin(int userId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql ="update vip set login=0 where userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, userId);
			pre.executeUpdate();
		   }catch(SQLException | ClassNotFoundException e){
	         e.printStackTrace();
            }finally{
			    try {
				  Connect.closeCon(pre, con);
			    } catch (Exception e) {
				   e.printStackTrace();
			    }
	         }
	}

	public String getUsernameById(int hisuserId) {
		Connection con = null;
		PreparedStatement pre = null;
		try{
			String sql="select username from vip where userId=?";
			con=Connect.getCon();
			pre=con.prepareStatement(sql);
			pre.setInt(1, hisuserId);
			ResultSet res=pre.executeQuery();
			while(res.next()){
				String username = res.getString("username");   
				return username;
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
		return null;
	}

}
