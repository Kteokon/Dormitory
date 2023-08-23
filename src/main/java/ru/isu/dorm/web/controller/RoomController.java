package ru.isu.dorm.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.repository.RoomRepository;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    
    @Autowired
    private RoomRepository roomService;
    
    @RequestMapping(value="/{roomId}")
    public String getRoom(Model model, @PathVariable("roomId") Room room){
        if (room != null){
            model.addAttribute("room", room);
            List <Student> students = roomService.findStudents(room.getId(), new Sort("id"));
            model.addAttribute("students", students);
            return "room";
        }
        else
            return "error";
    }
}
