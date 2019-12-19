package com.ttn.todo.repo;

import com.ttn.todo.entity.Task;
import com.ttn.todo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
    List<Task> findByUser(User user);
}
