package com.restapiconcepts.TableCreationAndRestCall.controllers;

import com.restapiconcepts.TableCreationAndRestCall.entities.Task;
import com.restapiconcepts.TableCreationAndRestCall.repository.TaskRepository;
import com.restapiconcepts.TableCreationAndRestCall.requests.CreateTaskInput;
import com.restapiconcepts.TableCreationAndRestCall.requests.UpdateTaskInput;
import com.restapiconcepts.TableCreationAndRestCall.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    public TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput) {
        Task taskCreated = taskService.create(createTaskInput.toTask());

        return new ResponseEntity<>(taskCreated, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> returnTask(@PathVariable int id) {
        Optional<Task> optionalTask = taskService.findById(id);
        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> allTasksInDb(){
        List<Task> tasks = taskService.findAll();

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestParam UpdateTaskInput updateTaskInput) {
        Optional<Task> optionalTask = taskService.findById(id);

        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Task taskToUpdate = optionalTask.get();

        taskToUpdate.setStatus(updateTaskInput.status());
        taskToUpdate.setDuedate(updateTaskInput.dueDate());

        Task taskUpdated = taskService.update(taskToUpdate);
        return new ResponseEntity<>(taskUpdated,HttpStatus.OK);
    }


}
