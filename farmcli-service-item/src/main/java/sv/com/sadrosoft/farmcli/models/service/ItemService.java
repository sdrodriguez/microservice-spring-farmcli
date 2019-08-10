package sv.com.sadrosoft.farmcli.models.service;

import java.util.List;

import sv.com.sadrosoft.farmcli.models.Item;
import sv.com.sadrosoft.commons.models.entity.Product;

public interface ItemService {
	
	List<Item> findAll();
	List<Item> findAll(String serviceName, String url);
	Item findById(Long id, Integer count);
	
	public Product save(Product produc);
	
	public Product update(Product produc, Long id);
	
	public void deleteById(Long id);

}
