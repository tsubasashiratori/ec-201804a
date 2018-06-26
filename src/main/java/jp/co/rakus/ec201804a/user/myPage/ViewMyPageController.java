package jp.co.rakus.ec201804a.user.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String myPage() {
		return "user/myPage";
	}
}
