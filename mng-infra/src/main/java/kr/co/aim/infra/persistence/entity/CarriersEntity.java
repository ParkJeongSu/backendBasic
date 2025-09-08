package kr.co.aim.infra.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "carriers")
@NoArgsConstructor
public class CarriersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carrierCode;
    private boolean reserved;
    private boolean error;


}
