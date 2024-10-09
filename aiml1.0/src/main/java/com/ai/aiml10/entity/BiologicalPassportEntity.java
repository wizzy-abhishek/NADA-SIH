package com.ai.aiml10.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "BiologicalPassport")
public class BiologicalPassportEntity {

    @Id
    private String passportID;    // Unique identifier for the passport

    private String athletesID;     // Reference to the Athlete

    @OneToMany( fetch = FetchType.EAGER)
    @JoinColumn(name = "bloodTests")
    private Set<BloodTestEntity> bloodTests;  // List of blood test results over time

    @OneToMany( fetch = FetchType.EAGER)
    @JoinColumn(name = "urineTests")
    private Set<UrineTestEntity> urineTests;  // List of urine test results over time

    private LocalDateTime startDate;       // Start date of monitoring

    private Date lastUpdated;     // Last update date

    private boolean suspicious;   // Whether the passport flags any suspicious patterns

    public BiologicalPassportEntity() {
        this.bloodTests = new HashSet<>(5);
        this.urineTests = new HashSet<>(5);
    }

}
