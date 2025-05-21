package org.deliverygo.global.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.deliverygo.global.constants.RiderStatus;

@Entity
@Getter
@NoArgsConstructor
public class Rider extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rider_id")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    private RiderStatus status;
}
