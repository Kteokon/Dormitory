package ru.isu.dorm.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.isu.dorm.domain.model.Application;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.repository.ApplicationRepository;
import ru.isu.dorm.domain.repository.RoomRepository;
import ru.isu.dorm.domain.repository.StudentRepository;


@Controller
@RequestMapping("/applications")
public class ApplicationController {
    
    @Autowired
    private ApplicationRepository appleService;
    
    @Autowired
    private StudentRepository studentService;
    
    @Autowired
    private RoomRepository roomService;
    
    @RequestMapping(value = "/{appleId}")
    public String getStudent(Model model, @PathVariable("appleId") Application apple){
        if (apple != null){
            model.addAttribute("apple", apple);
            return "application";
        }
        else
            return "error";
    }
    
    @RequestMapping(value="/all")
    public String findAll(Model model){
        List <Application> apples = appleService.findAll();
        model.addAttribute("apples", apples);
        return "applications";
    }
    
    @RequestMapping(value = "/{appleId}", method = RequestMethod.POST, params = "AcceptAssign")
    public String acceptAssign(@PathVariable("appleId") Application apple, Model model, RedirectAttributes attr){
        Student student = appleService.findStudent(apple.getId(), new Sort("id"));
        List <Room> rooms = roomService.findGenderRooms(student.getGender(), new Sort("id"));
        if (!rooms.isEmpty()){
            Room room = rooms.get(0);
            if (room.getGender().equals("i"))
                room.setGender(student.getGender());
            student.setRoom(room);
            List <Student> students = studentService.findRoomsStudents(room.getId(), new Sort("id"));
            students.add(student);
            if (students.size() == room.getSize())
                room.setIsFull(true);
            appleService.delete(apple);
            student.setApplication(false);
            studentService.save(student);
            roomService.save(room);
            return "redirect:/applications/all";
        }
        else{
            boolean isFree = false;
            model.addAttribute("isFree", isFree);
            model.addAttribute("apple", apple);
            return "application";
        }
    }
    
    @RequestMapping(value = "/{appleId}", method = RequestMethod.POST, params = "AcceptEvict")
    public String acceptEvict(@PathVariable("appleId") Application apple, Model model, RedirectAttributes attr){
        Student student = appleService.findStudent(apple.getId(), new Sort("id"));
        Room room = studentService.findRoom(student.getId(), new Sort("id"));
        if (room.getIsFull())
            room.setIsFull(false);
        List <Student> students = studentService.findRoomsStudents(room.getId(), new Sort("id"));
        if (students.size() == 1)
            room.setGender("i");
        student.setRoom(null);
        student.setApplication(false);
        appleService.delete(apple);
        roomService.save(room);
        studentService.save(student);
        return "redirect:/applications/all";
    }
    
    @RequestMapping(value = "/{appleId}", method = RequestMethod.POST, params = "Decline")
    public String decline(@PathVariable("appleId") Application apple){
        Student student = appleService.findStudent(apple.getId(), new Sort("id"));
        student.setApplication(false);
        appleService.delete(apple);
        studentService.save(student);
        return "redirect:/applications/all";
    }
}
