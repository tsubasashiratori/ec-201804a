package payment_9;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		if(payment == null) {
			model.addAttribute("nullError", "注文がありません");
		}
		model.addAttribute("payment", payment);
		return "/user/makePayment";
	}
	
	public String payment(String orderId) {
		Order payment = orderRepository.findById(orderId);
		
		payment.setStatus(0);
		payment.setOrderNumber(dateAndSequence());
		orderRepository.save(payment);
	}
	
	public String dateAndSequence() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String strDate = formatter.format(localDate);
		return strDate;
	}
}
