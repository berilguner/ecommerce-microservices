package com.example.productservice.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RoleFilter implements Filter {

    @Override
    public void doFilter(
            jakarta.servlet.ServletRequest request,
            jakarta.servlet.ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String role = req.getHeader("X-user-role");

        String path = req.getRequestURI();

        // SADECE ADMIN ürün ekleyebilir
        if(path.equals("/products") && req.getMethod().equals("POST")){

            if(role == null || !role.equals("ADMIN")){
                ((HttpServletResponse) response)
                        .sendError(403, "Only ADMIN can add products");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
