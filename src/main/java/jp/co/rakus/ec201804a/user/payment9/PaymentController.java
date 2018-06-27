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
	public String viewPaymentDetail(Model model) {
//		ユーザーログインチェックここから================================================================
		User user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof LoginUser) {
				LoginUser loginUser = (LoginUser)principal;
				user = loginUser.getUser();
			} else {
			 System.out.println(principal.toString());
		}
		
		if (user == null) {
			return "redirect:/user/loginForm";
		}
//		ユーザーログインチェックここまで================================================================
		
		
		
		Order order = orderRepository.findByUserIdAndStatus(user.getId(), 0);
//		リクエストパラメータで受け取ったordersテーブルのIDをもとに一件検索を行う、検索した情報をOrderオブジェクトに入れる

		if (order == null || order.getOrderItemList().isEmpty() == true) {
			System.out.println("empty-------------------------------------------------------");
			model.addAttribute("cartNullChecker", true);
			model.addAttribute("nullError", "注文がありません");
			return "/user/makePayment";
		}
//		検索した情報が空であるか、ショッピングカートに商品が一つも入ってない場合はエラーメッセージをスコープに入れてJSPを呼び出す
		
		

//		検索した情報が空でなく、ショッピングカートにも商品が入っていればOrderテーブルの注文情報を更新する======================
		
		order.setUserId(user.getId());
//		ログインチェックした際のユーザー情報からIDを取得、保存
		order.setStatus(0);
//		注文ステータスを未購入に設定
		order.setTotalPrice(order.getTotalPriceIncludeTaxAndPostage());
//		合計金額を取得して保存
		order.setOrderDate(setSqlDateNow());
//		注文日を保存
		order.setDeliveryName(user.getName());
		order.setDeliveryEmail(user.getEmail());
		order.setDeliveryZipCode(user.getZipCode());
		order.setDeliveryAddress(user.getAddress());
		order.setDeliveryTel(user.getTelephone());
//		届け先情報をログインチェックしたユーザー情報から取得、保存
//		現状ログインしているユーザー情報そのままだが、届け先を変えられるようにするときは要対応
		orderRepository.save(order);
//		取得した情報をOrdersテーブルに保存する
		
//		注文情報の更新ここまで======================================================================================
		
		
		model.addAttribute("cartNullChecker", false);
		model.addAttribute("order", order);
//		更新した情報をリクエストスコープに入れる
		
		System.out.println("beforeviewpayment===================================================================");
		return "/user/makePayment";
	}

	/**
	 * 決済完了処理を行う.
	 * @param orderId　決済完了するOrderオブジェクトのId
	 * @param model　モデル
	 * @return　決済完了画面へ移動するメソッドへのリダイレクト
	 */
	@RequestMapping(value = "/toPayment")
	public String payment(@RequestParam long userId, Model model) {
		
		Order order = orderRepository.findByUserIdAndStatus(userId, 0);
//		リクエストパラメータで受け取ったOrdersテーブルIDをもとに、注文情報が残っているか再確認する
		
		if (order == null || order.getOrderItemList().isEmpty() == true) {
			System.out.println("empty-------------------------------------------------------");
			model.addAttribute("cartNullChecker", true);
			model.addAttribute("nullError", "注文がありません");
			return "/user/makePayment";
		}
//		検索した情報が空であるか、ショッピングカートに商品が一つも入ってない場合はエラーメッセージをスコープに入れて呼び出し元画面に戻る
		
//		検索した情報が空でなく、ショッピングカートにも商品が入っていればOrderテーブルの注文情報を更新する======================
		
		order.setStatus(1);
//		注文ステータスを未入金に設定
		order.setOrderNumber(dateAndSequence());
//		注文番号を設定(注文日付＋年内の連番)
		orderRepository.save(order);
//		更新した情報をOrdersテーブルに保存
//		注文情報の更新ここまで======================================================================================
		
		System.out.println("beforeconfirmed===================================================================");
		return "redirect:/user/confirmedPayment";
//		決済完了画面を表示するメソッドへのリダイレクト
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
		
//		String sql = "SELECT order_date FROM orders ORDER BY order_date DESC LIMIT 1;";
		
		
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
