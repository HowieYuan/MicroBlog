package cc.model;

//微博用户Model
public class Vip {
	private int userId;
	private String phonenumber;
	private String username;
	private String email;
	private String password;
	private String realname;
	private String sex;
	private String birthday;
	private String qq;
	private String address;
	private int follow;
	private int fans;
	private int weibo;
	
	private boolean login;
	private String searchUsername;
	private String endTime;
	private boolean hasFreeze;
	
	public Vip() {
		super();
	}
	
	
	public Vip(int userId, String phonenumber, String username, String email, String password, String realname,
			String sex, String birthday, String qq, String address) {
		super();
		this.userId = userId;
		this.phonenumber = phonenumber;
		this.username = username;
		this.email = email;
		this.password = password;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.address = address;
	}



	public Vip(int userId, String phonenumber, String username, String email, String password, String realname,
			String sex, String birthday, String qq, String address, int follow, int fans, int weibo) {
		super();
		this.userId = userId;
		this.phonenumber = phonenumber;
		this.username = username;
		this.email = email;
		this.password = password;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.address = address;
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
	}


	public Vip(String phonenumber, String username, String email, String password) {
		super();
		this.phonenumber = phonenumber;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	

	public Vip(int userId, String username, String realname, String sex, String birthday, String qq, String address,
			int follow, int fans, int weibo) {
		super();
		this.userId = userId;
		this.username = username;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.address = address;
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
	}

	

	public Vip(int userId, String username,String searchUsername , int follow, int fans, int weibo) {
		super();
		this.userId = userId;
		this.username = username;
		this.searchUsername = searchUsername;
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
	}

	public Vip(int userId, String username, String realname, String sex, String birthday, String qq, String address,
			int follow, int fans, int weibo, String endTime, boolean hasFreeze) {
		super();
		this.userId = userId;
		this.username = username;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.address = address;
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
		this.endTime = endTime;
		this.hasFreeze = hasFreeze;
	}


	public Vip(int userId, String username, String realname, String sex, String birthday, String qq,
			String address, int follow, int fans, int weibo, String endTime, boolean isFreeze, boolean login) {
		super();
		this.userId = userId;
		this.username = username;
		this.realname = realname;
		this.sex = sex;
		this.birthday = birthday;
		this.qq = qq;
		this.address = address;
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
		this.endTime = endTime;
		this.hasFreeze = isFreeze;
		this.login = login;
	}


	public Vip(int followNum, int fansNum, int weiboNum) {
		super();
		this.follow = followNum;
		this.fans = fansNum;
		this.weibo = weiboNum;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getFollow() {
		return follow;
	}


	public void setFollow(int follow) {
		this.follow = follow;
	}


	public int getFans() {
		return fans;
	}


	public void setFans(int fans) {
		this.fans = fans;
	}


	public int getWeibo() {
		return weibo;
	}


	public void setWeibo(int weibo) {
		this.weibo = weibo;
	}

	public String getSearchUsername() {
		return searchUsername;
	}


	public void setSearchUsername(String searchUsername) {
		this.searchUsername = searchUsername;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public boolean isHasFreeze() {
		return hasFreeze;
	}


	public void setHasFreeze(boolean hasFreeze) {
		this.hasFreeze = hasFreeze;
	}


	public boolean getLogin() {
		return login;
	}


	public void setLogin(boolean login) {
		this.login = login;
	}

	
	
}
