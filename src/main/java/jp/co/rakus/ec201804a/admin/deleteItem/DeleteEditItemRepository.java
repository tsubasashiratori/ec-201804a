package jp.co.rakus.ec201804a.admin.deleteItem;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;

/**
 * @author shunta.nakamura
 *
 */
@Repository
public class DeleteEditItemRepository {

	private static final String TABLE_NAME = "items";
	
	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getInt("price"));
		item.setImagePath(rs.getString("imagePath"));
		item.setDeleted(rs.getBoolean("deleted"));

		return item;
	};
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public Item load (Long id) {
		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME + " where id = :id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
		
		return item;
	}
	//デリート処理のみを行うメソッド
	public void save (Item item) {
		
		String sql = "update " + TABLE_NAME + " set deleted =:deleted where id = :id;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", item.getId()).addValue("deleted", item.getDeleted());
		
		template.update(sql, param);
	}
}
