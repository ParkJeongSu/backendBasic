package kr.co.aim.infra.persistence.mapper;

import kr.co.aim.domain.model.Alarm;
import kr.co.aim.infra.persistence.entity.AlarmEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface AlarmMapper {

    Alarm toDomain(AlarmEntity entity);

    AlarmEntity toEntity(Alarm domain);
}