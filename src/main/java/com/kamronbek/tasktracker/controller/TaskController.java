package com.kamronbek.tasktracker.controller;

import com.kamronbek.tasktracker.dto.TaskRequest;
import com.kamronbek.tasktracker.model.Task;
import com.kamronbek.tasktracker.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Iterable<Task> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequest taskRequest, @PathVariable Long id) {
        return taskService.updateTask(taskRequest, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

}
