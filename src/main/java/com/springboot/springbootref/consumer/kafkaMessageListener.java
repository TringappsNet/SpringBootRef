package com.springboot.springbootref.consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkaMessageListener {

    Logger log = LoggerFactory.getLogger(kafkaMessageListener.class);

    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer1(String message)
    {
        log.info("consumer1 received the message {} " + message);
    }
    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer2(String message)
    {
        log.info("consumer2 received the message {} " + message);
    }
    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer3(String message)
    {
        log.info("consumer3 received the message {} " + message);
    }
    @KafkaListener(topics = "demo-2", groupId = "grp-1")
    public void consumer4(String message)
    {
        log.info("consumer4 received the message {} " + message);
    }


}
