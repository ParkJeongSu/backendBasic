package kr.co.aim.api.rabbitmq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.api.dto.request.MessageHeader;
import kr.co.aim.api.rabbitmq.controller.dispatcher.MessageDispatcher;
import kr.co.aim.api.service.RouterService;
import kr.co.aim.common.handler.MessageHandler;
import kr.co.aim.infra.config.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile({"pex","tex","dispatcher"})
public class RouterMessageListener {

    private final RouterService routerService;
    private final ObjectMapper objectMapper;

    @RabbitListener(id = "dispatcher-Listener",queues= RabbitConfig.DISPATCHER_REQUEST_QUEUE_NAME, concurrency = "10")
    @SneakyThrows
    public void recevieDispatcher(String message) {
        log.info("Received raw message: {}", message);
        // 이곳의 분배로직을 넣기
        // 특정 메시지 이름으로 분배한다거나, queue 이름으로 분배한다거나
        routerService.routePEXMessage(message);
        //routerService.routeTEXMessage(message);
        log.info("route success");
    }
}
