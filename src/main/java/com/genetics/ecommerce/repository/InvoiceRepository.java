package com.genetics.ecommerce.repository;

import com.genetics.ecommerce.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, String> {

    @Query("{\"id\": {$regex : ?0, $options: 'i'}}")
    Page<Invoice> search(String keyword, Pageable pageable);

    @Query("{\"id\": {$regex : ?0, $options: 'i'}}")
    List<Invoice> search(String keyword);

    @Query(value = "{'id': ?0}", fields = "{'person' : 0}")
    Invoice findByInvoiceId(String id);
}
