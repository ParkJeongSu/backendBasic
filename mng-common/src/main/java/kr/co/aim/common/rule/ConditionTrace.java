package kr.co.aim.common.rule;

import java.util.List;

class ConditionTrace<T> {
    String name;               // 예: "Reserved 필터"
    String expression;         // 예: "c.isReserved() == true"
    List<T> removedItems;      // 이 조건에 의해 제거된 항목들
    List<T> passedItems;       // 이 조건을 통과한 항목들 (선택)
}
