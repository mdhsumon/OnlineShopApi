package com.onlineshopapi.services;

import com.onlineshopapi.Response;
import com.onlineshopapi.entities.OrderEntity;
import com.onlineshopapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Response create(OrderEntity order) {
        Response response = new Response();
        if(order.getCustomerPhone() != null && order.getCustomerAddress() != null) {
            orderRepository.save(order);
            response.setCode("200");
            response.setMessage("Order created successfully");
            response.setSuccess(true);
        }
        else {
            response.setCode("400");
            response.setMessage("Customer phone and address required");
            response.setSuccess(false);
        }
        return response;
    }

    public Response getById(Integer id) {
        Response response = new Response();
        response.setCode("200");
        response.setSuccess(true);
        response.setData(orderRepository.findById(id));
        return response;
    }

    public Response getList() {
        Response response = new Response();
        List<OrderEntity> orders = orderRepository.findAll();
        response.setCode("200");
        response.setSuccess(true);
        response.setData(orders);
        return response;
    }

    public Response updateById(Integer id, OrderEntity requestBody) {
        Response response = new Response();
        Optional<OrderEntity> currentOrder = orderRepository.findById(id);

        if(currentOrder.isEmpty()) {
            response.setCode("404");
            response.setMessage("Order not found");
            response.setSuccess(false);
            return response;
        }

        OrderEntity order = currentOrder.get();

        orderRepository.save(order);
        response.setCode("200");
        response.setSuccess(true);
        response.setMessage("Order updated successfully");
        return response;
    }

    public Response deleteById(Integer id) {
        Response response = new Response();
        Optional<OrderEntity> order = orderRepository.findById(id);

        if(order.isEmpty()) {
            response.setCode("404");
            response.setMessage("Order not found");
            response.setSuccess(false);
            return response;
        }

        orderRepository.deleteById(id);
        response.setCode("200");
        response.setSuccess(true);
        response.setMessage("Order deleted successfully");
        return response;
    }
}
