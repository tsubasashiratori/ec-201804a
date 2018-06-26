package jp.co.rakus.ec201804a.admin.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ec201804a.common.domain.AdminUser;

/**
 * 管理者がログインをするコントローラ.
 * 
 * @author yuta.kitazawa
 */
@Controller
@SessionAttributes(types = {AdminUser.class})
@RequestMapping(value = "/admin")
public class AdminUserLoginController {
	
	/**
	 * ログインフォームを初期化.
	 * 
	 * @return AdminUserLoginForm
	 */
	@ModelAttribute
	public AdminUserLoginForm setUpForm() {
		return new AdminUserLoginForm();
	}
	
	/**
	/**
	 * 管理者ログイン画面を表示する.
	 * 
	 * @param form　フォーム
	 * @param result　リザルト
	 * @param model　モデル
	 * @param error　エラー
	 * @return　管理者ログイン画面
	 */
	@RequestMapping(value = "/")
	public String index(AdminUserLoginForm form, BindingResult result
			,Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		
		if(error != null) {
			System.err.println("adminUser: login failed");
			result.addError(new ObjectError("loginError", "入力情報は不正です"));
		}

		return "/admin/loginAdminister";

	}
}
