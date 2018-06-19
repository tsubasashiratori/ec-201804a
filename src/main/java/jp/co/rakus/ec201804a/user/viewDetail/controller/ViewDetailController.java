package jp.co.rakus.ec201804a.user.viewDetail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository.ViewAllItemsRepository;

@Controller
@RequestMapping("/ViewDetail")
public class ViewDetailController {

	@Autowired
	public ViewAllItemsRepository repository;

	@RequestMapping("")
	public String ViewDetail(@RequestParam ("id") String id, HttpServletRequest request) {
		long idLong = Long.parseLong(id);
		Item item = repository.findDetailByIdNotDeleted(idLong);
		request.setAttribute("item", item);
		return "/user/viewDetail";
	}

}
