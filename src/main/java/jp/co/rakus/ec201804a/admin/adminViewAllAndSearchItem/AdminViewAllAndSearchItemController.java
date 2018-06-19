package jp.co.rakus.ec201804a.admin.adminViewAllAndSearchItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;

@Controller
@RequestMapping("/adminAllandSearch")
public class AdminViewAllAndSearchItemController {

	@Autowired
	AdminViewAllAndSearchItemRepository adminRepository;
	
	@RequestMapping("/adminFindAll")
	public String adminItemFindAll(Model model) {
		List<Item> itemList = adminRepository.adminItemFindAll();
		itemList.forEach(System.out::println);
		model.addAttribute("itemList", itemList);
		
		return "/admin/itemList";
	}
	
	@RequestMapping("/adminFindByName")
	public String adminItemFindByName(Model model, String name) {
		List<Item> itemList = adminRepository.adminItemFindByName(name);
		model.addAttribute("itemList", itemList);
		return "/admin/itemList";
	}
}
