package jp.co.rakus.ec201804a.user.shoppingcart;

/**
 * フォームを受け取るクラス.
 * 
 * @author takumi.omoto
 *
 */
public class DeleteShoppingCartForm {
	/** 注文商品ID */
	String orderItemId;

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getIntOrderItemId() {
		return Integer.parseInt(getOrderItemId());
	}

}
