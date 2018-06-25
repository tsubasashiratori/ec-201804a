package jp.co.rakus.ec201804a.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 管理者ログイン認証用設定.
 * 
 * @author yuta.kitazawa
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class AdminUserSecurityConfig extends WebSecurityConfigurerAdapter{
	@Qualifier("AdminUserDetailsServiceImp1")
	@Autowired
	private UserDetailsService userDetailsService;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
	 */
	//静的リソースのセキュリティ設定を無効にする.
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/css/**"
				,"/img/**"
				,"/js/**"
				,"/fonts/**");
	}
	
	//ログインログアウトに関する設定.
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		//認可に関する設定
		http
		.antMatcher("/admin/**")
		.authorizeRequests()
		.antMatchers(
				//ログインしていなくても開ける画面
				"/admin/"// : 管理者のログインページ		
				//ログインしていなくてもできる操作
				,"/admin/login"// : 管理者のログイン処理
				).permitAll() //このパスは全てのユーザに許可
		.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()//それ以外のパスは認証が必要
		;
		
		//ログインに関する設定
		//「/login」は不要になる
		//例外がスローされると失敗とみなす
		http.formLogin()
		.loginPage("/admin/")//ログイン画面のパス
		.loginProcessingUrl("/admin/login")//ログインボタンを押したときのパス
		.failureUrl("/admin/?error=true")//ログイン失敗画面のパス
		.defaultSuccessUrl("/admin/menu", false)
		// 第1引数:デフォルトでログイン成功時に遷移させるパス
        // 第2引数: true :認証後常に第1引数のパスに遷移 
        //         false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
		.usernameParameter("email")//認証に使用するユーザ名のリクエストパラメータ
		.passwordParameter("password")//認証時に使用するパスワードのリクエストパラメータ
		;
		
		//ログアウトに関する設定
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout**"))//ログアウトさせる際に遷移させるパス
		.logoutSuccessUrl("/admin/")//ログアウト後に遷移させるパス
		.deleteCookies("JESESIONID")// ログアウト後、Cookieに保存されているセッションIDを削除
		.invalidateHttpSession(true)// true:ログアウト後、セッションを無効にする false:セッションを無効にしない	
		;
	}
	
	//ログインに使用する「UserDetailsService」と「PasswordEncoder」の設定.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
