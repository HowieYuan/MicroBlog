package cc.model;
//评论的回复对象
public class ReplyComment {
	private int replyCommentId;
	private int userId;
	private int weiboId;
	private int commentId;
	private String content;
	private String dateLine;
	
	private boolean canDelete;    //该回复能否被某用户删除
	private String username;
	int follow;
	int fans;
	int weibo;
	
	public ReplyComment(int replyCommentId, int userId, int weiboId, int commentId, String content, String dateLine) {
		super();
		this.replyCommentId = replyCommentId;
		this.userId = userId;
		this.weiboId = weiboId;
		this.commentId = commentId;
		this.content = content;
		this.dateLine = dateLine;
	}

	public ReplyComment(int replyCommentId, int commentId, int userId, String username, String content, String dateLine,
			boolean canDelete,int follow,int fans,int weibo) {
		super();
		this.replyCommentId = replyCommentId;
		this.userId = userId;
		this.username = username;
		this.commentId = commentId;
		this.content = content;
		this.dateLine = dateLine;
		this.canDelete = canDelete;
		this.follow = follow;
		this.fans = fans;
		this.weibo = weibo;
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

	public int getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(int replyCommentId) {
		this.replyCommentId = replyCommentId;
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

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
}
