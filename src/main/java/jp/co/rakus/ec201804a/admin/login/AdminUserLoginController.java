package jp.co.rakus.ec201804a.admin.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.AdminUser;

@Controller
@RequestMapping(value = "/admin")
public class AdminUserLoginController {
	@Autowired
	private AdminUsenLoginRepository repository;
	
	@ModelAttribute
	public AdminUserLoginForm setUpForm() {
		return new AdminUserLoginForm();
	}
	
	@RequestMapping(value = "/")
	public String index() {
		return "/admin/loginAdminister";
	}
	
	@RequestMapping(value = "login")
	public String login(AdminUserLoginForm form, HttpSession session) {
		AdminUser adminUser = repository.findByOneMailAddress(form.getEmail());
		
		boolean error = false;
		if(adminUser==null) {
			System.out.println("管理者が存在しない");
			return index();
		}
		System.out.println(adminUser);
		if(!form.getPassword().equals(adminUser.getPassword())) {
			error = true;
		}
		//errorがあればreturn
		if(error) {
			System.out.println("エラーがあります");
			return index();
		}
		
		session.setAttribute("adminUser", adminUser);
		
		return "administerMenu";
	}
}
