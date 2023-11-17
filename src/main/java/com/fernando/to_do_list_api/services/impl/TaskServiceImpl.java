package com.fernando.to_do_list_api.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fernando.to_do_list_api.dtos.TaskRequest;
import com.fernando.to_do_list_api.dtos.UpdateTaskDTO;
import com.fernando.to_do_list_api.mappers.TaskMapper;
import com.fernando.to_do_list_api.models.Task;
import com.fernando.to_do_list_api.repositories.TaskRepository;
import com.fernando.to_do_list_api.services.interfaces.TaskService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(TaskRequest taskRequest) {
        Task task = taskMapper.toModel(taskRequest);
        return taskRepository.save(task);
    }

    @Override
    public Task findTaskByID(Long taskID) {
        return taskRepository.findById(taskID)
        .orElseThrow(
            () -> new EntityNotFoundException("Task is not exists"));
    }

    @Override
    public Task updateTask(Long taskID, UpdateTaskDTO updateTaskDTO) {
        Task task = findTaskByID(taskID);
        task.setName(updateTaskDTO.name());
        task.setDescription(updateTaskDTO.description());
        task.setFinish(updateTaskDTO.finish());
        task.setUpdatedAt(LocalDate.now());

        return taskRepository.save(task);
    }

    @Override
    public Boolean deleteTask(Long taskID) {
        taskRepository.deleteById(taskID);
        Optional<Task> task = taskRepository.findById(taskID);
        return task.isPresent();
    }

    @Override
    public Task setFinishTask(Long taskID, Boolean finish) {
        return taskRepository.setFinish(finish, taskID);
    }

    @Override
    public List<Task> findFinishTasks() {
       return taskRepository.findFinishTasks();
    }

    @Override
    public List<Task> findNotFinishTasks() {
        return taskRepository.findNotFinishTasks();
    }
    
}