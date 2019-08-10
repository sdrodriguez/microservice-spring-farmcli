package sv.com.sadrosoft.farmcli.models.service;

import java.util.List;

import sv.com.sadrosoft.commons.models.entity.Product;

public interface IProductService {
	List<Product> findAll();
	Product findById(Long id);
	
	public Product save(Product produc);
	
	public void deleteById(Long id);
}
