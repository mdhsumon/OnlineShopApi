package com.onlineshopapi.controllers;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.OrderEntity;
import com.onlineshopapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    public Response create(@RequestBody OrderEntity order) {
        return orderService.create(order);
    }

    @GetMapping()
    public Response getList() {
        return orderService.getList();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Integer id) {
        return orderService.getById(id);
    }

    @PutMapping("/{id}")
    public Response update(@PathVariable Integer id, @RequestBody OrderEntity order) {
        return orderService.updateById(id, order);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        return orderService.deleteById(id);
    }
}
