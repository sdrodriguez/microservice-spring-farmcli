package sv.com.sadrosoft.farmcli.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import sv.com.sadrosoft.farmcli.clients.ProductClient;
import sv.com.sadrosoft.farmcli.clients.ProductClientGeneric;
import sv.com.sadrosoft.farmcli.models.Item;
import sv.com.sadrosoft.commons.models.entity.Product;

@Service("serviceFeing")
@Primary
public class ItemServiceFeing implements ItemService {

	@Autowired
	private ProductClient productClient;
	
	@Autowired 
	private ApplicationContext appContext;
	
	@Override
	public List<Item> findAll() {
		return productClient.listAll().stream().map(pr -> new Item(pr, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer count) {
		// TODO Auto-generated method stub
		return new Item(productClient.detalle(id), count);
	}

	@Override
	public List<Item> findAll(String serviceName, String url) {
		FeignClientBuilder feignClientBuilder = new FeignClientBuilder(appContext);
		ProductClientGeneric genericClient = feignClientBuilder.forType(ProductClientGeneric.class, serviceName).build();
		return genericClient.listAll(url).stream().map(pr -> new Item(pr, 1)).collect(Collectors.toList());
	}

	@Override
	public Product save(Product produc) {
		return productClient.crear(produc);
	}

	@Override
	public Product update(Product produc, Long id) {
		return productClient.edited(produc, id);
	}

	@Override
	public void deleteById(Long id) {
		productClient.delete(id);
	}
	

}
