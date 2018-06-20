package jp.co.rakus.ec201804a.user.ViewAllAndsearch4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;

import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.form.ViewAllItemsAndSearchItemForm;

import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository.ViewAllItemsRepository;

/**
 * @author shunta.nakamura
 *
 */
@Controller
@RequestMapping("/ViewAllItemsAndSearchItem")
public class ViewAlItemsAndSearchItemController {

	@Autowired
	private ViewAllItemsRepository repository;

	@ModelAttribute
	public ViewAllItemsAndSearchItemForm setUpForm() {
		return new ViewAllItemsAndSearchItemForm();
	}

	@RequestMapping("/findAllNotDeleted")
	public String findAllNotDeleted(Model model) {

		List<Item> itemList = repository.findAllNotDeleted();

		model.addAttribute("itemList", itemList);

		return "/user/viewShoppingCart";

	}

	@RequestMapping("/findByNameNotDeleted")
	public String findByNameNotDeleted(@Validated ViewAllItemsAndSearchItemForm viewAllItemsAndSearchForm, BindingResult result, Model model) {

		String name = viewAllItemsAndSearchForm.getName();
		
		List<Item> itemList = repository.findByNameNotDeleted(name);

		
		if(itemList.size() == 0) {
			
			result.rejectValue("name","", "該当する商品がありません");
			System.out.println("test");
		}
		if(result.hasErrors()) {
			return "/user/viewShoppingCart";
		}
		
		model.addAttribute("itemList", itemList);

		return "/user/viewShoppingCart";
	}
}
