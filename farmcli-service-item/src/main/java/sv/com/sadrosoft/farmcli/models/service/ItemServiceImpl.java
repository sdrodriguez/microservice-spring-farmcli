package sv.com.sadrosoft.farmcli.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sv.com.sadrosoft.farmcli.models.Item;
import sv.com.sadrosoft.commons.models.entity.Product;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clientRest;

	@Override
	public List<Item> findAll() {
		// solamente cuando tiene contexto
		//List<Product> products = Arrays.asList(clientRest.getForObject("http://farmcli-service-product/farmcli-service-product/api/list", Product[].class));
		List<Product> products = Arrays.asList(clientRest.getForObject("http://farmcli-service-product/list", Product[].class));
		return products.stream().map(pr -> new Item(pr, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer count) {
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("id", id.toString());
		Product product = clientRest.getForObject("http://farmcli-service-product/see/{id}", Product.class, pathVar);
		return new Item(product, count);
	}

	@Override
	public List<Item> findAll(String serviceName, String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product save(Product produc) {
		HttpEntity<Product> body = new HttpEntity<>(produc);
		ResponseEntity<Product> response = clientRest.exchange("http://farmcli-service-product/create", HttpMethod.POST, body, Product.class);
		return response.getBody();
	}

	@Override
	public Product update(Product produc, Long id) {
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("id", id.toString());
		HttpEntity<Product> body = new HttpEntity<>(produc);
		ResponseEntity<Product> response = clientRest.exchange("http://farmcli-service-product/edit/{id}", 
				HttpMethod.PUT, body, Product.class, pathVar);
		return response.getBody();
	}

	@Override
	public void deleteById(Long id) {
		Map<String, String> pathVar = new HashMap<>();
		pathVar.put("id", id.toString());
		clientRest.delete("http://farmcli-service-product/delete/{id}", pathVar);
		
	}

}
