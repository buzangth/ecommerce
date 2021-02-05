package com.genetics.ecommerce.repository;

import com.genetics.ecommerce.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, String> {

@Query("{\"firstName\": {$regex : ?0, $options: 'i'}}")
Page<Customer> search(String keyword, Pageable pageable);

@Query("{\"name\": {$regex : ?0, $options: 'i'}}")
List<Customer> search(String keyword);

@Query(value = "{'id': ?0}", fields = "{'person' : 0}")
    Customer findCustomerById(String id);
}
