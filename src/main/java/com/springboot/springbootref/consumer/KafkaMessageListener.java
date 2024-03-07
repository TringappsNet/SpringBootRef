package com.springboot.springbootref.consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;


@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consume1(String message)
    {
        log.info("Consumer 1 received the message" + message);
    }
    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer2(String message)
    {
        log.info("Consumer 2 received the message" + message);
    }

    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer3(String message)
    {
        log.info("Consumer 3 received the message" + message);
    }

    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer4(String message)
    {
        log.info("Consumer 4 received the message" + message);
    }



}
