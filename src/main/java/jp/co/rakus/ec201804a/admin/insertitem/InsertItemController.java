package jp.co.rakus.ec201804a.admin.insertitem;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

/**
 * 商品登録処理を行うコントローラー.
 * 
 * @author tsubasa.shiratori
 */
@Controller
@Transactional
@RequestMapping(value="/admin")
public class InsertItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * フォームの初期化
	 * @return 初期かされたフォーム
	 */
	@ModelAttribute
	public InsertItemForm setUpForm() {
		return new InsertItemForm();
	}
	
	/**
	 * 商品登録画面表示.
	 * 
	 * @return 商品登録画面
	 */
	@RequestMapping(value="/insertItem")
	public String viewInsertItem() {
		return "admin/insertItem";
	}
	
	/**
	 * 商品登録処理.
	 * 
	 * @param form フォーム
	 * @param result リザルト
	 * @return 商品一覧画面
	 */
	@RequestMapping(value="/insert")
	public String insertItem(@Validated InsertItemForm form, BindingResult result) {
		if (result.hasErrors()) {			
			return viewInsertItem();
		}
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		System.out.println(item);
		itemRepository.insertItem(item);
		return "redirect:/admin/adminFindAll";
	}

}
