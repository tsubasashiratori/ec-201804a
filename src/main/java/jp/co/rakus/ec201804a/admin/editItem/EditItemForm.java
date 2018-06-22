package jp.co.rakus.ec201804a.admin.editItem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditItemForm {

	/** id */
	private Long id;
	
	/** 名前 */
	@Size(min=1, max=20, message="1文字以上20文字以内でキーワードを入力してください")
	private String name;
	
	/** 価格 */
	@NotNull(message="価格を入力してください")
	private Integer price;
	
	/** 商品説明 */
	@NotBlank(message="商品説明を入力してください")
	private String description;
	
	/** 画像までのパス */
	@NotBlank(message="画像を選択してください")
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