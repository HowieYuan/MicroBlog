package cc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//加密工具类
public class Code {
     //回显信息时加密用户手机号码
	public static String phonenumberCode(String content){
		int begin=3;
		int end=content.length()-3;       
		String starStr = "";  
        for (int i = begin; i < end; i++) {  
            starStr = starStr + "*";  
        }  
        return content.substring(0, begin) + starStr + content.substring(end, content.length());
	}
	
	 //回显信息时加密用户邮箱地址
		public static String emailCode(String content){
			int begin=2;
			int end=content.length()-3;       
			String starStr = "";  
	        for (int i = begin; i < end; i++) {  
	            starStr = starStr + "*";  
	        }  
	        return content.substring(0, begin) + starStr + content.substring(end, content.length());
		}
		
		//字符转义
		public static  String htmlEncode(String content){
			if(content==null) return "";        
		    String newContent = content;
		    newContent = newContent.replace( "'", "&apos;");
		    newContent = newContent.replace( "\"", "&quot;");
		    newContent = newContent.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
		    newContent = newContent.replace( " ", "&nbsp;");// 替换空格
		    newContent = newContent.replace( "<", "&lt;");
		    newContent = newContent.replace( ">", "&gt;");
		    newContent = newContent.replace( "\r", "<br>");
		    newContent = newContent.replace( "\\", "/");
		    System.out.println(newContent);
		    return newContent;
		}
		
		//将密码写入数据库时加密
		public static String encryption(String source){
			String hashType = "MD5";     //使用MD5算法
			StringBuilder sb = new StringBuilder();   //线程安全，一个类似于String 的字符串缓冲区，但不能修改。
			MessageDigest md5;     //MessageDigest可以为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
			try {
				md5 = MessageDigest.getInstance(hashType);   //返回实现指定摘要算法的 MessageDigest 对象。
				md5.update(source.getBytes());       //得到一个系统默认的编码格式的字节数组，使用该数组更新摘要。
				for (byte b : md5.digest()) {       //完成哈希计算
					sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
				}
				return sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return null;
		}
		
}
