package cc.model;
//关注对象
public class Follow {
	private int followId;
	private int userId;   //关注人
	private int by_userId;   //被关注人
	
	
	public Follow(int followId, int userId, int by_userId) {
		super();
		this.followId = followId;
		this.userId = userId;
		this.by_userId = by_userId;
	}
	
	public int getFollowId() {
		return followId;
	}
	public void setFollowId(int followId) {
		this.followId = followId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBy_userId() {
		return by_userId;
	}
	public void setBy_userId(int by_userId) {
		this.by_userId = by_userId;
	}
}
