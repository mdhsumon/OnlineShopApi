package com.onlineshopapi.services;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.UserEntity;
import com.onlineshopapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Response create(UserEntity user) {
        Response response = new Response();
        if(user.getUsername() != null && user.getPassword() != null) {
            userRepository.save(user);
            response.setSuccess(true);
            response.setCode("200");
            response.setMessage("User created successfully");
        }
        else {
            response.setSuccess(false);
            response.setCode("400");
            response.setMessage("Username and password are required");
        }
        return response;
    }

    public Response getById(int id) {
        Response response = new Response();
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()) {
            response.setSuccess(true);
            response.setCode("200");
            response.setMessage("User found");
            response.setData(user.get());
        }
        else {
            response.setSuccess(false);
            response.setCode("404");
            response.setMessage("User not found");
        }
        return response;
    }

    public Response getList() {
        Response response = new Response();
        List<UserEntity> users = userRepository.findAll();
        response.setSuccess(true);
        response.setCode("200");
        response.setData(users);
        return response;
    }

    public Response updateById(Integer id, UserEntity requestBody) {
        Response response = new Response();
        Optional<UserEntity> currentUser = userRepository.findById(id);

        if(currentUser.isEmpty()) {
            response.setCode("404");
            response.setMessage("User not found");
            response.setSuccess(false);
            return response;
        }

        UserEntity user = currentUser.get();

        if(requestBody.getName() != null) {
            user.setName(requestBody.getName());
        }

        if(requestBody.getImage() != null) {
            user.setImage(requestBody.getImage());
        }
        userRepository.save(user);
        response.setSuccess(true);
        response.setCode("200");
        response.setMessage("User updated successfully");
        return response;
    }

    public Response deleteById(Integer id) {
        Response response = new Response();
        Optional<UserEntity> user = userRepository.findById(id);

        if(user.isEmpty()) {
            response.setSuccess(false);
            response.setCode("404");
            response.setMessage("User not found");
            return response;
        }

        userRepository.deleteById(id);
        response.setSuccess(true);
        response.setCode("200");
        response.setMessage("User deleted successfully");
        return response;
    }
}
