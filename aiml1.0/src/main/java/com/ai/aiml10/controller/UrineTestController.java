package com.ai.aiml10.controller;

import com.ai.aiml10.dto.UrineTestDTO;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.service.UrineTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urineTest")
public class UrineTestController {

    private final UrineTestService urineTestService ;

    public UrineTestController(UrineTestService urineTestService) {
        this.urineTestService = urineTestService;
    }

    @GetMapping("/athlete/{athleteId}")
    public ResponseEntity<List<UrineTestDTO>> findAllUrineTestOfIndividualAthlete(@PathVariable String athleteId){
        return ResponseEntity.ok(urineTestService.findAllUrineTestOfIndividual(athleteId));
    }

    @GetMapping("/{urineTestId}")
    public ResponseEntity<UrineTestDTO> findParticularUrineTest(@PathVariable String urineTestId){
        return ResponseEntity.ok(urineTestService.findUrineTestById(urineTestId));
    }

    @PostMapping
    public ResponseEntity<UrineTestDTO> addUrineTestRequest(@RequestBody UrineTestDTO urineTestDTO ){
        return ResponseEntity.ok(urineTestService.addNewUrineTest(urineTestDTO));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<UrineTestDTO>> getUrineTestByStatus(@PathVariable("status")Status status){
        return ResponseEntity.ok(urineTestService.findAllUrineTestByStatus(status));
    }
}
