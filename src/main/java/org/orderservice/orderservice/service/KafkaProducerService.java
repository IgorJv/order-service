package org.orderservice.orderservice.service;

import lombok.AllArgsConstructor;
import org.orderservice.orderservice.OrderEventDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "order-events";

    public void sendOrderEvent(OrderEventDto event) {
        kafkaTemplate.send(TOPIC, event.getOrderId(), event);
    }
}
