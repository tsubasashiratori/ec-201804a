package jp.co.rakus.ec201804a.admin.orderList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;

/**
 * 注文一覧表示を行うコントローラー
 * @author kohei.taguchi
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
public class OrderListController {
	@Autowired
	OrderRepository orderRipositroy;
	
	/**注文一覧画面の初期表示を行うメソッド.
	 * @param model　モデル
	 * @return　注文一覧表示画面のURL
	 */
	@RequestMapping(value = "/viewOrderList")
	public String index(Model model) {
		List<Order> orderList = orderRipositroy.findAll();
		
		if (orderList.isEmpty() == true) {
			model.addAttribute("orderListEmptyChecker", true);
			model.addAttribute("EmptyError", "注文がありません");
			return "/admin/orderList";
		}
		model.addAttribute("orderListEmptyChecker", false);
		model.addAttribute("orderList", orderList);
		
		return "/admin/orderList";
	}
}
