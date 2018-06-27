package jp.co.rakus.ec201804a.user.viewDetail;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;
import jp.co.rakus.ec201804a.user.shoppingcart.InsertShoppingCartForm;

/**
 * 商品の詳細を表示するコントローラー.
 * @author shunta.nakamura
 *
 */
@Controller
@RequestMapping("/user")
public class ViewDetailController {

	@Autowired
	public ItemRepository itemRepository;
	
	@ModelAttribute
	public InsertShoppingCartForm setUpInsert() {
		return new InsertShoppingCartForm();
	}

	/** 
	 * 商品の詳細を表示するメソッド.
	 * @param id
	 * @param request リクエストスコープ
	 * @return 商品詳細ページへ遷移する
	 */
	@RequestMapping("/viewDetail")
	public String ViewDetail(@RequestParam ("id") String id, HttpServletRequest request) {
		//System.out.println("a");
		long idLong = Long.parseLong(id);
		Item item = itemRepository.findDetailByIdNotDeleted(idLong);
		Map<Integer, Integer> quantityMap = new LinkedHashMap<>();
		quantityMap.put(1, 1);
		quantityMap.put(2, 2);
		quantityMap.put(3, 3);
		quantityMap.put(4, 4);
		quantityMap.put(5, 5);
		quantityMap.put(6, 6);
		quantityMap.put(7, 7);
		quantityMap.put(8, 8);
		quantityMap.put(9, 9);
		request.setAttribute("quantityMap", quantityMap);
		
		if(item == null) {
			return "user/errorDirectWritingURL";
		}
		
		try {
			//System.out.println(item.getCount());
		item.setCount(item.getCount()+1);
		itemRepository.updateCount(item);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("item", item);
		return "/user/viewDetail";
	}

}
