package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

/**
 * 商品詳細の編集に関するコントローラクラス.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping(value = "/admin")
public class EditItemController {
	
//	@Autowired
//	private EditItemRepository editItemRepository;

	@Autowired
	private ItemRepository ItemRepository;

	@ModelAttribute
	private EditItemForm setUpForm() {
		return new EditItemForm();
	}

	/**
	 * 商品詳細を編集し管理者の商品一覧画面にリダイレクトする.
	 * 
	 * @param form
	 *            フォーム
	 * @param model
	 *            モデル
	 * @return
	 */
	@RequestMapping("/editItem")
	public String editItem(@Validated EditItemForm form, BindingResult result, RedirectAttributes flash) {
		if ("".equals(form.getPrice())) {
			result.rejectValue("price", null, "価格を入力してください");
		} else if (!form.getPrice().matches("\\d+")) {
			result.rejectValue("price", null, "1～1000000の数字で入力してください");
		} else if (form.getIntPrice() <= 0 || 1000001 <= form.getIntPrice()) {
			result.rejectValue("price", null, "1～1000000の数字で入力してください");
		}
		Item beforeItem = ItemRepository.load(form.getId());
		if(beforeItem.getName().equals(form.getName())) {
			
		}else if(ItemRepository.adminItemFindByName(form.getName())==null) {			
			
		}else {
			result.rejectValue("name", null, "すでに登録されている商品名です");	
		}
		if (result.hasErrors()) {
			return "/admin/edit";
		}
		
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setPrice(form.getIntPrice());

		ItemRepository.save(item);

		flash.addFlashAttribute("success", "編集完了しました");
		System.out.println("編集完了");

		return "redirect:/admin/adminFindAll";
	}
}