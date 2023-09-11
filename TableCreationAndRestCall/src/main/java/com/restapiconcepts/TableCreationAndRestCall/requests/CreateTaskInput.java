package com.restapiconcepts.TableCreationAndRestCall.requests;

import com.restapiconcepts.TableCreationAndRestCall.entities.Task;
import com.restapiconcepts.TableCreationAndRestCall.entities.TaskStatusEnum;

import java.util.Date;

public record CreateTaskInput(String name, String description, TaskStatusEnum status, Date dueDate) {

    public Task toTask(){
        Task task = new Task();

        task.setName(name);
        task.setDescription(description);
        task.setStatus(status);
        task.setDuedate(dueDate);

        return task;
    }

}
