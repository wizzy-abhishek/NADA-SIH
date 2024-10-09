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
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ai.aiml10.service.AthleteService.CACHE_NAME;

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

    @Transactional
    public UrineTestDTO addNewUrineTest(UrineTestDTO urineTestDTO){

        if(!doesAthleteExist(urineTestDTO.getAthletesID())){
            throw new ResourceNotFoundException("Athlete with id :" + urineTestDTO.getAthletesID() + " unavailable") ;
        }

        if(!doesUrineTestIdExist(urineTestDTO.getTestID())){
            throw new DuplicateIdException("URINE REPORT WITH ID : "+ urineTestDTO.getTestID() + " ALREADY PRESENT");
        }

        UrineTestEntity urineTestEntity = modelMapper.map(urineTestDTO , UrineTestEntity.class);
        urineTestRepo.save(urineTestEntity);

        AthleteEntity athleteEntity = modelMapper.map(athleteService.findAthleteById(urineTestEntity.getAthletesID()) , AthleteEntity.class);
        athleteEntity.getUrineTestIds().add(urineTestEntity.getTestID());
        athleteService.updateAthleteEntity(modelMapper.map(athleteEntity , AthleteDTO.class));

        BiologicalPassportEntity biologicalPassportEntity = modelMapper.map( biologicalPassportService.getBiologicalPassport(urineTestEntity.getAthletesID()) , BiologicalPassportEntity.class ) ;
        biologicalPassportEntity.getUrineTests().add(urineTestEntity);
        biologicalPassportEntity.setLastUpdated(new Date());
        biologicalPassportService.updateExistingBloodAndUrineList(modelMapper.map(biologicalPassportEntity , BiologicalPassportDTO.class));

        return findUrineTestById(urineTestEntity.getTestID());
    }

    @Transactional
    public List<UrineTestDTO> findAllUrineTestOfIndividual(String athleteId) {

        List<UrineTestEntity> listOfAllUrineTest = urineTestRepo.findByAthletesID(athleteId);

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