package jp.co.rakus.ec201804a.admin.insertitem;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

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
	@NotBlank(message = "値段を入力してください")
	private String price;
	/**
	 * 値段の数値
	 */
	private int intPrice;
	/**
	 * 画像の保存先
	 */
	private MultipartFile imagePath;
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
	public MultipartFile getImagePath() {
		return imagePath;
	}
	public void setImagePath(MultipartFile imagePath) {
		this.imagePath = imagePath;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
