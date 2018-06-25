package jp.co.rakus.ec201804a.admin.registeradmin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.AdminUser;
import jp.co.rakus.ec201804a.common.repository.AdminUserRepository;

@Controller
@Transactional
@RequestMapping(value="/admin")
public class RegisterAdminController {
	@Autowired
	private AdminUserRepository adminUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * フォームの初期化
	 * @return 初期かされたフォーム
	 */
	@ModelAttribute
	public RegisterAdminForm setUpForm() {
		return new RegisterAdminForm();
	}
	
	/**
	 * 管理者登録画面表示.
	 * 
	 * @return 新規管理者登録画面
	 */
	@RequestMapping(value="/form")
	public String viewRegisterAdmin() {
		return "admin/registerAdmin";
	}
	
	/**
	 * 管理者登録処理.
	 * 
	 * @param form フォーム
	 * @param result リザルト
	 * @return 管理者ログイン画面
	 */
	@RequestMapping(value="/register")
	public String registerUser(@Validated RegisterAdminForm form, BindingResult result) {
		if(!form.getEmail().equals("")) {
			if(!form.getEmail().matches("^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$")){
				result.rejectValue("email","","アドレスが不正です");
			}else if(adminUserRepository.findByOneMailAddress(form.getEmail()) != null){
			result.rejectValue("email","","そのアドレスはすでに使われています");
			}
		}
		if(!form.getPassword().equals(form.getCheckPassword())) {
			result.rejectValue("checkPassword","","確認用パスワードか一致していません");
		}
		if (result.hasErrors()) {			
			return viewRegisterAdmin();
		}
		
		
		AdminUser adminUser = new AdminUser();
		BeanUtils.copyProperties(form, adminUser);
		System.out.println(adminUser);
		adminUser.setPassword(passwordEncoder.encode(adminUser.getPassword()));
		System.out.println(adminUser);
		adminUserRepository.registerAdmin(adminUser);
		return "redirect:/admin/";
	}

}
