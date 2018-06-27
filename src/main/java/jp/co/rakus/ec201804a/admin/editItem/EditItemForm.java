package jp.co.rakus.ec201804a.admin.editItem;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class EditItemForm {

	/** id */
	private Long id;
	
	/** 名前 */
	@Size(min=1, max=20, message="1文字以上20文字以内で商品名を入力してください")
	private String name;
	
	/** 価格 */
	private String price;
	
	/** 商品説明 */
	@NotBlank(message="商品説明を入力してください")
	private String description;
	
	/** 画像までのパス */
//	@NotBlank(message="画像を選択してください")
	private MultipartFile imagePath;
	
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

	public String getPrice() {
		return price;
	}
	
	public int getIntPrice() {
		return Integer.parseInt(price);
	}

	public MultipartFile getImagePath() {
		return imagePath;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImagePath(MultipartFile imagePath) {
		this.imagePath = imagePath;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}