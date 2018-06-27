package jp.co.rakus.ec201804a.user.registeruser;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping(value="/user")
public class RegisterUserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	@RequestMapping(value="/form")
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
	@RequestMapping(value="/register")
	public String registerUser(@Validated RegisterUserForm form, BindingResult result) {
		if(!form.getEmail().equals("")) {
			if(!form.getEmail().matches("^([\\w])+([\\w\\._-])*\\@([\\w])+([\\w\\._-])*\\.([a-zA-Z])+$")){
				result.rejectValue("email","","アドレスが不正です");
			}else if(userRepository.findByOneMailAddress(form.getEmail()) != null){
				result.rejectValue("email","","そのアドレスはすでに使われています");
			}
		}
		
		if(!form.getZipCode().equals("")) {
			if(!form.getZipCode().matches("^\\d{3}\\-?\\d{4}|\\d{7}$")){
			result.rejectValue("zipCode","","郵便番号が不正です");
			}
		}
		
		String telephone = ""+ form.getTelHead() +"-"+ form.getTelBody() +"-"+ form.getTelTeil();
		if(telephone.equals("--")) {
			result.rejectValue("telHead","","電話番号を入力してください");
		} else if(!telephone.matches("[0-9]+-[0-9]+-[0-9]+")) {
			result.rejectValue("telHead","","電話番号が不正です");
		}
		
		if(!form.getCheckPassword().equals("")) {
			if(!form.getPassword().equals(form.getCheckPassword())) {
				result.rejectValue("checkPassword","","確認用パスワードか一致していません");
			}
		}
		
		if (result.hasErrors()) {			
			return viewRegisterUser();
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setTelephone(telephone);
		String[] zipcodeArray = form.getZipCode().split("-");
		String sumZipCode = "";
		for (String string : zipcodeArray) {
			sumZipCode += string;
		}
		user.setZipCode(sumZipCode);		
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.registerUser(user);
		
		return "redirect:/user/loginForm";
	}
	

}
