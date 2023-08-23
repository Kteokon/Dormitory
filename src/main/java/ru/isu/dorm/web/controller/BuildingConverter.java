package ru.isu.dorm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.isu.dorm.domain.model.Building;
import ru.isu.dorm.domain.repository.BuildingRepository;

public class BuildingConverter implements Converter<Long, Building>{
    
    @Autowired
    private BuildingRepository buildingService;
    
    @Override
    public Building convert(Long id){
        Building building = buildingService.findOne(id);
        return building;
    }
}
