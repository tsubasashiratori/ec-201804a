package jp.co.rakus.ec201804a.user.ViewAllAndsearch4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository.ViewAllItemsRepository;

@Controller
@RequestMapping("/ViewAllItemsAndSearchItem")
public class ViewAlItemsAndSearchItemController {

	@Autowired
	private ViewAllItemsRepository repository;

	@RequestMapping("/findAllNotDeleted")
	public String findAllNotDeleted(HttpServletRequest request) {
		
		List<Item> itemList = repository.findAllNotDeleted();
		
		request.setAttribute("itemList", itemList);
		
		return "viewShoppingCart";

	}
	
	@RequestMapping("/findByNameNotDeleted")
	public String findByNameNotDeleted(String name, HttpServletRequest request) {
		
		List<Item> itemList = repository.findByNameNotDeleted(name);
		
		request.setAttribute("itemList", itemList);
		
		return "viewShoppingCart";
	}
}
