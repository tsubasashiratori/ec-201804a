package jp.co.rakus.ec201804a.user.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class ViewMyPageController {
	@RequestMapping(value = "/myPage")
	public String myPage() {
		return "user/myPage";
	}
}
