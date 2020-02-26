package cn.tedu.store.entity;

public class Cart extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 316677457072152015L;
	private  Integer id;
	private Integer uid;
	private String productId;
	private Integer productNum;
	
	public Cart() {
		super();
	}
	public Cart(Integer uid, String productId, Integer productNum) {
		super();
		this.uid = uid;
		this.productId = productId;
		this.productNum = productNum;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", uid=" + uid + ", productId=" + productId + ", productNum=" + productNum + "]";
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the productNum
	 */
	public Integer getProductNum() {
		return productNum;
	}
	/**
	 * @param productNum the productNum to set
	 */
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	
}
