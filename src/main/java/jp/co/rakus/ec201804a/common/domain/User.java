package jp.co.rakus.ec201804a.common.domain;

/**
 * 利用者を表すクラス.
 * 
 * @author yuta.kitazawa
 */
public class User {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 名前
	 */
	private String name;
	/**
	 * メール
	 */
	private String email;
	/**
	 * パスワード
	 */
	private String password;
	/**
	 * 郵便番号
	 */
	private String zipCode;
	/**
	 * 住所
	 */
	private String address;
	/**
	 * 電話番号
	 */
	private String telephone;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", zipCode="
				+ zipCode + ", address=" + address + ", telephone=" + telephone + "]";
	}
}
