package com.genetics.ecommerce.repository;

import com.genetics.ecommerce.model.Customer;
import com.genetics.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

    @Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    Page<Product> search(String keyword, Pageable pageable);

    @Query("{\"name\": {$regex : ?0, $options: 'i'}}")
    List<Product> search(String keyword);

    @Query(value = "{'id': ?0}", fields = "{'person' : 0}")
    Product findProductById(String id);

}
