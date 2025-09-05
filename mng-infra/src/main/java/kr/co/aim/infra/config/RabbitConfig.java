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
public class RabbitConfig {

    // 사용할 Exchange, Queue, Routing Key를 상수로 정의
    public static final String RPC_EXCHANGE_NAME = "rpc.exchange";
    public static final String PEX_REQUEST_QUEUE_NAME = "PEX.request.queue";
    public static final String TEX_REQUEST_QUEUE_NAME = "TEX.request.queue";
    public static final String DISPATCHER_REQUEST_QUEUE_NAME = "DISPATCHER.request.queue";
    public static final String PEX_ROUTING_KEY = "PEX.key";
    public static final String TEX_ROUTING_KEY = "TEX.key";
    public static final String DISPATCHER_ROUTING_KEY = "DISPATCHER.key";

    // Exchange 빈 등록
    @Bean
    @Profile({"pex","tex","dispatcher"})
    DirectExchange exchange() {
        return new DirectExchange(RPC_EXCHANGE_NAME);
    }

    @Bean
    @Profile({"pex","tex","dispatcher"})
    public Queue pexQueue(){
        return new Queue(PEX_REQUEST_QUEUE_NAME,true);
    }


    @Bean
    @Profile({"pex","tex","dispatcher"})
    public Queue texQueue(){
        return new Queue(TEX_REQUEST_QUEUE_NAME,true);
    }

    @Bean
    @Profile({"pex","tex","dispatcher"})
    public Queue dispatcherQueue(){
        return new Queue(DISPATCHER_REQUEST_QUEUE_NAME,true);
    }

    // RabbitTemplate 설정
    @Bean
    @Profile({"pex","tex","dispatcher"})
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    // Spring Boot가 Jackson 라이브러리를 사용해 메시지를 JSON으로 자동 변환하도록 설정
    @Bean
    @Profile({"pex","tex","dispatcher"})
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
