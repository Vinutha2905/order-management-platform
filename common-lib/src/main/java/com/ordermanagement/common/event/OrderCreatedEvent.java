package com.ordermanagement.common.event;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent (
    String eventId,
    String orderNumber,
    Long userId,
    List<OrderItemEvent> items,
    BigDecimal totalAmount,
        String currency,
        Instant occurredAt
) {
    public record OrderItemEvent(
        Long productId,
            String productSku,
            Integer quantity,
            BigDecimal unitPrice
    ) {}

    
}
