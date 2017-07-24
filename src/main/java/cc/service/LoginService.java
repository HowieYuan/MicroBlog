package cc.service;

import cc.dao.UserDao;
import cc.dao.WeiboDao;
import cc.model.Vip;
import cc.model.Weibo;
import cc.util.Code;
import cc.util.GetDate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class LoginService {
	//检查输入的和账号和密码是否正确
	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) {
		String accountnumber=request.getParameter("accountnumber");
		String password=request.getParameter("password");
		
		if(new UserDao().checkAccountnumberAndPassword(accountnumber, password)){
			return true;
		}
		return false;
	}
	
	//登录验证码图案
	public BufferedImage drawCodeImage(HttpServletRequest request, HttpServletResponse response){
		 Random random = new Random();
	        int size = 5;
	        String vCode = "";
	        char c;

	        for (int i = 0; i < size; i++) {
	            int number = random.nextInt(26);
	            if (number % 2 == 0) {
	                c = (char)('0'+(char)((int)(Math.random()*10)));
	            } 
	            else {
	                c = (char)((char)((int)(Math.random()*26))+'A');
	            }
	            vCode = vCode + c;
	        }
            System.out.println(vCode);
	        request.getSession().setAttribute("vCode", vCode);

	        int width = (int) Math.ceil(size * 20);
	        int height = 50;
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics gr = image.getGraphics();
	        gr.setColor(Color.WHITE);
	        gr.fillRect(0, 0, width, height);
	        gr.setColor(Color.GRAY);
	        gr.drawRect(0, 0, width - 1, height - 1);
	        for (int i = 0; i < 5; i++) {
	            int x1 = random.nextInt(width);
	            int y1 = random.nextInt(height);
	            int x2 = random.nextInt(width);
	            int y2 = random.nextInt(height);
	            gr.setColor(randomColor());
	            gr.drawLine(x1, y1, x2, y2);
	        }
	        gr.setColor(randomColor());
	        gr.setFont(randomFont());
	        gr.drawString(vCode, 10, 22);
	        gr.dispose();
	        return image;
	}
	
	//随机生成验证码颜色
	public Color randomColor() {
        Random r = new Random();
    	int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }
	
    // 生成随机的字体
    private Font randomFont() {
    	String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
        Random r = new Random();
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];// 生成随机的字体名称
        int style = r.nextInt(4);
        int size = r.nextInt(3) + 24; // 生成随机字号, 24 ~ 28
        return new Font(fontName, style, size);
    }

    //检查输入的验证码是否正确
	public boolean checkCode(HttpServletRequest request, HttpServletResponse response) {
		String vCode = (String) request.getSession().getAttribute("vCode"); 
		String checkcode = request.getParameter("checkcode");
		checkcode = checkcode.toUpperCase();
		System.out.println(vCode+"   "+checkcode);    //在控制台输出输入的验证码和正确的验证码
		if(vCode.equals(checkcode)){
			return true;
		}
		else return false;
	}

	//登录时将一系列信息放入Session，如userId，username，用户个人主页的微博等
	public void setUserAttribute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		WeiboDao w = new WeiboDao();
		UserDao u = new UserDao();
		String accountnumber=request.getParameter("accountnumber");
		String username = u.getUsername(accountnumber);
		int userId = u.getUserId(username);
		int weiboN=3;
		u.calibrateNum(userId);   //校准用户微博数，粉丝数，关注数
		
		Vip vip=u.getVip(username,userId);
		String email= Code.emailCode(vip.getEmail());
		String phonenumber= Code.phonenumberCode(vip.getPhonenumber());
		
		u.beingLogin(userId);     //当前用户已上线，数据库login字段记为1
		
		List<Weibo> list= w.showWeibo(username,userId,request,response);
		List<Weibo> list2= w.showPartWeibo(userId,request,response,weiboN);
		List<Weibo> list3= w.showRecommendWeibo(userId,request,response);
		List<Weibo> list4= w.showMyRecommendWeibo(userId,userId,request,response);
		
		request.getSession().setAttribute("login",username);
		request.getSession().setAttribute("userId",userId);
		request.getSession().setAttribute("vip", vip);
		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("phonenumber", phonenumber);
		request.getSession().setAttribute("weiboList", list);
		request.getSession().setAttribute("weiboList2", list2);
		request.getSession().setAttribute("recommendWeiboList", list3);
		request.getSession().setAttribute("myRecommendWeiboList", list4);
	}
	
    //当用户修改个人信息后，将更改后的新信息放入Session，例如当用户改变了用户名，每天微博对应的用户名也要对应修改
	public void setUserAttribute2(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		WeiboDao wd = new WeiboDao();
		String username = (String) request.getSession().getAttribute("login");
		int userId = new UserDao().getUserId(username);
		Vip vip=new UserDao().getVip(username,userId);
		
		List<Weibo> list= wd.showWeibo(username,userId, request, response);
		List<Weibo> list2= wd.showOthersWeibo(userId, request, response);
		
		request.getSession().setAttribute("login",username);
		request.getSession().setAttribute("userId",userId);
		request.getSession().setAttribute("vip", vip);
		request.getSession().setAttribute("weiboList", list);
		request.getSession().setAttribute("weiboList2", list2);
	}

	//检查管理员登录时账号密码是否正确
	public boolean checkAdminLogin(HttpServletRequest request, HttpServletResponse response) {
		String adminnumber=request.getParameter("accountnumber");
		String password=request.getParameter("password");
		
		if(new UserDao().checkAdminnumberAndPassword(adminnumber, password)){
			return true;
		}
		return false;
	}

	public void setAdminAttribute(HttpServletRequest request, HttpServletResponse response) {
		String adminnumber = request.getParameter("accountnumber");
		List<Vip> userList = new UserDao().getUserList();
		
		request.getSession().setAttribute("userId", "admin");
		request.getSession().setAttribute("login", adminnumber);
		request.getSession().setAttribute("adminKey", "admin");
		request.getSession().setAttribute("userList", userList);
	}

	//进行对用户的封号处理
	public void freeze(HttpServletRequest request, HttpServletResponse response) {
		int year = Integer.valueOf(request.getParameter("year"));
		int month = Integer.valueOf(request.getParameter("month"));
		int day = Integer.valueOf(request.getParameter("day"));
		int hour = Integer.valueOf(request.getParameter("hour"));
		int userId = Integer.valueOf(request.getParameter("userId"));
		year = year+2000;
		String endTime = year+"-"+month+"-"+day+" "+hour+":00:00";
		System.out.println(endTime);
		String startTime = GetDate.getDateLine();
		new UserDao().freeze(userId,startTime,endTime);    //获得封号时间，封号截止时间
	}

	//检测用户是否被封号
	public boolean checkFreeze(HttpServletRequest request, HttpServletResponse response){
		try {
			UserDao u = new UserDao();
			String accountnumber=request.getParameter("accountnumber");
			String username = u.getUsername(accountnumber);
			int userId = u.getUserId(username);
			if(u.checkFreeze(userId)){
				String nowTime = GetDate.getDateLine();
				String[] date = u.getFreezeEndTime(userId);
				String startTime = date[0];
				String endTime = date[1];
				if(GetDate.compareDate(nowTime,endTime)){
					u.cancelFreeze(userId);
					return false;
				}
				else{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date start = df.parse(startTime);
					Date end = df.parse(endTime);
					startTime = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(start); 
					endTime = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(end); 
					request.getSession().setAttribute("startTime", startTime);
					request.getSession().setAttribute("endTime", endTime);
					return true;
				}
			}
			else return false;
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

	//取消对用户的封号
	public void cancelFreeze(HttpServletRequest request, HttpServletResponse response) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		new UserDao().cancelFreeze(userId);
	}

	//登录时，若选取的记住账号或记住密码，则进行相应处理
	public void setLoginRemember(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookie = request.getCookies();
		if(cookie!=null){
			for(int i=0;i<cookie.length;i++){
				if(cookie[i].getName().equals("rememberUser")) {
					cookie[i].setMaxAge(0);              
					response.addCookie(cookie[i]);     //先将之前用于储存记住账号或密码的信息的cookie销毁，将其时间设置为0
					System.out.println("账号保存");
				}
				else if(cookie[i].getName().equals("rememberPass")) {
					cookie[i].setMaxAge(0);
					response.addCookie(cookie[i]);
					System.out.println("密码保存");
				}
			}
		}
		
		//设置新的cookie，若是记住账号，则存储入登录账号，若是记住密码，则存入登录账号和密码
		String accountnumber=request.getParameter("accountnumber");
		String password=request.getParameter("password");
		String rememberUser = request.getParameter("rememberUser");
		String rememberPass = request.getParameter("rememberPass");
		if(!(rememberPass==null)){      //记住密码，两个cookie
			Cookie cookieUser = new Cookie("rememberUser",accountnumber);
			Cookie cookiePass = new Cookie("rememberPass",password);
			cookieUser.setMaxAge(60*60*24*7);   //保存一个星期
			cookiePass.setMaxAge(60*60*24*7);
			response.addCookie(cookieUser);
			response.addCookie(cookiePass);
		}
		else if(!(rememberUser==null)){   //记住账号，一个cookie
			Cookie cookieUser = new Cookie("rememberUser",accountnumber);
			cookieUser.setMaxAge(60*60*24*7);
			response.addCookie(cookieUser);
		}
	}

	public boolean checkFreezeAndFilter(HttpServletRequest request, HttpServletResponse response) {
		try {
			UserDao u = new UserDao();
			int userId = (int) request.getSession().getAttribute("userId");
			if(u.checkFreeze(userId)){
				String nowTime = GetDate.getDateLine();
				String[] date = u.getFreezeEndTime(userId);
				String startTime = date[0];
				String endTime = date[1];
				if(GetDate.compareDate(nowTime,endTime)){
					u.cancelFreeze(userId);
					return false;
				}
				else{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date start = df.parse(startTime);
					Date end = df.parse(endTime);
					startTime = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(start); 
					endTime = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss").format(end); 
					request.getSession().setAttribute("startTime", startTime);
					request.getSession().setAttribute("endTime", endTime);
					return true;
				}
			}
			else return false;
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

}
