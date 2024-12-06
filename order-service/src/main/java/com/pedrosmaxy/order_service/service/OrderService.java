package com.pedrosmaxy.order_service.service;

import com.pedrosmaxy.order_service.DTO.OrderRequestDTO;
import com.pedrosmaxy.order_service.domain.order.Order;
import com.pedrosmaxy.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<UUID, String> kafkaTemplate;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setUser(orderRequestDTO.user());
        order.setItems(orderRequestDTO.items());
        order.setStatus(orderRequestDTO.status());
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());

        kafkaTemplate.send("order-events", order.getId(), "CREATED");

        return orderRepository.save(order);
    }

    public Order updateOrder(UUID id, OrderRequestDTO orderRequestDTO) {
        Order order = getOrderById(id);
        order.setUser(orderRequestDTO.user());
        order.setItems(orderRequestDTO.items());
        order.setUpdatedAt(new Date());
        order.setStatus(orderRequestDTO.status());
        return orderRepository.save(order);
    }

    public void deleteOrder(UUID id) {
        var order = getOrderById(id);
        orderRepository.deleteById(order.getId());
    }
}