package jp.co.rakus.ec201804a.user.payment9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.OrderItem;
import jp.co.rakus.ec201804a.common.domain.User;

@Repository
public class OrderRepository9 {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTER = (rs) -> {
		List<Order> orderList = new LinkedList<Order>();
		Order order = null;
		List<OrderItem> orderItemList = null;
		Item item = null;
		User user = null;
		long beforeOrderId = 0;

		while (rs.next()) {
			if (rs.getInt("order_id") != beforeOrderId) {
				order = new Order();
				order.setId(rs.getLong("order_id"));
				order.setOrderNumber(rs.getString("order_number"));
				order.setUserId(rs.getLong("order_user_id"));
				order.setStatus(rs.getInt("status"));
				orderItemList = new ArrayList<OrderItem>();
				order.setOrderItemList(orderItemList);
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDeliveryName(rs.getString("delivery_name"));
				order.setDeliveryEmail(rs.getString("delivery_email"));
				order.setDeliveryZipCode(rs.getString("delivery_zip_code"));
				order.setDeliveryAddress(rs.getString("delivery_address"));
				order.setDeliveryTel(rs.getString("delivery_tel"));

				user = new User();
				user.setId(rs.getLong("user_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("password"));
				user.setZipCode(rs.getString("zipcode"));
				user.setAddress(rs.getString("address"));
				user.setTelephone(rs.getString("telephone"));
				order.setUser(user);

				orderList.add(order);
			}

			if (rs.getInt("order_item_id") != 0) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getLong("order_item_id"));
				orderItem.setItemId(rs.getLong("item_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setOrderId(rs.getLong("order_id"));

				item = new Item();
				item.setId(rs.getLong("item_id"));
				item.setName(rs.getString("item_name"));
				item.setDescription(rs.getString("description"));
				item.setPrice(rs.getInt("price"));
				item.setImagePath(rs.getString("imagepath"));
				item.setDeleted(rs.getBoolean("deleted"));
				orderItem.setItem(item);

				orderItemList.add(orderItem);
			}

			beforeOrderId = order.getId();
		}

		return orderList;
	};

	public Order findById(long orderId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderId);

		String sql = "SELECT orders.id as order_id,	orders.order_number "
				+ ",	orders.user_id as order_user_id, orders.status ,orders.total_price "
				+ ",	orders.order_date , orders.delivery_name "
				+ ",	orders.delivery_email , orders.delivery_zip_code "
				+ ",	orders.delivery_address , orders.delivery_tel " + ",	order_items.id as order_item_id"
				+ ",	order_items.item_id, order_items.order_id,	order_items.quantity "
				+ ",	items.id as item_id, items.name as item_name, items.description "
				+ ",	items.price , items.imagepath , items.deleted "
				+ ",	users.id as user_id, users.name as user_name, users.email "
				+ ",	users.password , users.zipcode , users.address " + ",	users.telephone "
				+ "		FROM orders " + "		LEFT OUTER JOIN order_items "
				+ "		ON orders.id = order_items.order_id " + " 	LEFT OUTER JOIN items "
				+ "		ON order_items.item_id = items.id " + "		LEFT OUTER JOIN users "
				+ "		ON orders.user_id = users.id " + "		WHERE orders.id = :id " + "		ORDER BY orders.id ;";

		List<Order> orderList = namedParameterJdbcTemplate.query(sql, param, ORDER_RESULT_SET_EXTRACTER);
		Order order = orderList.get(0);

		return order;
	}

	public void save(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		String sql = "UPDATE orders SET order_number = :orderNumber , user_id = :userId "
				+", status = :status , total_price = :totalPrice , order_date = :orderDate "
				+", delivery_name = :deliveryName, delivery_email = :deliveryEmail "
				+", delivery_zip_code = :deliveryZipCode, delivery_address = :deliveryAddress "
				+", delivery_tel = :deliveryTel"
				+" WHERE id = :id ;";

		namedParameterJdbcTemplate.update(sql, param);
	}
}
