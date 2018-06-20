package jp.co.rakus.ec201804a.admin.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.rakus.ec201804a.common.domain.AdminUser;

@Controller
@SessionAttributes(types = {AdminUser.class})
@RequestMapping(value = "/admin")
public class AdminUserLoginController {
//	@Autowired
//	private AdminUserLoginRepository repository;
	
	@ModelAttribute
	public AdminUserLoginForm setUpForm() {
		return new AdminUserLoginForm();
	}
	
	@RequestMapping(value = "/")
	public String index(AdminUserLoginForm form, BindingResult result
			,Model model, @RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		
		if(error != null) {
			System.err.println("adminUser: login failed");
			result.addError(new ObjectError("loginError", "メールアドレスまたはパスワードが違います"));
		}

		return "/admin/loginAdminister";
	}
	
//	@RequestMapping(value = "login")
//	public String login(AdminUserLoginForm form, HttpSession session) {
//		AdminUser adminUser = repository.findByOneMailAddress(form.getEmail());
//		
//		boolean error = false;
//		if(adminUser==null) {
//			System.out.println("管理者が存在しない");
//			return index();
//		}
//		System.out.println(adminUser);
//		if(!form.getPassword().equals(adminUser.getPassword())) {
//			error = true;
//		}
//		//errorがあればreturn
//		if(error) {
//			System.out.println("エラーがあります");
//			return index();
//		}
//		
//		session.setAttribute("adminUser", adminUser);
//		
//		return "administerMenu";
//	}
}
