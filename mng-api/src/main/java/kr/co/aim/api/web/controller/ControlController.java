package kr.co.aim.api.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ControlController {

    private final RabbitListenerEndpointRegistry registry;
    private final ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;


    @PostMapping("/stop")
    public String stop() {
        // 1. 수신 중단
        registry.getListenerContainer("demoListener").stop();
        log.info("RabbitListener 중단 요청됨");

        log.info("taskExecutor.shutdown(); taskExecutor start");
        taskExecutor.shutdown();
        log.info("taskExecutor.shutdown(); taskExecutor end");

        try {
            taskExecutor.getThreadPoolExecutor().awaitTermination(60, TimeUnit.SECONDS);
            log.info("모든 작업이 완료되었습니다.");
        } catch (InterruptedException e) {
            log.warn("대기 중 인터럽트됨", e);
        }

        // 비동기로 종료
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 응답 반환 기다리기
            } catch (InterruptedException ignored) {}
            ((ConfigurableApplicationContext) applicationContext).close();
        }).start();
        return "Stopped gracefully";
    }
}
