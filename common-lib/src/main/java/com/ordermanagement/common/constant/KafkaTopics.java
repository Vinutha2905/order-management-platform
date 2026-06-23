package com.ordermanagement.common.constant;

public final class KafkaTopics {

    private KafkaTopics() {
        //private constructor to prevent instantiation
    }

    public static final String ORDER_CREATED       = "order.created";
    public static final String ORDER_CANCELLED     = "order.cancelled";
    public static final String ORDER_CONFIRMED     = "order.confirmed";
    public static final String INVENTORY_RESERVED  = "inventory.reserved";
    public static final String INVENTORY_RELEASED  = "inventory.released";
    public static final String NOTIFICATION_EMAIL  = "notification.email";
}