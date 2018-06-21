package jp.co.rakus.ec201804a.user.viewDetail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository.ItemsRepository;

/**
 * 商品の詳細を表示するコントローラー.
 * @author shunta.nakamura
 *
 */
@Controller
@RequestMapping("/user/ViewDetail")
public class ViewDetailController {

	@Autowired
	public ItemsRepository repository;

	/** 
	 * 商品の詳細を表示するメソッド.
	 * @param id
	 * @param request リクエストスコープ
	 * @return 商品詳細ページへ遷移する
	 */
	@RequestMapping("")
	public String ViewDetail(@RequestParam ("id") String id, HttpServletRequest request) {
		long idLong = Long.parseLong(id);
		Item item = repository.findDetailByIdNotDeleted(idLong);
		if(item == null) {
			return "user/errorDirectWritingURL";
		}
		request.setAttribute("item", item);
		return "/user/viewDetail";
	}

}
