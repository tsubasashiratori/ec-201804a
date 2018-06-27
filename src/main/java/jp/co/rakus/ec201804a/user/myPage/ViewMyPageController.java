package jp.co.rakus.ec201804a.user.myPage;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.User;
import jp.co.rakus.ec201804a.common.login.LoginUser;

/**
 * 利用者のマイページを表示するコントローラ.
 * 
 * @author yuta.kitazawa
 */
@Controller
@RequestMapping(value = "/user")
public class ViewMyPageController {
	
	/**
	 * マイページを表示する.
	 * 
	 * @return マイページ
	 */
	@RequestMapping(value = "/myPage")
	public String myPage(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LoginUser loginUser = (LoginUser)principal;
		User user = loginUser.getUser();
		
		model.addAttribute("user" , user);
		return "user/myPage";
	}
}
