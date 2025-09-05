package kr.co.aim.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile({"rabbitmq","dispatcher"})
public class RouterService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper; // ObjectMapper 선언

    public void routeMessage(String message){
        rabbitTemplate.convertAndSend(
                "demo-queue",
                message
        );
    }
}
