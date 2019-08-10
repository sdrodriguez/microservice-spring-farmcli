package sv.com.sadrosoft.farmcli.models.repository;

import org.springframework.data.repository.CrudRepository;

import sv.com.sadrosoft.commons.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
