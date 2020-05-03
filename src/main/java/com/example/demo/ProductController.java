package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Set;

@Controller
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute ("product", new Product ());
        return "index";
    }

    @GetMapping("/lista") //http://localhost:8080/lista
    String getListOfProducts(Model model){
        Set<Product> products = productRepository.findAll();
        String sumText = calculateSumOfPrices (products);
        model.addAttribute ("products", products);
        model.addAttribute ("sum", sumText);
        return "lista";
    }

    @GetMapping("/tabela") //http://localhost:8080/tabela
    String getTableOfProducts(Model model){
        Set<Product> products = productRepository.findAll();
        String sumText = calculateSumOfPrices (products);
        model.addAttribute ("products", products);
        model.addAttribute ("sum", sumText);
        return "tabela";
    }

    private String calculateSumOfPrices(Set<Product> products) {
        Double sum = 0.0;
        for (Product product : products) {
            sum += product.getPrice ();
        }
        DecimalFormat df = new DecimalFormat ("####0.00");
        return df.format (sum);
    }

    @RequestMapping("/addProduct") //http://localhost:8080/addProduct
    public String addProduct(Product product){
        if (product.getName ().isEmpty () || product.getPrice () == null){
            return "err";
        } else {
            productRepository.addProduct (product);
            return "success";
        }
    }

}
