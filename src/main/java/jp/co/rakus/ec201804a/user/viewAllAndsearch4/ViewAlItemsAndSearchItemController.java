package jp.co.rakus.ec201804a.user.viewAllAndsearch4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

/**
 * ４．商品一覧の表示と検索を行うコントローラー.
 * 
 * @author shunta.nakamura
 *
 */
@Controller
@RequestMapping("/user")
public class ViewAlItemsAndSearchItemController {

	@Autowired
	private ItemRepository itemRepository;

	@ModelAttribute
	public ViewAllItemsAndSearchItemForm setUpForm() {
		return new ViewAllItemsAndSearchItemForm();
	}

	/**
	 * 商品一覧を表示するメソッド.
	 * 
	 * @param model
	 * @return 商品一覧にページを遷移する
	 */
	@RequestMapping("/")
	public String findAllNotDeleted(Model model) {

		double limit = 10.0;
		Integer pageCount = itemRepository.pageCount();
		Integer page = (int) Math.ceil(pageCount / limit);
		model.addAttribute("page", page);
		int pageNum = 1;
		model.addAttribute("pageNum", pageNum);
		int thisfind = (int) ((pageNum * limit) - limit);
		List<Item> itemList = itemRepository.findAllNotDeletedByPageNum(thisfind, (int) limit);
		model.addAttribute("itemList", itemList);

		// List<Item> itemList = itemRepository.findAllNotDeleted();
		// model.addAttribute("itemList", itemList);

		List<Item> itemListTop5Count = itemRepository.findHighCountItem();
		model.addAttribute("itemListTop5Count", itemListTop5Count);

		model.addAttribute("Item", true);
		model.addAttribute("searchItem", false);
		return "/user/viewShoppingList";
	}

	/**
	 * ページ番号に対応して表示
	 * 
	 * @param pageNum
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAllNotDeletedByPageNum")
	public String findAllNotDeletedByPageNum(@RequestParam Integer pageNum, Model model) {
		try {
			double limit = 10.0;
			Integer pageCount = itemRepository.pageCount();
			Integer page = (int) Math.ceil(pageCount / limit);
			model.addAttribute("page", page);
			model.addAttribute("pageNum", pageNum);
			int thisfind = (int) ((pageNum * limit) - limit);

			List<Item> itemList = itemRepository.findAllNotDeletedByPageNum(thisfind, (int) limit);
			model.addAttribute("itemList", itemList);

			// List<Item> itemList = itemRepository.findAllNotDeleted();
			// model.addAttribute("itemList", itemList);

			List<Item> itemListTop5Count = itemRepository.findHighCountItem();
			model.addAttribute("itemListTop5Count", itemListTop5Count);

			model.addAttribute("Item", true);
			model.addAttribute("searchItem", false);
			System.out.println(page);
			System.out.println(pageNum);
			return "/user/viewShoppingList";
		} catch (Exception e) {
			e.printStackTrace();
			return "/user/viewShoppingList";
		}
	}

	/**
	 * 商品検索をするメソッド.
	 * 
	 * @param viewAllItemsAndSearchForm
	 *            商品検索をするフォーム
	 * @param result
	 *            エラー文を格納するバインディングリザルト
	 * @param model
	 *            モデル
	 * @return 商品一覧画面へページを遷移する
	 */
	@RequestMapping("/findByNameNotDeleted")
	public String findByNameNotDeleted(@Validated ViewAllItemsAndSearchItemForm viewAllItemsAndSearchForm,
			BindingResult result, Model model) {

		String name = viewAllItemsAndSearchForm.getName();
		model.addAttribute("name", name);

		double limit = 10.0;
		Integer pageCount = itemRepository.pageCountSearch(name);
		Integer page = (int) Math.ceil(pageCount / limit);
		model.addAttribute("page", page);
		int pageNum = 1;
		model.addAttribute("pageNum", pageNum);
		int thisfind = (int) ((pageNum * limit) - limit);
		System.out.println(page);
		System.out.println(pageNum);
		System.out.println(name);
		// List<Item> itemList=itemRepository.findAllNotDeletedByPageNum(thisfind,
		// limit);
		// model.addAttribute("itemList", itemList);

		if (result.hasErrors()) {
			model.addAttribute("notDisplay", true);
			return "/user/viewShoppingList";
		}

		List<Item> itemList = itemRepository.findByNameNotDeleted(thisfind, (int) limit, name);
		model.addAttribute("itemList", itemList);

		model.addAttribute("searchItem", true);
		model.addAttribute("Item", false);
		return "/user/viewShoppingList";
	}

	/**
	 * 検索時のページング.
	 * 
	 * @param pageNum
	 * @param viewAllItemsAndSearchForm
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/findByNameNotDeletedPageNum")
	public String findByNameNotDeletedPageNum(@RequestParam String name, @RequestParam Integer pageNum, Model model) {

		model.addAttribute("name", name);
		double limit = 10.0;
		Integer pageCount = itemRepository.pageCountSearch(name);
		Integer page = (int) Math.ceil(pageCount / limit);
		model.addAttribute("page", page);
		model.addAttribute("pageNum", pageNum);
		int thisfind = (int) ((pageNum * limit) - limit);

		List<Item> itemList = itemRepository.findByNameNotDeleted(thisfind, (int) limit, name);
		model.addAttribute("itemList", itemList);

		model.addAttribute("searchItem", true);
		model.addAttribute("Item", false);
		return "/user/viewShoppingList";
	}
}
