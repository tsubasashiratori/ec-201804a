package jp.co.rakus.ec201804a.admin.insertitem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品情報が入るフォーム.
 * 
 * @author tsubasa.shiratori
 */
public class InsertItemForm {
	/**
	 * 商品名
	 */
	@NotBlank(message = "商品名を入力してください")
	private String name;
	/**
	 * 商品説明
	 */
	@NotBlank(message = "商品説明を入力してください")
	private String description;
	/**
	 * 値段
	 */
	@NotNull(message = "値段を入力してください")
	private String price;
	/**
	 * 
	 */
	private int intPrice;
	/**
	 * 画像の保存先
	 */
	@NotBlank(message = "画像を選択してください")
	private String imagePath;
	/**
	 * 削除したか
	 */
	private Boolean deleted;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getIntPrice() {
		return intPrice;
	}
	public void setIntPrice(int intPrice) {
		this.intPrice = intPrice;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
