package jp.co.rakus.ec201804a.user.shoppingcart;

public class InsertShoppingCartForm {
	/**商品ID*/
	private String itemId;
	/**個数*/
	private String quantity;
	/**注文ID*/
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public int getIntQuantiy() {
		return Integer.parseInt(getQuantity());
	}
}
