package cc.service;

import cc.dao.UserDao;
import cc.model.Vip;

import java.util.ArrayList;
import java.util.List;

//检查邮箱，用户名，密码是否符合
public class RegisterService {
	
	public List<String> getErrors(Vip vip) {
		//建List集合，装入错误种类，出现一种错误则装进集合，错误之后会显示到注册失败页面中
		List<String> errors=new ArrayList<String>();				
		
		if(isInvalidemail(vip.getEmail())){
			errors.add("该邮箱地址已被注册");
		}
		if(isInvalidusername(vip.getUsername())){
			errors.add("该用户名已被注册");
		}
		if(isInvalidphonenumber(vip.getPhonenumber())){
			errors.add("该手机号码已被注册");
		}
	    return errors;
	}

    //注册时检查输入的用户名是否已经存在
	public boolean isInvalidusername(String username)  {
		if(new UserDao().checkName(username)){
			return true;
		}
		return false;
	   }

    //检查输入的邮箱地址是否符合格式以及是否已被注册
	public boolean isInvalidemail(String email)  {
		if(new UserDao().checkEmail(email))	{
			return true;
		}
		return false;		
	}
	
	//检查输入的手机号码是否符合格式以及是否已被注册
	public boolean isInvalidphonenumber(String phonenumber){
		if(new UserDao().checkPhonenumber(phonenumber))	{
			return true;
		}
		return false;		
	}


		

}
