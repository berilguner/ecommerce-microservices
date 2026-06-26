package com.example.orderservice.service;


import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.ProductResponse;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class OrderService {



    private final OrderRepository orderRepository;

    private final ProductClient productClient;




    public Order create(OrderRequest request){



        ProductResponse product =
                productClient.getProduct(request.getProductId());



        System.out.println(
                "Alınan ürün: "
                        + product.getName()
        );



        Order order = new Order();


        order.setUserId(request.getUserId());

        order.setProductId(request.getProductId());

        order.setQuantity(request.getQuantity());


        return orderRepository.save(order);


    }


}
