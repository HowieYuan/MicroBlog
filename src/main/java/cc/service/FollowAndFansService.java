package cc.service;

import cc.dao.FollowDao;
import cc.dao.UserDao;
import cc.model.Vip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FollowAndFansService {
    //获取List ，放入被关注的用户
	public void setFollowList(HttpServletRequest request, HttpServletResponse response, int userId) throws ClassNotFoundException, SQLException {
		FollowDao f = new FollowDao();
		UserDao u = new UserDao();
		List<Vip> followList = new LinkedList<Vip>();
		List<Integer> userIdList = f.getFollowUserIdList(userId);
		Iterator<Integer> i = userIdList.iterator();
		while(i.hasNext()){
			int by_userId = (int) i.next();
			Vip followVip = u.getVip(null,by_userId);
			followList.add(followVip);
		}
		request.getSession().setAttribute("follow_fansList",followList);
	}

	////获取List ，放入某用户的粉丝
	public void setFansList(HttpServletRequest request, HttpServletResponse response, int userId) throws ClassNotFoundException, SQLException {
		FollowDao f = new FollowDao();
		UserDao u = new UserDao();
		List<Vip> fansList = new LinkedList<Vip>();
		List<Integer> userIdList = f.getFansUserIdList(userId);
		Iterator<Integer> i = userIdList.iterator();
		while(i.hasNext()){
			int fansId = (int) i.next();
			Vip fansVip = u.getVip(null,fansId);
			fansList.add(fansVip);
		}
		request.getSession().setAttribute("follow_fansList",fansList);
	}

	//进行关注处理
	public void followPeople(HttpServletRequest request, HttpServletResponse response) {
		FollowDao f = new FollowDao();
		int userId = (int) request.getSession().getAttribute("userId");
		Vip hisVip = (Vip) request.getSession().getAttribute("hisVip");
		int by_userId = hisVip.getUserId();
		boolean isFollow = (boolean) request.getSession().getAttribute("isFollow");
		if(isFollow){                                         
			f.cancelFollow(userId,by_userId);
			request.getSession().setAttribute("isFollow", false);
		}    //如果当前用户已被关注，则取消关注，否则，则进行关注
		else{
			f.setFollow(userId,by_userId);
			request.getSession().setAttribute("isFollow", true);
		}
		f.setFollowAndfansNumber(request,response,userId,by_userId);
	}

	//根据JSP传入的action参数 进行判断 以进行相应操作  用于显示关注用户或粉丝页面
	public void followAndfansAction(int userId, String action, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(action.equals("follow")){
			try {
				setFollowList(request,response,userId);
				request.getSession().setAttribute("isFollowNum", true);
				response.sendRedirect("follow_Fans.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else if(action.equals("fans")){
			try {
				setFansList(request,response,userId);
				request.getSession().setAttribute("isFollowNum", false);
				response.sendRedirect("follow_Fans.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else if(userId==Integer.parseInt(request.getParameter("hisuserId"))){
			response.sendRedirect("home.jsp");
		}
		else if(action.equals("hisfollow")){
			try {
				
				setFollowList(request,response,Integer.parseInt(request.getParameter("hisuserId")));
				request.getSession().setAttribute("hisVip",new UserDao().getVip(null, Integer.parseInt(request.getParameter("hisuserId"))));
				request.getSession().setAttribute("isFollowNum", true);
				response.sendRedirect("hisfollow_Fans.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				setFansList(request,response,Integer.parseInt(request.getParameter("hisuserId")));
				request.getSession().setAttribute("hisVip",new UserDao().getVip(null, Integer.parseInt(request.getParameter("hisuserId"))));
				request.getSession().setAttribute("isFollowNum", false);
				response.sendRedirect("hisfollow_Fans.jsp");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void setFollowListAdmin(HttpServletRequest request, HttpServletResponse response, int userId) throws ClassNotFoundException, SQLException {
		FollowDao f = new FollowDao();
		UserDao u = new UserDao();
		List<Vip> followList = new LinkedList<Vip>();
		List<Integer> userIdList = f.getFollowUserIdList(userId);
		Iterator<Integer> i = userIdList.iterator();
		while(i.hasNext()){
			int by_userId = (int) i.next();
			Vip followVip = u.getVip(null,by_userId);
			followList.add(followVip);
		}
		request.setAttribute("follow_fansList",followList);
	}

	public void setFansListAdmin(HttpServletRequest request, HttpServletResponse response, int userId) throws ClassNotFoundException, SQLException {
		FollowDao f = new FollowDao();
		UserDao u = new UserDao();
		List<Vip> fansList = new LinkedList<Vip>();
		List<Integer> userIdList = f.getFansUserIdList(userId);
		Iterator<Integer> i = userIdList.iterator();
		while(i.hasNext()){
			int fansId = (int) i.next();
			Vip fansVip = u.getVip(null,fansId);
			fansList.add(fansVip);
		}
		request.setAttribute("follow_fansList",fansList);
	}
	
}
