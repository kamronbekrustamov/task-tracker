package com.kamronbek.tasktracker.repository;

import com.kamronbek.tasktracker.model.Task;
import com.kamronbek.tasktracker.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findByUser(User user);
}
