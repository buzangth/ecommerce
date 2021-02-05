package com.genetics.ecommerce.model;

public class Invoice extends Common{

    private Double totalAmount;

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {

        this.totalAmount = this.getTotalAmount() * this.getQuantity();

    }



}
