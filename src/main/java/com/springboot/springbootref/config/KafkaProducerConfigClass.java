package com.springboot.springbootref.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfigClass {

    @Bean
    public NewTopic createTopic()
    {
        return new NewTopic("demo-2", 3, (short) 1);
    }

}
