package jp.co.rakus.ec201804a.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.Item;

/**
 * 商品情報を操作するRepository.
 * 
 * @author tsubasa.shiratori
 */
@Repository
public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/**
	 * 商品情報を追加.
	 * @param 
	 */
	public void insertItem(Item item) {
		String sql = "INSERT INTO items(name,description,price,imagePath,deleted) values(:name,:description,:price,:imagePath,:deleted)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		jdbcTemplate.update(sql, param);
	}
	
}
