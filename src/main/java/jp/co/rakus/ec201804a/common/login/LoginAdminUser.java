package jp.co.rakus.ec201804a.common.login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jp.co.rakus.ec201804a.common.domain.AdminUser;

/**
 * 管理者のログイン情報を格納するドメイン.
 * 
 * @author yuta.kitazawa
 */
public class LoginAdminUser extends org.springframework.security.core.userdetails.User{
	private static final long serialVersionUID = 1L;
	/**
	 * メンバー情報
	 */
	private final AdminUser adminUser;
	
	/**
	 * 通常のメンバー情報に加え、認可用ロールを追加する.
	 * 
	 * @param adminUser
	 * @param authorityList
	 */
	public LoginAdminUser(AdminUser adminUser, Collection<GrantedAuthority> authorityList) {
		super(adminUser.getEmail(), adminUser.getPassword(), authorityList);
		this.adminUser = adminUser;
	}
	public AdminUser getUser() {
		return adminUser;
	}
}
