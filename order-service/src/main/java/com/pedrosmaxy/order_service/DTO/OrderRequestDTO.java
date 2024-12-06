package com.pedrosmaxy.order_service.DTO;


import java.util.List;

public record OrderRequestDTO(String user, List<String> items, String status) {
}
