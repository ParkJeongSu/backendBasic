package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "ALARM_ACTION")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
public class AlarmActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alarmActionName;
    private String actionType;
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
}
