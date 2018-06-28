package jp.co.rakus.ec201804a.admin.viewAllAndSearchItem;

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

/**
 * 管理者の商品一覧と部分一致検索を行うコントローラークラス.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminViewAllAndSearchItemController {

	@Autowired
	private ItemRepository itemRepository;

	@ModelAttribute
	private AdminViewAllAndSearchItemForm setUpform() {
		return new AdminViewAllAndSearchItemForm();
	}
	/**
	 * 検索結果をリクエストスコープに格納する.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページに遷移する
	 */
	@RequestMapping("/adminFindAll")
	public String adminItemFindAll(Model model){
		List<Item> itemList = itemRepository.adminItemFindAll();
		itemList.forEach(System.out::println);
		
		model.addAttribute("itemList", itemList);
		
		return "/admin/itemList";
	}
	
	/**
	 * 検索結果をリクエストスコープに格納する.
	 * 
	 * @param model モデル
	 * @param name 商品名
	 * @return 商品一覧ページに遷移する
	 */
	@RequestMapping("/adminFindByName")
	public String adminItemFindByName(@Validated AdminViewAllAndSearchItemForm form ,BindingResult result, Model model) {
		
		String name = form.getName();
		List<Item> itemList = itemRepository.adminItemFindByName(name);
		
		if(1 > name.length() || name.length() > 20) {
			result.rejectValue("name",null, "1文字以上20文字以内でキーワードを入力してください");
		}
		if(itemList.size()==0) {
			result.rejectValue("name",null, "");
		}
		if(result.hasErrors()) {
			return "/admin/itemList";
		}
		
		model.addAttribute("itemList", itemList);

		return "/admin/itemList";
	}
}
