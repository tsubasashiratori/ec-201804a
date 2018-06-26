package jp.co.rakus.ec201804a.admin.deleteItem;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

@Controller
@RequestMapping("/admin")
public class DeleteItemController {

	@Autowired
	private ItemRepository itemRepository;

	@RequestMapping("/deleteItem")
	public String deleteItemById(@RequestParam("id") Long id, RedirectAttributes flash) {
		System.out.println("testdelete");

		Item item = itemRepository.load(id);
		System.out.println(item);
		
		if(item.getDeleted() == true) {
			item.setDeleted(false);
			flash.addFlashAttribute("redisplay", item.getName()+"を再表示しました");
		}
		else {
			item.setDeleted(true);
			flash.addFlashAttribute("delete", item.getName()+"を削除しました");
		}
		itemRepository.save(item);
		
		return "redirect:/admin/adminFindAll";

	}
}
