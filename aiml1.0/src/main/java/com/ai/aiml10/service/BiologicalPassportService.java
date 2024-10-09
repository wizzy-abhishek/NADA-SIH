package com.ai.aiml10.service;

import com.ai.aiml10.dto.BiologicalPassportDTO;
import com.ai.aiml10.entity.BiologicalPassportEntity;
import com.ai.aiml10.exceptions.DuplicateIdException;
import com.ai.aiml10.repo.BiologicalPassportRepo;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Service
public class BiologicalPassportService {

    protected final static String ABP_CACHE = "BIOLOGICAL_PASSPORT_CACHE" ;

    private final BiologicalPassportRepo biologicalPassportRepo ;
    private final ModelMapper modelMapper ;

    public BiologicalPassportService(BiologicalPassportRepo biologicalPassportRepo, ModelMapper modelMapper) {
        this.biologicalPassportRepo = biologicalPassportRepo;
        this.modelMapper = modelMapper;
    }

    public boolean doesBiologicalPassportExists(String biologicalPassportId){
        return biologicalPassportRepo.existsById(biologicalPassportId);
    }

    @Transactional
    public BiologicalPassportDTO addNewBiologicalPassport( BiologicalPassportEntity biologicalPassportEntity){

        boolean doesExist = doesBiologicalPassportExists(biologicalPassportEntity.getPassportID());

        if(doesExist){
            throw new DuplicateIdException("Biological passport id : " + biologicalPassportEntity.getPassportID() + "already present");
        }

        BiologicalPassportEntity savedBiologicalPassportEntity = biologicalPassportRepo.save(biologicalPassportEntity);

        return  modelMapper.map(savedBiologicalPassportEntity, BiologicalPassportDTO.class);
    }

    @Cacheable(cacheNames = ABP_CACHE , key = "#passportID")
    public BiologicalPassportDTO getBiologicalPassport(String passportID){
        BiologicalPassportEntity getBiologicalPassport = biologicalPassportRepo.findById(passportID).orElse(null);
        return modelMapper.map(getBiologicalPassport , BiologicalPassportDTO.class) ;
    }

    public BiologicalPassportDTO updateBiologicalPassportPartially(String passportId , Map<String, Object> updateDetails){

        boolean doesExist = doesBiologicalPassportExists(passportId);

        if(!doesExist){
            return null ;
        }

        BiologicalPassportEntity biologicalPassportEntity = biologicalPassportRepo.findById(passportId).get();

        updateDetails.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(BiologicalPassportEntity.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, biologicalPassportEntity, value);
            }
        });
        return modelMapper.map(biologicalPassportRepo.save(biologicalPassportEntity) , BiologicalPassportDTO.class);
    }

    @Transactional
    @CachePut(cacheNames = ABP_CACHE , key = "#result.passportID")
    public BiologicalPassportDTO updateExistingBloodAndUrineList(BiologicalPassportDTO biologicalPassportDTO){

        BiologicalPassportEntity biologicalPassportEntity = modelMapper.map(biologicalPassportDTO , BiologicalPassportEntity.class);
        biologicalPassportRepo.save(biologicalPassportEntity);
        return modelMapper.map(biologicalPassportRepo.findById(biologicalPassportDTO.getPassportID()) , BiologicalPassportDTO.class) ;
    }
}