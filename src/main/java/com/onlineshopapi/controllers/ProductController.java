package com.onlineshopapi.controllers;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.ProductEntity;
import com.onlineshopapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public Response create(@RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @GetMapping()
    public Response getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Integer id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Integer id, @RequestBody ProductEntity product) {
        return productService.updateById(id, product);
    }

    @DeleteMapping("/{id}")
    public Response update(@PathVariable Integer id) {
        return productService.deleteById(id);
    }
}
