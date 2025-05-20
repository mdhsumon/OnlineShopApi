package com.onlineshopapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/hi")
    public String index() {
        return "Hello World";
    }
}
