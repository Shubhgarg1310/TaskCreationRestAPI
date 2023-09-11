package com.restapiconcepts.TableCreationAndRestCall.services;

import com.restapiconcepts.TableCreationAndRestCall.entities.Task;
import com.restapiconcepts.TableCreationAndRestCall.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    public Task create(Task task){
        List<Task> tasks = new ArrayList<>();
        return taskRepository.save(task);
    }

    public Optional<Task> findById(int id){
        return taskRepository.findById(id);
    }

    public List<Task> findAll(){
        List<Task> allTasks = new ArrayList<>();
        taskRepository.findAll().forEach(allTasks::add);
        return allTasks;
    }

    public void deleteById(int id){
         taskRepository.deleteById(id);
    }

    public Task update(Task taskToUpdate){
        return taskRepository.save(taskToUpdate);
    }

}
