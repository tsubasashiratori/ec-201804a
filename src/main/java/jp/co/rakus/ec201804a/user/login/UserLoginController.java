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
		User user = repository.findByOneMailAddress(form.getMailAddress());
		
		boolean error = false;
		if(user==null) {
			return index();
		}
		if(form.getPassword().equals(user.getPassword())) {
			error = true;
		}
		//errorがあればreturn
		if(error) {
			return index();
		}
		
		session.setAttribute("user", user);
		System.out.println(user);
		
		return "ItemList";
	}
}
