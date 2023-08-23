package ru.isu.dorm.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isu.dorm.domain.model.Building;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.repository.BuildingRepository;
import ru.isu.dorm.domain.repository.RoomRepository;

@Controller
@RequestMapping("/dorms")
public class BuildingController {
    
    @Autowired
    private BuildingRepository buildingService;
    
    @Autowired
    private RoomRepository roomService;
    
    @RequestMapping(value="/all")
    public String findAll(Model model){
        List <Building> buildings = buildingService.findAll();
        model.addAttribute("buildings", buildings);
        return "buildings";
    }
    
    @RequestMapping(value="/{buildingId}")
    public String getBuilding(Model model, @PathVariable("buildingId") Building building){
        if (building != null){
            model.addAttribute("building", building);
            
            List <Room> rooms = buildingService.findAllRooms(building.getId(), new Sort("id"));
            List <Pair <Room, String>> fullness = new ArrayList<Pair <Room, String>>();
            for (int i = 0; i < rooms.size(); i++){
                Room room = rooms.get(i);
                List <Student> students = roomService.findStudents(room.getId(), new Sort("id"));
                if (students != null)
                    fullness.add(Pair.of(room, students.size() + " / " + room.getSize()));
                else
                    fullness.add(Pair.of(room, "0 / " + room.getSize()));
            }
            model.addAttribute("fullness", fullness);
            return "building";
        }
        else
            return "error";
    }
}
