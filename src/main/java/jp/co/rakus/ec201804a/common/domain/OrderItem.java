package jp.co.rakus.ec201804a.common.domain;

/**
 * 注文された商品を表すクラス.
 * 
 * @author yuta.kitazawa
 */
public class OrderItem {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 商品id
	 */
	private Long itemId;
	/**
	 * 合計金額
	 */
	private Integer quantity;
	/**
	 * 注文id
	 */
	private Long orderId;
	/**
	 * 注文された商品
	 */
	private Item item;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", quantity=" + quantity + ", orderId=" + orderId
				+ ", item=" + item + "]";
	}
}
