package jp.co.rakus.ec201804a.user.viewAllAndsearch4;

import javax.validation.constraints.Size;

/**
 * 商品検索をするフォーム.
 * @author shunta.nakamura
 *
 */
public class ViewAllItemsAndSearchItemForm {

	/** 名前 */
	@Size(min=1, max=20, message="1文字以上20文字以内でキーワードを入力してください")
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
