package jp.co.rakus.ec201804a.common.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.OrderItem;
import jp.co.rakus.ec201804a.common.domain.User;
import jp.co.rakus.ec201804a.common.repository.OrderItemRepository;
import jp.co.rakus.ec201804a.common.repository.OrderRepository;
import jp.co.rakus.ec201804a.common.repository.UserRepository;

/**
 * ログイン後の利用者情報に権限を付与するサービスクラス.
 * 
 * @author yuta.kitazawa
 */
@Service("UserDetailsServiceImp1")
public class UserDetailsServiceImp1 implements UserDetailsService{
	/**
	 * 利用者をfindByOneMailAddresするリポジトリ
	 */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByOneMailAddress(email);
		System.out.println(user);
		if(user == null) {
			throw new UsernameNotFoundException("そのメールアドレスは登録されていません");
		}
		
		//ゲスト情報受け取り
		String sessionId=(String) session.getAttribute("sessionId");
		if(sessionId!=null) {
		Long gestId=Long.parseLong(sessionId);
		Long userId=user.getId();
		int status=0;
		//ログイン者の情報取得
		Order orderHost=orderRepository.findByUserIdAndStatusForInsert(userId, status);
		//ゲストの情報取得
		Order orderGest=orderRepository.findByUserIdAndStatusForInsert(gestId, status);
		//ログイン者のオーダーID取得
		Long orderHostId=orderHost.getId();
		//ゲストのオーダーID取得
		Long orderGestId=orderGest.getId();
		//①ゲストのオーダーIDをログイン者のオーダーIDを上書き
		orderItemRepository.updateOrderId(orderHostId,orderGestId);
		//②上書きしたのでゲスト情報は消す
		orderRepository.delete(gestId);
		//ゲストの注文一覧
		List<OrderItem> orderItemList=orderItemRepository.findByOrderId(orderHostId);
		
		
		Long beforeItemId=(long)0;
		Long orderId=null;
		Long currentItemId=null;
		Integer quantity=null;
		
		for(OrderItem orderItem:orderItemList) {
			currentItemId=orderItem.getItemId();
			
			if(currentItemId == beforeItemId) {
				quantity+=orderItem.getQuantity();
				System.out.println(quantity);
				orderItemRepository.updateQuantity(quantity,orderId,currentItemId);
				orderId=orderItem.getId();
				orderItemRepository.deleteByOrderItemId(orderId);
				continue;
			}
			
			quantity = orderItem.getQuantity();
			orderId=orderItem.getOrderId();
			beforeItemId=currentItemId;
		}
		}
		
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		//ROLE_は必ず書く.
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new LoginUser(user, authorityList);
	}
}
