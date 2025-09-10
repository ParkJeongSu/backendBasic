package kr.co.aim.api.rtd.service;

import kr.co.aim.common.rule.AbstractRuleStep;
import kr.co.aim.domain.model.CarrierAndCarrierDef;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrierFilterLastOneStep extends AbstractRuleStep<CarrierAndCarrierDef,CarrierAndCarrierDef> {

    @Override
    protected List<CarrierAndCarrierDef> process(List<CarrierAndCarrierDef> input) {

        List<CarrierAndCarrierDef> filtered = new ArrayList<>();
        if(input.size()>0)
        {
            filtered.add(input.get(0));
        }
        return filtered;
        /*
        return input.stream()
                .filter(
                        (c) ->{ !c.isReserved() && !c.isError() }

                )
                .toList();

         */
    }

    @Override
    protected String getDescription() {
        return "Filter: reserved=false, error=false";
    }
}