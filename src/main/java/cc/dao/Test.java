package cc.dao;

import java.io.File;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		 File directory = new File("");
         System.out.println(System.getProperty("user.dir"));
         System.out.println(directory.getCanonicalPath());//获取标准的路径 
         System.out.println(directory.getAbsolutePath());//获取绝对路径
        System.out.println(directory.getAbsolutePath());//获取绝对路径
	}
}
