package kr.co.aim.common.rule;

import java.util.*;

public abstract class AbstractJoinRuleStep<L,R,T> implements JoinRuleStep<L,R,T> {

    @Override
    public RuleContext<T> apply(RuleContext<L> left,RuleContext<R> right) {

        List<T> result = process(left,right);// 기존 데이터 가공 후 output 에

        RuleContext<T> resultContext = new RuleContext<>();
        resultContext.setOutput(result);
        resultContext.setDescription(getDescription());
        resultContext.setMeta(getMeta(left.output, right.output));
        return resultContext;
    }

    protected abstract List<T> process(RuleContext<L> left,RuleContext<R>right);
    protected abstract String getDescription();
    protected Map<String, Object> getMeta(List<L> left, List<R> right) {
        if (left == null || right ==null){
            return null;
        }
        // 이 메소드는 left가 null이면 NullPointerException을 던지고 즉시 종료됩니다.
//        Objects.requireNonNull(left, "Input list 'left' cannot be null.");
//        Objects.requireNonNull(right, "Input list 'right' cannot be null.");

        Map<String, Object> meta = new HashMap<>();
        meta.put("inputSize", left.size());
        meta.put("outputSize", right.size());
        return meta;
    }

}
