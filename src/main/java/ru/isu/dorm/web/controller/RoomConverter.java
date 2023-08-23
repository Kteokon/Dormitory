package ru.isu.dorm.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.repository.RoomRepository;

public class RoomConverter implements Converter<Long, Room>{
    
    @Autowired
    private RoomRepository roomService;
    
    @Override
    public Room convert(Long id){
        Room room  = roomService.findOne(id);
        return room;
    }
}
