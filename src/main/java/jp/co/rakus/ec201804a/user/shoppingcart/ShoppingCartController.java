package jp.co.rakus.ec201804a.user.shoppingcart;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.OrderItem;
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
	 * 仮ページ表示(テスト用)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "a")
	public String index(Model model) {
		Map<Integer, Integer> map = new LinkedHashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		model.addAttribute("list", map);
		return "/user/case";
	}

	/**
	 * ショッピングカートに商品を追加するメソッド.
	 * 
	 * @param price
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/toInsertShoppingCart")
	public String insert(/* @RequestParam Integer price, */InsertShoppingCartForm form,
			/* @RequestParam Integer itemId, */Model model,HttpSession session) {
		
		/*String userId=session.getAttribute(id);*/
		
		String sessionId = session.getId();
		String a=sessionId.replaceAll("[^0-9]","");
		String b=a.substring(0, 15);
		//String b=String.format("%5d", a);
		//System.out.println(sessionId);
		//System.out.println(a);
		Long userId=Long.parseLong(b);
		// 2回目押したときの判定
		//int userId = Integer.parseInt(sessionId.replaceAll("[^1-9]",""));
		//int userId = ret;
		//int userId=1;
		int status=0;
		
		
		
		Order order1 = orderRepository.findByUserIdAndStatusForInsert(userId,status);
		
		if (order1 == null) {
			// orderが取れなかったr新しいidを発行
			Order order = new Order();

			LocalDate localdate = LocalDate.now();
			Date day = localDate2Date(localdate);
			int price = 100;
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

		OrderItem orderItem = new OrderItem();
		int itemId = 1;
		orderItem.setItemId((long) itemId);
		orderItem.setOrderId((long) 1);
		orderItem.setQuantity(form.getIntQuantiy());
		orderItemRepository.insert(orderItem);

		System.out.println("a");
		return viewShoppingCart(model);
	}

	public static Date localDate2Date(final LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * ショッピングカートを表示するメソッド.
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toViewShoppingCart")
	public String viewShoppingCart(Model model) {
		int status=0;
		Long userId=(long)1;
		List<Order> orderList=orderRepository.findByUserIdAndStatusForView(userId, status);
		model.addAttribute("orderList",orderList);
		
		return "/user/viewShoppingCart";
	}
	
	/**
	 * ショッピングカートの名前を削除するメソッド.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toDeleteShoppingCart")
	public String deleteShoppingCart(DeleteShoppingCartForm form,Model model) {
		Long orderItemId=(long)form.getIntOrderItemId();
		orderItemRepository.delete(orderItemId);
		return viewShoppingCart(model);
	}
}
