package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import kr.co.aim.infra.listener.AlarmHistoryListener;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "ALARM")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
//@EntityListeners(AlarmHistoryListener.class)
public class AlarmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long alarmDefId;
    private Long equipmentId;
    private String alarmState;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date clearTime;
    private String eventName;
    private String timeKey;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    private String eventUser;
    private String eventComment;
}
