package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "ALARM_ACTION_USER_GROUP_USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
@ToString(exclude = {"alarmActionUserGroup"}) // 필드명 배열로 제외
public class AlarmActionUserGroupUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ALARM_ACTION_USER_GROUP_ID", insertable = false, updatable = false)
    private Long alarmActionUserGroupId;
    private Long userId;
    private String eventName;
    private String timeKey;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    private String eventUser;
    private String eventComment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ALARM_ACTION_USER_GROUP_ID")
    private AlarmActionUserGroupEntity alarmActionUserGroup;

    // Setter for bidirectional relationship
    public void setAlarmActionUserGroup(AlarmActionUserGroupEntity group) {
        this.alarmActionUserGroup = group;
    }
}
