package com.springboot.springbootref.rabbitmq.entity;

public record OrderRequest(Integer id, String name, Integer price, Integer quantity) {
}
