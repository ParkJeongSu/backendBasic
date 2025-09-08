package kr.co.aim.common.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRuleStep<T> implements RuleStep<T> {
    @Override
    public RuleContext<T> apply(RuleContext<T> context) {
        List<T> input = context.output != null ? context.output : new ArrayList<>();
        List<T> output = process(input);
        RuleContext<T> result = new RuleContext<>();
        result.input = input;
        result.output = output;
        result.description = getDescription();
        result.meta = getMeta(input, output);
        return result;
    }

    protected abstract List<T> process(List<T> input);
    protected abstract String getDescription();
    protected Map<String, Object> getMeta(List<T> input, List<T> output) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("inputSize", input.size());
        meta.put("outputSize", output.size());
        meta.put("removed", input.size() - output.size());
        return meta;
    }
}
