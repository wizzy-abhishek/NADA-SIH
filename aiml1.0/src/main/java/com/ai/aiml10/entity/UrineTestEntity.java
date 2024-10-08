package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "UrineTest")
public class UrineTestEntity {

    @Id
    private String testID;          // Unique identifier for the test

/*    @ManyToOne()
    @JoinColumn(name = "biologicalPassport" , nullable = false )
    private BiologicalPassportEntity biologicalPassport;*/

    @Column(updatable = false )
    private String athletesID;       // Reference to the Athlete

    private Date testDate;          // Date of the test

    private double specificGravity; // Urine specific gravity Normal Range: 1.005 to 1.030

    private double creatinine;      // Creatinine level (mg/dL) Normal Range 0.5 to 2.5 mg/dL in urine (varies based on hydration level)

    private double tEratio;         // Testosterone to Epitestosterone Normal ratio Male 1:1 and Female sometimes it might increase due ro PCOS

    private boolean nandrolone;     // Presence of nandrolone (positive/negative)

    private boolean stimulants;     // Presence of stimulants (positive/negative)

    @Enumerated(EnumType.STRING)
    private Status condition;       // Result of the doping test (POSITIVE/NEGATIVE/SUSPICIOUS/UNDER_INVESTIGATION)


}
