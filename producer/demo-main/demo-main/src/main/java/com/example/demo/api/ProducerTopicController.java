package com.example.demo.api;

import com.example.demo.config.RabbitMqConfig;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Subject;
import com.example.demo.entity.Teacher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerTopicController {
    @Autowired
    private RabbitTemplate template;

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/producer/topic/student")
    public String producer(@RequestBody StudentDto studentDto) {

        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.TOPIC_STUDENT_ROUTING_KEY, studentDto);

        return "Message sent to the RabbitMQ Fanout Exchange Successfully";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/producer/topic/teacher")
    public String producer(@RequestBody Teacher teacher) {

        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.TOPIC_TEACHER_ROUTING_KEY, teacher);
        return "Message sent to the RabbitMQ Fanout Exchange Successfully";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/producer/topic/subject")
    public String producer(@RequestBody Subject subject) {

        template.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE, RabbitMqConfig.TOPIC_STUDENT_ROUTING_KEY, subject);
        return "Message sent to the RabbitMQ Fanout Exchange Successfully";
    }
}
