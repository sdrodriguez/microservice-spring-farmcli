package sv.com.sadrosoft.farmcli.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import sv.com.sadrosoft.farmcli.models.Item;
import sv.com.sadrosoft.commons.models.entity.Product;
import sv.com.sadrosoft.farmcli.models.service.ItemService;

@RefreshScope
@RestController
public class ItemController {
	
	@Autowired
	private Environment env;

	@Autowired
	//  serviceRestTemplate, serviceFeing
	@Qualifier("serviceFeing")
	private ItemService itemService;
	
	@Value("${configur.text}")
	private String text;
	
	@HystrixCommand(fallbackMethod = "metodoAlterNativos")
	@GetMapping("/list")
	public List<Item> list(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlterNativos")
	@GetMapping("/listGen")
	public List<Item> listGeneric(){
		//Data for generic
		//return itemService.findAll("farmcli-service-product", "farmcli-service-product/api/list");
		return itemService.findAll("farmcli-service-product", "/list");
	}
	
	@HystrixCommand(fallbackMethod = "metodoAlterNativo")
	@GetMapping("/see/{id}/{count}")
	public Item see(@PathVariable Long id, @PathVariable Integer count){
		return itemService.findById(id, count);
	}
	
	public Item metodoAlterNativo(Long id, Integer count) {
		Item tm = new Item();
		Product pdr = new Product();
		pdr.setId(1L);
		pdr.setPrdName("asdasda");
		pdr.setPrdPrice(500.00);
		tm.setCount(count);
		
		tm.setProduct(pdr);
		return tm;
	}
	
	public List<Item> metodoAlterNativos(){
		return new ArrayList<>();
	}
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String puerto){
		
		Map<String, String> json = new HashMap<String, String>();
		json.put("text", text);
		json.put("puerto", puerto);
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equalsIgnoreCase("dev")) {
			json.put("autor.nombre", env.getProperty("configur.autor.nombre"));
			json.put("autor.email", env.getProperty("configur.autor.email"));
		}
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.deleteById(id);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product crear(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edited(@RequestBody Product product, @PathVariable Long id) {	
		return itemService.update(product, id);
	}
}
