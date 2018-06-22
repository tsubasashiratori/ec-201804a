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

@Controller
@RequestMapping("/admin")
public class ViewEditItemController {
	
	@Autowired
	private ItemRepository itemRepository;

	@ModelAttribute
	public EditItemForm setUpForm(@RequestParam long itemId) {
		Item item = itemRepository.load(itemId);
		EditItemForm form = new EditItemForm();
		BeanUtils.copyProperties(item, form);
		System.out.println(form.getId());
		return form;
	}

	
	@RequestMapping(value = "/viewEditItem")
	public String viewEditItemPage(Model model, @RequestParam long itemId) {
		System.out.println(itemId);
		System.out.println(itemRepository.load(itemId));
		Item item = itemRepository.load(itemId);
		model.addAttribute("item", item);
		System.out.println(model);
		return "/admin/edit";
	}
	
	
}
