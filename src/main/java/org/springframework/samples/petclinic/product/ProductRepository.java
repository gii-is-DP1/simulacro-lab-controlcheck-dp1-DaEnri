package org.springframework.samples.petclinic.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ProductRepository extends CrudRepository<Product, Integer>{
    
	List<Product> findAll();
    
	@Query("SELECT pt FROM ProductType pt")
	List<ProductType> findAllProductTypes();
    
    @Query("SELECT pt FROM ProductType pt WHERE pt.name = :name")
    ProductType getProductType(@Param("name") String name);
//    Optional<Product> findById(int id);

//    Product save(Product p);
    
    @Query("SELECT pt FROM Product pt WHERE pt.price < :maxCost")
    List<Product> getProductsCheaperThan(@Param("maxCost") Double maxCost);
    
    Product findByName(String name);
}
