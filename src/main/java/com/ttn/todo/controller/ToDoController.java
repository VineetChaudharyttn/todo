package com.ttn.todo.controller;

import com.ttn.todo.entity.Task;
import com.ttn.todo.entity.User;
import com.ttn.todo.service.RoleService;
import com.ttn.todo.service.TaskService;
import com.ttn.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
public class ToDoController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    TaskService taskService;


    @RequestMapping("/")
    String hello(Model model, Principal principal){
        model.addAttribute("user",new User());
        return principal == null ?  "login" : "redirect:/home";
    }

    @RequestMapping("/register")
    String register(User user, Model model){
        user.setRole(Arrays.asList(roleService.findRole("USER")));
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userService.save(user);
        model.addAttribute("user",new User());
        return "login";
    }


    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/home")
    String home(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        List<Task> tasks = taskService.getTasks(user);
        model.addAttribute("tasks",tasks);
        model.addAttribute("task", new Task());
        return "login";
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/addTask")
    Task addTask(Task task, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        task.setUser(user);
        task = taskService.saveTask(task);
        return task.getId() != 0 ? task: new Task();
    }

    @ResponseBody
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/updateStatus")
    Task updateStatus(int taskId, boolean status) {
        Task task = taskService.findById(taskId);
        task.setStatus(status);
        task = taskService.saveTask(task);
        return task.getId() != 0 ? task: new Task();
    }


    @ResponseBody
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/deleteTask")
    void deleteTask(int taskId) {
        taskService.deleteTask(taskId);
    }
    
}
