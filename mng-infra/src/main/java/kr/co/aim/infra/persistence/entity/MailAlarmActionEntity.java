package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import kr.co.aim.common.handler.NotificationHandler;
import kr.co.aim.domain.model.AlarmAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@DiscriminatorValue("Mail")
@Slf4j
public class MailAlarmActionEntity extends AlarmAction {

    // mappedBy = "alarmAction" 의미:
    // "이 관계는 AlarmMailActionDetailEntity 클래스의 'alarmAction' 필드에 의해 매핑된다"
    // FetchType.LAZY는 성능 최적화를 위해 항상 권장됩니다.
    @OneToMany(mappedBy = "alarmAction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<AlarmMailActionDetailEntity> alarmMailActionDetailEntities = new ArrayList<>();;


    @Override
    public void execute(Map<String, NotificationHandler> notificationServices) {
        NotificationHandler t = notificationServices.get("MAIL");
        log.info("mail test");

        for(AlarmMailActionDetailEntity detail : alarmMailActionDetailEntities){
            AlarmActionUserGroupEntity group = detail.getAlarmActionUserGroup();
            log.info(group.toString());
            List<AlarmActionUserGroupUsersEntity> userList = group.getAlarmActionUserGroupUsers();
            for(AlarmActionUserGroupUsersEntity user : userList){
                log.info(user.toString());
                //t.send(user.getUserId().toString(),"System",detail.getSubject(),detail.getContents());
            }
        }

    }

    @Override
    public String getActionType() {
        return "Mail";
    }

    // == 연관관계 편의 메소드 == //
    // 양쪽의 관계를 모두 설정해주는 헬퍼 메소드
    public void addDetail(AlarmMailActionDetailEntity detail) {
        this.alarmMailActionDetailEntities.add(detail);
        detail.setAlarmAction(this);
    }
}

