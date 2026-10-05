package org.orderservice.orderservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventDto {
    private String orderId;
    private String userId;
    private Double amount;
}
