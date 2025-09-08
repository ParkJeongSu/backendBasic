package kr.co.aim.infra.persistence.entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipments")
@Getter
@NoArgsConstructor
public class EquipmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipmentCode;
    private String location;
    private String status;

    // 생성자, getter/setter 생략
}
