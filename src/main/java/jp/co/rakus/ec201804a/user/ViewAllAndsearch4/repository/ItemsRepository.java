//package jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.stereotype.Repository;
//
//import jp.co.rakus.ec201804a.common.domain.Item;
//
///**
// * Itemsテーブルに接続し、商品一覧を表示するリポジトリ
// * @author shunta.nakamura
// *
// */
//@Repository
//public class ItemsRepository {
//	@Autowired
//	private NamedParameterJdbcTemplate template;
//
//	/** テーブル名 */
//	private static final String TABLE_NAME = "items";
//
//	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
//		Item item = new Item();
//		item.setId(rs.getLong("id"));
//		item.setName(rs.getString("name"));
//		item.setDescription(rs.getString("description"));
//		item.setPrice(rs.getInt("price"));
//		item.setImagePath(rs.getString("imagePath"));
//		item.setDeleted(rs.getBoolean("deleted"));
//
//		return item;
//	};
//
//	/**
//	 * 全件検索をするメソッド.
//	 * @return 全件の情報が入ったリストを返すか、もしくは空のリストを返す。
//	 */
//	public List<Item> findAllNotDeleted() {
//
//		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME
//				+ " where deleted = false order by price";
//
//		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);
//
//		return itemList;
//	}
//
//	
//	/**
//	 * 名前から検索をするメソッド.
//	 * @param name 名前
//	 * @return 検索の対象となったオブジェクトのリストを返すか、もしくは、空のリストを返す
//	 */
//	public List<Item> findByNameNotDeleted(String name) {
//
//		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME
//				+ " where deleted = false and name LIKE :name order by price";
//
//		String nameLike = "%" + name + "%";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("name", nameLike);
//		List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);
//		return itemList;
//
//	}
//
//	/**
//	 * 商品の詳細情報を検索するメソッド.
//	 * @param id
//	 * @return 対象となるオブジェクトかもしくは意図的にnullを返す
//	 */
//	public Item findDetailByIdNotDeleted(long id) {
//
//		String sql = "select id, name, description, price, imagePath, deleted from " + TABLE_NAME
//				+ " where id = :id and deleted = false";
//
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
//
//		try {
//			Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
//			return item;
//		} catch (EmptyResultDataAccessException ex) {
//			return null;
//		}
//	}
//
//}
