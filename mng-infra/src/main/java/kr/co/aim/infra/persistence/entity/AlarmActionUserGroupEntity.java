package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "ALARM_ACTION_USER_GROUP")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA를 위한 기본 생성자
@ToString(exclude = {"alarmMailActionDetailEntities","alarmActionUserGroupUsers"}) // 필드명 배열로 제외
public class AlarmActionUserGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userGroupName;
    private String eventName;
    private String timeKey;
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    private String eventUser;
    private String eventComment;

    // 이쪽이 주인이 아닌쪽
    // 읽기 전용
    @OneToMany(mappedBy = "alarmActionUserGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlarmMailActionDetailEntity> alarmMailActionDetailEntities= new ArrayList<>();

    
    // 이 UserGroup이 여러 명의 User를 포함합니다. (1:N)
    // 마찬가지로 주인이 아니고
    // 일기 전용
    @OneToMany(mappedBy = "alarmActionUserGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlarmActionUserGroupUsersEntity> alarmActionUserGroupUsers = new ArrayList<>();

    // Convenience method for bidirectional relationship
    public void addUser(AlarmActionUserGroupUsersEntity user) {
        this.alarmActionUserGroupUsers.add(user);
        user.setAlarmActionUserGroup(this);
    }
}
