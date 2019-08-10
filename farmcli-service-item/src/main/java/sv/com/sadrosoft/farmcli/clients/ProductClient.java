package sv.com.sadrosoft.farmcli.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import sv.com.sadrosoft.commons.models.entity.Product;

@FeignClient(name = "farmcli-service-product")
public interface ProductClient {
	
	//@GetMapping("/farmcli-service-product/api/list") siempre y cuando te contextpath
	@GetMapping("/list")
	List<Product> listAll();
	
	@GetMapping("/see/{id}")
	Product detalle(@PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);
	
	@PostMapping("/create")
	public Product crear(@RequestBody Product product);
	
	@PutMapping("/edit/{id}")
	public Product edited(@RequestBody Product product, @PathVariable Long id);

}
