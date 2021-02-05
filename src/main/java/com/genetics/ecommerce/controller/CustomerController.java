package com.genetics.ecommerce.controller;

import com.genetics.ecommerce.model.Customer;
import com.genetics.ecommerce.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
   // @Getter
    private CustomerRepository customerRepository;

    @PostMapping(path = "/newCustomer")
    public ResponseEntity<?> newCustomer(@Valid @RequestBody Customer customer){

        customerRepository.save(customer);

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllCustomer")
    public Iterable<Customer> allCustomer()  {
        return  customerRepository.findAll();
    }


    @GetMapping(value="/getCustomerById/{id}")
    public Customer getById(@PathVariable("id") String id){
        return customerRepository.findCustomerById(id);
    }

    @DeleteMapping(value="/deleteByCustomerId/{id}")
    public String deleteByCustomerById(@PathVariable("id") String id) throws Exception{

        customerRepository.deleteById(id);

        return id;

    }

    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Customer> search(@RequestParam(required = false, value = "keyword") String keyword,
                               @PathVariable("page") int page,
                               @PathVariable("perPage") int perPage,
                               @PathVariable("sortOrder") Sort.Direction sort,
                               @PathVariable("sortField") String sortField) throws Exception {
        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "";
        }


        return customerRepository.search(keyword,pageRequest);
    }




}
