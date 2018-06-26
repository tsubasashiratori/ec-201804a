package jp.co.rakus.ec201804a.common.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.OrderItem;

/**
 * order_itemsを操作するレポジトリー.
 * @author takumi.omoto
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	NamedParameterJdbcTemplate template;
	
	public final static RowMapper<OrderItem> rowMapper = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getLong("id"));
		orderItem.setItemId(rs.getLong("item_id"));
		orderItem.setOrderId(rs.getLong("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		return orderItem;
	};

	/**
	 * order_itemsに商品を追加するメソッド.
	 * @param orderItem
	 */
	public void insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items(item_id,order_id,quantity) VALUES(:item_id,:order_id,:quantity)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("item_id", orderItem.getItemId())
				.addValue("order_id", orderItem.getOrderId()).addValue("quantity", orderItem.getQuantity());
		template.update(sql, param);
	}
	/**
	 * ショッピングカートから商品を削除するメソッド.S
	 * @param orderItemId
	 */
	public void delete(Long orderItemId) {
		String sql ="DELETE FROM order_items WHERE id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(sql, param);
	}
	/**
	 * ショッピングカートに同じものが入れば更新するメソッド.
	 * @param quantity
	 */
	public void updateQuantity(Integer quantity,long orderId,long itemId) {
		String sql ="UPDATE order_items SET quantity=:quantity WHERE item_id=:item_id AND order_id=:order_id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("quantity", quantity).addValue("item_id", itemId).addValue("order_id", orderId);
		try {
		template.update(sql, param);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*public void updateQuantityByItemId(Integer quantity,long itemId) {
		String sql ="UPDATE order_items SET quantity=:quantity WHERE item_id=:item_id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("quantity", quantity).addValue("item_id", itemId);
		try {
		template.update(sql, param);
		System.out.println(template.query("SELECT quantity FROM order_items WHERE item_id=:item_id AND order_id=:order_id;", param, rowMapper));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	/**ゲストの注文番号をホストの注文番号に更新するメソッド.
	 * 
	 */
	public void updateOrderId(Long orderHostId,Long orderGestId) {
		String sql="UPDATE order_items SET order_id=:order_id WHERE order_id=:order_id2";
		SqlParameterSource param=new MapSqlParameterSource().addValue("order_id", orderHostId).addValue("order_id2", orderGestId);
		try {
			template.update(sql, param);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注文一覧から情報を取得するメソッド.
	 * @param orderId
	 * @param itemId
	 * @return
	 */
	public OrderItem findByOrderIdAndItemId(Long orderId,Long itemId) {
		String sql="SELECT id,item_id,order_id,quantity FROM order_items WHERE item_id=:item_id AND order_id=:order_id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("item_id", itemId).addValue("order_id", orderId);
		try {
		OrderItem orderItem=template.queryForObject(sql, param,rowMapper);
		return orderItem;
		}catch(Exception e) {
			return null;
		}
		
	}
	/**注文商品を検索するメソッド.
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> findByOrderId(Long orderId) {
		String sql="SELECT id,item_id,order_id,quantity FROM order_items WHERE order_id=:order_id ORDER BY item_id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("order_id", orderId);
		try {
		List<OrderItem> orderItemList=template.query(sql, param,rowMapper);
		return orderItemList;
		}catch(Exception e) {
			return null;
		}
	}
	/**
	 * ゲストの注文を統合後消すメソッド.
	 * @param orderItemId
	 */
	public void deleteByOrderItemId(Long orderItemId) {
		String sql="DELETE FROM order_items WHERE id=:id";
		SqlParameterSource param=new MapSqlParameterSource().addValue("id", orderItemId);
		template.update(sql, param);
	}
}
