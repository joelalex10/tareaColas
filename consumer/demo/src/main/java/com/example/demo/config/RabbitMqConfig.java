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

    public static final String QUEUE = "queue_student";
    public static final String QUEUE2 = "queue_teacher";
    public static final String EXCHANGE = "direct_exchange";
    //public static final String ROUTING_KEY = "student";

    public static final String STUDENT_ROUTING_KEY = "student";
    public static final String TEACHER_ROUTING_KEY = "teacher";



    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }


     @Bean
     public FanoutExchange exchangeFanout() {
     return new FanoutExchange(EXCHANGE);
     }

     @Bean
     public TopicExchange exchangeTopic() {
     return new TopicExchange(EXCHANGE);
     }




    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(STUDENT_ROUTING_KEY);
    }

    @Bean
    public Binding binding2(Queue queue2, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue2)
                .to(exchange)
                .with(TEACHER_ROUTING_KEY);
    }
    @Bean
    public TopicExchange exchange2(){
        return new TopicExchange("topic_exchange");
    }



    @Bean
    public Binding bindingFanout(Queue queue, FanoutExchange exchangeFanout) {
        return BindingBuilder
                .bind(queue)
                .to(exchangeFanout);
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
}
