package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "carrierdef")
@NoArgsConstructor
public class CarriersDefEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carrierDefName;
    private String carrierType;
    private String carrierType2;
}
