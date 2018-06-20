package jp.co.rakus.ec201804a.user.registeruser;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.User;
import jp.co.rakus.ec201804a.common.repository.UserRepository;

/**
 * 利用者登録処理を行うコントローラー.
 * 
 * @author tsubasa.shiratori
 */
@Controller
@Transactional
@RequestMapping(value="/regist")
public class RegisterUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * フォームの初期化
	 * @return 初期かされたフォーム
	 */
	@ModelAttribute
	public RegisterUserForm setUpForm() {
		return new RegisterUserForm();
	}
	
	/**
	 * 利用者登録画面表示.
	 * 
	 * @return 新規利用者登録画面
	 */
	@RequestMapping(value="/")
	public String viewRegisterUser() {
		return "user/registerUser";
	}
	
	/**
	 * 利用者登録処理.
	 * 
	 * @param form フォーム
	 * @param result リザルト
	 * @return ログイン画面
	 */
	@RequestMapping(value="/registerUser")
	public String registerUser(@Validated RegisterUserForm form, BindingResult result) {
		if(!form.getPassword().equals(form.getCheckPassword())) {
			result.rejectValue("checkPassword","","確認用パスワードか一致していません");
		}
		if (result.hasErrors()) {			
			return viewRegisterUser();
		}
		User user = new User();
		BeanUtils.copyProperties(form, user);
//		user.setName(form.getName());
//		user.setEmail(form.getEmail());
//		user.setPassword(form.getPassword());
//		user.setZipCode(form.getZipCode());
//		user.setAddress(form.getAddress());
//		user.setTelephone(form.getTelephone());
		System.out.println(user);
		userRepository.registerUser(user);
		return "redirect:/user/";
	}

}
