package ru.isu.dorm.web.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.isu.dorm.domain.model.AutoUser;
import ru.isu.dorm.domain.model.Student;
import ru.isu.dorm.domain.model.Application;
import ru.isu.dorm.domain.repository.AutoUserRepository;
import ru.isu.dorm.domain.repository.StudentRepository;
import ru.isu.dorm.domain.repository.ApplicationRepository;

@Controller
public class HomeController {

    @Autowired
    private AutoUserRepository userService;
    
    @Autowired
    private StudentRepository studentService;
    
    @Autowired
    private ApplicationRepository appleService;

    @RequestMapping(value="/")
    public String startingPage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            return "redirect:/login";
        }
        else{
            return "redirect:/home";
        }
    }
    
    @RequestMapping(value="/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model) {
        AutoUser user = (AutoUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findOne(user.getStudent().getId());
        Application apple = new Application();
        apple.setDate(LocalDate.now());
        apple.setStudent(student);
        if (student.getRoom() == null){
            apple.setType("Assigning");
        }
        else{
            apple.setType("Eviction");
        }
        model.addAttribute("apple", apple);
        model.addAttribute("student", student);
        return "student";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.POST, params = "Send")
    public String sendApplication(@ModelAttribute("apple") Application apple, RedirectAttributes attr) {
        AutoUser user = (AutoUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findOne(user.getStudent().getId());
        student.setApplication(true);
        appleService.save(apple);
        studentService.save(student);
        attr.addFlashAttribute("student", student);
        return "redirect:/profile";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.POST, params = "Cancel")
    public String cancelApplication(RedirectAttributes attr) {
        AutoUser user = (AutoUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.findOne(user.getStudent().getId());
        student.setApplication(false);
        Application apple = appleService.findByStudent(student);
        appleService.delete(apple);
        studentService.save(student);
        attr.addFlashAttribute("student", student);
        return "redirect:/profile";
    }
}
