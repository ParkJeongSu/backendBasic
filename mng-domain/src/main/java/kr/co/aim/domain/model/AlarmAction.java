package kr.co.aim.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
@ToString
@Builder
public class AlarmAction {
    private Long id;
    private String alarmActionName;
    private String actionType;
    private Long alarmDefId;
    private String description;
    private String dataState;
    private String checkOutState;
    private Date checkOutTime;
    private String checkOutUser;
    private String eventName;
    private String timeKey;
    private Date eventTime;
    private String eventUser;
    private String eventComment;
}
