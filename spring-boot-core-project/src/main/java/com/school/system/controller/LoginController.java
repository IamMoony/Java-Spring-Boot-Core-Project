package com.school.system.controller;

import javax.validation.Valid;

import com.school.system.model.User;
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
                            "There is already a user registered with the email provided");
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


    @RequestMapping(value="/student/classlist", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName());

        if(user.getRole() == 1){
            String result = "";
            for(User users : repository.findAll()){
                if(users.getRole() == 2){
                    result += "<div>" + users.toString() + "</div>";
                }
            }
            modelAndView.addObject("adminMessage",result);
        }
        modelAndView.setViewName("teacher/studentlist");
        return modelAndView;
    }
}