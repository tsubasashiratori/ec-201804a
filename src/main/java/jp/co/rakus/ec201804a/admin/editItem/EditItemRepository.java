package jp.co.rakus.ec201804a.admin.editItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;

@Repository
public class EditItemRepository {
	
	private final static String TABLE_NAME = "items";
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getInt("price"));
		item.setImagePath(rs.getString("imagepath"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};
	
	public Item load(Long id) {
		String sql = "SELECT id, name, description, price, imagepath, deleted FROM "+TABLE_NAME+" WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		try {
			Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
			return item;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void save(Item item) {
		String sql = "UPDATE "+TABLE_NAME+" SET name=:name, description=:description, price=:price, imagepath=:imagePath, deleted=:deleted WHERE id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", item.getName())
															  .addValue("description", item.getDescription())
															  .addValue("price", item.getPrice())
															  .addValue("imagePath", item.getImagePath())
															  .addValue("deleted", item.getDeleted())
															  .addValue("id", item.getId());
	
		try {
			template.update(sql, param);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}