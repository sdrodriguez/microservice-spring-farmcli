package sv.com.sadrosoft.farmcli.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sv.com.sadrosoft.commons.models.entity.Product;
import sv.com.sadrosoft.farmcli.models.service.IProductService;

@RestController
public class ProductController {
	
	@Value("${server.port}")
	private String port;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/list")
	public List<Product> listAll(){
		return productService.findAll().stream().map(prd -> {
			prd.setPort(Integer.valueOf(port));
			return prd;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/see/{id}")
	public Product detalle(@PathVariable Long id) {
		
		Product product = productService.findById(id);
		product.setPort(Integer.valueOf(port));
		return productService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.deleteById(id);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product crear(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edited(@RequestBody Product product, @PathVariable Long id) {
		
		Product productDb = productService.findById(id);
		productDb.setPrdName(product.getPrdName());
		productDb.setPrdPrice(product.getPrdPrice());
		
		return productService.save(productDb);
	}
}
