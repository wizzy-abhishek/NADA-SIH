package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Gender;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AthleteDTO implements Serializable {

    private String athletesID ;

    @NotBlank
    @Size(min = 2 , message = "Name must be of 2 letters at least")
    private String athletesName ;

    @Min(value = 5, message = "Age must be greater than or equal to 5")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private Integer age ;

    @NotNull(message = "Gender is required")
    private Gender gender ;

    @NotNull(message = "Sport is required")
    private Sport sport ;

    @NotBlank(message = "Mobile number is required")
    private String mobileNum ;

    @Email(message = "Email problem")
    private String email ;

    private Set<String> bloodTestIds;              // List of blood test IDs4

    private Set<String> urineTestIds;              // List of urine test IDs

    private String biologicalPassportId;            // ID for the biological passport

    private List<String> anomalyDetails;            // Detailed description of detected anomalies

    private String lastAnalyzedTestId;              // ID of the last test analyzed by the AI

    private boolean everBanned ;

    private boolean permanentBanned ;

    private Status suspicion ;

    public AthleteDTO() {
        this.bloodTestIds = new HashSet<>(5);
        this.urineTestIds = new HashSet<>(5);
        this.anomalyDetails = new ArrayList<>(5);
    }
}
