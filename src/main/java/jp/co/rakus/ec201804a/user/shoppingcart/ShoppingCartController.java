package jp.co.rakus.ec201804a.user.shoppingcart;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.OrderItem;
import jp.co.rakus.ec201804a.common.login.LoginUser;
import jp.co.rakus.ec201804a.common.repository.OrderItemRepository;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;

/**
 * ショッピングカートのレポジトリーを操作するコントローラー.
 * 
 * @author takumi.omoto
 *
 */
@Controller
@Transactional
@RequestMapping(value = "/user")
public class ShoppingCartController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@ModelAttribute
	public InsertShoppingCartForm setUpInsert() {
		return new InsertShoppingCartForm();
	}

	@ModelAttribute
	public DeleteShoppingCartForm setUpDelete() {
		return new DeleteShoppingCartForm();
	}

	/**
	 * ショッピングカートに商品を追加するメソッド.
	 * 
	 * @param price
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/toInsertShoppingCart")
	public String insert(/* @RequestParam Integer price, */InsertShoppingCartForm form, @RequestParam Long id,
			Model model, HttpSession session) {
		Long itemId = id;
		// セッションIDを10桁で取得
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId == null) {
			sessionId = session.getId();
			String a = sessionId.replaceAll("[^0-9]", "");
			String b = a.substring(0, 10);
			session.setAttribute("sessionId", b);
			sessionId = b;
		}
		// Long userId=Long.parseLong(b);

		// user情報を取得.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = (long) 0;
		if (principal instanceof LoginUser) {
			LoginUser loginUser = (LoginUser) principal;
			userId = loginUser.getUser().getId();
		}
		// useridが取得できなければsessionIDを代入.
		if (userId == 0) {
			userId = Long.parseLong(sessionId);
		}
		int status = 0;
		Order order1 = orderRepository.findByUserIdAndStatusForInsert(userId, status);

		if (order1 == null) {
			// orderが取れなかったら新しいidを発行
			Order order = new Order();

			LocalDate localdate = LocalDate.now();
			Date day = localDate2Date(localdate);
			int price = 0;
			Integer totalPrice = /* order.getTotalPrice()+ */price;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String day1 = formatter.format(day);

			order.setOrderNumber(day1);
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(totalPrice);
			order.setOrderDate(day);
			orderRepository.insert(order);
		}

		Order order2 = orderRepository.findByUserIdAndStatusForInsert(userId, status);
		Long orderId = order2.getId();
		OrderItem orderItem2 = orderItemRepository.findByOrderIdAndItemId(orderId, itemId);
		if (orderItem2 == null) {
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId((long) itemId);
			orderItem.setOrderId(order2.getId());
			orderItem.setQuantity(form.getIntQuantiy());
			orderItemRepository.insert(orderItem);
		} else {
			int quantity = orderItem2.getQuantity() + form.getIntQuantiy();
			Long orderId2 = orderItem2.getOrderId();
			Long itemId2 = orderItem2.getItemId();
			orderItemRepository.updateQuantity(quantity, orderId2, itemId2);
		}
		return "redirect:/user/toViewShoppingCart";
	}

	public static Date localDate2Date(final LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * ショッピングカートを表示するメソッド.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toViewShoppingCart")

	public String viewShoppingCart(Model model, HttpSession session) {
		// セッションIDが取れなければ新しいセッションIDを作成する
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId == null) {
			sessionId = session.getId();
			String a = sessionId.replaceAll("[^0-9]", "");
			String b = a.substring(0, 10);
			session.setAttribute("sessionId", b);
			sessionId = b;
		}
		// user情報を取得.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = (long) 0;
		if (principal instanceof LoginUser) {
			LoginUser loginUser = (LoginUser) principal;
			userId = loginUser.getUser().getId();
		}
		// useridが取得できなければsessionIDを代入.
		if (userId == 0) {
			userId = Long.parseLong(sessionId);
		}
		int status = 0;
		List<Order> orderList = orderRepository.findByUserIdAndStatusForView(userId, status);
		// オーダーリストの中身がなければダミーオブジェクトを作成する→ショッピングカートに中身がないときの対策
		if (orderList.size() == 0) {
			Order damyOrder = new Order();
			damyOrder.setId((long) -1);
			model.addAttribute("damyOrder", damyOrder);
		}
		model.addAttribute("orderList", orderList);
		return "/user/viewShoppingCart";
	}

	/**
	 * ショッピングカートの名前を削除するメソッド.
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toDeleteShoppingCart")
	public String deleteShoppingCart(DeleteShoppingCartForm form, Model model, HttpSession session) {
		Long orderItemId = (long) form.getIntOrderItemId();
		orderItemRepository.delete(orderItemId);
		return viewShoppingCart(model, session);
	}
}
