package jp.co.rakus.ec201804a.admin.insertitem;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.common.repository.ItemRepository;

/**
 * 商品登録処理を行うコントローラー.
 * 
 * @author tsubasa.shiratori
 */
@Controller
@Transactional
@RequestMapping(value="/admin")
public class InsertItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * フォームの初期化
	 * @return 初期かされたフォーム
	 */
	@ModelAttribute
	public InsertItemForm setUpForm() {
		return new InsertItemForm();
	}
	
	/**
	 * 商品登録画面表示.
	 * 
	 * @return 商品登録画面
	 */
	@RequestMapping(value="/insertItem")
	public String viewInsertItem() {
		return "admin/insertItem";
	}
	
	/**
	 * 商品登録処理.
	 * 
	 * @param form フォーム
	 * @param result リザルト
	 * @return 商品一覧画面
	 */
	@RequestMapping(value="/insert")
	public String insertItem(@Validated InsertItemForm form, BindingResult result) {
		
		if(itemRepository.findByOneName(form.getName()) != null){
			System.out.println("エラー");
			result.rejectValue("name","","すでに同じ名前で商品が登録されています");
		}
		
		if(!form.getPrice().equals("")) {
			if(!form.getPrice().matches("\\d+")) {
				result.rejectValue("price","","1～1000000の数字で入力してください");
			}else if(Integer.parseInt(form.getPrice()) <= 0 || Integer.parseInt(form.getPrice()) >= 1000001) {
				result.rejectValue("price","","1～1000000の数字で入力してください");
			}
		}
		
		MultipartFile uploadFile = form.getImagePath();
		if (!StringUtils.hasLength(uploadFile.getOriginalFilename())) {
			result.rejectValue("imagePath","","ファイルを選択してください");
	  	}else if(uploadFile.isEmpty()) {
		    result.rejectValue("imagePath","","画像の中身が空です");
		}else if(!uploadFile.getOriginalFilename().endsWith(".jpg")) {
			result.rejectValue("imagePath", null, "jpg画像を選択してください");
		}
		if (uploadFile.getSize() >= 512000){
			result.rejectValue("imagePath","","画像が大きすぎます");
		}
		
		if (result.hasErrors()) {			
			return viewInsertItem();
		}
		
		ServletContext context = ((ServletRequestAttributes) 
		RequestContextHolder.getRequestAttributes()).getRequest().getServletContext();
		File imageFile = new File(context.getRealPath("/img/")+uploadFile.getOriginalFilename());
		try {
		uploadFile.transferTo(imageFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		int count=0;
		Item item = new Item();
		item.setPrice(Integer.parseInt(form.getPrice()));
		BeanUtils.copyProperties(form, item);
		item.setImagePath(uploadFile.getOriginalFilename());
		item.setCount(count);
		itemRepository.insertItem(item);
		return "redirect:/admin/adminFindAll";
	}

	
}
