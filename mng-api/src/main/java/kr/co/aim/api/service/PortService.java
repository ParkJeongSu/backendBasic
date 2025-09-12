package kr.co.aim.api.service;

import kr.co.aim.common.format.*;
import kr.co.aim.common.format.request.BaseMessage;
import kr.co.aim.common.handler.NotificationHandler;
import kr.co.aim.domain.model.Alarm;
import kr.co.aim.domain.model.AlarmAction;
import kr.co.aim.domain.model.AlarmDef;
import kr.co.aim.domain.repository.AlarmActionRepository;
import kr.co.aim.domain.repository.AlarmDefRepository;
import kr.co.aim.domain.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 만들어줍니다. (DI)
@Slf4j
public class PortService {

    /**
     * 포트의 새로운 캐리어를 요청합니다.
     * 1. 포트에 반송중인 job 조회
     * 만일 포트로 반송중인 job이 있다면, transferState -> ReservedToLoad로 변경
     * 반송중인 job이 없다면, ReadToLoad 로 변경 후 WhatNext Message 반환
     *
     * @param message 받은 메시지
     * @return RTD 로 보낼 메시지 객체
     */
    @Transactional // 이 메소드가 하나의 트랜잭션으로 동작하도록 보장합니다.
    public BaseMessage<WhatNextBody> loadRequest(BaseMessage<LoadRequestBody> message) {
        return null;
    }

    /**
     * 포트의 캐리어가 도착했음을 보고
     * 1. 포트 테이블 조회
     * 포트의 transferState -> ReadyToProcess 변경
     * 2. Carrier 조회
     * Carrier 의 위치 정보를 Port 로 변경
     *
     * @param message 받은 메시지
     */
    @Transactional // 이 메소드가 하나의 트랜잭션으로 동작하도록 보장합니다.
    public void loadCompleted(BaseMessage<LoadCompletedBody> message) {

    }

    /**
     * 포트 위의 Carrier 를 Unload 요청합니다.
     * 1. Carrier 의 위치정보를 port 로 변경합니다.
     * 2. Carrier 정보와 port 정보를 WhereNext로 메시지를 반환
     *
     * @param message 받은 메시지
     * @return RTD 로 보낼 메시지 객체
     */
    @Transactional // 이 메소드가 하나의 트랜잭션으로 동작하도록 보장합니다.
    public BaseMessage<WhereNextBody> unLoadRequest(BaseMessage<UnLoadRequestBody> message) {
        return null;
    }

    /**
     * unload가 완료 되었음을 보고합니다.
     * 1. port의 transferState -> ReservedToUnload 로 변경합니다 << 이거 조금 고민
     * -> 아무런 동작을 하지 않아도 될것 같기도 함.. 이거 고민
     * @param message 받은 메시지
     */
    @Transactional // 이 메소드가 하나의 트랜잭션으로 동작하도록 보장합니다.
    public void unLoadCompleted(BaseMessage<UnLoadCompletedBody> message) {

    }
}