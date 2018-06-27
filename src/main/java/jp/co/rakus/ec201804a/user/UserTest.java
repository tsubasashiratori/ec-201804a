//package jp.co.rakus.ec201804a.user;
//
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import jp.co.rakus.ec201804a.common.login.LoginUser;
//
//@RestController
//@RequestMapping(value = "/user")
//public class UserTest {
//	@RequestMapping(value = "/test")
//	public String test() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username;
//		if (principal instanceof LoginUser) {
//				LoginUser loginUser = (LoginUser)principal;
//				username = loginUser.getUser().getName();
//			} else {
//			  username = principal.toString();
//		}
//		return username;
//	}
//}
