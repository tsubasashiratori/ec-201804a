package jp.co.rakus.ec201804a.common.domain;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.rakus.ec201804a.user.payment9.IncludeTaxLogic;


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
	
	private Integer itemTotalPriceExcludeTax;
	
	private Integer itemTotalPriceIncludeTax;
	
	private Integer itemTax;
	
	@Autowired
	private IncludeTaxLogic taxLogic;
	
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
	
	public Integer getItemTotalPriceExcludeTax() {
		int itemPrice = getItem().getPrice();
		int itemQuantity = getQuantity();
		int itemTotalPriceExcludeTax = itemPrice * itemQuantity;
		System.out.println(itemTotalPriceExcludeTax + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return itemTotalPriceExcludeTax;
		
		
	}
	
	public Integer getItemTotalPriceIncludeTax() {
		int itemTotalPriceIncludeTax = taxLogic.includeTax(getItemTotalPriceExcludeTax());
		System.out.println(itemTotalPriceIncludeTax + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas");
		return itemTotalPriceIncludeTax;
	}
	
	public int getItemTax() {
		int itemTax = taxLogic.calcTax(getItemTotalPriceExcludeTax());
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
				+ ", itemTotalPriceIncludeTax=" + itemTotalPriceIncludeTax + ", itemTax=" + itemTax + ", taxLogic="
				+ taxLogic + "]";
	}
	
	
}
