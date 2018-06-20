package jp.co.rakus.ec201804a.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.User;

@Repository
public class UserRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void registerUser(User user) {
		String sql = "INSERT INTO users(name,email,password,zipcode,address,telephone) values(:name,:email,:password,:zipCode,:address,:telephone)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		jdbcTemplate.update(sql, param);
	}
}