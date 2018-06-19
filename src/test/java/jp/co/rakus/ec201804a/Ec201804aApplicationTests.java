package jp.co.rakus.ec201804a;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.rakus.ec201804a.common.domain.Item;
import jp.co.rakus.ec201804a.user.ViewAllAndsearch4.repository.ViewAllItemsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ec201804aApplicationTests {

	@Autowired
	private ViewAllItemsRepository repository;

	@Test
	public void contextLoads() {
		try {
		List<Item> itemList = repository.findAllNotDeleted();
				for (Item item : itemList) {
					
					System.out.println(item);
				}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
