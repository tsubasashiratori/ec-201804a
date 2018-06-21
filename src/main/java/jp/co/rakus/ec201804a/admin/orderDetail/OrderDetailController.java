package jp.co.rakus.ec201804a.admin.orderDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.user.payment9.OrderRepository9;

@Controller
@Transactional
@RequestMapping(value = "/admin")
public class OrderDetailController {
	
	@Autowired
	OrderRepository9 orderRepository;
	
	@RequestMapping(value = "/viewOrderDetail")
	public String viewOrderDetail(@RequestParam long orderId, Model model) {
		Order order = orderRepository.findById(orderId);
		System.out.println(order);
		model.addAttribute("order", order);
		return "/admin/orderDetail";
	}
	
	@RequestMapping(value = "/updateStatus")
	public String updateStatus(@RequestParam long orderId, @RequestParam Integer status, Model model) {
		Order order = orderRepository.findById(orderId);
		order.setStatus(status);
		orderRepository.save(order);
		
		model.addAttribute("updateMessage", "更新しました");
		return "redirect:/admin/viewOrderDetail";
	}
}
