package jp.co.rakus.ec201804a.user;

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
 * 利用者ログイン認証用設定.
 * 
 * @author yuta.kitazawa
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter{
	@Qualifier("UserDetailsServiceImp1")
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
		.antMatcher("/user/**")
		.authorizeRequests()
		.antMatchers(
				//ログインしていなくても表示可能な画面
				"/user/loginForm"// : 利用者のログインページ
				,"/user/form" //: 利用者の登録画面
				,"/user/" // : 商品一覧画面(トップページ)
				,"/user/findAllNotDeletedByPageNum**" // : 商品の検索			
				,"/user/findByNameNotDeletedPageNum**"	
				,"/user/viewDetail**" // : 商品詳細の表示
				,"/user/toViewShoppingCart" //　:　ショッピングカート一覧画面
				,"/user/systemError" // :500エラー画面
				
				//ログインしていなくても可能な操作
				,"/user/login" // : 利用者のログイン処理
				,"/user/register" // : 利用者登録処理
				,"/user/findByNameNotDeleted**" // : 商品の検索
				,"/user/toInsertShoppingCart**" // : ショッピングカートに追加する
				,"/user/toDeleteShoppingCart" // : ショッピングカートの要素を削除する
				).permitAll() //このパスは全てのユーザに許可
		.antMatchers("/user/**").hasRole("USER")
		.anyRequest().authenticated()//それ以外のパスは認証が必要
		;
		
		//ログインに関する設定
		//「/login」は不要になる
		//例外がスローされると失敗とみなす
		http.formLogin()
		.loginPage("/user/loginForm")//ログイン画面のパス
		.loginProcessingUrl("/user/login")//ログインボタンを押したときのパス
		.failureUrl("/user/loginForm?error=true")//ログイン失敗画面のパス
		.defaultSuccessUrl("/user/", false)
		// 第1引数:デフォルトでログイン成功時に遷移させるパス
        // 第2引数: true :認証後常に第1引数のパスに遷移 
        //         false:認証されてなくて一度ログイン画面に飛ばされてもログインしたら指定したURLに遷移
		.usernameParameter("email")//認証に使用するユーザ名のリクエストパラメータ
		.passwordParameter("password")//認証時に使用するパスワードのリクエストパラメータ
		;
		
		//ログアウトに関する設定
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout**"))//ログアウトさせる際に遷移させるパス
		.logoutSuccessUrl("/user/")//ログアウト後に遷移させるパス
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
