package jp.co.rakus.ec201804a.user.ViewAllAndsearch4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.user.viewAllAndsearch4.repository.ViewAllItemsRepository;

@Controller
@RequestMapping("/ViewAllItemsAndSearchItem")
public class ViewAlItemsAndSearchItemController {

//	@ModelAttribute
//	public ViewAllItemsAndSearchItemForm setUpForm() {
//		return new ViewAllItemsAndSearchItemForm();
//	}
	@Autowired
	private ViewAllItemsRepository repository;

	@RequestMapping("/findAllNotDeleted")
	public String findAllNotDeleted(HttpServletRequest request) {
		
		List<Item> itemList = repository.findAllNotDeleted();
		
		request.setAttribute("itemList", itemList);
		
		return "/user/viewShoppingCart";

	}
	
	@RequestMapping("/findByNameNotDeleted")
	public String findByNameNotDeleted(@RequestParam ("name") String name, HttpServletRequest request) {
		
		List<Item> itemList = repository.findByNameNotDeleted(name);
		
		request.setAttribute("itemList", itemList);
		
		return "/user/viewShoppingCart";
	}
}
