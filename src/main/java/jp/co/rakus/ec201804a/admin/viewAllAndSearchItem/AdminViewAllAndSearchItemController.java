package jp.co.rakus.ec201804a.admin.viewAllAndSearchItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.admin.editItem.EditItemForm;
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
	private EditItemForm setUpform() {
		return new EditItemForm();
	}
	/**
	 * 検索結果をリクエストスコープに格納する.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページに遷移する
	 */
	@RequestMapping("/adminFindAll")
	public String adminItemFindAll(Model model) {
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
	public String adminItemFindByName(@Validated EditItemForm editItemForm,BindingResult result, Model model) {
		
		String name = editItemForm.getName();
		
		List<Item> itemList = itemRepository.adminItemFindByName(name);
		
		if(itemList.size()==0) {
			result.rejectValue("name",null, "該当する商品がありません");
		}
		if(result.hasErrors()) {
			return "/admin/itemList";
		}
		
		model.addAttribute("itemList", itemList);
		return "/admin/itemList";
	}
}
