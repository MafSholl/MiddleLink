package com.fs.middlelink.drone.models;

import com.fs.middlelink.drone.models.enums.Model;
import com.fs.middlelink.drone.models.enums.State;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class Drone {
//    @Id
//    @Column(length = 100)
    @Size(min = 1, max = 100)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long serialNumber;
    private Model model;
    private double weightLimit;
    private int batteryCapacity;
    private State state;
}
