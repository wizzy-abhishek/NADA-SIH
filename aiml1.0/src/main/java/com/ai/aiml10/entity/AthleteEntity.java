package com.ai.aiml10.entity;

import com.ai.aiml10.enums.Gender;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name = "Athletes")
public class AthleteEntity {

    @Id
    private String athletesID ;

    private String athletesName ;

    private Integer age ;

    @Enumerated(EnumType.STRING)
    private Gender gender ;

    private String mobileNum ;

    private String email ;

    @Enumerated(EnumType.STRING)
    private Sport sport ;

    private Set<String> bloodTestIds;          // List of blood test IDs

    private Set<String> urineTestIds;          // List of urine test IDs

    private String biologicalPassportId;        // ID for the biological passport

    private List<String> anomalyDetails;        // Detailed description of detected anomalies

    private String lastAnalyzedTestId;          // ID of the last test analyzed by the AI

    private boolean everBanned ;

    private boolean permanentBanned ;

    @Enumerated(EnumType.STRING)
    private Status suspicion ;

    public AthleteEntity() {
        this.bloodTestIds = new HashSet<>(5);
        this.urineTestIds = new HashSet<>(5);
        this.anomalyDetails = new ArrayList<>(5);
    }
}
