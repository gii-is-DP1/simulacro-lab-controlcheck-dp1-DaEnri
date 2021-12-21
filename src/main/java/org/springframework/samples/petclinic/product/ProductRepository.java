package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface ProductRepository extends CrudRepository<Product, Integer>{
    List<Product> findAll();
    @Query("SELECT pt FROM ProductType pt")
	List<ProductType> findAllProductTypes();
//    Optional<Product> findById(int id);
//    Product save(Product p);
    
    Product findByName(String name);
}
