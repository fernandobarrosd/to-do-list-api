package com.fernando.to_do_list_api.services.interfaces;

import java.util.List;

import com.fernando.to_do_list_api.dtos.TaskRequest;
import com.fernando.to_do_list_api.dtos.UpdateTaskDTO;
import com.fernando.to_do_list_api.models.Task;

public interface TaskService {
    // Task use cases
    List<Task> findAllTasks();
    Task createTask(TaskRequest taskRequest);
    Task findTaskByID(Long taskID);
    Task updateTask(Long taskID, UpdateTaskDTO updateTaskDTO);
    Boolean deleteTask(Long taskID);
    Task setFinishTask(Long taskID, Boolean finish);
    List<Task> findFinishTasks();
    List<Task> findNotFinishTasks();
}