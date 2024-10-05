package com.ai.aiml10.dto;

import com.ai.aiml10.enums.Gender;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@RequiredArgsConstructor
@ToString
public class AthleteDTO {

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

    private List<String> bloodTestIds;              // List of blood test IDs4

    private List<String> urineTestIds;              // List of urine test IDs

    private String biologicalPassportId;            // ID for the biological passport

    private List<String> anomalyDetails;            // Detailed description of detected anomalies

    private String lastAnalyzedTestId;              // ID of the last test analyzed by the AI

    private boolean everBanned ;

    private boolean permanentBanned ;

    private Status suspicion ;



}
