package jp.co.rakus.ec201804a.admin.editItem;

public class EditItemForm {

	/** id */
	private Long id;
	
	/** 名前 */
	private String name;
	
	/** 値段 */
	private Integer price;
	
	/** 商品説明 */
	private String description;
	
	/** 画像までのパス */
	private String imagePath;
	
	/** 削除フラグ */
	private Boolean deleted;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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