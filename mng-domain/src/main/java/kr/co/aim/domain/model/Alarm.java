package kr.co.aim.domain.model;

import kr.co.aim.common.format.AlarmReportBody;
import kr.co.aim.common.format.request.BaseMessage;
import kr.co.aim.common.handler.HasTransactionInfo;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
@ToString
@Builder
public class Alarm implements HasTransactionInfo {
    private Long id;
    private Long alarmDefId;
    private Long equipmentId;
    private String alarmState;
    private Date createTime;
    private Date clearTime;
    private String eventName;
    private String timeKey;
    private Date eventTime;
    private String eventUser;
    private String eventComment;

    public static Alarm issue(Long alarmDefId, Long equipmentId, BaseMessage<AlarmReportBody> message){
        Date currnetDate = new Date();
        return Alarm.builder()
                .alarmDefId(alarmDefId)
                .equipmentId(equipmentId)
                .alarmState(message.getBody().getAlarmState())
                .createTime(currnetDate)
                //.clearTime()
                .eventName(message.getHeader().getMessageName())
                .eventTime(currnetDate)
                .timeKey(message.getHeader().getTransactionId())
                .eventUser(message.getHeader().getEventUser())
                .eventComment(message.getHeader().getEventComment()).
                build();
    }
    public void updateAlarm(BaseMessage<AlarmReportBody> message){
        Date currnetDate = new Date();
        this.setAlarmState(message.getBody().getAlarmState());
        if(message.getBody().getAlarmState().equals("issue")) {
            this.setCreateTime(currnetDate);
        }
        else{
            this.setClearTime(currnetDate);
        }
        this.setEventName(message.getHeader().getMessageName());
        this.setEventTime(currnetDate);
        this.setTimeKey(message.getHeader().getTransactionId());
        this.setEventUser(message.getHeader().getEventUser());
        this.setEventComment(message.getHeader().getEventComment());
    }
}
