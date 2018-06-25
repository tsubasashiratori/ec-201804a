package jp.co.rakus.ec201804a.admin.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class ViewMenuController {
	
	@RequestMapping(value = "/menu")
	public String menu() {
		return "/admin/administerMenu";
	}
}
