package kr.co.aim.api.rtd.service;

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
    private final CarrierFilterStep carrierFilterStep;
    private final CarrierSortStep carrierSortStep;
    private final CarrierFilterLastOneStep carrierFilterLastOneStep;



    public Carriers execute() throws IOException {

        List<RuleStep<Carriers>> steps = List.of(
                carrierQueryStep,
                carrierFilterStep,
                carrierSortStep,
                carrierFilterLastOneStep
        );

        RuleContext<Carriers> context = new RuleContext<>();
        List<RuleContext<Carriers>> history = new ArrayList<>();

        for (RuleStep<Carriers> step : steps) {
            context = step.apply(context);
            history.add(context);
        }

        //String transactionId = (new Timestamp(System.currentTimeMillis())).toString();
        //historyLogService.saveHistory(transactionId,history);

        // 최종 결과는 context.output.get(0)
        // history는 추후 OpenSearch 저장 또는 Vue 디버깅용으로 사용
        return context.getOutput().get(0);
    }
}
