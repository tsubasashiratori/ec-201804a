package jp.co.rakus.ec201804a.admin.editItem;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

/**
 * 商品詳細の編集に関するコントローラクラス.
 * 
 * @author tatsuro.okazaki
 */
@Controller
@RequestMapping(value = "/admin")
public class EditItemController {
	
	@Autowired
	private ItemRepository itemRepository;

	@ModelAttribute
	private EditItemForm setUpForm() {
		return new EditItemForm();
	}

	/**
	 * 商品詳細を編集し管理者の商品一覧画面にリダイレクトする.
	 * 
	 * @param form
	 *            フォーム
	 * @param model
	 *            モデル
	 * @return DBを更新して商品一覧を表示
	 */
	@RequestMapping("/editItem")
	public String editItem(@Validated EditItemForm form, BindingResult result, RedirectAttributes flash, Model model) {
		
		System.out.println(form);
		
		//価格の入力チェック
		if ("".equals(form.getPrice())) {
			result.rejectValue("price", null, "価格を入力してください");
		} else if (!form.getPrice().matches("\\d+")) {
			result.rejectValue("price", null, "1～1000000の数字で入力してください");
		} else if (form.getIntPrice() <= 0 || 1000001 <= form.getIntPrice()) {
			result.rejectValue("price", null, "1～1000000の数字で入力してください");
		}
		
		//商品名の入力チェック
		Item beforeItem = itemRepository.load(form.getId());
		if(beforeItem.getName().equals(form.getName())) {
			
		}else if(itemRepository.findByOneName(form.getName())==null) {			
			
		}else {
			result.rejectValue("name", null, "すでに登録されている商品名です");	
		}
		
		MultipartFile uploadFile = form.getImagePath();
		if(512000 < uploadFile.getSize()) {
			result.rejectValue("imagePath", null, "サイズが大きすぎます");
		}
		if(uploadFile.isEmpty() && StringUtils.hasLength(uploadFile.getOriginalFilename())) {
			result.rejectValue("imagePath", null, "ファイルが空です");
		}
		if(!uploadFile.getOriginalFilename().endsWith(".jpg") && StringUtils.hasLength(uploadFile.getOriginalFilename())) {
			result.rejectValue("imagePath", null, "jpg画像を選択してください");
		}
		if (result.hasErrors()) {
			model.addAttribute("imagePath", itemRepository.load(form.getId()).getImagePath());
			return "/admin/edit";
		}
		
		
		//ServletContextインスタンスを取得
		ServletContext context = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
		
		//Fileクラスで保存先のURLを設定:
		File imageDir = new File(context.getRealPath("/img/") + uploadFile.getOriginalFilename());
		
		try {
			uploadFile.transferTo(imageDir);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setPrice(form.getIntPrice());
		
		//ファイルが選択されなかったら元の画像で登録
		if(!StringUtils.hasLength(uploadFile.getOriginalFilename())) {
			item.setImagePath(beforeItem.getImagePath());
			
		//ファイルが選択されたら新しい画像で登録
		}else {
			item.setImagePath(form.getImagePath().getOriginalFilename());
		}

		itemRepository.save(item);

		flash.addFlashAttribute("success", "編集完了しました");
		System.out.println("編集完了");

		return "redirect:/admin/adminFindAll";
	}
}