package payment_9;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Order;

@Controller
@Transactional
@RequestMapping(value = "/payment")
public class PaymentController {
	
	/*
	 * @AutoWired
	 * private OrderRepository orderRepository;
	 * 
	 * */
	
	@RequestMapping(value = "/toMakePayment")
	public String viewPaymentDetail(String orderId, Model model) {
		Order payment = orderRepository.findById(orderId);
		model.addAttribute("payment", payment);
	}
}
