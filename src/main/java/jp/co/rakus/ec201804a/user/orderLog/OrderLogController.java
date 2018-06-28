package jp.co.rakus.ec201804a.user.orderLog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.login.LoginUser;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;

/**
 * 利用者の購入履歴を表示するコントローラ.
 * 
 * @author yuta.kitazawa
 */
@Controller
@RequestMapping(value = "/user")
public class OrderLogController {
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 利用者の注文履歴の詳細を表示する.
	 * 
	 * @param model
	 *            モデル
	 * @param orderId
	 *            注文id
	 * @return 注文履歴の詳細表示画面
	 */
	@RequestMapping(value = "/orderLogDetail")
	public String buyLog(Model model, @RequestParam long orderId) {
		Order order = orderRepository.findByOrderId(orderId);
		model.addAttribute("order", order);
		return "user/orderLogDetail";
	}

	/**
	 * 利用者の注文履歴をリストで表示する.
	 * 
	 * @param model
	 *            モデル
	 * @return 注文履歴のリスト表示画面
	 */
	@RequestMapping(value = "/orderLogList")
	public String orderList(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LoginUser loginUser = (LoginUser) principal;
		Long userId = loginUser.getUser().getId();
		
	
		List<Order> orderList1 = orderRepository.findByUserIdAndStatusForView(userId, 1);
		List<Order> orderList2 = orderRepository.findByUserIdAndStatusForView(userId, 2);
		List<Order> orderList3 = orderRepository.findByUserIdAndStatusForView(userId, 3);

		List<Order> orderList =new ArrayList<>();

		orderList.addAll(orderList1);
		orderList.addAll(orderList2);
		orderList.addAll(orderList3);
	
		
		if (orderList.isEmpty() == true) {
			model.addAttribute("orderListEmptyChecker", true);
			model.addAttribute("EmptyError", "注文がありません");
			return "/user/orderLogList";
		}
		
		model.addAttribute("orderListEmptyChecker", false);
		model.addAttribute("orderList", orderList);

		return "user/orderLogList";
	}
}
