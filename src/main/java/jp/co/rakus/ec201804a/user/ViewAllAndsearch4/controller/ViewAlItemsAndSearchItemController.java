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
import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository.ItemsRepository;

/**
 * ４．商品一覧の表示と検索を行うコントローラー.
 * @author shunta.nakamura
 *
 */
@Controller
@RequestMapping("/ViewAllItemsAndSearchItem")
public class ViewAlItemsAndSearchItemController {

	@Autowired
	private ItemsRepository repository;

	@ModelAttribute
	public ViewAllItemsAndSearchItemForm setUpForm() {
		return new ViewAllItemsAndSearchItemForm();
	}

	/**
	 * 商品一覧を表示するメソッド.
	 * @param model
	 * @return 商品一覧にページを遷移する
	 */
	@RequestMapping("/findAllNotDeleted")
	public String findAllNotDeleted(Model model) {

		List<Item> itemList = repository.findAllNotDeleted();

		model.addAttribute("itemList", itemList);

		return "/user/viewShoppingList";

	}

	/**
	 * 商品検索をするメソッド.
	 * @param viewAllItemsAndSearchForm 商品検索をするフォーム
	 * @param result エラー文を格納するバインディングリザルト
	 * @param model モデル
	 * @return 商品一覧画面へページを遷移する
	 */
	@RequestMapping("/findByNameNotDeleted")
	public String findByNameNotDeleted(@Validated ViewAllItemsAndSearchItemForm viewAllItemsAndSearchForm, BindingResult result, Model model) {

		String name = viewAllItemsAndSearchForm.getName();
		
		List<Item> itemList = repository.findByNameNotDeleted(name);

		
		if(itemList.size() == 0) {
			
			result.rejectValue("name","", "該当する商品がありません");
			System.out.println("test");
		}
		if(result.hasErrors()) {
			return "/user/viewShoppingList";
		}
		
		model.addAttribute("itemList", itemList);

		return "/user/viewShoppingList";
	}
}
