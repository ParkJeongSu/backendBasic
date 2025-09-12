package kr.co.aim.common.handler;

import kr.co.aim.common.record.TransactionInfo;

import java.util.Date;

public interface HasTransactionInfo {

    // 표준 세터 시그니처(이름 통일! IDE 리팩터링 안전)
    void setEventName(String v);
    void setTimeKey(String v);
    void setEventTime(Date v);   // Date 쓰면 Date로
    void setEventUser(String v);
    void setEventComment(String v);

    default void apply(TransactionInfo tx) {
        if (tx == null) return;
        setEventName(tx.eventName());
        setTimeKey(tx.timeKey());
        setEventTime(tx.eventTime());
        setEventUser(tx.eventUser());
        setEventComment(tx.eventComment());
    }
}
