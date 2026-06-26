package com.example.orderservice.client;

import com.example.orderservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(
        name="product-service",
        url="localhost:8082"
)
public interface ProductClient {



    @GetMapping("/products/{id}")
    ProductResponse getProduct(
            @PathVariable Long id
    );


}
