package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jrojas
 */
@Configuration
public class RabbitMqConfig {

    public static final String DIRECT_QUEUE = "student_queue";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_ROUTING_KEY = "direct_routing_key";


    public static final String STUDENT_FANOUT_QUEUE = "student_queue";
    public static final String TEACHER_FANOUT_QUEUE = "teacher_queue";
    public static final String SUBJECT_FANOUT_QUEUE = "subject_queue";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String FANOUT_ROUTING_KEY = "fanout_routing_key";


    @Bean
    public Queue queue() {
        return new Queue(DIRECT_QUEUE);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }



    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(DIRECT_ROUTING_KEY);
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }


    @Bean
    public Queue studentQueue() {
        return new Queue(STUDENT_FANOUT_QUEUE);
    }
    @Bean
    public Queue teacherQueue() {
        return new Queue(TEACHER_FANOUT_QUEUE);
    }
    @Bean
    public Queue subjectQueue() {
        return new Queue(SUBJECT_FANOUT_QUEUE);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding studentBinding(Queue studentQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(studentQueue)
                .to(fanoutExchange);
    }
    @Bean
    public Binding teacherBinding(Queue teacherQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(teacherQueue).to(fanoutExchange);
    }
    @Bean
    public Binding subjectBinding(Queue subjectQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(subjectQueue).to(fanoutExchange);
    }


}
