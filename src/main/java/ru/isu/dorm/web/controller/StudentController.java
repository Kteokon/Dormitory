package ru.isu.dorm.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.model.Room;
import ru.isu.dorm.domain.model.Application;
import ru.isu.dorm.domain.model.StudentGroup;
import ru.isu.dorm.domain.repository.ApplicationRepository;
import ru.isu.dorm.domain.repository.StudentRepository;
import ru.isu.dorm.domain.repository.RoomRepository;
import ru.isu.dorm.domain.repository.StudentGroupRepository;

@Controller
@RequestMapping("/students")
public class StudentController{
    
    @Autowired
    private StudentRepository studentService;
    
    @Autowired
    private RoomRepository roomService;
    
    @Autowired
    private ApplicationRepository appleService;
    
    @Autowired
    private StudentGroupRepository groupService;
    
    @RequestMapping(value = "/{studentId}")
    public String getStudent(Model model, @PathVariable("studentId") Student student){
        if (student != null){
            model.addAttribute("student", student);
            return "student";
        }
        else
            return "error";
    }
    
    @RequestMapping(value = {"/all", "/all/search"})
    public String findAll(Student student, Model model, String keyword, String assigne, Long studentGroupId){
        List <Student> students;
        Sort nameSort = new Sort(Sort.Direction.ASC, "name");
        
        if(keyword == null){
            if (assigne == null){
                if (studentGroupId == null || studentGroupId == 0){
                    students = studentService.findAll(nameSort); // Все
                }
                else{
                    students = studentService.findByGroupId(studentGroupId, nameSort); // Определённая группа
                }
            }
            else{
                if (studentGroupId == null || studentGroupId == 0){
                    students = studentService.findWithRooms(nameSort); // Заселённые студенты
                }
                else{
                    students = studentService.findWithRoomsAndGroup(studentGroupId, nameSort); // Заселённые студенты с группы
                }
            }
        }
        else{
            if (assigne == null){
                if (studentGroupId == null || studentGroupId == 0){
                    students = studentService.findByKeyword(keyword, nameSort); // Поиск по имени
                }
                else{
                    students = studentService.findByKeywordAndGroup(keyword, studentGroupId, nameSort); // Поиск по имени с группы
                }
            }
            else{
                if (studentGroupId == null || studentGroupId == 0){
                    students = studentService.findByKeywordWithRooms(keyword, nameSort); // Поиск по имени, заселённые студенты
                }
                else{
                    students = studentService.findByKeywordWithRoomsAndGroup(keyword, studentGroupId, nameSort);  //Поиск по имени, заселённые студенты с группы
                }
            }
        }
        
        List <StudentGroup> groups = groupService.findAll();
        model.addAttribute("students", students);
        model.addAttribute("groups", groups);
        return "students";
    }
    
    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, params = "Settle")
    public String addRoom(@PathVariable("studentId") Student student, Model model, RedirectAttributes attr){
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
            if (student.isApplication()){
                student.setApplication(false);
                Application apple = appleService.findByStudent(student);
                appleService.delete(apple);
            }
            studentService.save(student);
            roomService.save(room);
            return "redirect:/rooms/" + student.getRoom().getId();
        }
        else{
            boolean isFree = false;
            model.addAttribute("isFree", isFree);
            model.addAttribute("student", student);
            return "student";
        }
    }
    
    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, params = "Unsettle")
    public String deleteRoom(@PathVariable("studentId") Student student){
        Room room = studentService.findRoom(student.getId(), new Sort("id"));
        if (room.getIsFull())
            room.setIsFull(false);
        List <Student> students = studentService.findRoomsStudents(room.getId(), new Sort("id"));
        if (students.size() == 1)
            room.setGender("i");
        student.setRoom(null);
        if (student.isApplication()){
            student.setApplication(false);
            Application apple = appleService.findByStudent(student);
            appleService.delete(apple);
        }
        roomService.save(room);
        studentService.save(student);
        return "redirect:/rooms/" + room.getId();
    }
}
