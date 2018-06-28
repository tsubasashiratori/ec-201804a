package jp.co.rakus.ec201804a.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * エラーページを表示するコントローラクラス.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping(value = "/user")
public class CommonController {

	/**
	 * 500エラーページを表示するメソッド.
	 * 
	 * @return 500エラーページ
	 */
	@RequestMapping(value = "/systemError")
	public String exception() {
		return "common/error500";
	}
	
}
