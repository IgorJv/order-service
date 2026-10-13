package org.orderservice.orderservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.orderservice.orderservice.service.KafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

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
        SendResult<String, Object> result = mock(SendResult.class);
        when(kafkaTemplate.send(anyString(), anyString(), any()))
                .thenReturn(CompletableFuture.completedFuture(result));
        kafkaProducerService.sendOrderEvent(event);
        verify(kafkaTemplate).send("order-events", "ord-12345", event);
    }
}
