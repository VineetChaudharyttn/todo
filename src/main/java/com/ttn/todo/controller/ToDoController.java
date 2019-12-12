package com.ttn.todo.controller;

import com.ttn.todo.entity.User;
import com.ttn.todo.service.RoleService;
import com.ttn.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;

@Controller
public class ToDoController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @RequestMapping("/")
    String hello(Model model){
        model.addAttribute("attr","this is attribute");
        model.addAttribute("user",new User());
        model.addAttribute("isLoggedIn",false);
        return "login";
    }

    @RequestMapping("/login")
    String login(Model model){
        model.addAttribute("attr","this is login page");
        model.addAttribute("user",new User());
        model.addAttribute("isLoggedIn",false);
        return "login";
    }

    @RequestMapping("/register")
    String register(User user, Model model){
        user.setRole(Arrays.asList(roleService.findRole("USER")));
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userService.save(user);
        model.addAttribute("attr","this is login page");
        model.addAttribute("user",new User());
        model.addAttribute("isLoggedIn",false);
        return "login";
    }


    @PreAuthorize("hasAuthority('ROLE_SUPPORT')")
    @RequestMapping("/home")
    String home(Model model, Principal principal){
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("attr","this is home page");
        model.addAttribute("isLoggedIn",true);
        return "login";
    }
}
