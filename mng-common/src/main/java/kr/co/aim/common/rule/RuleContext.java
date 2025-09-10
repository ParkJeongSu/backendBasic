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
    List<T> previous;
    List<T> output;
    String description;
    int stepNumber; // historyList 에 순서대로 넣고 있음
    String transactionId;
    Map<String, Object> meta = new HashMap<>();
}
