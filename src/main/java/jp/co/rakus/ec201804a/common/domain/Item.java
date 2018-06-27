package jp.co.rakus.ec201804a.common.domain;

/**
 * 商品を表すクラス.
 * 
 * @author yuta.kitazawa
 */
public class Item {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 商品名
	 */
	private String name;
	/**
	 * 商品説明
	 */
	private String description;
	/**
	 * 値段
	 */
	private Integer price;
	/**
	 * 画像の保存先
	 */
	private String imagePath;
	/**
	 * 削除したか
	 */
	private Boolean deleted;
	
	/**
	 * 観覧数
	 */
	private Integer count;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imagePath=" + imagePath + ", deleted=" + deleted + "]";
	}
}
