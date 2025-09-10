package kr.co.aim.api.rtd.service;

import kr.co.aim.common.rule.AbstractRuleStep;
import kr.co.aim.domain.model.Carriers;
import kr.co.aim.domain.repository.CarrierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierQueryStep extends AbstractRuleStep<Void,Carriers> {

    private final CarrierRepository carrierRepository;

    public CarrierQueryStep(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    protected List<Carriers> process(List<Void> input) {
        return carrierRepository.findAll();  // 또는 조건 조회
    }

    @Override
    protected String getDescription() {
        return "Query: 모든 Carrier 조회";
    }
}