package com.school.system.controller;

import javax.validation.Valid;

import com.school.system.model.Student;
import com.school.system.model.User;
import com.school.system.repository.StudentsRepository;
import com.school.system.repository.UserRepository;
import com.school.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository repository;

    @Autowired
    StudentsRepository studentsRepository;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    // Register Student
    @RequestMapping(value="/registerstudent", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registerstudent");
        return modelAndView;
    }

    @RequestMapping(value ="/registerstudent", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registerstudent");
        } else {
            userService.saveUserStudent(user);
            modelAndView.addObject("successMessage", "Student has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registerstudent");
        }
        return modelAndView;
    }
/*
    @RequestMapping(value="/registerteacher", method = RequestMethod.GET)
    public ModelAndView registrationT(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registerteacher");
        return modelAndView;
    }

    @RequestMapping(value ="/registerteacher", method = RequestMethod.POST)
    public ModelAndView createNewUserT(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registerteacher");
        } else {
            userService.saveUserTeacher(user);
            modelAndView.addObject("successMessage", "Teacher has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registerteacher");
        }
        return modelAndView;
    }
    */


    @RequestMapping(value="/student/studentlist", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());

            String result = "<div><h1>Studentlist from class " + user.getClasses() + "</h1></div>";
            for(Student oneStudent : studentsRepository.findAll()){
                int userClass = 0;

                if(user.getClasses().equals("1a")){
                    userClass = 1;
                } else if (user.getClasses().equals("1b")){
                    userClass = 2;
                } else if (user.getClasses().equals("2a")){
                    userClass = 3;
                } else if (user.getClasses().equals("2b")){
                    userClass = 4;
                } else if (user.getClasses().equals("3a")){
                    userClass = 5;
                } else if (user.getClasses().equals("3b")){
                    userClass = 6;
                } else if (user.getClasses().equals("4a")){
                    userClass = 7;
                } else if (user.getClasses().equals("4b")){
                    userClass = 8;
                }

                if(oneStudent.getFk_class_id() == userClass)
                result += "<div><h3>" + oneStudent.toString() + "</h3></div>";
            }

            modelAndView.addObject("adminMessage",result);

        modelAndView.setViewName("teacher/studentlist");
        return modelAndView;
    }
}