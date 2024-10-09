package com.ai.aiml10.controller;

import com.ai.aiml10.dto.AthleteDTO;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.service.AthleteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/athlete")
public class AthleteController {

    private final AthleteService athleteService ;

    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @PostMapping("/add")
    public ResponseEntity<AthleteDTO> addAthlete(@RequestBody @Valid AthleteDTO athleteDTO){
        AthleteDTO athleteDTOSaved = athleteService.addNewAthlete(athleteDTO);
        return new ResponseEntity<>(athleteDTOSaved , HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<List<AthleteDTO>>getAllAthletes(){
        return ResponseEntity.ok(athleteService.getAllAthletes());
    }

    @GetMapping("/view/{athleteId}")
    public ResponseEntity<AthleteDTO> getAthleteById(@PathVariable("athleteId") String athleteId){
        return ResponseEntity.ok(athleteService.findAthleteById(athleteId));
    }

    @PatchMapping("/{athletesID}")
    public ResponseEntity<AthleteDTO> updatePartially(@PathVariable String athletesID ,  @RequestBody Map<String, Object> updateDetails){
        return ResponseEntity.ok(athleteService.updatePartialInfoOfAthlete(athletesID , updateDetails)) ;
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AthleteDTO>> getByStatus(@PathVariable("status") Status status){
        return ResponseEntity.ok(athleteService.findAllByStatus(status));
    }
}
