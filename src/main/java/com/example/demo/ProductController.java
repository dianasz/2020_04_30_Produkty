package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/lista") //http://localhost:8080/lista
    String getlistOfProducts(Model model){
        Set<Product> products = productRepository.findAll();
        model.addAttribute ("products", products);
        return "lista";
    }

    @PostMapping("addProduct")
    String addProduct(Product product){
        productRepository.addProduct (product);
        return "success";
    }

}
