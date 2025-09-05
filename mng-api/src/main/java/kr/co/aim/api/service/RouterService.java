package kr.co.aim.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.infra.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile({"pex","tex","dispatcher"})
public class RouterService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper; // ObjectMapper 선언

    public void routePEXMessage(String message){
        rabbitTemplate.convertAndSend(
                RabbitConfig.RPC_EXCHANGE_NAME,
                RabbitConfig.PEX_ROUTING_KEY,
                message
        );
    }
    public void routeTEXMessage(String message){
        rabbitTemplate.convertAndSend(
                RabbitConfig.RPC_EXCHANGE_NAME,
                RabbitConfig.TEX_ROUTING_KEY,
                message
        );
    }
}
