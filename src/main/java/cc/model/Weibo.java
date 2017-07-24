package cc.model;

//微博Model
public class Weibo {
	private int weiboId;
	private String content;
	private int userId;
	private String dateLine;
	private int upvote;
	private int comment;
	private int recommend;
	
	private Vip vip;
	private boolean pictureExist;     //是否存在图片
	private boolean hasCollected;    //是否被登录用户关注
	private boolean hasUpvoted;     //是否被登录用户点赞
	private String username;
	private String searchContent;     //进行搜索时，微博中含有的搜索词（用于将该搜索词换为红色）
	
	
	public Weibo(int weiboId,int userId, String content, String dateLine, int upvote,int comment, boolean pictureExist, boolean hasCollected,
			boolean hasUpvoted, String username,String searchContent) {
		super();
		this.weiboId = weiboId;
		this.content = content;
		this.userId = userId;
		this.dateLine = dateLine;
		this.upvote = upvote;
		this.comment = comment;
		this.pictureExist = pictureExist;
		this.hasCollected = hasCollected;
		this.hasUpvoted = hasUpvoted;
		this.username = username;
		this.searchContent = searchContent;
	}

	public Weibo(int weiboId,int userId, String content, String dateLine, int upvote,int comment, boolean pictureExist, boolean hasCollected,
			boolean hasUpvoted, String username) {
		super();
		this.weiboId = weiboId;
		this.userId = userId;
		this.content = content;
		this.dateLine = dateLine;
		this.upvote = upvote;
		this.comment = comment;
		this.pictureExist = pictureExist;
		this.hasCollected = hasCollected;
		this.hasUpvoted = hasUpvoted;
		this.username = username;
	}

	public Weibo(int weiboId, String content, int userId, String dateLine, int upvote, boolean pictureExist,
			String username) {
		super();
		this.weiboId = weiboId;
		this.content = content;
		this.userId = userId;
		this.dateLine = dateLine;
		this.upvote = upvote;
		this.pictureExist = pictureExist;
		this.username = username;
	}

	public Weibo(int weiboId, String content, String dateLine,int upvote,int comment,boolean pictureExist, boolean hasCollected, boolean hasUpvoted) {
		super();
		this.weiboId = weiboId;
		this.content = content;
		this.dateLine = dateLine;
		this.upvote= upvote;
		this.comment = comment;
		this.pictureExist = pictureExist;
		this.hasCollected = hasCollected;
		this.hasUpvoted = hasUpvoted;
	}

	public Weibo(int userId, String username, int weiboId, String content, String dateLine, int upvote, int comment,
			boolean pictureExist,Vip vip) {
		super();
		this.weiboId = weiboId;
		this.content = content;
		this.userId = userId;
		this.upvote = upvote;
		this.comment = comment;
		this.pictureExist = pictureExist;
		this.username = username;
		this.dateLine = dateLine;
		this.vip = vip;
	}

	public int getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(int weiboId) {
		this.weiboId = weiboId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDateLine() {
		return dateLine;
	}

	public void setDateLine(String dateLine) {
		this.dateLine = dateLine;
	}

	public int getUpvote() {
		return upvote;
	}

	public void setUpvote(int upvote) {
		this.upvote = upvote;
	}

	public boolean isPictureExist() {
		return pictureExist;
	}

	public void setPictureExist(boolean pictureExist) {
		this.pictureExist = pictureExist;
	}

	public boolean isHasCollected() {
		return hasCollected;
	}

	public void setHasCollected(boolean hasCollected) {
		this.hasCollected = hasCollected;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isHasUpvoted() {
		return hasUpvoted;
	}

	public void setHasUpvoted(boolean hasUpvoted) {
		this.hasUpvoted = hasUpvoted;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public Vip getVip() {
		return vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}
	
	
}