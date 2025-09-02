package kr.co.aim.api.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.api.dto.request.BaseMessage;
import com.fasterxml.jackson.core.type.TypeReference;
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
        return "AreYouThereRequest";
    }

    @Override
    @SneakyThrows // objectMapper의 예외 처리를 간소화
    public Object handle(String message) {
        // 1. 자신에게 맞는 DTO로 역직렬화
        TypeReference<BaseMessage<AreYouThereBody>> typeRef = new TypeReference<>() {};
        BaseMessage<AreYouThereBody> request = objectMapper.readValue(message, typeRef);
        log.info("✅ Handling CreateUser request: {}", message);

        // 2. 해당 비즈니스 로직 호출
        //Object user = userService.createUser(request);
        log.info("machineName : {}", request.getBody().getMachineName());
        
        // 3. 메시지 송신
        BaseMessage<AreYouThereBody> reply = new BaseMessage<>();
        Header header = Header.builder().messageName("AreYouThereReply")
                .eventComment("test")
                .eventUser("pjs")
                .version("1.0")
                .replyQueueName("abc")
                .timestamp("test")
                .transactionId("123")
                .build();
        AreYouThereBody body = AreYouThereBody.
                builder()
                .machineName("machine1")
                .build();
        request.setHeader(header);
        request.setBody(body);

        // 3. DTO 객체를 JSON 문자열로 직접 변환합니다.
        String jsonPayload = objectMapper.writeValueAsString(request);

        System.out.println("Sending JSON Payload: " + jsonPayload);
        rabbitTemplate.convertAndSend(
                "demo-queue",
                jsonPayload
        );
        return null;
    }
}