package ru.isu.dorm.web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.isu.dorm.domain.model.Application;
import ru.isu.dorm.domain.repository.ApplicationRepository;
import ru.isu.dorm.domain.repository.RoomRepository;
import ru.isu.dorm.domain.repository.StudentGroupRepository;
import ru.isu.dorm.domain.repository.StudentRepository;
import ru.isu.dorm.domain.model.StudentGroup;


@Controller
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private StudentRepository studentService;
    
    @Autowired
    private StudentGroupRepository studentGroupService;
    
    @Autowired
    private ApplicationRepository appleService;
    
    @Autowired
    private RoomRepository roomService;
    
    @RequestMapping(value="/all")
    public String findAll(Model model){
        int allStudentsSize = studentService.findAll().size();
        int studentsWithRoomSize = studentService.findWithRooms(new Sort("id")).size();
        String studentReport = Integer.toString(studentsWithRoomSize) + " out of " + Integer.toString(allStudentsSize) + " students are with assigned rooms";
        List <String> studentGroupReports = new ArrayList<>();
        if (studentsWithRoomSize > 0){
            studentReport += ":";
            int sem = 1;
            List<StudentGroup> studentGroups = studentGroupService.findAll();
            for (StudentGroup i: studentGroups){
                int studentsWithRoomFromGroupSize = studentService.findWithRoomsAndGroup(i.getId(), new Sort("id")).size();
                int allStudnetsFromGroupSize = studentService.findByGroupId(i.getId(), new Sort("id")).size();
                String sg = Integer.toString(studentsWithRoomFromGroupSize) + " out of " + Integer.toString(allStudnetsFromGroupSize) + " students from " + i.getName() + " are with assigned rooms";
                if (sem == studentGroups.size()){ sg += "."; }
                else{
                    sg += ";";
                    sem++;
                }
                studentGroupReports.add(sg);
            }
        }
        else{ studentReport += "."; }
        model.addAttribute("studentReport", studentReport);
        model.addAttribute("studentGroupReports", studentGroupReports);
        
        int allApplicationsSize = appleService.findAll().size();
        String applicationReport = Integer.toString(allApplicationsSize) + " applications";
        if (allApplicationsSize > 0){
            int assigningApplicationsSize = appleService.findByType("Assigning").size();
            int evictionApplicationsSize = appleService.findByType("Eviction").size();
            applicationReport += ", where " + assigningApplicationsSize + " applications for assigning and " + evictionApplicationsSize + " applications for eviction";
        }
        applicationReport += ".";
        model.addAttribute("applicationReport", applicationReport);
        
        int allRoomsSize = roomService.findAll().size();
        int fullRoomsSize = roomService.findByIsFull(true, new Sort("id")).size();
        String roomReport = Integer.toString(fullRoomsSize) + " out of " + Integer.toString(allRoomsSize) + " rooms are full";
        if (fullRoomsSize > 0){
            int maleRoomsSize = roomService.findFullGenderRooms("m", new Sort("id")).size();
            int femaleRoomsSize = roomService.findFullGenderRooms("f", new Sort("id")).size();
            roomReport += ", where " + maleRoomsSize + " are male rooms and " + femaleRoomsSize + " are female rooms";
        }
        roomReport += ".";
        model.addAttribute("roomReport", roomReport);
        return "reports";
    }
}
