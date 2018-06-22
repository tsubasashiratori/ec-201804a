//package jp.co.rakus.ec201804a.admin.viewAllAndSearchItem;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.stereotype.Repository;
//
//import jp.co.rakus.ec201804a.common.domain.Item;
//
///**
// * 管理者の商品一覧と部分一致検索を行うレポジトリクラス.
// * 
// * @author tatsuro.okazaki
// */
//@Repository
//public class AdminViewAllAndSearchItemRepository {
//
//	/** DBの商品情報に関するテーブル名 */
//	private static final String TABLE_NAME = "items";
//
//	@Autowired
//	private NamedParameterJdbcTemplate template;
//
//	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
//
//		Item item = new Item();
//		item.setId(rs.getLong("id"));
//		item.setName(rs.getString("name"));
//		item.setPrice(rs.getInt("price"));
//		item.setImagePath(rs.getString("imagepath"));
//		item.setDescription(rs.getString("description"));
//		item.setDeleted(rs.getBoolean("deleted"));
//		return item;
//	};
//
//	/**
//	 * 削除フラグの状態に関係なく商品を全検索する.
//	 * 
//	 * @return 検索結果の入ったリストを返す
//	 */
//	public List<Item> adminItemFindAll() {
//		String sql = "SELECT id, name, description, price, imagepath, deleted FROM " + TABLE_NAME+" ORDER BY id;";
//		
//		try {
//			List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);
//			return itemList;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 削除フラグの状態に関係なく商品名で部分一致検索をする.
//	 * 
//	 * @param name 商品名
//	 * @return 検索結果の入ったリストを返す
//	 */
//	public List<Item> adminItemFindByName(String name) {
//		String sql = "SELECT id, name, description, price, imagepath, deleted FROM " + TABLE_NAME + " WHERE name LIKE :name ORDER BY id;";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%"+name+"%");
//
//		try {
//			List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);
//			return itemList;
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//			
//
//	}
//}