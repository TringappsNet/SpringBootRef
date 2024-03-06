package com.springboot.springbootref.KafkaController;

import com.springboot.springbootref.service.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/producer-api")
public class EventController {

    @Autowired
    private KafkaMessage publisher;


    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message)
    {
        try{
            for (int i=0;i<=1000;i++)
            {
                publisher.sendMessageToTopic(message + ":" +i);
            }
            return ResponseEntity.ok("Message published Successfully");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }



}
