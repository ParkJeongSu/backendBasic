package kr.co.aim.api.schedule;

import kr.co.aim.api.service.DataTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("scheduler")
public class Scheduler {

    private final DataTransferService dataTransferService;

    @Scheduled(fixedDelay = 5000) // 5초마다 실행
    public void transferData() {
        log.info("⏰ 스케줄러가 데이터를 전송합니다.");
        // 여기에 데이터베이스 연동 로직을 추가할 예정입니다.
        dataTransferService.transferUsersToDb2();
    }
}