package com.tecsup.app.micro.order.order_service.infrastructure.client.user;

import com.tecsup.app.micro.order.order_service.application.outbound.UserClient;
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
public class UserClientImpl implements UserClient {

    private final RestTemplate restTemplate;
    private final HttpServletRequest request;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Override
    public UserClientResponse getUserById(Long id) {
        log.info("Calling User Service {}", id);

        String url = this.userServiceUrl + "/api/users/" + id;

        try {
            String authorization = request.getHeader("Authorization");

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorization);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<UserClientResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    UserClientResponse.class
            );

            log.info("User retrieved successfully from user service: {}", response.getBody());

            return response.getBody();
        } catch (Exception e) {
            log.error("Error calling User Service: {}", e.getMessage());
            throw new RuntimeException("Error calling User Service: " + e.getMessage());
        }
    }
}
