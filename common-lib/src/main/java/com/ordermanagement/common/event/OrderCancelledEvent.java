package com.ordermanagement.common.event;
import java.time.Instant;

public record OrderCancelledEvent(
        String eventId,
        String orderNumber,
        Long userId,
        String cancellationReason,
        Instant occurredAt
) {}