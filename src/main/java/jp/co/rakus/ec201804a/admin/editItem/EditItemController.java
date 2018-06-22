package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;

/**
 * 商品詳細の編集に関するコントローラクラス.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping(value = "/admin")
public class EditItemController {
	
	@Autowired
	private EditItemRepository editItemRepository;
	
	/**
	 * 商品詳細を編集し管理者の商品一覧画面にリダイレクトする.
	 * 
	 * @param form フォーム
	 * @param model モデル
	 * @return 
	 */
	@RequestMapping("/editItem")
	public String editItem(@Validated EditItemForm form, BindingResult result, Model model) {

		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		
		if(result.hasErrors()) {
			return "/admin/edit";
		}
		
		editItemRepository.save(item);
		model.addAttribute("item", item);
		System.out.println("編集完了");
		
		return "redirect:/admin/adminFindAll";
	}
}