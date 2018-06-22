package jp.co.rakus.ec201804a.admin.orderList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.user.payment9.OrderRepository9;

@Controller
@Transactional
@RequestMapping(value = "/admin")
public class OrderListController {
	@Autowired
	OrderRepository9 orderRipositroy;
	
	@RequestMapping(value = "/viewOrderList")
	public String index(Model model) {
		List<Order> orderList = orderRipositroy.findAll();
		
		model.addAttribute("orderList", orderList);
		
		return "/admin/orderList";
	}
}
