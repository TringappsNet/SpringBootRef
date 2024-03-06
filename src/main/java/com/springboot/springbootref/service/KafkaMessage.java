package com.springboot.springbootref.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class KafkaMessage {

    private final KafkaTemplate<String, Object> template;

    public KafkaMessage(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    public void sendMessageToTopic(String message)
    {
        CompletableFuture<SendResult<String, Object>> future = template.send("demo-2", message);
        future.whenComplete((result,ex) ->
        {
            if (ex == null)
            {
                System.out.println("Sent message = [" + message + "] with offset =[ " + result.getRecordMetadata().offset() +" ]");
            }
            else
            {
                System.out.println("Unable to send message = [" + message + "] due to: "+ ex.getMessage());
            }

        });


    }

}
