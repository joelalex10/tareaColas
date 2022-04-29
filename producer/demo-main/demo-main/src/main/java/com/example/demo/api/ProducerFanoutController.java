package com.example.demo.api;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dto.StudentDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerFanoutController {

    @Autowired
    private RabbitTemplate template;

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/producer/fanout/student")
    public String producer(@RequestBody StudentDto studentDto) {

        template.convertAndSend(RabbitMqConfig.FANOUT_EXCHANGE, RabbitMqConfig.TOPIC_STUDENT_ROUTING_KEY, studentDto);

        return "Message sent to the RabbitMQ Topic Exchange Successfully";
    }
}
