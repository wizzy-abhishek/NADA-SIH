package com.ai.aiml10.service;

import com.ai.aiml10.dto.AthleteDTO;
import com.ai.aiml10.entity.AthleteEntity;
import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.enums.Sport;
import com.ai.aiml10.enums.Status;
import com.ai.aiml10.exceptions.DuplicateIdException;
import com.ai.aiml10.exceptions.ResourceNotFoundException;
import com.ai.aiml10.repo.AthleteRepo;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AthleteService {

    private final AthleteRepo athleteRepo;
    private final ModelMapper modelMapper;
    private final BiologicalPassportService biologicalPassportService ;
    protected final static String CACHE_NAME = "athlete" ;

    public AthleteService(AthleteRepo athleteRepo, ModelMapper modelMapper, BiologicalPassportService biologicalPassportService) {
        this.athleteRepo = athleteRepo;
        this.modelMapper = modelMapper;
        this.biologicalPassportService = biologicalPassportService;
    }

    public boolean doesExist(String athleteId){

        AthleteEntity athleteEntity = athleteRepo.findById(athleteId).orElse(null);

        if (athleteEntity != null){
            return true ;
        }
        return false ;
    }


    @Transactional
    public AthleteDTO addNewAthlete(AthleteDTO athleteDTO) {

        if (doesExist(athleteDTO.getAthletesID())){
            throw new DuplicateIdException("Athlete with id :" + athleteDTO.getAthletesID() + " available");
        }

        AthleteEntity athleteEntity = modelMapper.map(athleteDTO, AthleteEntity.class);
        athleteEntity.setAthletesID(athleteEntity.getAthletesID());
        athleteRepo.save(athleteEntity);

        BiologicalPassportEntity biologicalPassportEntity = new BiologicalPassportEntity();
        biologicalPassportEntity.setPassportID(athleteEntity.getAthletesID());
        biologicalPassportEntity.setAthletesID(athleteEntity.getAthletesID());
        biologicalPassportEntity.setStartDate(LocalDateTime.now());
        biologicalPassportEntity.setSuspicious(false);

        biologicalPassportService.addNewBiologicalPassport(biologicalPassportEntity);

        athleteEntity.setBiologicalPassportId(biologicalPassportEntity.getPassportID());
        AthleteEntity savedEntity = athleteRepo.save(athleteEntity);

        return modelMapper.map(savedEntity, AthleteDTO.class);  // Map the saved entity to DTO
    }

    @Transactional
    public List<AthleteDTO> getAllAthletes(){
        List<AthleteEntity> listOfAthletesEntity = athleteRepo.findAll();

        return listOfAthletesEntity.stream()
                .map(athleteEntity -> modelMapper.map(athleteEntity, AthleteDTO.class)) //mapping for individual entity
                .collect(Collectors.toList());
    }

    @Transactional
    @CachePut(cacheNames = CACHE_NAME , key = "#athletesID")
    public AthleteDTO updatePartialInfoOfAthlete(String athletesID, Map<String, Object> updateDetails) {

        AthleteEntity athlete = athleteRepo.findById(athletesID)
                .orElseThrow(() -> new ResourceNotFoundException("Athlete not found with id : " + athletesID));

        updateDetails.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(AthleteEntity.class, key);
            if (field != null) {
                field.setAccessible(true);

                // Check if the field is the 'sport' field
                if ("sport".equals(key)) {
                    // Convert the string to the enum type Sport
                    Sport sportEnum = Sport.valueOf((String) value);
                    ReflectionUtils.setField(field, athlete, sportEnum);
                } else {
                    ReflectionUtils.setField(field, athlete, value);
                }
            }
        });

        return modelMapper.map(athleteRepo.save(athlete) , AthleteDTO.class);
    }

    @Transactional
    @Cacheable(cacheNames = CACHE_NAME , key = "#athletesID")
    public AthleteDTO findAthleteById(String athletesID){

        return modelMapper.map(athleteRepo.findById(athletesID).orElseThrow(() ->new ResourceNotFoundException("Athlete not found with id : " + athletesID) ) , AthleteDTO.class);
    }

    @Transactional
    @CachePut(cacheNames = CACHE_NAME , key = "#result.athletesID")
    public AthleteDTO updateAthleteEntity(AthleteDTO athleteDTO){
        AthleteEntity athlete = athleteRepo.save(modelMapper.map(athleteDTO , AthleteEntity.class));
        return modelMapper.map(athlete , AthleteDTO.class);
    }

    @Transactional
    public List<AthleteDTO> findAllByStatus(Status status){

        List<AthleteEntity> athleteEntityList = athleteRepo.findBySuspicion(status);

        return athleteEntityList.stream()
                .map(athleteEntity -> modelMapper.map(athleteEntity , AthleteDTO.class))
                .collect(Collectors.toList());

    }

}