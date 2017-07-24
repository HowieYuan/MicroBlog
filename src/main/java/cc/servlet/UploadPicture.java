package cc.servlet;

import cc.service.InformationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/** 更改头像
 * servlet implementation class UploadPicture
 */
@WebServlet("/uploadPicture")
public class UploadPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
	File tmpDir = null;//初始化上传文件的临时存放目录  
    File saveDir = null;//初始化上传文件后的保存目录  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		new InformationService().changeHead(request, response);
		response.sendRedirect("changeSuccess.jsp");
    } 
}


