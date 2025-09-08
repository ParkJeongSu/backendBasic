package kr.co.aim.common.rule;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
public class RuleContext<T> {
    List<T> input;
    List<T> output;
    String description;
    int stepNumber;
    String transactionId;
    Map<String, Object> meta = new HashMap<>();
    List<ConditionTrace<T>> traceLog = new ArrayList<>();  // ⭐ 추가
}
