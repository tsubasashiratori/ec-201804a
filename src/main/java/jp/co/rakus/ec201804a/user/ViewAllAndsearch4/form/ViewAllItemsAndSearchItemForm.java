package jp.co.rakus.ec201804a.user.ViewAllAndsearch4.form;

import javax.validation.constraints.NotBlank;

public class ViewAllItemsAndSearchItemForm {

	@NotBlank(message = "該当する商品がありません")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ViewAllItemsAndSearchItemForm [name=" + name + "]";
	}
	
	
}
