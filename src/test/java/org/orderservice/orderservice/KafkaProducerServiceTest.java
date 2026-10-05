package org.orderservice.orderservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.orderservice.orderservice.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerServiceTest {
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;
    @InjectMocks
    private KafkaProducerService kafkaProducerService;

    @Test
    void sendOrderEvent_ShouldSendToKafkaWithCorrectTopicAndKey() {
        OrderEventDto event = new OrderEventDto();
        event.setOrderId("ord-12345");
        kafkaProducerService.sendOrderEvent(event);
        verify(kafkaTemplate).send("order-events", "ord-12345", event);
    }
}
