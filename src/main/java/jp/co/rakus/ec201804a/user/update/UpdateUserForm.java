package jp.co.rakus.ec201804a.user.update;

import javax.validation.constraints.NotBlank;

/**
 * 利用者が登録情報を変更するフォーム.
 * 
 * @author yuta.kitazawa
 */
public class UpdateUserForm {
	private Long id;
	/**
	 * 名前
	 */
	@NotBlank(message = "お名前を入力してください")
	private String name;
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
