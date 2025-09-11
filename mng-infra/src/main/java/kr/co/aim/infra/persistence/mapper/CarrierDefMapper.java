package kr.co.aim.infra.persistence.mapper;

import kr.co.aim.domain.model.CarrierDef;
import kr.co.aim.infra.persistence.entity.CarriersDefEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
        // builder = @Builder(disableBuilder = true)  <- 이 라인을 삭제
)
public interface CarrierDefMapper {

    CarrierDef toDomain(CarriersDefEntity entity);

    CarriersDefEntity toEntity(CarrierDef domain);
}