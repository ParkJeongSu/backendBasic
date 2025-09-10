package kr.co.aim.api.rtd.service;

import kr.co.aim.domain.model.CarrierAndCarrierDef;
import kr.co.aim.domain.model.CarrierDef;
import kr.co.aim.domain.model.Carriers;
import kr.co.aim.common.rule.RuleContext;
import kr.co.aim.common.rule.RuleStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WhatNextService {
    //private final HistoryLogService historyLogService;
    private final CarrierQueryStep carrierQueryStep;
    private final CarrierDefQueryStep carrierDefQueryStep;
    private final CarrierAndCarrierDefJoinStep carrierAndCarrierDefJoinStep;
    private final CarrierFilterStep carrierFilterStep;
    private final CarrierSortStep carrierSortStep;
    private final CarrierFilterLastOneStep carrierFilterLastOneStep;

    public CarrierAndCarrierDef execute() throws IOException {

        List<RuleStep<CarrierAndCarrierDef,CarrierAndCarrierDef>> steps = List.of(
                carrierFilterStep,
                carrierSortStep,
                carrierFilterLastOneStep
        );

        List<RuleContext<?>> history = new ArrayList<>(); // 모든 컨텍스트 기록

        // 1. Carrier 데이터 조회
        RuleContext<Carriers> carrierContext = carrierQueryStep.apply(null);
        history.add(carrierContext);

        // 2. CarrierDef 데이터 조회
        RuleContext<CarrierDef> carrierDefContext = carrierDefQueryStep.apply(null);
        history.add(carrierDefContext);

        // 3. 두 데이터 조인
        RuleContext<CarrierAndCarrierDef> carrierDefRuleContext = carrierAndCarrierDefJoinStep.apply(carrierContext,carrierDefContext);
        history.add(carrierDefRuleContext);

        RuleContext<CarrierAndCarrierDef> currentContext = carrierDefRuleContext;

        for (RuleStep<CarrierAndCarrierDef,CarrierAndCarrierDef> step : steps) {
            currentContext = step.apply(currentContext);
            history.add(currentContext);
        }

        //String transactionId = (new Timestamp(System.currentTimeMillis())).toString();
        //historyLogService.saveHistory(transactionId,history);

        // 최종 결과는 context.output.get(0)
        // history는 추후 OpenSearch 저장 또는 Vue 디버깅용으로 사용
        return currentContext.getOutput().get(0);
    }
}
