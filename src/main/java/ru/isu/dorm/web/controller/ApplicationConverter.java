package ru.isu.dorm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.isu.dorm.domain.model.Application;
import ru.isu.dorm.domain.repository.ApplicationRepository;

public class ApplicationConverter implements Converter<Long, Application>{
    
    @Autowired
    private ApplicationRepository appleService;
    
    @Override
    public Application convert(Long id){
        Application apple = appleService.findOne(id);
        return apple;
    }
}
