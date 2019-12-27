package com.ttn.todo.service;

import com.ttn.todo.entity.Task;
import com.ttn.todo.entity.User;
import com.ttn.todo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    public List<Task> getTasks(User user) {
        return taskRepo.findByUser(user);
    }
    public Task saveTask(Task task) {
        return taskRepo.save(task);
    }

    public Task findById(int taskId) {
        return taskRepo.findById(taskId).orElse(null);
    }

    public void deleteTask(Task task) {
        taskRepo.delete(task);
    }
}
