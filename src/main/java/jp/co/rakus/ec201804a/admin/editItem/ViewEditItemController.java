package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

/**
 * 管理者の商品編集画面を開く.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping("/admin")
public class ViewEditItemController {
	
	@Autowired
	private ItemRepository itemRepository;

	@ModelAttribute
	public EditItemForm setUpForm(@RequestParam long itemId, Model model) {
		Item item = itemRepository.load(itemId);
		EditItemForm form = new EditItemForm();
		BeanUtils.copyProperties(item, form);
		form.setPrice(""+item.getPrice());
		model.addAttribute("imagePath", item.getImagePath());
		return form;
	}

	
	/**
	 * 引数から検索した商品の編集ページを表示する.
	 * 
	 * @param model モデル
	 * @param itemId 商品ID
	 * @return 商品編集画面
	 */
	@RequestMapping(value = "/viewEditItem")
	public String viewEditItemPage(Model model, @RequestParam long itemId) {
		Item item = itemRepository.load(itemId);
		model.addAttribute("item", item);
		return "/admin/edit";
	}
	
	
}
