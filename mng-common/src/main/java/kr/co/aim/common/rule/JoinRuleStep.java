package kr.co.aim.common.rule;

public interface JoinRuleStep<L,R,T> {
    RuleContext<T> apply(RuleContext<L> left,RuleContext<R> right);
}

