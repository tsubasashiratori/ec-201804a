package jp.co.rakus.ec201804a.user.payment9;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;

@Controller
@Transactional
@RequestMapping(value = "/user")
public class PaymentController {

	@Autowired
	private OrderRepository9 orderRepository;

	@RequestMapping(value = "/viewPaymentDetail")
	public String viewPaymentDetail(/*@RequestParam String orderId,*/ Model model) {
		long longOrderId = new Long(1);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

		Order order = orderRepository.findById(longOrderId);

		if (order == null) {
			model.addAttribute("nullError", "注文がありません");
		}

		model.addAttribute("order", order);
		return "/user/makePayment";
	}

	@RequestMapping(value = "/toPayment")
	public String payment(@RequestParam String orderId, Model model) {
		long longOrderId = new Long(orderId);
		Order payment = orderRepository.findById(longOrderId);
		payment.setStatus(0);
		payment.setOrderNumber(dateAndSequence());

		orderRepository.save(payment);

		return "redirect:/user/confirmedPayment";
	}

	public String dateAndSequence() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String strDate = formatter.format(localDate);
		return strDate;
	}
}
