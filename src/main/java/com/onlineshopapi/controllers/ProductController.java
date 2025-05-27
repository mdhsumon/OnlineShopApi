package com.onlineshopapi.controllers;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.ProductEntity;
import com.onlineshopapi.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping({"", "/"})
    public Response create(@RequestBody ProductEntity product) {
        return productService.create(product);
    }

    @GetMapping({"", "/"})
    public Response getListByCategory(HttpServletRequest request) {
        String categoryId = request.getParameter("category");
        if (categoryId != null && !categoryId.isEmpty()) {
            Integer id = Integer.parseInt(categoryId);
            return productService.getListByCategory(id);
        }
        else {
            return productService.getList();
        }
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
    public Response delete(@PathVariable Integer id) {
        return productService.deleteById(id);
    }
}
