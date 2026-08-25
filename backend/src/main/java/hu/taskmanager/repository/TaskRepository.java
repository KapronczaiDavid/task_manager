package hu.taskmanager.repository;

import hu.taskmanager.model.Task;
import hu.taskmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusOrderByCreatedAtDesc(TaskStatus status);
    List<Task> findAllByOrderByCreatedAtDesc();
}
