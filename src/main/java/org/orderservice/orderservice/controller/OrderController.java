package org.orderservice.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.orderservice.orderservice.OrderEventDto;
import org.orderservice.orderservice.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private final KafkaProducerService service;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderEventDto orderEventDto) {
        log.info("Received order creation request for orderId: {}, userId: {}, amount: {}",
                orderEventDto.getOrderId(),
                orderEventDto.getUserId(),
                orderEventDto.getAmount());
        service.sendOrderEvent(orderEventDto);
        return ResponseEntity.ok("Order created and event published!");
    }
}
