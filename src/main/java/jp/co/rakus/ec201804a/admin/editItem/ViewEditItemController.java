package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;

@Controller
@RequestMapping("/admin")
public class ViewEditItemController {
	
	@Autowired
	private EditItemRepository editItemRepository;

	@ModelAttribute
	public EditItemForm setUpForm(@RequestParam long itemId) {
		Item item = editItemRepository.load(itemId);
		EditItemForm form = new EditItemForm();
		BeanUtils.copyProperties(item, form);
		System.out.println(form.getId());
		return form;
	}

	
	@RequestMapping(value = "/viewEditItem")
	public String viewEditItemPage(Model model, @RequestParam long itemId) {
		System.out.println(itemId);
		System.out.println(editItemRepository.load(itemId));
		Item item = editItemRepository.load(itemId);
		model.addAttribute("item", item);
		System.out.println(model);
		return "/admin/edit";
	}
	
	
}
