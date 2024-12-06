package com.pedrosmaxy.order_service.controller;

import com.pedrosmaxy.order_service.DTO.OrderRequestDTO;
import com.pedrosmaxy.order_service.domain.order.Order;
import com.pedrosmaxy.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(this.orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        var newOrder = this.orderService.createOrder(orderRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrder.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID id, @RequestBody OrderRequestDTO orderRequestDTO) {
        return ResponseEntity.ok(this.orderService.updateOrder(id, orderRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        this.orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}