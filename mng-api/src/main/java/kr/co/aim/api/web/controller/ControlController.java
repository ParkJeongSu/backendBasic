package kr.co.aim.api.web.controller;

import kr.co.aim.common.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ControlController {

    private final RabbitListenerEndpointRegistry registry;
    private final ApplicationContext applicationContext;

    @PostMapping("/stop")
    public String stop() {
        System.out.println("애플리케이션 종료 작업 시작...");
        log.info("애플리케이션 종료 작업 시작...");
        // 1. 모든 리스너 컨테이너의 메시지 소비 중단
        System.out.println("모든 리스너 컨테이너 종료 명령...");
        log.info("모든 리스너 컨테이너 종료 명령...");
        for (MessageListenerContainer container : registry.getListenerContainers()) {
            if (container.isRunning()) {
                log.info("container stop 명령시작");
                container.stop(new StopRunnable()); // 논블로킹, 현재 메시지 처리 후 종료
            }
        }

        // 2. 현재 처리 중인 메시지들이 완료될 때까지 대기 (30초)
        System.out.println("현재 처리 중인 메시지 완료를 위해 30초 대기...");
        log.info("현재 처리 중인 메시지 완료를 위해 30초 대기시작...");
        try {
            Thread.sleep(30_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("모든 리스너 컨테이너가 종료되었습니다.");
        log.info("모든 리스너 컨테이너가 종료되었습니다.");
        // 비동기로 종료
        new Thread(() -> {
            try {
                Thread.sleep(1000); // 응답 반환 기다리기
            } catch (InterruptedException ignored) {}
            ((ConfigurableApplicationContext) applicationContext).close();
        }).start();
        return "Stopped gracefully";
    }

    class StopRunnable implements Runnable{
        @Override
        public void run() {
            log.info("정상 종료 되었습니다.");
        }

    }

}
