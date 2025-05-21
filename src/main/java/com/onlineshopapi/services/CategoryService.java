package com.onlineshopapi.services;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.CategoryEntity;
import com.onlineshopapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Response create(CategoryEntity category) {
        Response response = new Response();
        if(category.getName() != null) {
            categoryRepository.save(category);
            response.setCode("200");
            response.setMessage("Category created successfully");
            response.setSuccess(true);
        }
        else {
            response.setCode("400");
            response.setMessage("Name required");
            response.setSuccess(false);
        }
        return response;
    }

    public Response getById(Integer id) {
        Response response = new Response();
        response.setCode("200");
        response.setSuccess(true);
        response.setData(categoryRepository.findById(id));
        return response;
    }

    public Response getAll() {
        Response response = new Response();
        List<CategoryEntity> categorys = categoryRepository.findAll();
        response.setCode("200");
        response.setSuccess(true);
        response.setData(categorys);
        return response;
    }

    public Response updateById(Integer id, CategoryEntity requestBody) {
        Response response = new Response();
        Optional<CategoryEntity> currentCategory = categoryRepository.findById(id);

        if(currentCategory.isEmpty()) {
            response.setCode("404");
            response.setMessage("Category not found");
            response.setSuccess(false);
            return response;
        }

        CategoryEntity category = currentCategory.get();

        if(requestBody.getName() != null) {
            category.setName(requestBody.getName());
        }
        if(requestBody.getDescription() != null) {
            category.setDescription(requestBody.getDescription());
        }

        categoryRepository.save(category);
        response.setCode("200");
        response.setSuccess(true);
        response.setMessage("Category updated successfully");
        return response;
    }

    public Response deleteById(Integer id) {
        Response response = new Response();
        Optional<CategoryEntity> category = categoryRepository.findById(id);

        if(category.isEmpty()) {
            response.setCode("404");
            response.setMessage("Category not found");
            response.setSuccess(false);
            return response;
        }

        categoryRepository.deleteById(id);
        response.setCode("200");
        response.setSuccess(true);
        response.setMessage("Category deleted successfully");
        return response;
    }
}
