package com.tecsup.app.micro.order.order_service.infrastructure.client.product;

import com.tecsup.app.micro.order.order_service.application.outbound.ProductClient;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductClientImpl implements ProductClient {

    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Override
    public ProductClientResponse getProductById(Long id) {
        log.info("Calling Product Service {}", id);

        String url = this.productServiceUrl + "/api/products/" + id;

        try {
            String authorization = request.getHeader("Authorization");

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorization);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<ProductClientResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    ProductClientResponse.class
            );

            log.info("Product retrieved successfully from product service: {}", response.getBody());

            return response.getBody();
        } catch (Exception e) {
            log.error("Error calling Product Service: {}", e.getMessage());
            throw new RuntimeException("Error calling Product Service: " + e.getMessage());
        }
    }
}
