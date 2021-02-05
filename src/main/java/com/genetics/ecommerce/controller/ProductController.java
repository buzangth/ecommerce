package com.genetics.ecommerce.controller;

import com.genetics.ecommerce.model.Customer;
import com.genetics.ecommerce.model.Product;
import com.genetics.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/creteProduct")
    public ResponseEntity<?> newProduct(@Valid @RequestBody Product product){

        productRepository.save(product);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllProduct")
    public Iterable<Product> allProduct()  {
        return  productRepository.findAll();
    }

    @GetMapping(value="/getProductById/{id}")
    public Product getProductById(@PathVariable("id") String id){
        return productRepository.findProductById(id);
    }

    @DeleteMapping(value="/deleteByProductId/{id}")
    public String deleteByProductById(@PathVariable("id") String id) throws Exception{

        productRepository.deleteById(id);

        return id;

    }

    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Product> search(@RequestParam(required = false, value = "keyword") String keyword,
                                 @PathVariable("page") int page,
                                 @PathVariable("perPage") int perPage,
                                 @PathVariable("sortOrder") Sort.Direction sort,
                                 @PathVariable("sortField") String sortField) throws Exception {
        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "";
        }


        return productRepository.search(keyword,pageRequest);
    }
}
