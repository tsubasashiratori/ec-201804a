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

import jp.co.rakus.ec201804a.common.domain.User;
import jp.co.rakus.ec201804a.user.login.UserLoginRepository;

/**
 * ログイン後の利用者情報に権限を付与するサービスクラス.
 * 
 * @author yuta.kitazawa
 */
@Service
public class UserDetailsServiceImp1 implements UserDetailsService{
	/**
	 * 利用者をfindByOneMailAddresするリポジトリ
	 */
	@Autowired
	private UserLoginRepository userRepository;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByOneMailAddress(email);
		System.out.println(user);
		if(user == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		//ROLE_は必ず書く.
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new LoginUser(user, authorityList);
	}
}
