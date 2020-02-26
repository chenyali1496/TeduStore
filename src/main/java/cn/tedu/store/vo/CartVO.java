package cn.tedu.store.vo;

import java.io.Serializable;

/*
 * Cart Value Object
 */
public class CartVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6205672105614156954L;
	private Integer cartId;
	private Integer uid;
	private String productId;
	private String productTitle;
	private String productImage;
	private Long productPrice;
	private Integer productNum;
	public CartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartVO [cartId=" + cartId + ", uid=" + uid + ", productId=" + productId + ", productTitle="
				+ productTitle + ", productImage=" + productImage + ", productPrice=" + productPrice + ", productNum="
				+ productNum + "]";
	}
	/**
	 * @return the cartId
	 */
	public Integer getCartId() {
		return cartId;
	}
	/**
	 * @param cartId the cartId to set
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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
	 * @return the productTitle
	 */
	public String getProductTitle() {
		return productTitle;
	}
	/**
	 * @param productTitle the productTitle to set
	 */
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	/**
	 * @return the productImage
	 */
	public String getProductImage() {
		return productImage;
	}
	/**
	 * @param productImage the productImage to set
	 */
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	/**
	 * @return the productPrice
	 */
	public Long getProductPrice() {
		return productPrice;
	}
	/**
	 * @param productPrice the productPrice to set
	 */
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
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
