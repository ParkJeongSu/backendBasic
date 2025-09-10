package kr.co.aim.api.rtd.service;

import kr.co.aim.domain.model.CarrierAndCarrierDef;
import kr.co.aim.domain.model.Carriers;
import kr.co.aim.common.rule.AbstractRuleStep;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CarrierSortStep extends AbstractRuleStep<CarrierAndCarrierDef,CarrierAndCarrierDef> {

    @Override
    protected List<CarrierAndCarrierDef> process(List<CarrierAndCarrierDef> input) {
        return input.stream()
                .sorted(Comparator.comparing(CarrierAndCarrierDef::getCarrierCode)) // 거리 기준 정렬
                .toList();
    }

    @Override
    protected String getDescription() {
        return "Sort: 거리 오름차순";
    }
}
