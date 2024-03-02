package com.springboot.springbootref.rabbitmq.producer;

import com.springboot.springbootref.rabbitmq.config.RabbitMQConfig;
import com.springboot.springbootref.rabbitmq.entity.Order;
import com.springboot.springbootref.rabbitmq.entity.OrderDt;
import com.springboot.springbootref.rabbitmq.entity.OrderRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RmqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/orders")
    public OrderDt placeOrder(@RequestBody OrderRequest orderRequest)
    {
        Order order = new Order(orderRequest.id(), orderRequest.name(), orderRequest.price(), orderRequest.quantity());
        OrderDt orderDt = new OrderDt(order, "Order Placed", "Hi Producer, your order is placed");
        System.out.println(order.getId());
        System.out.println(order.getName());
        System.out.println(order.getPrice());

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, orderDt);
        return orderDt;
    }
}
