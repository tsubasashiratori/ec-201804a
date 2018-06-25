package jp.co.rakus.ec201804a.admin.registeradmin;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 管理者情報が入るフォーム.
 * 
 * @author tsubasa.shiratori
 */
public class RegisterAdminForm {
	/**
	 * 名前
	 */
	@NotBlank(message = "お名前を入力してください")
	private String name;
	/**
	 * メール
	 */
	@NotBlank(message = "アドレスを入力してください")
	private String email;
	/**
	 * パスワード
	 */
	@NotBlank(message = "パスワードを入力してください")
	@Size(min=8,max=16,message="パスワードは{min}文字以上{max}文字以下です")
	private String password;
	/**
	 * 確認用パスワード
	 */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String checkPassword;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	

}
