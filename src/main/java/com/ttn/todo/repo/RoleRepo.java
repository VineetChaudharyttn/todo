package com.ttn.todo.repo;

import com.ttn.todo.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
