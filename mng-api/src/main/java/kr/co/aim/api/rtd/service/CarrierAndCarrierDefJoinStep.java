package kr.co.aim.api.rtd.service;

import kr.co.aim.common.rule.AbstractJoinRuleStep;
import kr.co.aim.common.rule.RuleContext;
import kr.co.aim.domain.model.CarrierAndCarrierDef;
import kr.co.aim.domain.model.CarrierDef;
import kr.co.aim.domain.model.Carriers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrierAndCarrierDefJoinStep extends AbstractJoinRuleStep<Carriers,CarrierDef,CarrierAndCarrierDef> {
    @Override
    protected List<CarrierAndCarrierDef> process(RuleContext<Carriers> left, RuleContext<CarrierDef> right) {

        List<Carriers> carriers =  left.getOutput();
        List<CarrierDef> carrierDefs = right.getOutput();

        List<CarrierAndCarrierDef> joinedList = carriers.stream()
                .flatMap(
                        carrier -> carrierDefs.stream()
                                .filter(carrierdef ->  carrier.getCarrierDefId().equals(carrierdef.getId()))
                                .map(
                                        carrierDef -> CarrierAndCarrierDef.builder()
                                                .carrierCode(carrier.getCarrierCode())
                                                .carrierDefName(carrierDef.getCarrierDefName())
                                                .carrierType(carrierDef.getCarrierType())
                                                .carrierType2(carrierDef.getCarrierType2())
                                                .error(carrier.isError())
                                                .reserved(carrier.isReserved())
                                                .build()
                                )
                ).collect(Collectors.toList());

//        List<CarrierAndCarrierDef> result = new ArrayList<>();
//        for(Carriers carrier : carriers)
//        {
//            for(CarrierDef carrierDef : carrierDefs){
//                if(carrier.getCarrierDefId().equals(carrierDef.getId()))
//                {
//                    CarrierAndCarrierDef t = CarrierAndCarrierDef.builder()
//                            .carrierCode(carrier.getCarrierCode())
//                            .carrierDefName(carrierDef.getCarrierDefName())
//                            .carrierType(carrierDef.getCarrierType())
//                            .carrierType2(carrierDef.getCarrierType2())
//                            .error(carrier.isError())
//                            .reserved(carrier.isReserved())
//                            .build();
//                    result.add(t);
//                }
//            }
//        }
        return joinedList;
    }

    @Override
    protected String getDescription() {
        return "Join : Carriers, CarrierDef Inner Join";
    }
}