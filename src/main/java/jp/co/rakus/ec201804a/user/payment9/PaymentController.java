package jp.co.rakus.ec201804a.user.payment9;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.User;
import jp.co.rakus.ec201804a.common.login.LoginUser;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;

/**
 * 決済関連処理を行うコントローラー.
 * @author kohei.taguchi
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/user")
public class PaymentController {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * 決済確認画面の表示を行う.
	 * @param orderId　表示するOrderオブジェクトのID
	 * @param model　モデル
	 * @return　画面表示を行うJspのURL
	 * 
	 */
	@RequestMapping(value = "/viewPaymentDetail")
	public String viewPaymentDetail(@RequestParam long orderId, Model model) {
		User user = null;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof LoginUser) {
				LoginUser loginUser = (LoginUser)principal;
				user = loginUser.getUser();
			} else {
			 System.out.println(principal.toString());
		}
		
		if (user == null) {
			return "redirect:/user/";
		}
		
		Order order = orderRepository.findById(orderId);

		if (order == null || order.getOrderItemList().isEmpty() == true) {
			model.addAttribute("orderNullChecker", true);
			model.addAttribute("nullError", "注文がありません");
			return "/user/makePayment";
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
		model.addAttribute("orderNullChecker", false);
		model.addAttribute("order", order);
		return "/user/makePayment";
	}

	/**
	 * 決済完了処理を行う.
	 * @param orderId　決済完了するOrderオブジェクトのId
	 * @param model　モデル
	 * @return　決済完了画面へ移動するメソッドへのリダイレクト
	 */
	@RequestMapping(value = "/toPayment")
	public String payment(@RequestParam String orderId, Model model) {
		long longOrderId = new Long(orderId);
		
		Order order = orderRepository.findById(longOrderId);
		
		if(order.getTotalPriceExcludeTax() == 0) {
			
		}
		
		order.setStatus(1);
		order.setOrderNumber(dateAndSequence());
		orderRepository.save(order);

		return "redirect:/user/confirmedPayment";
	}
	
	/**
	 * 決済完了画面へ移動を行うメソッド.
	 * @return　決済完了画面のjspのURL
	 */
	@RequestMapping(value = "/confirmedPayment")
	public String toPayment() {
		return "/user/payment";
	}

	/**
	 * Orderオブジェクト内のobjectNumberにセットする値を取得するメソッド.
	 * @return　objectNumberにセットする値
	 */
	public String dateAndSequence() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String strDate = formatter.format(localDate);
		return strDate;
	}
	
	/**
	 * Orderオブジェクト内のorderDateにセットする日付を取得するメソッド.
	 * @return　orderDateにセットする(Java.sql.)Date型日付
	 */
	public Date setSqlDateNow() {
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		return date;
	}
}
