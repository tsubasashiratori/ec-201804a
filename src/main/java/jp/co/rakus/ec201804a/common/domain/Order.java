package jp.co.rakus.ec201804a.common.domain;

import java.util.Date;
import java.util.List;

/**
 * 利用者の一つの注文を表すクラス.
 * 
 * @author yuta.kitazawa
 */
public class Order {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 注文番号
	 */
	private String orderNumber;
	/**
	 * 利用者id
	 */
	private Long userId;
	/**
	 * ステータス
	 */
	private Integer status;
	/**
	 * 注文商品リスト
	 */
	private List<OrderItem> orderItemList;
	/**
	 * 合計金額
	 */
	private Integer totalPrice;
	/**
	 * 注文日
	 */
	private Date orderDate;
	/**
	 * 配達先氏名
	 */
	private String deliveryName;
	/**
	 * 配達先メールアドレス
	 */
	private String deliveryEmail;
	/**
	 * 配達先郵便番号
	 */
	private String deliveryZipCode;
	/**
	 * 配達先住所
	 */
	private String deliveryAddress;
	/**
	 * 配達先電話番号
	 */
	private String deliveryTel;
	/**
	 * 利用者
	 */
	private User user;
	
	/**
	 * ショッピングカート内商品の税別小計
	 * @author kohei.taguchi
	 */	
	private int totalPriceExcludeTax;
	
	/**
	 * ショッピングカート内商品の消費税
	 * @author kohei.taguchi
	 */
	private int totalPriceTax;
	
	/**
	 * 送料
	 * @author kohei.taguchi
	 */
	private static final int POSTAGE = 500;
	
	/**
	 * 送料含めた合計金額
	 * @author kohei.taguchi
	 */
	private int totalPriceIncludeTaxAndPostage;
	
	public int getPostage() {
		return POSTAGE;
	}
	
	
	/**ショッピングカート内の消費税合計を取得するゲッター.
	 * @return totalPriceTax カート内の消費税
	 * @author kohei.taguchi
	 */
	public int getTotalPriceTax() {
		int totalPriceTax = 0;
		for (OrderItem orderItem : getOrderItemList()) {
			totalPriceTax += orderItem.getItemTax();
		}
		return totalPriceTax;
	}
	
	/**
	 * カート内商品の税別小計を取得するゲッター.
	 * @return totalPriceTax カート内の税別小計
	 * @author kohei.taguchi
	 */
	public int getTotalPriceExcludeTax() {
		int totalPriceTax = 0;
		for (OrderItem orderItem : getOrderItemList()) {
			totalPriceTax += orderItem.getItemTotalPriceExcludeTax();
		}
		return totalPriceTax;
	}
	
	/**
	 * 消費税と送料を含めた合計金額を取得するゲッター.
	 * @return　消費税と送料を含めた合計金額
	 * @author kohei.taguchi
	 */
	public int getTotalPriceIncludeTaxAndPostage() {
		int totalPriceIncludeTaxAndPostage = 0;
		for (OrderItem orderItem : getOrderItemList()) {
			totalPriceIncludeTaxAndPostage += orderItem.getItemTotalPriceIncludeTax();
		}
		totalPriceIncludeTaxAndPostage += getPostage();
		return totalPriceIncludeTaxAndPostage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public String getDeliveryEmail() {
		return deliveryEmail;
	}
	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}
	public String getDeliveryZipCode() {
		return deliveryZipCode;
	}
	public void setDeliveryZipCode(String deliveryZipCode) {
		this.deliveryZipCode = deliveryZipCode;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getDeliveryTel() {
		return deliveryTel;
	}
	public void setDeliveryTel(String deliveryTel) {
		this.deliveryTel = deliveryTel;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", userId=" + userId + ", status=" + status
				+ ", orderItemList=" + orderItemList + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate
				+ ", deliveryName=" + deliveryName + ", deliveryEmail=" + deliveryEmail + ", deliveryZipCode="
				+ deliveryZipCode + ", deliveryAddress=" + deliveryAddress + ", deliveryTel=" + deliveryTel + ", user="
				+ user + ", totalPriceExcludeTax=" + totalPriceExcludeTax + ", totalPriceTax=" + totalPriceTax
				+ ", totalPriceIncludeTaxAndPostage=" + totalPriceIncludeTaxAndPostage + "]";
	}
}
