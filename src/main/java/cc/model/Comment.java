package cc.model;
//微博评论对象
public class Comment {
	private int commentId;
	private int userId;
	private int weiboId;
	private String content;
	private String dateLine;
	private int replyComment;     //评论的回复数
	
	private boolean canDelete;   //该评论是否能被某用户删除
	private String username;  
	int follow;
	int fans;
	int weibo;
	
	public Comment(int commentId,int userId,String username, String content, String dateLine, boolean canDelete,int replyComment) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.username = username;
		this.content = content;
		this.dateLine = dateLine;
		this.canDelete = canDelete;
		this.replyComment = replyComment;
	}
	
	public Comment(int commentId,int userId,String username, String content, String dateLine, boolean canDelete,int replyComment
			,int follow,int fans,int weibo) {
		this(commentId, userId, username,  content,  dateLine,  canDelete, replyComment);
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
	}
	

	public Comment(int commentId, int userId, int weiboId, String content, String dateLine) {
		super();
		this.commentId = commentId;
		this.userId = userId;
		this.weiboId = weiboId;
		this.content = content;
		this.dateLine = dateLine;
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

	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getDateLine() {
		return dateLine;
	}
	public void setDateLine(String dateLine) {
		this.dateLine = dateLine;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public int getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(int replyComment) {
		this.replyComment = replyComment;
	}

	
}
