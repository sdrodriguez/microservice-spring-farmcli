package sv.com.sadrosoft.farmcli.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.com.sadrosoft.commons.models.entity.Product;
import sv.com.sadrosoft.farmcli.models.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	private ProductRepository prdRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) prdRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return prdRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Product save(Product produc) {
		return prdRepository.save(produc);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		prdRepository.deleteById(id);
	}
	
}
