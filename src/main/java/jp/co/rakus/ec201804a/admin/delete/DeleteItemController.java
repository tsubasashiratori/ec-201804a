package jp.co.rakus.ec201804a.admin.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;

@Controller
@RequestMapping("/admin/DeleteItem")
public class DeleteItemController {

	@Autowired
	private EditItemRepository repository;

	@RequestMapping("/")
	public String deleteItemById(@RequestParam("id") Long id) {

		Item item = repository.load(id);
		
		item.setDeleted(false);
		
		repository.save(item);

		return "redirect:/admin/adminItemfindAll";

	}
}
