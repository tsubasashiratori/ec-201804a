package jp.co.rakus.ec201804a.user.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ec201804a.common.domain.User;

/**
 * 利用者がログインをするコントローラ.
 * 
 * @author yuta.kitazawa
 */
@Controller
@SessionAttributes(types= {User.class})
@RequestMapping(value = "/user")
public class UserLoginController {	
	/**
	 * ログインフォームを初期化する
	 * 
	 * @return UserLoginForm
	 */
	@ModelAttribute
	public UserLoginForm setUpForm() {
		return new UserLoginForm();
	}
	
	/**
	 * ログイン画面を表示する.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping(value = "/")
	public String index(UserLoginForm form, BindingResult result
			,Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		
		if(error != null) {
			System.err.println("user: login failed");
			result.addError(new ObjectError("loginError", "入力情報が不正です"));
		}

		return "/user/loginUser";
	}
}
