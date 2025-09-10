package kr.co.aim.domain.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED) // JPA Entity 등을 위한 기본 생성자
@AllArgsConstructor
@ToString
@Builder
public class CarrierAndCarrierDef {

    private Long id;
    private String carrierCode;
    private boolean reserved;
    private boolean error;
    private String carrierDefName;
    private String carrierType;
    private String carrierType2;
}
