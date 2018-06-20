package jp.co.rakus.ec201804a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Ec201804aApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ec201804aApplication.class, args);
	}
	
	/**
	 * 暗号化アルゴリズムを返す.
	 * 
	 * @return 暗号化する実装オブジェクト
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
