package sv.com.sadrosoft.farmcli.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sv.com.sadrosoft.commons.models.entity.Product;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	
	private Product product;
	private Integer count;
	
	public Double getTotal() {
		return this.count.doubleValue()*product.getPrdPrice();
		
	}

}
