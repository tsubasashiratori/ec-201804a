package jp.co.rakus.ec201804a.admin.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.ec201804a.common.domain.AdminUser;

@Repository
public class AdminUserLoginRepository {
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
}
