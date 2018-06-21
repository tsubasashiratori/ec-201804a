package jp.co.rakus.ec201804a.admin.viewAllAndSearchItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;

/**
 * 管理者の商品一覧と部分一致検索を行うコントローラークラス.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminViewAllAndSearchItemController {

	@Autowired
	AdminViewAllAndSearchItemRepository adminRepository;
	
	/**
	 * 検索結果をリクエストスコープに格納する.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページに遷移する
	 */
	@RequestMapping("/adminFindAll")
	public String adminItemFindAll(Model model) {
		List<Item> itemList = adminRepository.adminItemFindAll();
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
	public String adminItemFindByName(Model model, String name) {
		List<Item> itemList = adminRepository.adminItemFindByName(name);
		model.addAttribute("itemList", itemList);
		return "/admin/itemList";
	}
}
