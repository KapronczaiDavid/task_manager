package hu.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Nem található feladat ezzel az azonosítóval: " + id);
    }
}
