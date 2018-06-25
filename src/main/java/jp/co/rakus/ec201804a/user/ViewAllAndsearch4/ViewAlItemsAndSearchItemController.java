package jp.co.rakus.ec201804a.user.ViewAllAndsearch4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;
import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.form.ViewAllItemsAndSearchItemForm;

/**
 * ４．商品一覧の表示と検索を行うコントローラー.
 * @author shunta.nakamura
 *
 */
@Controller
@RequestMapping("/user/ViewAllItemsAndSearchItem")
public class ViewAlItemsAndSearchItemController {

	@Autowired
	private ItemRepository itemRepository;

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

		List<Item> itemList = itemRepository.findAllNotDeleted();

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
		
		List<Item> itemList = itemRepository.findByNameNotDeleted(name);
		model.addAttribute("itemList", itemList);
		
		if(result.hasErrors()) {
			return "/user/viewShoppingList";
		}

		return "/user/viewShoppingList";
	}
}
