package jp.co.rakus.ec201804a.admin.insertitem;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
//		MultipartFile uploadFile = form.getImagePath();
//		if (!StringUtils.hasLength(uploadFile.getOriginalFilename())) {
//            result.rejectValue("imagePath","","fileを選択してください");
//        }
//		if (uploadAllowableFileSize < uploadFile.getSize())
//		if (uploadFile.isEmpty()) {
//            result.rejectValue("imagePath","","画像の中身が空です");
//        }
		
		if(itemRepository.findByOneName(form.getName()) != null){
			System.out.println("エラー");
			result.rejectValue("name","","すでに同じ名前で商品が登録されています");
		}
		
//		if(!form.getPrice().equals("")) {
//			if(form.getPrice())
//		}
		
		if (result.hasErrors()) {			
			return viewInsertItem();
		}
		 int count=0;		
//		FileUtils.copyInputStreamToFile(uploadFile.getInputStream(),);
		Item item = new Item();
		BeanUtils.copyProperties(form, item);
		item.setCount(count);
		System.out.println(item);
		itemRepository.insertItem(item);
		return "redirect:/admin/adminFindAll";
	}

}
