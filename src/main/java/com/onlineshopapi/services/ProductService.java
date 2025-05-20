package com.onlineshopapi.services;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.ProductEntity;
import com.onlineshopapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Response create(ProductEntity product) {
        Response response = new Response();
        if(product.getName() != null) {
            productRepository.save(product);
            response.setCode("200");
            response.setMessage("Product created successfully");
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
        response.setData(productRepository.findById(id));
        return response;
    }

    public Response getAll() {
        Response response = new Response();
        List<ProductEntity> products = productRepository.findAll();
        response.setCode("200");
        response.setSuccess(true);
        response.setData(products);
        return response;
    }
}
