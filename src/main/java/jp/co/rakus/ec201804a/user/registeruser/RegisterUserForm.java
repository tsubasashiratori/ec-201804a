package jp.co.rakus.ec201804a.user.registeruser;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 利用者情報が入るフォーム.
 * 
 * @author tsubasa.shiratori
 */
public class RegisterUserForm {
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
	/**
	 * 郵便番号
	 */
	@NotBlank(message = "郵便番号を入力してください")
	private String zipCode;
	/**
	 * 住所
	 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	/**
	 * 電話番号の先頭
	 */
	private String telHead;
	/**
	 * 電話番号の真ん中
	 */
	private String telBody;
	/**
	 * 電話番号の最後
	 */
	private String telTeil;
	
	public String getTelHead() {
		return telHead;
	}
	public void setTelHead(String telHead) {
		this.telHead = telHead;
	}
	public String getTelBody() {
		return telBody;
	}
	public void setTelBody(String telBody) {
		this.telBody = telBody;
	}
	public String getTelTeil() {
		return telTeil;
	}
	public void setTelTeil(String telTeil) {
		this.telTeil = telTeil;
	}
	
	
}
