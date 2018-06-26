package jp.co.rakus.ec201804a.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.User;

/**
 * 利用者情報を操作するRepository.
 * 
 * @author tsubasa.shiratori
 */
@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipCode(rs.getString("zipCode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};
	
	/**
	 * メールアドレスでユーザを検索.返すユーザは必ず1つ
	 * 
	 * @param mailAddress　メールアドレス
	 * @return user　検索されたユーザ
	 */
	public User findByOneMailAddress(String email) {
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("email", email);
		String sql = "SELECT id, name, email, password, zipCode, address, telephone "
				+ "FROM users "
				+ "WHERE email=:email "
				+ ";";
		try {
			User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
			return user;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 利用者情報を追加.
	 * @param user 利用者
	 */
	public void registerUser(User user) {
		String sql = "INSERT INTO users(name,email,password,zipcode,address,telephone) values(:name,:email,:password,:zipCode,:address,:telephone)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}
	
	/**
	 * 利用者情報を更新する.
	 * @param user　利用者情報
	 */
	public void update(User user) {
		System.out.println(user);
		String sql = "UPDATE users SET name=:name, zipcode=:zipCode, address=:address, telephone=:telephone WHERE id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}
}