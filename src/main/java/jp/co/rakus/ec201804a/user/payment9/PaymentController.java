package jp.co.rakus.ec201804a.user.payment9;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.User;

@Controller
@Transactional
@RequestMapping(value = "/user")
public class PaymentController {

	@Autowired
	private OrderRepository9 orderRepository;

	@RequestMapping(value = "/viewPaymentDetail/{id}")
	public String viewPaymentDetail(User user, @PathVariable("id") String orderId, Model model) {
//		user.setId(1l);
//		user.setName("abc");
//		user.setEmail("abc@abc");
//		user.setZipCode("1234567");
//		user.setAddress("abc-abc");
//		user.setTelephone("012-345-6789");
//		
		if (user.getId() == null) {
			return "redirect:/user/";
		}
		
		System.out.println(user.toString());
		
		//long longOrderId = new Long(2);
		
		long longOrderId = new Long(orderId);
		
		Order order = orderRepository.findById(longOrderId);

		if (order == null) {
			model.addAttribute("nullError", "注文がありません");
		}
		
		else {
			order.setUserId(user.getId());
			order.setStatus(0);
			order.setTotalPrice(order.getTotalPriceIncludeTaxAndPostage());
			order.setOrderDate(setSqlDateNow());
			order.setDeliveryName(user.getName());
			order.setDeliveryEmail(user.getEmail());
			order.setDeliveryZipCode(user.getZipCode());
			order.setDeliveryAddress(user.getAddress());
			order.setDeliveryTel(user.getTelephone());
			
			orderRepository.save(order);
		}

		model.addAttribute("order", order);
		return "/user/makePayment";
	}

	@RequestMapping(value = "/toPayment")
	public String payment(@RequestParam String orderId, Model model) {
		long longOrderId = new Long(orderId);
		Order order = orderRepository.findById(longOrderId);
		order.setStatus(1);
		order.setOrderNumber(dateAndSequence());

		orderRepository.save(order);

		return "redirect:/user/confirmedPayment";
	}
	
	@RequestMapping(value = "/confirmedPayment")
	public String toPayment() {
		return "/user/payment";
	}

	public String dateAndSequence() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String strDate = formatter.format(localDate);
		return strDate;
	}
	
	public Date setSqlDateNow() {
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		return date;
	}
}
