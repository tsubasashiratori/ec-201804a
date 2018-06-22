package jp.co.rakus.ec201804a.admin.deleteItem;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

@Controller
@RequestMapping("/admin")
public class DeleteItemController {

	@Autowired
	private ItemRepository itemRepository;

	@RequestMapping("/deleteItem")
	public String deleteItemById(@RequestParam("id") Long id) {
		System.out.println("testdelete");

		Item item = itemRepository.load(id);
		
		if(item.getDeleted() == true) {
			item.setDeleted(false);
		}
		else {
			item.setDeleted(true);
		}
		itemRepository.save(item);

		return "redirect:/admin/adminFindAll";

	}
}
