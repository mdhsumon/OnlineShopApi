package com.onlineshopapi.services;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.ProductEntity;
import com.onlineshopapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Response updateById(Integer id, ProductEntity requestBody) {
        Response response = new Response();
        Optional<ProductEntity> currentProduct = productRepository.findById(id);

        if(currentProduct.isEmpty()) {
            response.setCode("404");
            response.setMessage("Product not found");
            response.setSuccess(false);
            return response;
        }

        ProductEntity product = currentProduct.get();

        if(requestBody.getName() != null) {
            product.setName(requestBody.getName());
        }
        if(requestBody.getPrice() != null) {
            product.setPrice(requestBody.getPrice());
        }
        if(requestBody.getDescription() != null) {
            product.setDescription(requestBody.getDescription());
        }
        if(requestBody.getImage() != null) {
            product.setImage(requestBody.getImage());
        }
        if(requestBody.getCategoryId() != null) {
            product.setCategoryId(requestBody.getCategoryId());
        }
        if(requestBody.getStatus() != null) {
            product.setCategoryId(requestBody.getCategoryId());
        }
        productRepository.save(product);
        response.setCode("200");
        response.setSuccess(true);
        response.setMessage("Product updated successfully");
        return response;
    }

    public Response deleteById(Integer id) {
        Response response = new Response();
        Optional<ProductEntity> product = productRepository.findById(id);

        if(product.isEmpty()) {
            response.setCode("404");
            response.setMessage("Product not found");
            response.setSuccess(false);
            return response;
        }

        productRepository.deleteById(id);
        response.setCode("200");
        response.setSuccess(true);
        response.setMessage("Product deleted successfully");
        return response;
    }
}
