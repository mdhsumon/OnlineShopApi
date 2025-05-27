package com.onlineshopapi.controllers;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.UserEntity;
import com.onlineshopapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping({"", "/"})
    public Response create(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @GetMapping({"", "/"})
    public Response getList() {
        return userService.getList();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Integer id, @RequestBody UserEntity user) {
        return userService.updateById(id, user);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        return userService.deleteById(id);
    }
}
