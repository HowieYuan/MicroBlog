package cc.model;
//被收藏的微博
public class Collection {
	private int collectionId;
	private int userId;
	private int weiboId;
	
	public Collection(int collectionId, int userId, int weiboId) {
		super();
		this.collectionId = collectionId;
		this.userId = userId;
		this.weiboId = weiboId;
	}

	public int getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(int collectionId) {
		this.collectionId = collectionId;
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
	
	
}
