package kr.co.aim.api.rtd.service;

import kr.co.aim.common.rule.AbstractRuleStep;
import kr.co.aim.domain.model.CarrierDef;
import kr.co.aim.domain.repository.CarrierDefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrierDefQueryStep extends AbstractRuleStep<Void, CarrierDef> {

    private final CarrierDefRepository carrierDefRepository;

    @Override
    protected List<CarrierDef> process(List<Void> input)
    {
        return carrierDefRepository.findAll();  // 또는 조건 조회
    }

    @Override
    protected String getDescription() {
        return "Query: 모든 CarrierDef 조회";
    }
}