package com.onlineshopapi.controllers;


import com.onlineshopapi.Response;
import com.onlineshopapi.entities.CategoryEntity;
import com.onlineshopapi.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public Response create(@RequestBody CategoryEntity category) {
        return categoryService.create(category);
    }

    @GetMapping()
    public Response getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Integer id, @RequestBody CategoryEntity category) {
        return categoryService.updateById(id, category);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        return categoryService.deleteById(id);
    }
}
