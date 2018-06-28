package jp.co.rakus.ec201804a.user.update;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.ec201804a.common.domain.User;
import jp.co.rakus.ec201804a.common.login.LoginUser;
import jp.co.rakus.ec201804a.common.repository.UserRepository;

/**
 * 利用者が登録情報を変更するコントローラ.
 * 
 * @author yuta.kitazawa
 */
@Controller
@RequestMapping(value = "/user")
@Transactional
public class UpdateUserController {
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * フォームの初期化.
	 * @return
	 */
	@ModelAttribute
	private UpdateUserForm setUpForm() {
		UpdateUserForm form = new UpdateUserForm();
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LoginUser loginUser = (LoginUser) principal;
		User user = loginUser.getUser();
		
		BeanUtils.copyProperties(user, form);
		String[] tel = user.getTelephone().split("-");
		form.setTelHead(tel[0]);
		form.setTelBody(tel[1]);
		form.setTelTeil(tel[2]);
		
		return form;
	}
	
	/**
	 * 登録情報変更画面を表示する.
	 * 
	 * @return 登録情報変更画面
	 */
	@RequestMapping(value = "/updateForm")
	public String viewUpdate() {
		return "/user/updateUser";
	}
	
	/**
	 * ユーザ登録情報を変更する.
	 * 
	 * @param form
	 * @param result
	 * @return
	 */
	//TODO:エラー条件式の編集
	@RequestMapping(value = "/update")
	public String updateUser(@Validated UpdateUserForm form, BindingResult result) {
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
		if (result.hasErrors()) {			
			return viewUpdate();
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
		
		userRepository.update(user);
		
		return "redirect:/user/logout";
	}
}
