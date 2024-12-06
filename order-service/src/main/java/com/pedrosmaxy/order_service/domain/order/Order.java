package com.pedrosmaxy.order_service.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "orders")
public class Order {
    @Id
    private UUID id;
    private String user;
    private List<String> items;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
