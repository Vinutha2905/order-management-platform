package com.ordermanagement.common.event;
import java.time.Instant;


public record InventoryReservedEvent (
        String eventId,
        String orderNumber,
        boolean reserved,
        String failureReason,
        Instant occurredAt
){}
    

