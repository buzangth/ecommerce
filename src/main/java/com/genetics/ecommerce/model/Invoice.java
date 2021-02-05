package com.genetics.ecommerce.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Invoice {

    @Id
    private String id;

    private Customer customer;

    private List<Product> products;

    private Double totalAmount ;



    public Double getTotalAmount() {
        return totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTotalAmount(Double totalAmount) {

        this.totalAmount = totalAmount;

    }



}
