package org.example.minitasks.controllers;

import org.example.minitasks.dto.CreateTaskDTO;
import org.example.minitasks.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/tasks")
public class TaskControllerV1 {

    private final List<Task> tasks = new ArrayList<Task>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody CreateTaskDTO dto) {
        Task task = Task.builder().id(idGenerator.getAndIncrement()).title(dto.title()).build();
        tasks.add(task);
        return ResponseEntity.created(URI.create("/v1/tasks/" + task.getId())).body(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(t -> ResponseEntity.ok(t))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
