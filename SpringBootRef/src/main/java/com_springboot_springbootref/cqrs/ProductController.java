package com_springboot_springbootref.cqrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    // Declare dependencies on command and query services
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    // Constructor-based dependency injection
    @Autowired
    public ProductController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;

    }

    // Endpoint for creating a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productCommandService.createProduct(product.getName(), product.getPrice());
    }

    // Endpoint for updating an existing product
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable("productId") Long id, @RequestBody Product product) {
        return productCommandService.updateProduct(id, product.getName(), product.getPrice());
    }

    // Endpoint for deleting a product by ID
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id) {
        productCommandService.deleteProduct(id);
    }

    // Endpoint for retrieving all products
    @GetMapping
    public List<Product> getProducts() {
        return productQueryService.getProducts();
    }

    // Endpoint for retrieving a product by ID
    @GetMapping("/{productId}")
    public Optional<Product> getProduct(@PathVariable("productId") Long id) {
        return productQueryService.getProduct(id);
    }
//    @GetMapping
//    public List<Product> getProducts() {
//        return productService.getProducts();
//    }
//    @GetMapping("/{productId}")
//    public Optional<Product> getProduct(@PathVariable("productId") Long id) {
//        return productService.getProduct(id);
//    }
}