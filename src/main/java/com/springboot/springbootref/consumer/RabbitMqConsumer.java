package com.springboot.springbootref.consumer;

import com.springboot.springbootref.config.RabbitMQConfig;
import com.springboot.springbootref.entity.OrderDt;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitMqConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consume(OrderDt orderDt)
    {
        System.out.println("Consumer is able to consume the messages from queue"+orderDt);
    }
}
