package kr.co.aim.api.rabbitmq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.api.dto.request.MessageHeader;
import kr.co.aim.api.rabbitmq.controller.dispatcher.MessageDispatcher;
import kr.co.aim.api.service.RouterService;
import kr.co.aim.common.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"rabbitmq","dispatcher"})
public class RouterMessageListener {

    private final RouterService routerService;
    private final ObjectMapper objectMapper;

    @RabbitListener(id = "dispatcher-Listener2",queues="dispatcher-queue", concurrency = "10")
    @SneakyThrows
    public void recevieDispatcher(String message) {
        log.info("Received raw message: {}", message);

        routerService.routeMessage(message);

        log.info("route success");
    }
}
