package org.deliverygo.global.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.deliverygo.global.constants.DeliveryStatus;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Delivery extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    private Long orderId;

    private Long riderId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;
}
