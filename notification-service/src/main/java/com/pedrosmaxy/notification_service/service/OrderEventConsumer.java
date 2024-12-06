package com.pedrosmaxy.notification_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public OrderEventConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "order-events", groupId = "notification_service_group")
    public void consumeOrderEvent(String message) {
        System.out.println("Received Kafka message: " + message);
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }
}
