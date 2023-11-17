package com.fernando.to_do_list_api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fernando.to_do_list_api.dtos.TaskRequest;
import com.fernando.to_do_list_api.dtos.UpdateTaskDTO;
import com.fernando.to_do_list_api.models.Task;
import com.fernando.to_do_list_api.services.interfaces.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @CacheEvict(value = "tasks", allEntries = true)
    @CachePut("tasks")
    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks(@RequestParam(required = false) Boolean finish) {
        List<Task> tasks;
        
        if (finish != null) {
            if (finish == true) {
                tasks = taskService.findFinishTasks();
            }
            else {
                tasks = taskService.findNotFinishTasks();
            }   
        }
        else {
            tasks = taskService.findAllTasks();
        }
        return ResponseEntity.ok().body(tasks);
    }


    @GetMapping("/{taskID}")
    public ResponseEntity<Task> findTaskByID(@PathVariable Long taskID) {
        return ResponseEntity.ok().body(taskService.findTaskByID(taskID));
    }


    
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok().body(taskService.createTask(taskRequest));
    }

    
    @PutMapping("/{taskID}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskID, @RequestBody UpdateTaskDTO updateTaskDTO) {
        return ResponseEntity.ok().body(taskService.updateTask(taskID, updateTaskDTO));
    }

    
    @DeleteMapping("/{taskID}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskID) {
        taskService.deleteTask(taskID);
        return ResponseEntity.noContent().build();
    }

}
