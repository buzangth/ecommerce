package com.genetics.ecommerce.controller;


import com.genetics.ecommerce.model.Invoice;

import com.genetics.ecommerce.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping(path = "/newInvoice")
    public ResponseEntity<?> newInvoice(@Valid @RequestBody Invoice invoice){

        invoiceRepository.save(invoice);

        return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllInvoice")
    public Iterable<Invoice> allInvoice()  {
        return  invoiceRepository.findAll();
    }

    @GetMapping(value="/getInvoiceById/{id}")
    public Invoice getInvoiceById(@PathVariable("id") String id){
        return invoiceRepository.findByInvoiceId(id);
    }

    @DeleteMapping(value="/deleteByInvoiceId/{id}")
    public String deleteByInvoiceById(@PathVariable("id") String id) throws Exception{

        invoiceRepository.deleteById(id);

        return id;

    }

    @GetMapping(value = "/{page}/{perPage}/{sortOrder}/{sortField}")
    public Page<Invoice> search(@RequestParam(required = false, value = "keyword") String keyword,
                                @PathVariable("page") int page,
                                @PathVariable("perPage") int perPage,
                                @PathVariable("sortOrder") Sort.Direction sort,
                                @PathVariable("sortField") String sortField) throws Exception {
        page = page - 1;
        PageRequest pageRequest = PageRequest.of(page, perPage, sort, sortField);
        if (keyword == null) {
            keyword = "";
        }


        return invoiceRepository.search(keyword,pageRequest);
    }
}
