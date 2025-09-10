package kr.co.aim.api.rtd.service;

import kr.co.aim.domain.model.CarrierAndCarrierDef;
import kr.co.aim.domain.model.Carriers;
import kr.co.aim.common.rule.AbstractRuleStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrierFilterStep extends AbstractRuleStep<CarrierAndCarrierDef,CarrierAndCarrierDef> {

    @Override
    protected List<CarrierAndCarrierDef> process(List<CarrierAndCarrierDef> input) {

        List<CarrierAndCarrierDef> filtered = new ArrayList<>();
        for (CarrierAndCarrierDef c : input) {
            if (!c.isReserved() && !c.isError()) {
                filtered.add(c);
            }
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