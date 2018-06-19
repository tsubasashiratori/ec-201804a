package jp.co.rakus.ec201804a.user.login;

/**
 * 利用者ログインのフォームクラス.
 * 
 * @author yuta.kitazawa
 */
public class UserLoginForm {
	private String mailAddress;
	private String password;
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
