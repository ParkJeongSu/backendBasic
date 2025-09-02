package kr.co.aim.infra.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbitmq")
public class RabbitConfig {

    // 사용할 Exchange, Queue, Routing Key를 상수로 정의
    public static final String RPC_EXCHANGE_NAME = "rpc.exchange";
    public static final String REQUEST_QUEUE_NAME = "rpc.request.queue";
    public static final String ROUTING_KEY = "rpc.key";

    // Exchange 빈 등록
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(RPC_EXCHANGE_NAME);
    }

    @Bean
    public Queue demoeQueue(){
        return new Queue("demo-queue",true);
    }

    // RabbitTemplate 설정
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    // Spring Boot가 Jackson 라이브러리를 사용해 메시지를 JSON으로 자동 변환하도록 설정
    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
