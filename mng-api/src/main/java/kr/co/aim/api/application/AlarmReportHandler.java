package kr.co.aim.api.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.aim.common.format.request.BaseMessage;
import kr.co.aim.api.service.AlarmService;
import kr.co.aim.common.enums.MessageList;
import kr.co.aim.common.format.AlarmReportBody;
import kr.co.aim.common.handler.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmReportHandler implements MessageHandler<String> {

    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final AlarmService alarmService;

    @Override
    public String getSupportedMessageName() {
        return MessageList.ALARM_REPORT.getMessageName();
    }

    @Override
    @SneakyThrows // objectMapper의 예외 처리를 간소화
    public Object handle(String message) {
        // 1. 자신에게 맞는 DTO로 역직렬화
        TypeReference<BaseMessage<AlarmReportBody>> typeRef = new TypeReference<>() {};
        BaseMessage<AlarmReportBody> requestMessage = objectMapper.readValue(message, typeRef);
        log.info("✅ Handling Message request: {}", message);

        // 2. 해당 비즈니스 로직 호출
        // 서비스 호출
        alarmService.alarmReport(requestMessage);
        
        // 3. 만일 서비스 호출 후 메시지 송신해야하면 이 부분에서 reply 메시지 생성
        // reply 객체 정의

        // 4. DTO 객체를 JSON 문자열로 직접 변환합니다.
        // String jsonPayload = objectMapper.writeValueAsString(reply);
        // System.out.println("Sending JSON Payload: " + jsonPayload);

        // 5. String 으로 변환된 메시지 reply
        // rabbitTemplate.convertAndSend( "demo-queue", jsonPayload );
        return null;
    }
}