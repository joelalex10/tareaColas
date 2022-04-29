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

    public static final String DIRECT_STUDENT_QUEUE = "direct_student_queue";
    public static final String DIRECT_TEACHER_QUEUE = "direct_teacher_queue";
    public static final String DIRECT_SUBJECT_QUEUE = "direct_subject_queue";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_ROUTING_KEY = "direct_routing_key";

    public static final String FANOUT_STUDENT_QUEUE = "fanout_student_queue";
    public static final String FANOUT_TEACHER_QUEUE = "fanout_teacher_queue";
    public static final String FANOUT_SUBJECT_QUEUE = "fanout_subject_queue";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String FANOUT_ROUTING_KEY = "fanout_routing_key";

    public static final String TOPIC_STUDENT_QUEUE = "topic_student_queue";
    public static final String TOPIC_TEACHER_QUEUE = "topic_teacher_queue";
    public static final String TOPIC_SUBJECT_QUEUE = "topic_subject_queue";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String TOPIC_STUDENT_ROUTING_KEY = "queue.student";
    public static final String TOPIC_TEACHER_ROUTING_KEY = "queue.teacher";
    public static final String TOPIC_SUBJECT_ROUTING_KEY = "queue.subject";

    @Bean
    public Queue directStudentQueue() {
        return new Queue(DIRECT_STUDENT_QUEUE);
    }
    @Bean
    public Queue directTeacherQueue() {
        return new Queue(DIRECT_TEACHER_QUEUE);
    }
    @Bean
    public Queue directSubjectQueue() {
        return new Queue(DIRECT_SUBJECT_QUEUE);
    }


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directStudentBinding(Queue directStudentQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(directStudentQueue)
                .to(exchange)
                .with(DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding directTeacherBinding(Queue directTeacherQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(directTeacherQueue)
                .to(exchange)
                .with(DIRECT_ROUTING_KEY);
    }

    @Bean
    public Binding directSubjectBinding(Queue directSubjectQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(directSubjectQueue)
                .to(exchange)
                .with(DIRECT_ROUTING_KEY);
    }

    @Bean
    public Queue fanoutStudentQueue() {
        return new Queue(FANOUT_STUDENT_QUEUE);
    }
    @Bean
    public Queue fanoutTeacherQueue() {
        return new Queue(FANOUT_TEACHER_QUEUE);
    }
    @Bean
    public Queue fanoutSubjectQueue() {
        return new Queue(FANOUT_SUBJECT_QUEUE);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding fanoutStudentBinding(Queue fanoutStudentQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(fanoutStudentQueue)
                .to(fanoutExchange);
    }
    @Bean
    public Binding fanoutTeacherBinding(Queue fanoutTeacherQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutTeacherQueue).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutSubjectBinding(Queue fanoutSubjectQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutSubjectQueue).to(fanoutExchange);
    }

    @Bean
    public Queue topicStudentQueue() {
        return new Queue(TOPIC_STUDENT_QUEUE);
    }
    @Bean
    public Queue topicTeacherQueue() {
        return new Queue(TOPIC_TEACHER_QUEUE);
    }
    @Bean
    public Queue topicSubjectQueue() {
        return new Queue(TOPIC_SUBJECT_QUEUE);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicStudentBinding(Queue topicStudentQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(topicStudentQueue)
                .to(topicExchange)
                .with(TOPIC_STUDENT_ROUTING_KEY);
    }
    @Bean
    public Binding topicTeacherBinding(Queue topicTeacherQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(topicTeacherQueue)
                .to(topicExchange)
                .with(TOPIC_TEACHER_ROUTING_KEY);
    }

    @Bean
    public Binding topicSubjectBinding(Queue topicSubjectQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(topicSubjectQueue)
                .to(topicExchange)
                .with(TOPIC_SUBJECT_ROUTING_KEY);
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
