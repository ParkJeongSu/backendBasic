package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "ALARM_DEF")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
public class AlarmDefEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alarmCodeName;
    private String alarmType;
    private String description;
    private String alarmLevel;
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
