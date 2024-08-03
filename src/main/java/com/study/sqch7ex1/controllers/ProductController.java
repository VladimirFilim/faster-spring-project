package com.study.sqch7ex1.controllers;

import com.study.sqch7ex1.models.Product;
import com.study.sqch7ex1.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;



@Controller
public class ProductController {

    Logger logger = Logger.getLogger(ProductController.class.getName());
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products")
    public String addProduct(
            @RequestParam String name,
            @RequestParam String price,
            Model model) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(Double.parseDouble(price));

        var products = productService.findAll();
        productService.addProduct(product);
        model.addAttribute("products", products);
        logger.info(products.toString());

        return "products.html";
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        var products = productService.findAll();
        model.addAttribute("products", products);

        return "products.html";
    }
}
