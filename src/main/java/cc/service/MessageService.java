package cc.service;


import cc.dao.*;
import cc.model.ChatMessage;
import cc.model.Vip;
import cc.model.Weibo;
import cc.servlet.GetChatMessage;
import cc.servlet.GetUnreadMes;
import cc.util.Code;
import cc.util.GetDate;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class MessageService {
	
	//检查微博内容长度是否符合要求
	public void buildContent(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException, ClassNotFoundException, SQLException{
		WeiboDao wd = new WeiboDao();
		MessageService mes=new MessageService();
		String dateLine = GetDate.getDateLine();      //取得时间
		List weibo_Picture=getWeiboAndPicture(request,response);
		String weibo = (String) weibo_Picture.get(0);
		String content = Code.htmlEncode(weibo);    //字符转义
		String username=(String)request.getSession().getAttribute("login");
		int userId = (int) request.getSession().getAttribute("userId");
		wd.addWeibo(username,content,dateLine);
		int weiboId = wd.showWeiboId(userId,content,dateLine);
		weibo_Picture.set(0, weiboId);
		
		if(weibo_Picture.get(1) != "false"){
			mes.pictureRename(weibo_Picture);
			}
		
		wd.setWeiboNumber(request,response,userId,username);
		setWeiboList(username,request,response);
	}
	
	//发微博时提交的表单为multipart/form-data类型，用于传递文件数据
	public List getWeiboAndPicture(HttpServletRequest request, HttpServletResponse response) throws FileUploadException, IOException{
		 FileItemFactory factory = new DiskFileItemFactory();  
		 ServletFileUpload upload = new ServletFileUpload(factory);  
		 List weibo_Picture = new LinkedList(); 
         List<FileItem> items = upload.parseRequest(request);  
         Iterator<FileItem> iter = items.iterator();  
         Map<String,String> params = new HashMap<String,String>();  
	     while (iter.hasNext())  {  
	        FileItem item = iter.next();  
	        if (item.isFormField())  {         //如果是普通表单类型，生成微博文本                      
	           String weibo = item.getString("utf-8");  
	           weibo_Picture.add(0, weibo);    
	        }
	        else{   //否则为文件，复制到文件夹
        	     InputStream in = item.getInputStream();  
                 byte[] buffer = new byte[1024];  
                 int len = 0; 
                 String fileName = item.getName();  
                 long sizeInBytes = item.getSize();  
                 System.out.println("图片名称："+fileName);  
                 System.out.println("图片大小："+sizeInBytes+"字节");
                 if(fileName!=""){               
                 String fileAddress = "../../../WebContent/weiboimages/picture.jpg";//文件最终上传的位置  
                 System.out.println("储存路径："+fileAddress);  
                 OutputStream out = new FileOutputStream(fileAddress);  
                 weibo_Picture.add(1, fileAddress);
                 while ((len = in.read(buffer)) != -1) {  
                         out.write(buffer, 0, len);  
                     }  
                 out.close();  
                 in.close();
                 }
                 else weibo_Picture.add(1, "false");
	        }
	    }      
	    return weibo_Picture;
	}

	//发微博时，用微博Id将微博的图片重命名
	public void pictureRename(List weibo_Picture) {
		String oldPath = (String) weibo_Picture.get(1);
		System.out.println("旧路径是"+oldPath);
		int weiboId = (int) weibo_Picture.get(0);
		String newPath = "../../../WebContent/weiboimages/"+weiboId+".jpg"; 
		File old = new File(oldPath);
		File rname = new File(newPath);
		System.out.println("新路径更改成功？"+old.renameTo(rname));
	}

	//删除微博时，将对应的微博图片删除
	public void deletePicture(String weiboId) {
		String path =  "../../../WebContent/weiboimages/"+weiboId+".jpg";
		File file = new File(path);
		System.out.println("已经删除该微博图片？"+file.delete());
	}
	
	//进入首页或进入他人主页时获取微博的List
	public void setWeiboList(String username,HttpServletRequest request, HttpServletResponse response){
		int userId = (int) request.getSession().getAttribute("userId");
		List<Weibo> list= new WeiboDao().showWeibo(username,userId, request, response);
		List<Weibo> list2= new WeiboDao().showOthersWeibo(userId, request, response);
		request.getSession().setAttribute("weiboList", list);    //进入他人主页是的微博List
		request.getSession().setAttribute("weiboList2", list2);  //进入首页时的微博List
	}

	//进入他人的个人主页时，获取其个人信息的Vip对象，获取其微博List，获取isFollow变量（是否已被关注）等
	public void setOthersHomeAttribute(HttpServletRequest request, HttpServletResponse response, String hisUsername) throws ClassNotFoundException, SQLException {
		WeiboDao wd = new WeiboDao();
		UserDao us = new UserDao();
		int myuserId = (int) request.getSession().getAttribute("userId");
		int hisUserId = us.getUserId(hisUsername);
		List<Weibo> list= wd.showWeibo(hisUsername,myuserId, request, response); 
		List<Weibo> list2= wd.showMyRecommendWeibo(myuserId,hisUserId,request,response);
		Vip hisVip=us.getVip(hisUsername,hisUserId);
		boolean isFollow =  new FollowDao().checkFollow(request,response,hisUserId);
		us.calibrateNum(hisUserId);
		request.getSession().setAttribute("hisVip", hisVip);
		request.getSession().setAttribute("personWeiboList", list);
		request.getSession().setAttribute("personRecommendWeiboList", list2);
		request.getSession().setAttribute("isFollow", isFollow);
	}

    //删除微博
	public void deleteService(HttpServletRequest request, HttpServletResponse response) {
		String weiboId=request.getParameter("weiboId");
		int userId=Integer.valueOf(request.getParameter("userId"));
		String username=(String)request.getSession().getAttribute("login");
		WeiboDao wbd=new WeiboDao();
		MessageService ms = new MessageService();
		wbd.deleteWeibo(weiboId,username);   //删除微博
		ms.deletePicture(weiboId);   //删除图片
		ms.setWeiboList(username,request,response);    //获取经删除之后的List
		Vip vip;
		try {
			vip = new UserDao().getVip(username,userId);
			request.getSession().setAttribute("vip", vip);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

    //搜索微博
	public void weiboSearch(HttpServletRequest request, HttpServletResponse response) {
		String searchKey = (String) request.getAttribute("searchKey");
		int userId = (int) request.getSession().getAttribute("userId");
		List<Weibo> list = new WeiboDao().weiboSearchList(searchKey,userId);
		if(list.size()==0){
			request.setAttribute("noResult", true);   //没有搜索结果
		}
		else {
			request.setAttribute("weiboResult", true);   //搜索微博
			request.setAttribute("searchList", list);
		}
		request.setAttribute("searchKey", searchKey);
	}

    //搜索用户
	public void userSearch(HttpServletRequest request, HttpServletResponse response) {
		String searchKey = request.getParameter("searchKey");
		searchKey = Code.htmlEncode(searchKey);
		int userId = (int) request.getSession().getAttribute("userId");
		List<Vip> list = new UserDao().userSearchList(searchKey,userId);
		if(list.size()==0){
			request.setAttribute("noResult", true);    //没有搜索结果
		}
		else {
			request.setAttribute("userResult", true);  //搜索用户
			request.setAttribute("searchList", list);
		}
		request.setAttribute("searchKey", searchKey);
	}


	//取得两人的聊天记录
	public void chatService(HttpServletRequest request, HttpServletResponse response) {
		ChatDao cd = new ChatDao();
		int myuserId = Integer.valueOf(request.getParameter("myuserId"));
		int hisuserId = Integer.valueOf(request.getParameter("hisuserId"));
		String hisusername = request.getParameter("hisusername");
		List<String> list = cd.getChatMessageList(myuserId,hisuserId);
		
		cd.isRead(myuserId,hisuserId);
		
		request.setAttribute("chatList", list);
		request.setAttribute("myuserId", myuserId);
		request.setAttribute("hisuserId", hisuserId);
		request.setAttribute("hisusername", hisusername);
	}

	public void collectionAttribute(int userId, String username, HttpServletRequest request,
			HttpServletResponse response) {
		WeiboDao wd = new WeiboDao();
		List<Integer> idList = new CollectionDao().getCollectionWeiboIdList(userId);
		List<Weibo> list = wd.getCollectionWeiboList(userId,idList);
		List<Weibo> list2= wd.showOthersWeibo(userId, request, response);
		List<Weibo> list3= wd.showWeibo(username,userId,request,response); 
		List<Weibo> list4= wd.showRecommendWeibo(userId,request,response);
		List<Weibo> list5= wd.showMyRecommendWeibo(userId,userId,request,response);
		request.getSession().setAttribute("collectionWeiboList", list);
		request.getSession().setAttribute("weiboList2", list2);
		request.getSession().setAttribute("weiboList", list3);
		request.getSession().setAttribute("recommendWeiboList", list4);
		request.getSession().setAttribute("myRecommendWeiboList", list5);
	}

	public void checkUnreadNum() {
		Set<String> sessions2 = GetUnreadMes.waitMap.keySet();
		Iterator<String> it2 = sessions2.iterator();
		while(it2.hasNext()){                       
			String userId = it2.next();
			ChatMessage msg = GetUnreadMes.waitMap.get(userId);
			int unread = new ChatDao().getUnreadNum(Integer.valueOf(userId));
			msg.setUnread(unread);
			synchronized (msg) {
				msg.notifyAll();        
			}
		}
	}

	//用户聊天  发新信息时，将新信息内容和时间放入 等待状态中的ChatMessage对象 并唤醒以传回JSP页面
	public void userChat(HttpServletRequest request, String chatmsg, String dateLine) {
		ChatDao cd = new ChatDao();
		int hisuserId = Integer.valueOf(request.getParameter("hisuserId"));
	    int myuserId = (int)request.getSession().getAttribute("userId");
	    cd.addChatMessage(myuserId,hisuserId,chatmsg,dateLine);
	    int chatId = cd.getChatId(myuserId,hisuserId,chatmsg,dateLine);
	    Set<String> sessions = GetChatMessage.waitMap.keySet();
		Iterator<String> it = sessions.iterator();
		while(it.hasNext()){                       //每个用户的页面中都会显示新信息（不同session）
			String userId = it.next();
			ChatMessage msg = GetChatMessage.waitMap.get(userId);
			it.remove();
			msg.setMessage(chatmsg);
			msg.setUserId(myuserId);
			msg.setBy_userId(hisuserId);
			msg.setDateLine(dateLine);
			msg.setChatId(chatId);
			synchronized (msg) {
				msg.notifyAll();        
			}
		}
	}

	//管理员私信  发新信息时，将新信息内容和时间放入 等待状态中的ChatMessage对象 并唤醒以传回JSP页面
	public void adminChat(HttpServletRequest request, String chatmsg, String dateLine) {
		int hisuserId = Integer.valueOf(request.getParameter("hisuserId"));
		new ChatDao().addAdminChatMessage(hisuserId,chatmsg,dateLine);
		Set<String> sessions = GetChatMessage.waitMap.keySet();
		Iterator<String> it = sessions.iterator();
		while(it.hasNext()){                       //每个用户的页面中都会显示新信息（不同session）
			String userId = it.next();
			ChatMessage msg = GetChatMessage.waitMap.get(userId);
			it.remove();
			msg.setMessage(chatmsg);
			msg.setUserId(0);
			msg.setBy_userId(hisuserId);
			msg.setDateLine(dateLine);
			synchronized (msg) {
				msg.notifyAll();        
			}
		}
	}
	
	//管理员群信  发新信息时，将新信息内容和时间放入 等待状态中的ChatMessage对象 并唤醒以传回JSP页面
	public void adminGroupChat(HttpServletRequest request, String chatmsg, String dateLine) {
		List<String> list = (List<String>) request.getSession().getAttribute("groupUserIdList");
		Iterator<String> userIdIt = list.iterator();
		while(userIdIt.hasNext()){
			int hisuserId = Integer.valueOf(userIdIt.next());
			new ChatDao().addGroupChatMessage(hisuserId,chatmsg,dateLine);
		}
		Set<String> sessions = GetChatMessage.waitMap.keySet();
		Iterator<String> it = sessions.iterator();
		while(it.hasNext()){                       //每个用户的页面中都会显示新信息（不同session）
			String userId = it.next();
			ChatMessage msg = GetChatMessage.waitMap.get(userId);
			it.remove();
			msg.setMessage(chatmsg);
			msg.setUserId(0);
			msg.setBy_userId(0);
			msg.setDateLine(dateLine);
			synchronized (msg) {
				msg.notifyAll();        
			}
		}
	}

	public void chatServiceToDelete(HttpServletRequest request, HttpServletResponse response) {
		ChatDao cd = new ChatDao();
		int myuserId = Integer.valueOf(request.getParameter("myuserId"));
		int hisuserId = Integer.valueOf(request.getParameter("hisuserId"));
		String hisusername = new UserDao().getUsernameById(hisuserId);
		cd.checkdeleteMes(myuserId,hisuserId);
		List<String> list = cd.getChatMessageList(myuserId,hisuserId);
		
		cd.isRead(myuserId,hisuserId);
		
		request.setAttribute("chatList", list);
		request.setAttribute("myuserId", myuserId);
		request.setAttribute("hisuserId", hisuserId);
		request.setAttribute("hisusername", hisusername);
	}

	public void homePageAttribute(HttpServletRequest request, HttpServletResponse response) {
		WeiboDao w = new WeiboDao();
		String username = (String) request.getSession().getAttribute("login");
		int userId = (int) request.getSession().getAttribute("userId");
		int weiboN = (int) request.getSession().getAttribute("weiboN");
		List<Weibo> list= w.showWeibo(username,userId,request,response); 
		List<Weibo> list2= w.showPartWeibo(userId,request,response,weiboN);
		List<Weibo> list3= w.showRecommendWeibo(userId,request,response);
		List<Weibo> list4= w.showMyRecommendWeibo(userId,userId,request,response);
		request.getSession().setAttribute("weiboList", list);
		request.getSession().setAttribute("weiboList2", list2);
		request.getSession().setAttribute("recommendWeiboList", list3);
		request.getSession().setAttribute("myRecommendWeiboList", list4);
	}
}
