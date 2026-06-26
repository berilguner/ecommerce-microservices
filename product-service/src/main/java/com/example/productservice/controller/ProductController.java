package com.example.productservice.controller;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;



    @PostMapping
    public Product create(
            @RequestBody ProductRequest request
    ){

        return productService.create(request);

    }



    @GetMapping
    public List<Product> getAll(){

        return productService.getAll();

    }



    @GetMapping("/{id}")
    public Product getById(
            @PathVariable Long id
    ){

        return productService.getById(id);

    }


}
