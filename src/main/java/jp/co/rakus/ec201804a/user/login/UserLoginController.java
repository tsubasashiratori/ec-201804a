package jp.co.rakus.ec201804a.user.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.User;

@Controller
@RequestMapping(value = "/user")
public class UserLoginController {
	@Autowired
	UserLoginRepository repository;
	
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
	public String index() {
		return "/user/loginUser";
	}
	
	@RequestMapping(value = "/login")
	public String login(UserLoginForm form, HttpSession session) {
		User user = repository.findByOneMailAddress(form.getEmail());
		
		boolean error = false;
		if(user==null) {
			System.out.println("ユーザが存在しない");
			return index();
		}
		System.out.println(user);
		if(!form.getPassword().equals(user.getPassword())) {
			error = true;
		}
		//errorがあればreturn
		if(error) {
			System.out.println("エラーがあります");
			return index();
		}
		
		session.setAttribute("user", user);
		
		return "redirect:/ViewAllItemsAndSearchItem/findAllNotDeleted";
	}
}
