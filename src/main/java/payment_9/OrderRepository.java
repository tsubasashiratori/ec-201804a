package payment_9;

import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Order;

@Repository
public class OrderRepository {
	
	public Order findById(long orderId) {
		Order order = new Order();
		order.setId(1l);
		
		return order;
	}
	
	public void save(Order order) {
		String sql = ""
	}
}
