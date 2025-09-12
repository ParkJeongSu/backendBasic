package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import kr.co.aim.domain.model.AlarmAction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "ALARM_MAIL_ACTION_DETAIL")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
public class AlarmMailActionDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ALARM_ACTION_ID", insertable = false, updatable = false)
    private Long alarmActionId;
    @Column(name = "ALARM_ACTION_USER_GROUP_ID", insertable = false, updatable = false)
    private Long alarmActionUserGroupId;
    private String subject;
    private String contents;
    private String eventName;
    private String timeKey;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    private String eventUser;
    private String eventComment;

    // 이쪽이 관계의 주인이 됩니다.
    // FetchType.LAZY는 성능 최적화를 위해 항상 권장됩니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALARM_ACTION_ID") // 외래 키 컬럼을 명시
    private AlarmAction alarmAction; // 부모 타입을 추상 클래스로 참조할 수 있습니다.

    // 이쪽이 관계의 주인이 됩니다.
    // FetchType.LAZY는 성능 최적화를 위해 항상 권장됩니다.
    // 하나의 그룹을 가진다
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALARM_ACTION_USER_GROUP_ID")
    private AlarmActionUserGroupEntity alarmActionUserGroup;

    // 양방향 관계 편의를 위한 Setter
    public void setAlarmAction(AlarmAction alarmAction) {
        this.alarmAction = alarmAction;
    }
}
