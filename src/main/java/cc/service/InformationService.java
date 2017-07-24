package cc.service;

import cc.dao.UserDao;
import cc.model.Vip;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class InformationService {
    //取得用户输入的信息，并判断用户名情况，最后调用改变用户信息的方法
	public boolean checkInformation(HttpServletRequest request, HttpServletResponse response) {
		String username=(String)request.getSession().getAttribute("login");
		Vip vip=new Vip();
		UserDao u=new UserDao();
	    vip.setUsername(request.getParameter("username"));
	    vip.setRealname(request.getParameter("realname"));
		vip.setAddress(request.getParameter("address"));
		vip.setSex(request.getParameter("sex"));	
		vip.setBirthday(request.getParameter("birthday"));
		vip.setQq(request.getParameter("qq"));
		if(!u.checkName2(request.getParameter("username"),username)){
			u.changeInformation(vip,username);
			return true;
		}
		else return false;
	}

	//检查原密码是否正确，最后调用改变密码的方法
	public boolean checkPasswordChange(HttpServletRequest request, HttpServletResponse response) {
		String username=(String)request.getSession().getAttribute("login");
		UserDao u=new UserDao();
		if(u.checkPassword(request.getParameter("oldpassword"),username)){
			u.changePassword(request.getParameter("newpassword"),username);
			return true;
		}
		else return false;
	}

	//检查email和手机号码是否为同一用户，是则改变密码，使用改密码的方法时传入用户名以检索密码
	public boolean checkForgetPassword(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		UserDao u=new UserDao();
		if(u.checkEmailAndPhonenumber(request.getParameter("email"),request.getParameter("phonenumber"))){
			String name=u.getUsername(request.getParameter("email"));
			u.changePassword(request.getParameter("newpassword"),name);
			return true;
		}
		else return false;
	}
	
	//将图片复制到放用户头像的文件夹中，并以userId重命名
	public void changeHead(HttpServletRequest request, HttpServletResponse response){
		String usename = (String) request.getSession().getAttribute("login");
		
		//创建一个DiskFileItemFactory  将请求消息实体中的每一个项目封装成单独的DiskFileItem (FileItem接口的实现) 对象的任务
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        
        //创建一个文件上传解析器  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        
        //解决上传文件名的中文乱码  
        upload.setHeaderEncoding("UTF-8");   
        factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K  
        File temporary = new File("D:/Swit Project/MicroBlog/WebContent/temporaryimages");//当超过500K的时候，存到一个临时文件夹中  
        factory.setRepository(temporary);  
        upload.setSizeMax(1024 * 1024 * 10);//设置上传的文件总的大小不能超过10M  
        try { 
        	int userId = new UserDao().getUserId(usename);
        	
            // 得到 FileItem 的集合 items  
            List<FileItem> items = upload.parseRequest(request);  
  
            // 遍历 items:  
            for (FileItem item : items) {  
                // 若是一个一般的表单域, 打印信息  
                if (item.isFormField()) {  
                	response.sendError(401, "文件不对！"); 
                }  
                
                // 若是文件域则把文件保存到指定录下.  
                else {  
                    String fileName = item.getName();  
                    long sizeInBytes = item.getSize();  
                    System.out.println("图片名称："+fileName);  
                    System.out.println("图片大小："+sizeInBytes+"字节"); 
                    fileName = userId+".jpg";
  
                    InputStream in = item.getInputStream();  
                    byte[] buffer = new byte[1024];  
                    int len = 0;  
  
                    String fileAddress = "D:/Swit Project/MicroBlog/WebContent/headimages/" + fileName;//文件最终上传的位置  
                    System.out.println("储存路径："+fileAddress);  
                    OutputStream out = new FileOutputStream(fileAddress);  
  
                    while ((len = in.read(buffer)) != -1) {  
                        out.write(buffer, 0, len);  
                    }  
  
                    out.close();  
                    in.close();  
                }  
            }  
  
        } catch (FileUploadException | IOException | ClassNotFoundException | SQLException e) {  
            e.printStackTrace();  
        } 
	}
	
	
}
