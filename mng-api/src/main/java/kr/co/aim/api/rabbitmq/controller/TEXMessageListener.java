package kr.co.aim.api.rabbitmq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.common.format.request.MessageHeader;
import kr.co.aim.api.rabbitmq.controller.dispatcher.MessageDispatcher;
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
public class TEXMessageListener {

    private final MessageDispatcher messageDispatcher;
    private final ObjectMapper objectMapper;

    @RabbitListener(
            id = "tex-Listener",
            queues= RabbitConfig.TEX_REQUEST_QUEUE_NAME,
            concurrency = "10",
            containerFactory = "rabbitListenerContainerFactory"
    )
    @SneakyThrows
    public Object recevie(String message) {
        log.info("Received raw message: {}", message);

        // 1. MessageName 추출
        MessageHeader messageHeader = objectMapper.readValue(message, MessageHeader.class);
        String messageName = messageHeader.getHeader().getMessageName();
        log.info("messageName : {}", messageName);
        // 2. Dispatcher를 통해 적절한 핸들러 찾기
        MessageHandler<String> handler = messageDispatcher.getHandler(messageName);

        Object replyObject = null;
        if (handler != null) {
            // 3. 핸들러에게 작업 위임
            replyObject = handler.handle(message);
        } else {
            log.warn("⚠️ No handler found for messageName: {}", messageName);
        }
        return replyObject;
    }
}
