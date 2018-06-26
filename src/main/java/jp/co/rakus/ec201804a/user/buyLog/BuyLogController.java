package jp.co.rakus.ec201804a.user.buyLog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class BuyLogController {
	@RequestMapping(value = "/buyLog")
	public String buyLog() {
		return "user/buyLog";
	}
}
