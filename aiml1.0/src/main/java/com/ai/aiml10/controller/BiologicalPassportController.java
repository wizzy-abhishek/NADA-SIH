package com.ai.aiml10.controller;

import com.ai.aiml10.dto.BiologicalPassportDTO;
import com.ai.aiml10.service.BiologicalPassportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biologicalPassport")
public class BiologicalPassportController {

    private final BiologicalPassportService biologicalPassportService ;

    public BiologicalPassportController(BiologicalPassportService biologicalPassportService) {
        this.biologicalPassportService = biologicalPassportService;
    }

    @GetMapping("/{biologicalPassportId}")
    public BiologicalPassportDTO getParticularBiologicalPassport(@PathVariable String biologicalPassportId){
        return biologicalPassportService.getBiologicalPassport(biologicalPassportId);
    }

}
