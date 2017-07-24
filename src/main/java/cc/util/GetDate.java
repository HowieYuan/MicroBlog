package cc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//时间工具类
public class GetDate {
	//获得当前时间
	public static String getDateLine(){
		 String dateLine = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());  
	     return dateLine;			
	}

	//比较两时间
	public static boolean compareDate(String nowTime, String endTime) {
		try{	
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date now = df.parse(nowTime);
			Date end = df.parse(endTime);
			  if (now.getTime() < end.getTime()) {
                   return false;	              
	          } 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

}
