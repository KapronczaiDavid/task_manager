package hu.taskmanager.service;

import hu.taskmanager.exception.TaskNotFoundException;
import hu.taskmanager.model.Task;
import hu.taskmanager.model.TaskStatus;
import hu.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(TaskStatus status) {
        if (status == null) {
            return taskRepository.findAllByOrderByCreatedAtDesc();
        }
        return taskRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        task.setId(null);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task incomingTask) {
        Task existingTask = getTaskById(id);
        existingTask.setTitle(incomingTask.getTitle());
        existingTask.setDescription(incomingTask.getDescription());
        existingTask.setStatus(incomingTask.getStatus());
        existingTask.setDeadline(incomingTask.getDeadline());
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        Task existingTask = getTaskById(id);
        taskRepository.delete(existingTask);
    }
}
