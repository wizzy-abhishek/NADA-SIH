package com.ai.aiml10.service;

import com.ai.aiml10.dto.AthleteDTO;
import com.ai.aiml10.dto.BiologicalPassportDTO;
import com.ai.aiml10.dto.UrineTestDTO;
import com.ai.aiml10.entity.AthleteEntity;
import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.entity.UrineTestEntity;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.exceptions.DuplicateIdException;
import com.ai.aiml10.exceptions.ResourceNotFoundException;
import com.ai.aiml10.repo.UrineTestRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrineTestService {

    private final UrineTestRepo urineTestRepo ;
    private final ModelMapper modelMapper ;
    private final AthleteService athleteService ;
    private final BiologicalPassportService biologicalPassportService ;


    public UrineTestService(UrineTestRepo urineTestRepo, ModelMapper modelMapper, AthleteService athleteService, BiologicalPassportService biologicalPassportService) {
        this.urineTestRepo = urineTestRepo;
        this.modelMapper = modelMapper;
        this.athleteService = athleteService;
        this.biologicalPassportService = biologicalPassportService;
    }

    public boolean doesAthleteExist(String athleteId){
        return athleteService.doesExist(athleteId) ;
    }

    public boolean doesUrineTestIdExist(String urineTestId){
        return urineTestRepo.findById(urineTestId).isEmpty();
    }

    public UrineTestDTO findUrineTestById(String urineTestId ){
        return modelMapper.map(urineTestRepo.findById(urineTestId).orElseThrow(() -> new ResourceNotFoundException("Urine test with id : " + urineTestId + " unavailable")) , UrineTestDTO.class);
    }


    public UrineTestDTO addNewUrineTest(UrineTestDTO urineTestDTO){

        System.out.println("In addNewUrineTest Service");

        if(!doesAthleteExist(urineTestDTO.getAthleteId())){
            System.out.println("Athlete doesn't exist");
            throw new ResourceNotFoundException("Athlete with id :" + urineTestDTO.getAthleteId() + " unavailable") ;
        }

        if(!doesUrineTestIdExist(urineTestDTO.getTestId())){
            System.out.println("Urine Test ID already exists");
            throw new DuplicateIdException("URINE REPORT WITH ID : "+ urineTestDTO.getTestId() + " ALREADY PRESENT");
        }

        UrineTestEntity urineTestEntity = modelMapper.map(urineTestDTO , UrineTestEntity.class);
        urineTestRepo.save(urineTestEntity);

        AthleteEntity athleteEntity = modelMapper.map(athleteService.findAthleteById(urineTestEntity.getAthleteId()) , AthleteEntity.class);
        athleteEntity.getUrineTestIds().add(urineTestEntity.getTestId());
        athleteService.updateAthleteEntity(modelMapper.map(athleteEntity , AthleteDTO.class));

        BiologicalPassportEntity biologicalPassportEntity = modelMapper.map( biologicalPassportService.getBiologicalPassport(urineTestEntity.getAthleteId()) , BiologicalPassportEntity.class ) ;
        biologicalPassportEntity.getUrineTests().add(urineTestEntity);
        biologicalPassportEntity.setLastUpdated(new Date());
        biologicalPassportService.updateExistingBloodAndUrineList(modelMapper.map(biologicalPassportEntity , BiologicalPassportDTO.class));

        return findUrineTestById(urineTestEntity.getTestId());
    }


    public List<UrineTestDTO> findAllUrineTestOfIndividual(String athleteId) {

        List<UrineTestEntity> listOfAllUrineTest = urineTestRepo.findByAthleteId(athleteId);

        return listOfAllUrineTest.stream()
                .map(urineTestEntity -> modelMapper.map(urineTestEntity , UrineTestDTO.class))
                .collect(Collectors.toList());
    }

    public List<UrineTestDTO> findAllUrineTestByStatus(Status status){
        List<UrineTestEntity> urineTestEntityList = urineTestRepo.findByCondition(status);

        return urineTestEntityList.stream()
                .map(urineTestEntity -> modelMapper.map(urineTestEntity, UrineTestDTO.class))
                .collect(Collectors.toList());
    }
    
}