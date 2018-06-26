package jp.co.rakus.ec201804a.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class CommonController {

	@RequestMapping(value = "/systemError")
	public String exception() {
		return "common/error500";
	}
	
}
