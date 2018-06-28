package jp.co.rakus.ec201804a.common.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	private NamedParameterJdbcTemplate template;
	
	/** DBの商品情報に関するテーブル名 */
	private static final String TABLE_NAME = "items";
	
	private static final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getLong("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPrice(rs.getInt("price"));
		item.setImagePath(rs.getString("imagePath"));
		item.setDeleted(rs.getBoolean("deleted"));
		item.setCount(rs.getInt("count"));
		return item;
	};
	
	/**
	 * 商品のid検索をする.
	 * 
	 * @param id id
	 * @return 検索された商品
	 */
	public Item load(Long id) {
		String sql = "SELECT id, name, description, price, imagepath, deleted, count FROM "+TABLE_NAME+" WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		try {
			Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
			return item;
			
		}catch(Exception e){
			e.printStackTrace();
		}		
		return null;
	}
	/**観覧数TOP５を検索するメソッド.
	 * @return
	 */
	public List<Item> findHighCountItem(){
		String sql = "select id, name, description, price, imagePath, deleted,count from " + TABLE_NAME
				+ " where deleted = false order by count desc limit 5";
		List<Item> itemList=template.query(sql, ITEM_ROWMAPPER);
		return itemList;
		
	}
	
	/**指定された所から10件検索
	 * @param thisfind
	 * @param limit
	 * @return
	 */
	public List<Item> findAllNotDeletedByPageNum(int thisfind,int limit) {

		String sql = "select id, name, description, price, imagePath, deleted,count from " + TABLE_NAME
				+ " where deleted = false order by id offset :thisfind limit :limit";
		SqlParameterSource param=new MapSqlParameterSource().addValue("thisfind", thisfind).addValue("limit", limit);
		List<Item> itemList = template.query(sql,param,ITEM_ROWMAPPER);
		return itemList;
	}

	/**商品が何件あるか検索するメソッド.
	 * @return 商品数
	 */
	public Integer pageCount() {
		String sql="select COUNT(*) from items where deleted=false";
		SqlParameterSource param=new MapSqlParameterSource();
		return template.queryForObject(sql, param, Integer.class);
	}
	/**
	 * 全件検索をするメソッド.
	 * @return 全件の情報が入ったリストを返すか、もしくは空のリストを返す。
	 */
	public List<Item> findAllNotDeleted() {

		String sql = "select id, name, description, price, imagePath, deleted,count from " + TABLE_NAME
				+ " where deleted = false order by price";

		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);

		return itemList;
	}

	/**検索結果で何件あるか検索する
	 * @param name
	 * @return
	 */

	public Integer pageCountSearch(String name) {

		String sql = "select COUNT(*) from " + TABLE_NAME
				+ " where deleted = false and name ILIKE :name";

		String nameLike = "%" + name + "%";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", nameLike);
		return template.queryForObject(sql, param, Integer.class);
	}
	
	/**
	 * 名前から検索をするメソッド(指定された場所から検索).
	 * @param name 名前
	 * @return 検索の対象となったオブジェクトのリストを返すか、もしくは、空のリストを返す
	 */
	public List<Item> findByNameNotDeleted(Integer thisfind,Integer limit,String name) {
		String sql = "select id, name, description, price, imagePath, deleted,count from " + TABLE_NAME
				+ " where deleted = false and name ILIKE :name order by id offset :thisfind limit :limit";

		String nameLike = "%" + name + "%";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", nameLike).addValue("thisfind", thisfind).addValue("limit", limit);
		List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);
		return itemList;
	}

	
	
	/**
	 * 商品の詳細情報を検索するメソッド.
	 * @param id
	 * @return 対象となるオブジェクトかもしくは意図的にnullを返す
	 */
	public Item findDetailByIdNotDeleted(long id) {

		String sql = "select id, name, description, price, imagePath, deleted,count from " + TABLE_NAME
				+ " where id = :id and deleted = false";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		try {
			Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
			return item;
		} catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	
	/**
	 * 削除フラグの状態に関係なく商品を全検索する.
	 * 
	 * @return 検索結果の入ったリストを返す
	 */
	public List<Item> adminItemFindAll() {
		String sql = "SELECT id, name, description, price, imagepath, deleted,count FROM " + TABLE_NAME+" ORDER BY id;";
		
		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);
		return itemList;
	}
	
	/**
	 * 削除フラグの状態に関係なく商品名で部分一致検索をする.
	 * 
	 * @param name 商品名
	 * @return 検索結果の入ったリストを返す
	 */
	public List<Item> adminItemFindByName(String name) {
		String sql = "SELECT id, name, description, price, imagepath, deleted,count FROM " + TABLE_NAME + " WHERE name ILIKE :name ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%"+name+"%");
		List<Item> itemList = template.query(sql, param, ITEM_ROWMAPPER);
		return itemList;
	}
	
	public void save(Item item) {
		String sql = "UPDATE "+TABLE_NAME+" SET name=:name, description=:description, price=:price, imagepath=:imagePath, deleted=:deleted WHERE id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		try {
			template.update(sql, param);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	/**
//	 * 削除情報を更新する.
//	 * 
//	 * @param item 削除情報を変更した商品情報
//	 */
//	public void saveDelete(Item item) {
//		String sql = "update " + TABLE_NAME + " set deleted =:deleted where id = :id;";
//		
//		SqlParameterSource param = new MapSqlParameterSource().addValue("id", item.getId()).addValue("deleted", item.getDeleted());
//		
//		template.update(sql, param);
//	}
	
	public void updateCount(Item item) {
		String sql = "update " + TABLE_NAME + " set count =:count where id = :id;";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", item.getId()).addValue("count", item.getCount());
		
		template.update(sql, param);
	}
	
	/**
	 * 商品情報を追加.
	 * @param 
	 */
	/**
	 * 商品情報を追加する.
	 * 
	 * @param item 商品情報
	 */
	public void insertItem(Item item) {
		String sql = "INSERT INTO items(name,description,price,imagePath,deleted,count) values(:name,:description,:price,:imagePath,:deleted,:count)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
	
		template.update(sql, param);
	}
	
	
	public Item findByOneName(String name) {
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("name",name);
		String sql = "SELECT id, name, description, price, imagePath, deleted, count "
				+ "FROM items "
				+ "WHERE name=:name "
				+ ";";
		try {
			Item item = template.queryForObject(sql, param, ITEM_ROWMAPPER);
			return item;
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return null;
	}
}
