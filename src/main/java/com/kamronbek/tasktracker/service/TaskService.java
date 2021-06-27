package com.kamronbek.tasktracker.service;

import com.kamronbek.tasktracker.dto.TaskRequest;
import com.kamronbek.tasktracker.model.Task;
import com.kamronbek.tasktracker.model.User;
import com.kamronbek.tasktracker.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Iterable<Task> getTasks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return taskRepository.findByUser(user);
    }

    public Task createTask(TaskRequest taskRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Task task = new Task(
                null,
                taskRequest.getTitle(),
                taskRequest.getDate(),
                taskRequest.getTime(),
                taskRequest.getImportant(),
                user
        );

        return taskRepository.save(task);
    }

    public ResponseEntity<?> updateTask(TaskRequest taskRequest, Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        Task task = optionalTask.get();
        if (!task.getUser().equals(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        Task updatedTask = new Task(
                id,
                taskRequest.getTitle(),
                taskRequest.getDate(),
                taskRequest.getTime(),
                taskRequest.getImportant(),
                user
        );
        taskRepository.save(updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    public ResponseEntity<?> deleteTask(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        Task task = optionalTask.get();
        if (!task.getUser().equals(user))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

        taskRepository.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
