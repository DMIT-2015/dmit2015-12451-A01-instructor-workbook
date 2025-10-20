package dmit2015.service;

import dmit2015.model.Task;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task createTask(@Valid Task task);

    Optional<Task> getTaskById(String id);

    List<Task> getAllTasks();

    Task updateTask(@Valid Task task);

    void deleteTaskById(String id);

    void deleteAllTasks();
}