package jp.co.rakus.ec201804a.common.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<AdminUser> ADMIN_USER_ROW_MAPPER = (rs, i) -> {
		AdminUser adminUser = new AdminUser();
		adminUser.setId(rs.getLong("id"));
		adminUser.setName(rs.getString("name"));
		adminUser.setEmail(rs.getString("email"));
		adminUser.setPassword(rs.getString("password"));
		return adminUser;
	};
	
	/**
	 * 管理者をメールアドレスで検索する.
	 * 
	 * @param email
	 * @return adminUser 管理者アカウント
	 */
	public AdminUser findByOneMailAddress(String email){
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("email", email);
		String sql = "SELECT id, name, email, password "
				+ "FROM admin_users "
				+ "WHERE email=:email "
				+ ";";
		try {
			AdminUser adminUser = template.queryForObject(sql, param, ADMIN_USER_ROW_MAPPER);
			return adminUser;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 管理者情報を追加.
	 * @param adminUser 管理者
	 */
	public void registerAdmin(AdminUser adminUser) {
		String sql = "INSERT INTO admin_users(name,email,password) values(:name,:email,:password)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(adminUser);
		template.update(sql, param);
	}

}
