package kr.co.aim.common.rule;

public interface RuleStep<T,R> {
    RuleContext<R> apply(RuleContext<T> previous);
}

