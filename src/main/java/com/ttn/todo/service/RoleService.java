package com.ttn.todo.service;

import com.ttn.todo.entity.Role;
import com.ttn.todo.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public Role findRole(String role) {
        return roleRepo.findByRole(role);
    }
}
