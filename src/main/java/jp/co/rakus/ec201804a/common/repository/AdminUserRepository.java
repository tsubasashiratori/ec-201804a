package jp.co.rakus.ec201804a.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.AdminUser;

/**
 * 管理者情報を操作するRepository.
 * 
 * @author tsubasa.shiratori
 */
@Repository
public class AdminUserRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/**
	 * 管理者情報を追加.
	 * @param adminUser 管理者
	 */
	public void registerUser(AdminUser adminUser) {
		String sql = "INSERT INTO admin_users(name,email,password) values(:name,:email,:password)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUser);
		jdbcTemplate.update(sql, param);
	}

}
