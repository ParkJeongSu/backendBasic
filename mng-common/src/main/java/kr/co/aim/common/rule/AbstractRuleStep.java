package kr.co.aim.common.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractRuleStep<T,R> implements RuleStep<T,R> {

    @Override
    public RuleContext<R> apply(RuleContext<T> previousContext) {
        List<T> previous = null;
        if(previousContext == null || previousContext.output == null) {
            previous = new ArrayList<>();
        }
        else {
            previous = previousContext.output;
        }
        List<R> output = process(previous);// 기존 데이터 가공 후 output 에

        RuleContext<R> resultContext = new RuleContext<>();
        resultContext.setOutput(output);
        resultContext.setDescription(getDescription());
        resultContext.setMeta(getMeta(previous, output));

        if(areElementTypesEqual(previous,output))
        {
            // @SuppressWarnings("unchecked") 설명:
            // 컴파일러는 이 시점에도 A와 B가 같은지 100% 확신하지 못해 "unchecked cast" 경고를 보낸다.
            // 하지만 우리는 바로 위 if문에서 타입을 직접 확인했으므로 이 캐스팅이 100% 안전함을 알고 있다.
            // 따라서 이 경고를 무시하도록 명시적으로 알려준다.
            @SuppressWarnings("unchecked")
            List<R> safePrevious = (List<R>) previous;
            resultContext.setPrevious(safePrevious); // 타입이 다르므로 직접 설정하기보다 메타 정보로 관리
        }
        return resultContext;
    }

    protected abstract List<R> process(List<T> previous);
    protected abstract String getDescription();
    protected Map<String, Object> getMeta(List<T> previous, List<R> output) {
        if(previous==null || output==null){
            return null;
        }
        Map<String, Object> meta = new HashMap<>();
        meta.put("inputSize", previous.size());
        meta.put("outputSize", output.size());
        meta.put("removed", previous.size() - output.size());
        return meta;
    }

    /**
     * 두 리스트의 첫 번째 요소를 비교하여 실제 타입이 같은지 확인합니다.
     * @param listT T 타입을 담는 리스트
     * @param listR R 타입을 담는 리스트
     * @return 타입이 같으면 true, 다르면 false
     */
    private boolean areElementTypesEqual(List<T> listT, List<R> listR) {
        // 1. 빈 리스트 방어 코드: 사용자 정책에 따라 하나라도 비어있으면 다르다고 판단
        if (listT.isEmpty() || listR.isEmpty()) {
            return false;
        }

        T firstElementT = listT.get(0);
        R firstElementR = listR.get(0);

        // 2. null 요소 방어 코드: 첫 요소가 null이면 비교가 불가능하므로 다르다고 판단
        if (firstElementT == null || firstElementR == null) {
            return false;
        }

        // 3. 실제 타입 비교: 두 요소의 런타임 클래스가 같은지 확인
        return firstElementT.getClass().equals(firstElementR.getClass());
    }

}
