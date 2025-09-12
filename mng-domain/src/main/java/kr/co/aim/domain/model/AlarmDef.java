package kr.co.aim.domain.model;

import kr.co.aim.common.handler.HasTransactionInfo;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
@ToString
@Builder
public class AlarmDef implements HasTransactionInfo {
    private Long id;
    private String alarmCodeName;
    private String alarmType;
    private String description;
    private String alarmLevel;
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
