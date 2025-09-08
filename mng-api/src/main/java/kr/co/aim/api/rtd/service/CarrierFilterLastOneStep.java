package kr.co.aim.api.rtd.service;

import kr.co.aim.domain.model.Carriers;
import kr.co.aim.common.rule.AbstractRuleStep;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrierFilterLastOneStep extends AbstractRuleStep<Carriers> {

    @Override
    protected List<Carriers> process(List<Carriers> input) {

        List<Carriers> filtered = new ArrayList<>();
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