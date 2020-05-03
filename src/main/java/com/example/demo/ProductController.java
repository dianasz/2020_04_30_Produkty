package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
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
        Double sum = 0.0;
        for (Product product : products) {
            sum+=product.getPrice ();
        }
        DecimalFormat df = new DecimalFormat("####0.00");
        String sumText = df.format (sum);
        model.addAttribute ("products", products);
        model.addAttribute ("sum", sumText);
        return "lista";
    }

    @PostMapping("addProduct")
    String addProduct(Product product){
        productRepository.addProduct (product);
        return "success";
    }

}
