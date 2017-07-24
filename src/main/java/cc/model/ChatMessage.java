package cc.model;
//聊天记录对象
public class ChatMessage {
	private int chatId;
	private String message;
	private int userId;    //发信人
	private int by_userId;   //收信人
	private String dateLine;
	private int unread;    //该聊天信息是否已读
	
	public ChatMessage() {
		super();
	}

	public ChatMessage(int chatId, String message, int userId, int by_userId, String dateLine) {
		super();
		this.chatId = chatId;
		this.message = message;
		this.userId = userId;
		this.by_userId = by_userId;
		this.dateLine = dateLine;
	}
	
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getDateLine() {
		return dateLine;
	}
	public void setDateLine(String dateLine) {
		this.dateLine = dateLine;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}


	


}
