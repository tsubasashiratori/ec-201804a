package jp.co.rakus.ec201804a.common.repository;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.domain.Order;
import jp.co.rakus.ec201804a.common.domain.OrderItem;
import jp.co.rakus.ec201804a.common.domain.User;

/**
 * ordersテーブルを操作するレポジトリー.
 * @author takumi.omoto
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public final static RowMapper<Order> rowMapper = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		return order;
	};

	/**
	 * ショッピングカートに商品を追加するメソッド.
	 * @param order
	 */
	public void insert(Order order) {

		String sql = "INSERT INTO orders(order_number,user_id,status,total_price,order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel) VALUES(:order_number,:user_id,:status,:total_price,:order_date,:delivery_name,:delivery_email,:delivery_zip_code,:delivery_address,:delivery_tel)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("order_number", order.getOrderNumber())
				.addValue("user_id", order.getUserId()).addValue("status", order.getStatus())
				.addValue("total_price", order.getTotalPrice()).addValue("order_date", order.getOrderDate())
				.addValue("delivery_name", order.getDeliveryName()).addValue("delivery_email", order.getDeliveryEmail())
				.addValue("delivery_zip_code", order.getDeliveryZipCode())
				.addValue("delivery_address", order.getDeliveryAddress())
				.addValue("delivery_tel", order.getDeliveryTel());
		namedParameterJdbcTemplate.update(sql, param);
	}
	/**
	 * ショッピングカートの情報を取得するメソッド.
	 * @param userId
	 * @param status
	 * @return
	 */
	public Order findByUserIdAndStatusForInsert(Long userId,int status) {
		String sql = "SELECT id,order_number,user_id,status,total_price,order_date,delivery_name,delivery_email,delivery_zip_code,delivery_address,delivery_tel FROM orders WHERE user_id=:user_id AND status=:status";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId).addValue("status", status);
		try{
		Order order = namedParameterJdbcTemplate.queryForObject(sql, param, rowMapper);
		return order;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Order> orderList = new LinkedList<Order>();
		Order order=null;
		OrderItem orderItem=null;
		User user=null;
		Item item=null;
		Long beforeOrderId=(long)0;
		List<OrderItem> orderItemList=null;
		while(rs.next()) {
			if(rs.getLong("order_id") != beforeOrderId) {
				order=new Order();
				order.setId(rs.getLong("order_id"));
				order.setOrderNumber(rs.getString("order_number"));
				order.setUserId(rs.getLong("user_id"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("total_price"));
				orderItemList=new ArrayList<>();
				order.setOrderItemList(orderItemList);
				order.setOrderDate(rs.getDate("order_date"));
				order.setDeliveryName(rs.getString("delivery_name"));
				order.setDeliveryEmail(rs.getString("delivery_email"));
				order.setDeliveryZipCode(rs.getString("delivery_zip_code"));
				order.setDeliveryAddress(rs.getString("delivery_address"));
				order.setDeliveryTel(rs.getString("delivery_tel"));
				
				user=new User();
				user.setId(rs.getLong("users_id"));
				user.setName(rs.getString("user_name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setZipCode(rs.getString("zipcode"));
				user.setAddress(rs.getString("address"));
				user.setTelephone(rs.getString("telephone"));
				order.setUser(user);
				orderList.add(order);
			}
			if(rs.getLong("order_items_id") != 0) {
				orderItem=new OrderItem();
				orderItem.setId(rs.getLong("order_items_id"));
				orderItem.setItemId(rs.getLong("item_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setOrderId(rs.getLong("order_id"));
				
				item=new Item();
				item.setId(rs.getLong("items_id"));
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
	
	/**
	 * ショッピングカートの情報を取得するメソッド.
	 * @param userId
	 * @param status
	 * @return
	 */
	public List<Order> findByUserIdAndStatusForView(Long userId,int status){
		String sql="SELECT"+  
				 " o.id AS order_id,o.order_number,o.user_id ,o.status"+ 
				 ",o.total_price,o.order_date,o.delivery_name ,o.delivery_email"+ 
				 ",o.delivery_zip_code,o.delivery_address,o.delivery_tel"+ 
				 ",oi.id AS order_items_id,oi.item_id,oi.order_id,oi.quantity"+ 
				 ",i.id AS items_id,i.name AS item_name,i.description,i.price"+ 
				 ",i.imagepath,i.deleted"+ 
				 ",u.id AS users_id,u.name AS user_name ,u.email"+ 
				 ",u.password,u.zipcode,u.address,u.telephone"+ 
				  " FROM"+ 
				  " orders o LEFT OUTER JOIN order_items oi ON o.id = oi.order_id"+ 
				  " LEFT OUTER JOIN items i ON oi.item_id = i.id LEFT OUTER JOIN"+ 
				  " users u ON o.user_id = u.id"+ 
				  " WHERE"+ 
				  " o.user_id =:usersId AND o.status=:status"+
				  " ORDER BY"+ 
				  " o.id DESC";
		try {
		SqlParameterSource param=new MapSqlParameterSource().addValue("usersId", userId).addValue("status", status);
		List<Order> orderList = namedParameterJdbcTemplate.query(sql, param,ORDER_RESULT_SET_EXTRACTOR);
		return orderList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 一件検索を行うメソッド.
	 * @param orderId　OrdersテーブルのID
	 * @return　検索された情報を持ったOrderオブジェクト
	 * @author kohei.taguchi
	 */
	public Order findByUserIdAndStatus(long userId, Integer status) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId).addValue("status", status);

		String sql = "SELECT orders.id as order_id,	orders.order_number "
				+ ",	orders.user_id as user_id, orders.status ,orders.total_price "
				+ ",	orders.order_date , orders.delivery_name "
				+ ",	orders.delivery_email , orders.delivery_zip_code "
				+ ",	orders.delivery_address , orders.delivery_tel "
				+ ",	order_items.id as order_items_id"
				+ ",	order_items.item_id, order_items.order_id,	order_items.quantity "
				+ ",	items.id as items_id, items.name as item_name, items.description "
				+ ",	items.price , items.imagepath , items.deleted "
				+ ",	users.id as users_id, users.name as user_name, users.email "
				+ ",	users.password , users.zipcode , users.address "
				+ ",	users.telephone "
				+ "		FROM orders "
				+ "		LEFT OUTER JOIN order_items "
				+ "		ON orders.id = order_items.order_id "
				+ " 	LEFT OUTER JOIN items "
				+ "		ON order_items.item_id = items.id "
				+ "		LEFT OUTER JOIN users "
				+ "		ON orders.user_id = users.id "
				+ "		WHERE user_id = :user_id AND status = :status "
				+ "		ORDER BY orders.id ;";

		List<Order> orderList = namedParameterJdbcTemplate.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		Order order = orderList.get(0);
		return order;
	}
	/**
	 * 一件検索を行うメソッド.
	 * @param orderId　OrdersテーブルのID
	 * @return　検索された情報を持ったOrderオブジェクト
	 * @author kohei.taguchi
	 */
	public Order findByOrderId(long orderId) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderId);
		
		String sql = "SELECT orders.id as order_id,	orders.order_number "
				+ ",	orders.user_id as user_id, orders.status ,orders.total_price "
				+ ",	orders.order_date , orders.delivery_name "
				+ ",	orders.delivery_email , orders.delivery_zip_code "
				+ ",	orders.delivery_address , orders.delivery_tel "
				+ ",	order_items.id as order_items_id"
				+ ",	order_items.item_id, order_items.order_id,	order_items.quantity "
				+ ",	items.id as items_id, items.name as item_name, items.description "
				+ ",	items.price , items.imagepath , items.deleted "
				+ ",	users.id as users_id, users.name as user_name, users.email "
				+ ",	users.password , users.zipcode , users.address "
				+ ",	users.telephone "
				+ "		FROM orders "
				+ "		LEFT OUTER JOIN order_items "
				+ "		ON orders.id = order_items.order_id "
				+ " 	LEFT OUTER JOIN items "
				+ "		ON order_items.item_id = items.id "
				+ "		LEFT OUTER JOIN users "
				+ "		ON orders.user_id = users.id "
				+ "		WHERE order_id = :id "
				+ "		ORDER BY orders.id ;";
		
		List<Order> orderList = namedParameterJdbcTemplate.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		Order order = orderList.get(0);
		
		return order;
	}
	
	/**
	 * 情報の登録を行うメソッド.
	 * @param order　登録したい情報を持ったOrderオブジェクト
	 * @author kohei.taguchi
	 */
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
	
	/**
	 * 全件検索を行うメソッド.
	 * @return　検索された情報を持ったリスト
	 * @author kohei.taguchi
	 */
	public List<Order> findAll(){
		String sql = "SELECT orders.id as order_id,	orders.order_number "
				+ ",	orders.user_id as user_id, orders.status ,orders.total_price "
				+ ",	orders.order_date , orders.delivery_name "
				+ ",	orders.delivery_email , orders.delivery_zip_code "
				+ ",	orders.delivery_address , orders.delivery_tel "
				+ ",	order_items.id as order_items_id"
				+ ",	order_items.item_id, order_items.order_id,	order_items.quantity "
				+ ",	items.id as items_id, items.name as item_name, items.description "
				+ ",	items.price , items.imagepath , items.deleted "
				+ ",	users.id as users_id, users.name as user_name, users.email "
				+ ",	users.password , users.zipcode , users.address "
				+ ",	users.telephone "
				+ "		FROM orders "
				+ "		LEFT OUTER JOIN order_items "
				+ "		ON orders.id = order_items.order_id "
				+ " 	LEFT OUTER JOIN items "
				+ "		ON order_items.item_id = items.id "
				+ "		LEFT OUTER JOIN users "
				+ "		ON orders.user_id = users.id "
				+ "		ORDER BY orders.order_number ;";
		
		List<Order> orderList = jdbcTemplate.query(sql, ORDER_RESULT_SET_EXTRACTOR);

		return orderList;
		}
	/**
	 * テーブルを削除するメソッド.
	 * @param userId
	 */
	public void delete(Long userId) {
		String sql="DELETE FROM orders WHERE user_id=:user_id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("user_id", userId);
		namedParameterJdbcTemplate.update(sql, param);
	}

}
