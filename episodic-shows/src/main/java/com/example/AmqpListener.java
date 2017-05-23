package com.example;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AmqpListener {

    @RabbitListener(queues = "my-queue-2")
    public void receiveMessage(final Map<String, String> message) {
        System.out.println("************************************************");
        System.out.println(message.toString());
        System.out.println("************************************************");
    }

}
