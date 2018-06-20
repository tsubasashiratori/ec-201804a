package jp.co.rakus.ec201804a.common.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jp.co.rakus.ec201804a.common.domain.User;

/**
 * 利用者のログイン情報を格納するドメイン.
 * 
 * @author yuta.kitazawa
 */
public class LoginUser extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
	/**
	 * メンバー情報
	 */
	private final User user;
	
	/**
	 * 通常のメンバー情報に加え、認可用ロールを追加する.
	 * 
	 * @param user
	 * @param authorityList
	 */
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}
	public User getUser() {
		return user;
	}
}
