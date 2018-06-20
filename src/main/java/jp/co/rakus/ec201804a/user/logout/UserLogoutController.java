package jp.co.rakus.ec201804a.user.logout;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserLogoutController {
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/user/";
	}
}
