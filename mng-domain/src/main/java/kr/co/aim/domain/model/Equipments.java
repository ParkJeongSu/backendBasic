package kr.co.aim.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
@ToString
public class Equipments {
    private Long id;
    private String equipmentCode;
    private String location;
    private String status;
}
