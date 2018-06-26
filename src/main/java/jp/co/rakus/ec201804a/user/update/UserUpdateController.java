package jp.co.rakus.ec201804a.user.update;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
@Transactional
public class UserUpdateController {
	@RequestMapping(value = "/updateForm")
	public String viewUpdate() {
		return "";
	}
	
	@RequestMapping(value = "/update")
	public String updateUser() {
		return "redirect:/user/updateForm";
	}
}
