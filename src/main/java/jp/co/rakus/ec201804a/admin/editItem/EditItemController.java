package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;

@Controller
@RequestMapping(value = "/admin")
public class EditItemController {
	
	@Autowired
	private EditItemRepository editItemRepository;
	
	@RequestMapping("/editItem")
	public String editItem(EditItemForm form, Model model) {

		Item item = new Item();
		System.out.println(form.getId());
		BeanUtils.copyProperties(form, item);
		System.out.println(item.getId());
		editItemRepository.save(item);
		model.addAttribute("item", item);
		System.out.println("編集完了");
		
		return "redirect:/admin/adminFindAll";
	}
}