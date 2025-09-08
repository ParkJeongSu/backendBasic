package kr.co.aim.api.rtd.service;

import kr.co.aim.domain.model.Carriers;
import kr.co.aim.domain.repository.CarrierRepository;
import kr.co.aim.common.rule.AbstractRuleStep;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrierQueryStep extends AbstractRuleStep<Carriers> {

    private final CarrierRepository carrierRepository;

    public CarrierQueryStep(CarrierRepository carrierRepository) {
        this.carrierRepository = carrierRepository;
    }

    @Override
    protected List<Carriers> process(List<Carriers> input) {
        return carrierRepository.findAll();  // 또는 조건 조회
    }

    @Override
    protected String getDescription() {
        return "Query: 모든 Carrier 조회";
    }
}