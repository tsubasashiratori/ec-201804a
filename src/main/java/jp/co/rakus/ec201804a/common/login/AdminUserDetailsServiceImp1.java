package jp.co.rakus.ec201804a.common.login;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rakus.ec201804a.common.domain.AdminUser;
import jp.co.rakus.ec201804a.common.repository.AdminUserRepository;

/**
 * ログイン後の管理者情報に権限を付与するサービスクラス.
 * 
 * @author yuta.kitazawa
 */
@Service("AdminUserDetailsServiceImp1")
public class AdminUserDetailsServiceImp1 implements UserDetailsService{
	/**
	 * 管理者をfindByOneMailAddresするリポジトリ
	 */
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AdminUser adminUser = adminUserRepository.findByOneMailAddress(email);
		System.out.println(adminUser);
		if(adminUser == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		//ROLE_は必ず書く.
		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return new LoginAdminUser(adminUser, authorityList);
	}
}
