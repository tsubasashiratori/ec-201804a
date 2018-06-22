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
	 * 個数
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
	
	/**
	 * 注文された商品の価格小計
	 * @author kohei.taguchi
	 */
	private Integer itemTotalPriceExcludeTax;
	
	/**
	 * 注文された商品の税込小計
	 * @author kohei.taguchi
	 */
	private Integer itemTotalPriceIncludeTax;
	
	/**
	 * 注文された商品の消費税
	 * @author kohei.taguchi
	 */
	private Integer itemTax;

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
	
	/**
	 * 商品の税別小計を取得するゲッター.
	 * @param　itemPrice　商品単価
	 * @param　itemQuantity　注文個数
	 * @return　itemTotalPriceExcludeTax　商品の税別小計
	 * @author kohei.taguchi
	 */
	public Integer getItemTotalPriceExcludeTax() {
		int itemPrice = getItem().getPrice();
		int itemQuantity = getQuantity();
		int itemTotalPriceExcludeTax = itemPrice * itemQuantity;
		return itemTotalPriceExcludeTax;
	}
	
	/**
	 * 商品の税込小計を取得するゲッター.
	 * @return　itemTotalPriceIncludeTax　商品の税込小計.
	 * @author kohei.taguchi
	 */
	public Integer getItemTotalPriceIncludeTax() {
		int itemTotalPriceIncludeTax = (int)(getItemTotalPriceExcludeTax() * 1.08);
		return itemTotalPriceIncludeTax;
	}
	
	/**商品の消費税を取得するゲッター.
	 * @return itemtax 消費税.
	 * @author kohei.taguchi
	 */
	public int getItemTax() {
		int itemTax = (int)(getItemTotalPriceExcludeTax() * 0.08);
		return itemTax;
	}
	
	public void setItemTotalPriceExcludeTax(Integer itemTotalPriceExcludeTax) {
		this.itemTotalPriceExcludeTax = itemTotalPriceExcludeTax;
	}
	public void setItemTotalPriceIncludeTax(Integer itemTotalPriceIncludeTax) {
		this.itemTotalPriceIncludeTax = itemTotalPriceIncludeTax;
	}
	public void setItemTax(Integer itemTax) {
		this.itemTax = itemTax;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", quantity=" + quantity + ", orderId=" + orderId
				+ ", item=" + item + ", itemTotalPriceExcludeTax=" + itemTotalPriceExcludeTax
				+ ", itemTotalPriceIncludeTax=" + itemTotalPriceIncludeTax + ", itemTax=" + itemTax + "]";
	}
	
	
}
