package sv.com.sadrosoft.farmcli.clients;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sv.com.sadrosoft.commons.models.entity.Product;

public interface ProductClientGeneric {
	
	@GetMapping(value = "/{url}")
	List<Product> listAll(@PathVariable String url);

}
