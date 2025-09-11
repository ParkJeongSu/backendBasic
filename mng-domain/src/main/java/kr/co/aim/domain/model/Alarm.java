package kr.co.aim.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
@ToString
@Builder
public class Alarm {
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
}
