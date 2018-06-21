package jp.co.rakus.ec201804a.user.case6;

import org.springframework.beans.factory.annotation.Autowired;
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

}
