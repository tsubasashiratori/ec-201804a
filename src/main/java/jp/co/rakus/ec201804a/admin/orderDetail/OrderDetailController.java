package jp.co.rakus.ec201804a.admin.orderDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;

/**
 * 注文詳細関連処理を行うコントローラー.
 * @author kohei.taguchi
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/admin")
public class OrderDetailController {
	
	@Autowired
	OrderRepository orderRepository;
	
	/**
	 * 注文詳細情報を表示するメソッド.
	 * @param orderId　表示したいOrdersテーブルのID
	 * @param update 更新フラグ
	 * @param model　モデル
	 * @return　詳細情報を表示するjspのURL
	 */
	@RequestMapping(value = "/viewOrderDetail")
	public String viewOrderDetail(@RequestParam long orderId, @RequestParam boolean update,  Model model) {
		if(update == true) {
			model.addAttribute("updateMessage", "更新しました");
		}
		
		Order order = orderRepository.findByOrderId(orderId);
		model.addAttribute("order", order);
		return "/admin/orderDetail";
	}
	
	/**
	 * 注文ステータスを変更するメソッド.
	 * @param status　変更するステータス
	 * @param orderId　ステータスを変更するOrdersテーブルのID
	 * @param model　モデル
	 * @return　詳細表示するメソッドへのリダイレクト
	 */
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(@RequestParam Integer status, @RequestParam long orderId, Model model) {
		Order order = orderRepository.findByOrderId(orderId);
		
		order.setStatus(status);
		orderRepository.save(order);
		
		return "redirect:/admin/viewOrderDetail?orderId=" + orderId + "&update=true";
	}
}
