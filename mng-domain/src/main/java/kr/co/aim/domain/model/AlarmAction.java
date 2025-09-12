package kr.co.aim.domain.model;

import jakarta.persistence.*;
import kr.co.aim.common.handler.NotificationHandler;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ALARM_ACTION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACTION_TYPE")
public abstract class AlarmAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alarmActionName;
    private Long alarmDefId;
    private String description;
    private String dataState;
    private String checkOutState;
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;
    private String checkOutUser;
    private String eventName;
    private String timeKey;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    private String eventUser;
    private String eventComment;

    public abstract void execute(Map<String, NotificationHandler> notificationServices);

    public abstract String getActionType();

}
