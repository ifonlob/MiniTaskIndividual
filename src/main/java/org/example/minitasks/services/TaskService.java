package org.example.minitasks.services;
import org.example.minitasks.dto.CreateTaskDTO;
import org.example.minitasks.entities.Task;
import org.example.minitasks.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service @Transactional
public class TaskService {
    private final TaskRepository repo;
    public TaskService(TaskRepository repo){this.repo=repo;}

    public List<Task> list(Boolean completed){ return (completed==null)? repo.findAll(): repo.findByCompleted(completed); }

    public Task create(CreateTaskDTO dto){ Task t=new Task(dto.title().strip());
        return repo.save(t);
    }

    public Task toggle(Long id){ Task t=repo.findById(id).orElseThrow(()->new IllegalArgumentException("Task not found")); t.setCompleted(!t.isCompleted()); return t; }

    public void delete(Long id){ if(!repo.existsById(id)) throw new IllegalArgumentException("Task not found"); repo.deleteById(id); }
}