package kr.co.aim.common.rule;

public interface RuleStep<T> {
    RuleContext<T> apply(RuleContext<T> context);
}

