package payment_9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Order;

@Repository
public class OrderRepository {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Order findById(long orderId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderId);
		String sql = "SELECT orders.id ,orders.order_number "
				+", orders.user_id , orders.status , orders.total_price "
				+",	orders.order_date ,	orders.delivery_name "
				+",	orders.delivery_email ,	orders.delivery_zip_code "
				+",	orders.delivery_address , orders.delivery_tel "
				+",	order_items.quantity ,	items.name , items.price "
				+" FROM orders LEFT OUTER JOIN order_items "
				+" ON orders.id = order_items.order_id "
				+" LEFT OUTER JOIN items ON order_items.item_id = items.id "
				+" WHERE orders.id = :id ORDER BY id DESC ;";
		
		Order order = namedParameterJdbcTemplate.query(sql, param, ARTICLE_RESULT_SET_EXTRACTOR)
		
		return order;
	}
	
	public void save(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		
		String sql = "UPDATE orders SET order_number = '2018-06-19' , status = 0 WHERE id = 3;";
		
		namedParameterJdbcTemplate.update(sql, param);
	}
}
