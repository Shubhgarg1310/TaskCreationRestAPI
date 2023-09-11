package com.restapiconcepts.TableCreationAndRestCall.requests;

import com.restapiconcepts.TableCreationAndRestCall.entities.TaskStatusEnum;

import java.util.Date;

public record UpdateTaskInput(TaskStatusEnum status, Date dueDate)  {

}
