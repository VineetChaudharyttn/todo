package com.ttn.todo.service;

import com.ttn.todo.entity.User;
import com.ttn.todo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public User findByUsername(String username) {
        return userRepo.findByUsername(username).get();
    }

    public void save(User user) {
        userRepo.save(user);
    }
}
