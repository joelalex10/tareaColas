package com.example.demo;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dto.MessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListener {

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void listener(MessageDto messageDto){
        System.out.println(messageDto);
    }
}
