package kr.co.aim.api.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.common.format.request.BaseMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import kr.co.aim.common.enums.MessageList;
import kr.co.aim.common.format.AreYouThereBody;
import kr.co.aim.common.format.Header;
import kr.co.aim.common.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AreYouThereRequestHandler implements MessageHandler<String> {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public String getSupportedMessageName() {
        return MessageList.ARE_YOU_THERE_REQUEST.getMessageName();
    }

    @Override
    @SneakyThrows // objectMapper의 예외 처리를 간소화
    public Object handle(String message) {
        // 1. 자신에게 맞는 DTO로 역직렬화
        TypeReference<BaseMessage<AreYouThereBody>> typeRef = new TypeReference<>() {};
        BaseMessage<AreYouThereBody> request = objectMapper.readValue(message, typeRef);
        log.info("✅ Handling CreateUser request: {}", message);

        // 2. 해당 비즈니스 로직 호출
        // AreYouThereRequest 는 단순히 로그
        log.info("machineName : {}", request.getBody().getMachineName());
        
        // 3. 메시지 송신 객체 생성
        BaseMessage<AreYouThereBody> reply = new BaseMessage<>();
        Header header = Header.builder().messageName(MessageList.ARE_YOU_THERE_REPLY.getMessageName())
                .eventComment(request.getHeader().getEventComment())
                .eventUser("MNG")
                .version("1.0")
                .replyQueueName("")
                .timestamp("test")
                .transactionId(request.getHeader().getTransactionId())
                .build();
        AreYouThereBody body = AreYouThereBody.
                builder()
                .machineName(request.getBody().getMachineName())
                .build();
        request.setHeader(header);
        request.setBody(body);

        // 3. DTO 객체를 JSON 문자열로 직접 변환합니다.
        String jsonPayload = objectMapper.writeValueAsString(request);
        log.info("Sending JSON Payload: " + jsonPayload);

        // 4. Message Reply
        rabbitTemplate.convertAndSend(
                "demo-queue",
                jsonPayload
        );
        return null;
    }
}