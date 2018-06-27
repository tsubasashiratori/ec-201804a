package jp.co.rakus.ec201804a.common.logic;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.OrderItem;
import jp.co.rakus.ec201804a.common.login.LoginUser;
import jp.co.rakus.ec201804a.common.repository.OrderItemRepository;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;

@Component
public class CreateOrder {
	@Autowired
	private HttpSession session;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public  void createOrder() {
		// user情報を取得.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = (long) 0;
		if (principal instanceof LoginUser) {
			LoginUser loginUser = (LoginUser) principal;
			userId = loginUser.getUser().getId();
		}
		// ゲスト作成
		int status = 0;
		Order order1 = orderRepository.findByUserIdAndStatusForInsert(userId, status);

		if (order1 == null) {
			// orderが取れなかったら新しいidを発行
			Order order = new Order();

			LocalDate localdate = LocalDate.now();
			Date day = localDate2Date(localdate);
			int price = 0;
			Integer totalPrice = /* order.getTotalPrice() */+price;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String day1 = formatter.format(day);

			order.setOrderNumber(day1);
			order.setUserId(userId);
			order.setStatus(0);
			order.setTotalPrice(totalPrice);
			order.setOrderDate(day);
			orderRepository.insert(order);

		}

		// ゲスト情報受け取り
		String sessionId = (String) session.getAttribute("sessionId");
		if (sessionId != null) {
			Long gestId = Long.parseLong(sessionId);
			/* Long */ //userId = user.getId();
			/* int */ status = 0;
			// ログイン者の情報取得
			Order orderHost = orderRepository.findByUserIdAndStatusForInsert(userId, status);
			// ゲストの情報取得
			Order orderGest = orderRepository.findByUserIdAndStatusForInsert(gestId, status);
			if (orderHost != null && orderGest != null) {
				// ログイン者のオーダーID取得
				Long orderHostId = orderHost.getId();
				// ゲストのオーダーID取得
				Long orderGestId = orderGest.getId();
				// ①ゲストのオーダーIDをログイン者のオーダーIDを上書き
				orderItemRepository.updateOrderId(orderHostId, orderGestId);
				// ②上書きしたのでゲスト情報は消す
				orderRepository.delete(gestId);
				// ゲストの注文一覧
				List<OrderItem> orderItemList = orderItemRepository.findByOrderId(orderHostId);

				Long beforeItemId = (long) 0;
				Long orderId = null;
				Long currentItemId = null;
				Integer quantity = null;

				for (OrderItem orderItem : orderItemList) {
					currentItemId = orderItem.getItemId();

					if (currentItemId == beforeItemId) {
						quantity += orderItem.getQuantity();
						orderItemRepository.updateQuantity(quantity, orderId, currentItemId);
						orderId = orderItem.getId();
						orderItemRepository.deleteByOrderItemId(orderId);
						continue;
					}

					quantity = orderItem.getQuantity();
					orderId = orderItem.getOrderId();
					beforeItemId = currentItemId;
				}
			}
		}

	}

	public static Date localDate2Date(final LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}
