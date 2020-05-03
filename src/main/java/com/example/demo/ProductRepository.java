package com.example.demo;

import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductRepository {

    private Set<Product> products = new HashSet<>();

    public ProductRepository(){
        this.products.add(new Product("Czekolada milka truskawkowa", 3.99));
        this.products.add(new Product("Czekolada milka malinowa", 3.79));
        this.products.add(new Product("Czekolada wedel wiśniowa", 4.29));
        this.products.add(new Product("Czekolada wedel mleczna", 2.99));
        this.products.add(new Product("Czekolada lindt mleczna", 8.99));
    }

    public void addProduct(Product product){
        products.add (product);
    }

    public Set<Product> findAll() {
        return new HashSet<> (products);
    }

    String calculateSumOfPrices(Set<Product> products) {
        Double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice ();
        }
        DecimalFormat df = new DecimalFormat ("####0.00");
        return df.format (sum);
    }
}
