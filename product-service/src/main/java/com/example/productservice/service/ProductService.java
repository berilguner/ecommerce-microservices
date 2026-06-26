package com.example.productservice.service;

import com.example.productservice.dto.ProductRequest;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;



    public Product create(ProductRequest request){


        Product product = new Product();


        product.setName(request.getName());

        product.setPrice(request.getPrice());

        product.setStock(request.getStock());


        return productRepository.save(product);

    }



    public List<Product> getAll(){

        return productRepository.findAll();

    }



    public Product getById(Long id){

        return productRepository.findById(id)
                .orElseThrow();

    }


}
