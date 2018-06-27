package jp.co.rakus.ec201804a.admin.viewAllAndSearchItem;

import javax.validation.constraints.Size;

/**
 * 検索する際のフォームクラス.
 * 
 * @author tatsuro.okazaki
 */
public class AdminViewAllAndSearchItemForm {

	/** 名前 */
	@Size(message="1文字以上20文字以内でキーワードを入力してください", min=1, max=20)
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
