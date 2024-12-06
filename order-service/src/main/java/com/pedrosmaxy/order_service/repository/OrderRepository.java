package com.pedrosmaxy.order_service.repository;

import com.pedrosmaxy.order_service.domain.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, UUID> {
}
